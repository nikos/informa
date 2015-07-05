//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;

import java.util.ArrayList;
import java.util.List;


/**
 * Common utility functions for Atom 0.3 and Atom 1.0 parser
 */
public class AtomParserUtils {
    private static final String[] ITEM_LINK_REL_PREFERRENCE_ORDER = {
            "alternate",
            null
    };
    private static final String[] ITEM_LINK_TYPES_PREFERRENCE_ORDER = {
            "text/html",
            "text/plain"
    };
    static public final Log LOGGER = LogFactory.getLog(AtomParserUtils.class);
    private static final XMLOutputter OUTPUTER = new XMLOutputter();

    /**
     * Clears namespace signature from the element and all of the children.
     */
    private static void clearNamespace(Element elt) {
        if (elt == null) {
            return;
        }

        elt.setNamespace(null);

        for (Object item : elt.getContent()) {
            if (item instanceof Element) {
                clearNamespace((Element) item);
            }
        }
    }

    /**
     * Looks for link sub-elements of type "link" and selects the most preferred.
     *
     * @param item  item element.
     * @param defNS default namespace.
     * @return link in string or <code>null</code>.
     */
    public static String getItemLink(Element item, Namespace defNS) {
        String currentHref = null;
        int currentOrder = Integer.MAX_VALUE;

        List links = item.getChildren("link", defNS);

        for (int i = 0; (currentOrder != 0) && (i < links.size()); i++) {
            Element link = (Element) links.get(i);

            // get type of the link
            String type = link.getAttributeValue("type");
            String rel = link.getAttributeValue("rel");

            if (type != null) {
                type = type.trim().toLowerCase();
            }

            // if we prefer this type more than the one we already have then
            // replace current href with new one and update preference order
            // value.
            int preferenceOrder = getPreferenceOrderForItemLinkType(type, rel);

            LOGGER.info("Link " + link.getAttributeValue("href") +
                    " with pref " +
                    getPreferenceOrderForItemLinkType(type, rel) + " " +
                    type + " " + rel);

            if (preferenceOrder < currentOrder) {
                String href = link.getAttributeValue("href");

                if (href != null) {
                    currentHref = href.trim();
                    currentOrder = preferenceOrder;
                }
            }
        }

        LOGGER.debug("url read : " + currentHref);

        return currentHref;
    }

    /**
     * Returns order of item link type preference.
     *
     * @param type type ("text/html", "text/plain", "application/xml" ...).
     * @return order (the lower the more preferred).
     */
    public static int getPreferenceOrderForItemLinkType(String type, String rel) {
        int orderType = -1;

        if (type != null) {
            for (int i = 0;
                 (orderType == -1) && (i < ITEM_LINK_TYPES_PREFERRENCE_ORDER.length);
                 i++) {
                if (type.equals(ITEM_LINK_TYPES_PREFERRENCE_ORDER[i])) {
                    orderType = i;
                }
            }
        }

        if (orderType == -1) {
            orderType = ITEM_LINK_TYPES_PREFERRENCE_ORDER.length;
        }

        int orderRel = -1;

        if (rel != null) {
            for (int i = 0;
                 (orderRel == -1) && (i < ITEM_LINK_REL_PREFERRENCE_ORDER.length);
                 i++) {
                if (rel.equals(ITEM_LINK_REL_PREFERRENCE_ORDER[i])) {
                    orderRel = i;
                }
            }
        }

        if (orderRel == -1) {
            orderRel = ITEM_LINK_REL_PREFERRENCE_ORDER.length;
        }

        return (orderRel * (ITEM_LINK_REL_PREFERRENCE_ORDER.length + 1)) +
                orderType;
    }

    /**
     * Returns value of the element.
     *
     * @param elt  the element to retrieve the value from.
     * @param mode can be one of "escaped", "base64" or "xml". If null, "xml" is assumed.
     */
    public static String getValue(Element elt, String mode) {
        if (elt == null) {
            return "";
        }

        mode = (mode == null) ? "xml" : mode;

        clearNamespace(elt);

        String value;

        // Here we convert the contents of element into some readable text.
        // If the contents (after removing leading and trailing spaces) is CDATA only,
        // we need to treat it specially by unpacking the contents it has. Otherwise,
        // we simply output what we have.
        List<Content> content = elt.getContent();
        content = trimContents(content);

        if ((content.size() == 1) && content.get(0) instanceof CDATA) {
            value = content.get(0).getValue();
        } else {
            value = OUTPUTER.outputString(content);
        }

        // Unescape or decode stuff if necessary
        if ("escaped".equals(mode)) {
            value = ParserUtils.unEscape(value);
        } else if ("base64".equals(mode)) {
            value = ParserUtils.decodeBase64(value);
        }

        return value;
    }

    /**
     * Cuts all empty (whitespace) text blocks from the head and tail of contents list.
     *
     * @param contents list of contents.
     * @return trimmed version.
     */
    public static List<Content> trimContents(List contents) {
        if (contents == null) {
            return null;
        }

        int head = 0;
        int count = contents.size();

        while ((head < count) &&
                (contents.get(head) instanceof Text &&
                        (((Text) contents.get(head)).getTextTrim().length() == 0))) {
            head++;
        }

        int tail = count - 1;

        while ((tail > head) &&
                (contents.get(tail) instanceof Text &&
                        (((Text) contents.get(tail)).getTextTrim().length() == 0))) {
            tail--;
        }

        return (tail >= head) ? contents.subList(head, tail + 1) : new ArrayList();
    }
}
