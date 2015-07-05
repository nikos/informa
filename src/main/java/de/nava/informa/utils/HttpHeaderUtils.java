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

import java.net.HttpURLConnection;

/**
 * utilities to deal with http headers
 *
 * @author Jean-Guy Avelin
 */
public class HttpHeaderUtils {

    /**
     * add a if-modified-since property on http header.
     *
     * @param conn
     * @param value
     */
    static public void setIfModifiedSince(HttpURLConnection conn, long value) {
        if (value > 0)
            conn.setIfModifiedSince(value);
    }

    /**
     * do nothing if etag == null otherwise add a If-None-Match property
     * in http header
     *
     * @param conn
     * @param etag
     */
    static public void setETagValue(HttpURLConnection conn, String etag) {
        if (etag != null) {
            conn.setRequestProperty("If-None-Match", etag);
        }
    }

    static public long getLastModified(HttpURLConnection conn) {
        long result = conn.getHeaderFieldDate("Last-Modified", 0L);
        return result;
    }

    static public String getETagValue(HttpURLConnection conn) {
        return conn.getHeaderField("ETag");
    }

    static public void setUserAgent(HttpURLConnection conn, String agent) {
        if (agent == null)
            return;
        conn.setRequestProperty("User-Agent", agent);
    }
}
