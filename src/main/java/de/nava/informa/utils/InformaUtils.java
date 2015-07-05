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

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;

/**
 * InformaUtils class contain helper methods for various channel operations.
 */
public final class InformaUtils {

    /**
     * Copies all the top level properties from <code>sourceChan</code> into
     * this <code>updChan</code>. Convience method to update basic channel
     * properties (like title and description) in one call.
     * <p>
     * <b>Important:</b> Location and Format is unchanged by this operation.
     * <p>
     * TODO: Tricky. There is a bug here that has to be fixed. Channel Properties
     * which are actually pointers to other objects (e.g. Cloud) cannot be simply
     * copied because during channel and item parsing, we first parse the stream
     * into a basic (e.g. impl.basic.Cloud) object, which then gets subsequently copied
     * by this method into a persistent (e.g. impl.hibernate.Cloud) object.
     * <p>
     * I think the solution to this is to pass a ChannelBuilder into this method
     * which is used to create the right type of duplicates of the objects instead
     * of just copying the references. For now I will be skipping those. A similar
     * kind of problem probably exists when copying item properties.
     */
    public static void copyChannelProperties(ChannelIF sourceChan, ChannelIF updChan) {
        updChan.setTitle(sourceChan.getTitle());
        updChan.setDescription(sourceChan.getDescription());
        updChan.setSite(sourceChan.getSite());
        updChan.setCreator(sourceChan.getCreator());
        updChan.setCopyright(sourceChan.getCopyright());
        updChan.setPublisher(sourceChan.getPublisher());
        updChan.setLanguage(sourceChan.getLanguage());
        updChan.setRating(sourceChan.getRating());
        updChan.setGenerator(sourceChan.getGenerator());
        updChan.setDocs(sourceChan.getDocs());
        updChan.setTtl(sourceChan.getTtl());
        updChan.setLastBuildDate(sourceChan.getLastBuildDate());
        updChan.setUpdateBase(sourceChan.getUpdateBase());
        updChan.setUpdateFrequency(sourceChan.getUpdateFrequency());
        updChan.setUpdatePeriod(sourceChan.getUpdatePeriod());
        updChan.setPubDate(sourceChan.getPubDate());
        updChan.setFormat(sourceChan.getFormat());

        //   updChan.setTextInput(sourceChan.getTextInput());
        //   updChan.setCloud(sourceChan.getCloud());
        //   updChan.setImage(sourceChan.getImage());
    }

    /**
     * Analogous function to copy all the properties of an item to another one.
     *
     * @param src  - Source ItemIF
     * @param dest - Destination ItemIF
     */
    public static void copyItemProperties(ItemIF src, ItemIF dest) {
        dest.setTitle(src.getTitle());
        dest.setDescription(src.getDescription());
        dest.setLink(src.getLink());
        dest.setCreator(src.getCreator());
        dest.setSubject(src.getSubject());
        dest.setDate(src.getDate());
        dest.setFound(src.getFound());
        dest.setUnRead(src.getUnRead());
    }

}
