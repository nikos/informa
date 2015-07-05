//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.impl.basic;

import de.nava.informa.core.CloudIF;

/**
 * In-Memory implementation of the CloudIF interface.
 *
 * @author Michael Harhen
 */
public class Cloud implements CloudIF {

    private static final long serialVersionUID = -48710418882153466L;

    private long id;
    private String domain;
    private int port;
    private String path;
    private String registerProcedure;
    private String protocol;

    public Cloud() {
        this("[No Cloud]", -1, null, null, null);
    }

    public Cloud(String domain, int port, String path, String registerProcedure, String protocol) {
        this.id = IdGenerator.getInstance().getId();
        this.domain = domain;
        this.port = port;
        this.path = path;
        this.registerProcedure = registerProcedure;
        this.protocol = protocol;
    }

    // --------------------------------------------------------------
    // implementation of CloudIF interface
    // --------------------------------------------------------------

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRegisterProcedure() {
        return registerProcedure;
    }

    public void setRegisterProcedure(String registerProcedure) {
        this.registerProcedure = registerProcedure;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

}
