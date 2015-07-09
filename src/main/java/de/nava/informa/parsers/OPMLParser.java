//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.parsers;

import de.nava.informa.core.FeedIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.core.UnsupportedFormatException;
import de.nava.informa.utils.NoOpEntityResolver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.Collection;

/**
 * OPML (Outline processor markup language) parser for to read in a collection
 * of news channels (feeds) that will be made available as news channel object
 * model.
 *
 * <p>Currently OPML version 1.1 is supported.</p>
 *
 * @author Niko Schmuck
 * @author Benjamin Wiedmann
 * @see de.nava.informa.core.FeedIF
 */
public class OPMLParser {

    private static Log logger = LogFactory.getLog(OPMLParser.class);

    private OPMLParser() {
    }

    public static Collection parse(URL aURL) throws IOException, ParseException {
        return parse(new InputSource(aURL.toExternalForm()), aURL);
    }

    /**
     * Reads in a news feed definition from the specified URL.
     *
     * @return A collection of <code>FeedIF</code> objects.
     */
    public static Collection parse(String url) throws IOException, ParseException {
        URL aURL = null;
        try {
            aURL = new URL(url);
        } catch (java.net.MalformedURLException e) {
            logger.warn("Could not create URL for " + url);
        }
        return parse(new InputSource(url), aURL);
    }

    public static Collection parse(Reader reader) throws IOException, ParseException {
        return parse(new InputSource(reader), null);
    }

    public static Collection parse(InputStream stream) throws IOException, ParseException {
        return parse(new InputSource(stream), null);
    }

    public static Collection<FeedIF> parse(File aFile) throws IOException, ParseException {
        URL aURL;
        try {
            aURL = aFile.toURI().toURL();
        } catch (java.net.MalformedURLException e) {
            throw new IOException("File " + aFile + " had invalid URL " +
                    "representation.");
        }
        return parse(new InputSource(aURL.toExternalForm()), aURL);
    }

    public static Collection<FeedIF> parse(InputSource inpSource,
                                           URL baseLocation) throws IOException, ParseException {
        // document reading without validation
        SAXBuilder saxBuilder = new SAXBuilder(false);
        // turn off DTD loading
        saxBuilder.setEntityResolver(new NoOpEntityResolver());
        try {
            Document doc = saxBuilder.build(inpSource);
            return parse(doc);
        } catch (JDOMException e) {
            throw new ParseException(e);
        }
    }

    // ------------------------------------------------------------
    // internal helper methods
    // ------------------------------------------------------------

    private static synchronized Collection<FeedIF> parse(Document doc) throws ParseException {

        logger.debug("start parsing.");
        // Get the root element (must be opml)
        Element root = doc.getRootElement();
        String rootElement = root.getName().toLowerCase();
        String opmlVersion;

        // Decide which parser to use
        if (rootElement.startsWith("opml")) {
            Attribute attrOpmlVersion = root.getAttribute("version");

            // there is no version information set
            if (attrOpmlVersion == null) {
                // there seems to be no opml version set, so we'll try to parse it with 1.1..
                // TODO is it worth to implement also a opml 1.0 parser? are there markable differences between opml 1.1 and 1.0?
                logger.info("Collection uses OPML root element (no version information available), trying to parse with 1.1 anyway.");
                return OPML_1_1_Parser.parse(root);
            } else {
                // version information seems to be set, so go get it
                opmlVersion = attrOpmlVersion.getValue();
            }

            // version information is set
            if (opmlVersion.contains("1.1")) {
                // OPML 1.1 version information is set, so we'll parse it with 1.1!
                logger.info("Collection uses OPML root element (Version 1.1).");
                return OPML_1_1_Parser.parse(root);
            } else if (opmlVersion.contains("1.0")) {
                // TODO is it worth to implement also a opml 1.0 parser? are there markable differences between opml 1.1 and 1.0?
                // OPML 1.0 version information is set, we'll try to parse it using 1.1 anyway..
                logger.info("Collection uses OPML root element (Version 1.0), trying to parse with 1.1 anyway.");
                return OPML_1_1_Parser.parse(root);
            } else {
                // since it is neither a 1.0 nor 1.1 opml feed we maybe cannot handle it, so it's better to throw some exception..
                throw new UnsupportedFormatException(MessageFormat.format("Unsupported OPML version information [{0}].", opmlVersion));
            }
        }

        // did not match anything
        throw new UnsupportedFormatException(MessageFormat.format("Unsupported OPML root element [{0}].", rootElement));
    }

}
