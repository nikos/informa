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
import de.nava.informa.impl.basic.Feed;
import de.nava.informa.utils.ParserUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.*;

/**
 * Parser which reads in document according to the OPML 1.1 specification
 * and generates a collection of <code>FeedIF</code> instances.
 *
 * @author Niko Schmuck
 */
class OPML_1_1_Parser {

    private static Log logger = LogFactory.getLog(OPML_1_1_Parser.class);

    static Collection<FeedIF> parse(Element root) {

        Collection<FeedIF> feedColl = new ArrayList<>();

        Date dateParsed = new Date();
        logger.debug("start parsing.");

        // Lower the case of these tags to simulate case-insensitive parsing
        ParserUtils.matchCaseOfChildren(root, "body");

        // Get the head element (only one should occur)
//    Element headElem = root.getChild("head");
//    String title = headElem.getChildTextTrim("title");

        // Get the body element (only one occurs)
        Element bodyElem = root.getChild("body");

        // 1..n outline elements
        ParserUtils.matchCaseOfChildren(bodyElem, "outline");
        List feeds = bodyElem.getChildren("outline");
        for (Object feed1 : feeds) {
            Element feedElem = (Element) feed1;
            // get title attribute
            Attribute attrTitle = feedElem.getAttribute("title");
            String strTitle = "[No Title]";
            if (attrTitle != null) {
                strTitle = attrTitle.getValue();
            }
            FeedIF feed = new Feed(strTitle);
            if (logger.isDebugEnabled()) {
                logger.debug("Feed element found (" + strTitle + ").");
            }
            // get text attribute
            Attribute attrText = feedElem.getAttribute("text");
            String strText = "[No Text]";
            if (attrText != null) {
                strText = attrText.getValue();
            }
            feed.setText(strText);
            // get attribute type (for example: 'rss')
            Attribute attrType = feedElem.getAttribute("type");
            String strType = "text/xml";
            if (attrType != null) {
                strType = attrType.getValue();
            }
            feed.setContentType(strType);

            // TODO: handle attribute version (for example: 'RSS')

            // get attribute xmlUrl
            Attribute attrXmlUrl = feedElem.getAttribute("xmlUrl");
            if (attrXmlUrl != null && attrXmlUrl.getValue() != null) {
                feed.setLocation(ParserUtils.getURL(attrXmlUrl.getValue()));
            }
            // get attribute htmllUrl
            Attribute attrHtmlUrl = feedElem.getAttribute("htmlUrl");
            if (attrHtmlUrl != null && attrHtmlUrl.getValue() != null) {
                feed.setSite(ParserUtils.getURL(attrHtmlUrl.getValue()));
            }
            // set current date
            feed.setDateFound(dateParsed);
            // add feed to collection
            feedColl.add(feed);
        }

        return feedColl;
    }

}
