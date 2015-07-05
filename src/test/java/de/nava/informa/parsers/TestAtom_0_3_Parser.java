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

import de.nava.informa.core.ChannelFormat;
import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.core.ParseException;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.impl.basic.Item;
import de.nava.informa.utils.AtomParserUtils;
import de.nava.informa.utils.InformaTestCase;
import org.jdom2.CDATA;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jdom2.Text;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class TestAtom_0_3_Parser extends InformaTestCase {

    public TestAtom_0_3_Parser(String name) {

        super("TestAtom_0_3_Parser", name);
        this.method_name = name;
    }

    public void testParseDiveIntoMark() throws Exception {
        File inpFile = new File(getDataDir(), "diveintomark.xml");
        ChannelIF channel_mark = FeedParser.parse(new ChannelBuilder(), inpFile);
        assertEquals("dive into mark", channel_mark.getTitle());
        assertEquals(3, channel_mark.getItems().size());

        // test generator
        assertEquals("Movable Type", channel_mark.getGenerator());

        //test publisher
        assertEquals("Mark Pilgrim", channel_mark.getCreator());

        //test Last pub date
        // should be  <modified>2004-05-13T17:16:00Z</modified>
        Calendar updtDate = new GregorianCalendar(TimeZone.getTimeZone("GMT"));

        updtDate.set(Calendar.YEAR, 2004);
        updtDate.set(Calendar.MONTH, Calendar.MAY);
        updtDate.set(Calendar.DAY_OF_MONTH, 13);
        updtDate.set(Calendar.HOUR_OF_DAY, 17);
        updtDate.set(Calendar.MINUTE, 16);
        updtDate.set(Calendar.SECOND, 0);
        updtDate.set(Calendar.MILLISECOND, 0);
        assertEquals(updtDate.getTime(), channel_mark.getPubDate());

        //test build date

        // test site url
        assertNotNull(channel_mark.getSite());
        assertEquals("http://diveintomark.org/", channel_mark.getSite().toExternalForm());

        // test link for first item
        java.util.Iterator itemsColl = channel_mark.getItems().iterator();
        Item item = (Item) itemsColl.next();
        assertEquals("http://diveintomark.org/archives/2004/05/12/copy-editor".trim(), item.getLink().toExternalForm());

        assertEquals(ChannelFormat.ATOM_0_3, channel_mark.getFormat());
    }

    public void testParseBloggerLinuxHelp() throws Exception {
        File inpFile = new File(getDataDir(), "atom-0.3-linuxhelp.xml");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        assertEquals(10, channel.getItems().size());
        assertEquals(ChannelFormat.ATOM_0_3, channel.getFormat());

        // check each item that for null values
        for (Object o : channel.getItems()) {
            Item item = (Item) o;
            assertNotNull(item.getDate());
            assertNotNull(item.getDescription());
            assertNotNull(item.getLink());
            assertNotNull(item.getTitle());
        }
    }

    public void testEntryTitleFormat() throws Exception {
        File inpFile = new File(getDataDir(), "blink.xml");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);
        java.util.Iterator itemsColl = channel.getItems().iterator();
        // skip first entry
        itemsColl.next();
        while (itemsColl.hasNext()) {
            Item item = (Item) itemsColl.next();
            //System.out.println("TC title :*"+item.getTitle()+"*"+item.getTitle().length());
            assertEquals("History of the <blink> tag", item.getTitle());
        }
    }

    public void testLanguage() throws Exception {
        File inpFile = new File(getDataDir(), "diveintomark.xml");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        assertEquals("en", channel.getLanguage());

    }

    public void testCopyright() throws Exception {
        File inpFile = new File(getDataDir(), "multilink-linkblog.xml");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        //  test copyright text/plain
        assertEquals("Copyright (c) 2004 Mark Pilgrim", channel.getCopyright());

        inpFile = new File(getDataDir(), "diveintomark.xml");
        channel = FeedParser.parse(new ChannelBuilder(), inpFile);
        assertEquals("Copyright \u00a9 2004, Mark Pilgrim", channel.getCopyright());

    }

    /**
     * Tests parsing of feed with no version specified (0.3 is assumed).
     */
    public void testNoVersion() throws Exception {
        File inpFile = new File(getDataDir(), "ongoing.atom");
        FeedParser.parse(new ChannelBuilder(), inpFile);
    }

    public void testSummaryOnly() throws Exception {
        File inpFile = new File(getDataDir(), "atom-summary-only.xml");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        ItemIF[] items = channel.getItems().toArray(new ItemIF[channel.getItems().size()]);
        assertEquals(3, items.length);

        assertEquals("Google <b>Blog</b>", items[1].getDescription());
    }

    public void testXmlContent() throws Exception {
        Element elt = new Element("summary");
        elt.addContent(new Element("b").addContent("b"));
        elt.setAttribute("mode", "xml");
        assertEquals("<b>b</b>", Atom_0_3_Parser.getValue(elt));
    }

    public void testEscapedContent() throws Exception {
        Element elt = new Element("summary");
        elt.addContent("<b>b</b>");
        elt.setAttribute("mode", "escaped");
        assertEquals("<b>b</b>", Atom_0_3_Parser.getValue(elt));
    }

    public void testBase64Content() throws Exception {
        Element elt = new Element("summary");
        elt.addContent(Base64.getEncoder().encodeToString("<b>b</b>".getBytes()));
        elt.setAttribute("mode", "base64");
        assertEquals("<b>b</b>", Atom_0_3_Parser.getValue(elt));
    }

    public void testDescriptionSelectionSummaryOnly() {
        Element entry = new Element("entry");

        Element summary = new Element("summary");
        summary.addContent("summary");

        entry.addContent(summary);

        assertEquals("summary", Atom_0_3_Parser.getDescription(entry, null));
    }

    public void testDescriptionSelectionSummaryAndContent() {
        Element entry = new Element("entry");

        Element summary = new Element("summary");
        summary.addContent("summary");

        Element content1 = new Element("content");
        content1.addContent("content1");

        Element content2 = new Element("content");
        content2.addContent("content2");

        entry.addContent(summary);
        entry.addContent(content1);
        entry.addContent(content2);

        assertEquals("content1", Atom_0_3_Parser.getDescription(entry, null));
    }

    public void testDescriptionSelectionContentOnly() {
        Element entry = new Element("entry");

        Element content1 = new Element("content");
        content1.addContent("content1");

        Element content2 = new Element("content");
        content2.addContent("content2");

        entry.addContent(content1);
        entry.addContent(content2);

        assertEquals("content1", Atom_0_3_Parser.getDescription(entry, null));
    }

    public void testMultipleLinksForItem() {
        Element item;

        item = new Element("entry");
        item.addContent(createLinkElement("text/html", "alternative", "test1"));
        item.addContent(createLinkElement("text/html", "alternative", "test2"));
        item.addContent(createLinkElement("application/xml", "alternative", "test3"));

        assertEquals("test1", AtomParserUtils.getItemLink(item, null));

        item = new Element("entry");
        item.addContent(createLinkElement("text/html", "alternative", "test1"));
        item.addContent(createLinkElement("text/plain", "alternative", "test2"));
        item.addContent(createLinkElement("application/xml", "alternative", "test3"));

        assertEquals("test1", AtomParserUtils.getItemLink(item, null));

        item = new Element("entry");
        item.addContent(createLinkElement("text/plain", "alternative", "test2"));
        item.addContent(createLinkElement("text/html", "alternative", "test1"));
        item.addContent(createLinkElement("application/xml", "alternative", "test3"));

        assertEquals("test1", AtomParserUtils.getItemLink(item, null));

        item = new Element("entry");
        item.addContent(createLinkElement("application/xml", "alternative", "test3"));
        item.addContent(createLinkElement("text/plain", "alternative", "test2"));

        assertEquals("test2", AtomParserUtils.getItemLink(item, null));

        item = new Element("entry");
        item.addContent(createLinkElement("application/xml", "alternative", "test3"));

        assertEquals("test3", AtomParserUtils.getItemLink(item, null));
    }

    private Element createLinkElement(String type, String rel, String href) {
        Element link = new Element("link");
        link.setAttribute("type", type);
        link.setAttribute("rel", rel);
        link.setAttribute("href", href);
        return link;
    }

    /**
     * Tests parsing of titles and descriptions with CDATA objects inside.
     */
    public void testCDATAInTitlesAndDescriptions() throws IOException, ParseException {
        File inpFile = new File(getDataDir(), "atomic-cdata.xml");
        ChannelIF feed = FeedParser.parse(new ChannelBuilder(), inpFile);

        Collection items = feed.getItems();
        assertEquals(1, items.size());

        Item item = (Item) items.iterator().next();
        assertEquals("Tilden <b>Hike</b>", item.getTitle());
        assertEquals("A hike in Tilden Park...", item.getDescription());
    }

    public void testTrimContents() {
        Content whitespace = new Text(" \n\t\t");
        Content cdata = new CDATA("Test");
        List<Content> content;
        List newList;

        // Empty content list
        content = new ArrayList<>();
        assertEquals(0, AtomParserUtils.trimContents(content).size());

        // Whitespace
        content = new ArrayList<>(1);
        content.add(whitespace);
        assertEquals(0, AtomParserUtils.trimContents(content).size());

        // Two whitespaces
        content = new ArrayList<>(2);
        content.add(whitespace);
        content.add(whitespace);
        assertEquals(0, AtomParserUtils.trimContents(content).size());

        // CDATA alone
        content = new ArrayList<>(1);
        content.add(cdata);
        newList = AtomParserUtils.trimContents(content);
        assertEquals(1, newList.size());
        assertTrue(newList.get(0) == cdata);

        // Whitespace + CDATA
        content = new ArrayList<>(2);
        content.add(whitespace);
        content.add(cdata);
        newList = AtomParserUtils.trimContents(content);
        assertEquals(1, newList.size());
        assertTrue(newList.get(0) == cdata);

        // CDATA + whitespace
        content = new ArrayList<>(2);
        content.add(cdata);
        content.add(whitespace);
        newList = AtomParserUtils.trimContents(content);
        assertEquals(1, newList.size());
        assertTrue(newList.get(0) == cdata);

        // CDATA surrounded with whitespaces
        content = new ArrayList<>(2);
        content.add(whitespace);
        content.add(cdata);
        content.add(whitespace);
        newList = AtomParserUtils.trimContents(content);
        assertEquals(1, newList.size());
        assertTrue(newList.get(0) == cdata);

        // Two CDATA's surrounded with couples of whitespaces
        content = new ArrayList<>(2);
        content.add(whitespace);
        content.add(whitespace);
        content.add(cdata);
        content.add(cdata);
        content.add(whitespace);
        content.add(whitespace);
        newList = AtomParserUtils.trimContents(content);
        assertEquals(2, newList.size());
        assertTrue(newList.get(0) == cdata);
        assertTrue(newList.get(1) == cdata);
    }
}
