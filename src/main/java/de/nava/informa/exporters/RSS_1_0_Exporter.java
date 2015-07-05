//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.exporters;

import de.nava.informa.core.ChannelExporterIF;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.utils.ParserUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Iterator;

/**
 * A channel exporter that can write channel objects out into the
 * interchange syntax defined by RSS 1.0.
 */
public class RSS_1_0_Exporter implements ChannelExporterIF {

    public static final Namespace NS_DEFAULT =
            Namespace.getNamespace("http://purl.org/rss/1.0/");
    public static final Namespace NS_RDF =
            Namespace.getNamespace("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
    /**
     * RSS 1.0 Dublin Core namespace
     */
    public static final Namespace NS_DC =
            Namespace.getNamespace("dc", "http://purl.org/dc/elements/1.1/");
    /**
     * RSS 1.0 Syndication Module namespace
     */
    public static final Namespace NS_SY =
            Namespace.getNamespace("sy", "http://purl.org/rss/1.0/modules/syndication/");

    private static SimpleDateFormat df =
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");


    private Writer writer;
    private String encoding;

    /**
     * Creates a channel exporter bound to the file given in the
     * argument. The channel will be written out in the UTF-8 encoding.
     *
     * @param filename - The name of the file to which the channel object
     *                 is to be written.
     */
    public RSS_1_0_Exporter(String filename) throws IOException {
        this(new File(filename), "utf-8");
    }

    /**
     * Creates a channel exporter bound to the file given in the
     * argument. The channel will be written out in the UTF-8 encoding.
     *
     * @param file - The file object to which the channel object is
     *             to be written.
     */
    public RSS_1_0_Exporter(File file) throws IOException {
        this(file, "utf-8");
    }

    /**
     * Creates a channel exporter bound to the file given in the
     * arguments.
     *
     * @param file     - The file object to which the channel object is
     *                 to be written.
     * @param encoding - The character encoding to write the channel
     *                 object in.
     */
    public RSS_1_0_Exporter(File file, String encoding) throws IOException {
        this.writer = new OutputStreamWriter(new FileOutputStream(file), encoding);
        this.encoding = encoding;
    }

    /**
     * Creates a channel exporter bound to the Writer given in the
     * arguments.
     *
     * @param writer   - The Writer to which the channel object is to be
     *                 written.
     * @param encoding - The character encoding the Writer writes in.
     */
    public RSS_1_0_Exporter(Writer writer, String encoding) {
        this.writer = writer;
        this.encoding = encoding;
    }

    // ------------------------------------------------------------
    // implementation of ChannelExporterIF interface
    // ------------------------------------------------------------

    public void write(ChannelIF channel) throws IOException {
        if (writer == null) {
            throw new RuntimeException("No writer has been initialized.");
        }

        Element rootElem = new Element("RDF", NS_RDF);
        rootElem.addNamespaceDeclaration(NS_DEFAULT);  // TODO
        rootElem.addNamespaceDeclaration(NS_DC);
        rootElem.addNamespaceDeclaration(NS_SY);
        // rootElem.setAttribute("version");
        Element channelElem = new Element("channel", NS_DEFAULT);
        if (channel.getLocation() != null) {
            channelElem.setAttribute("about",
                    channel.getLocation().toString(), NS_RDF);
        }
        channelElem.addContent(new Element("title", NS_DEFAULT)
                .setText(channel.getTitle()));
        if (channel.getSite() != null) {
            channelElem.addContent(new Element("link", NS_DEFAULT)
                    .setText(channel.getSite().toString()));
            channelElem.addContent(new Element("source", NS_DC)
                    .setAttribute("resource",
                            channel.getSite().toString()));
        }

        channelElem.addContent(new Element("description", NS_DEFAULT)
                .setText(channel.getDescription()));
        if (channel.getLanguage() != null) {
            channelElem.addContent(new Element("language", NS_DC)
                    .setText(channel.getLanguage()));
        }
        if (channel.getCopyright() != null) {
            channelElem.addContent(new Element("copyright", NS_DC)
                    .setText(channel.getCopyright()));
        }
        if (channel.getUpdateBase() != null) {
            channelElem.addContent(new Element("updateBase", NS_SY)
                    .setText(df.format(channel.getUpdateBase())));
        }
        if (channel.getUpdatePeriod() != null) {
            // don't put out frequency without specifying period
            channelElem.addContent(new Element("updateFrequency", NS_SY)
                    .setText((new Integer(channel.getUpdateFrequency())).toString()));
            channelElem.addContent(new Element("updatePeriod", NS_SY)
                    .setText(channel.getUpdatePeriod().toString()));
        }
        // export channel image
        if (channel.getImage() != null) {
            Element imgElem = new Element("image", NS_DEFAULT);
            imgElem.addContent(new Element("title", NS_DEFAULT)
                    .setText(channel.getImage().getTitle()));
            imgElem.addContent(new Element("url", NS_DEFAULT)
                    .setText(channel.getImage().getLocation().toString()));
            imgElem.addContent(new Element("link", NS_DEFAULT)
                    .setText(channel.getImage().getLink().toString()));
            imgElem.addContent(new Element("height", NS_DEFAULT)
                    .setText("" + channel.getImage().getHeight()));
            imgElem.addContent(new Element("width", NS_DEFAULT)
                    .setText("" + channel.getImage().getWidth()));
            imgElem.addContent(new Element("description", NS_DEFAULT)
                    .setText(channel.getImage().getDescription()));
            channelElem.addContent(imgElem);
        }

        // TODO: add exporting textinput field
        //     if (channel.getTextInput() != null) {
        //       channelElem.addContent(channel.getTextInput().getElement());
        //     }

        // ===========================================
        Element itemsElem = new Element("items", NS_DEFAULT);
        Element seqElem = new Element("Seq", NS_RDF);
        Collection items = channel.getItems();
        Iterator it = items.iterator();
        while (it.hasNext()) {
            ItemIF item = (ItemIF) it.next();
            Element itemElem = new Element("li", NS_RDF);
            if (item.getLink() != null) {
                itemElem.setAttribute("resource", item.getLink().toString());
            }
            seqElem.addContent(itemElem);
        }
        itemsElem.addContent(seqElem);
        channelElem.addContent(itemsElem);
        rootElem.addContent(channelElem);

        // item-by-item en detail
        items = channel.getItems();
        it = items.iterator();
        while (it.hasNext()) {
            rootElem.addContent(getItemElement((ItemIF) it.next()));
        }

        // create XML outputter with indent: 2 spaces, print new lines.
        Format format = Format.getPrettyFormat();
        format.setEncoding(encoding);
        XMLOutputter outputter = new XMLOutputter(format);
        // write DOM to file
        Document doc = new Document(rootElem);
        outputter.output(doc, writer);
        writer.close();
    }


    protected Element getItemElement(ItemIF item) {
        Element itemElem = new Element("item", NS_DEFAULT);
        if (item.getLink() != null) {
            itemElem.setAttribute("about",
                    item.getLink().toString(), NS_RDF);
        }
        itemElem.addContent(new Element("title", NS_DEFAULT).setText(item.getTitle()));
        if (item.getLink() != null) {
            itemElem.addContent(new Element("link", NS_DEFAULT)
                    .setText(item.getLink().toString()));
        }
        if (item.getDescription() != null) {
            itemElem.addContent(new Element("description", NS_DC)
                    .setText(item.getDescription()));
        }
        if (item.getDate() != null) {
            itemElem.addContent(new Element("date", NS_DC)
                    .setText(ParserUtils.formatDate(item.getDate())));
        }
        return itemElem;
    }

}
