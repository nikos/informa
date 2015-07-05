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

/**
 * contain the values of HTTP headers. These values are necessary
 * to implement "conditionnal get" for the feed.
 *
 * @author Jean-Guy Avelin
 */
public class ConditionalGetValues {
    String ETag = null;
    long ifModifiedSince = 0L;


    /**
     * @return Returns the eTag.
     */
    public String getETag() {
        return ETag;
    }


    /**
     * @param tag The eTag to set.
     */
    public void setETag(String tag) {
        ETag = tag;
    }


    /**
     * @return Returns the ifModifiedSince.
     */
    public long getIfModifiedSince() {
        return ifModifiedSince;
    }


    /**
     * @param ifModifiedSince The ifModifiedSince to set.
     */
    public void setIfModifiedSince(long ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

}
