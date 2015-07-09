//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.core;

import java.io.Serializable;

/**
 * This interface is implemented by Cloud objects
 * supporting RSS 0.92 and RSS 2.0 Publish and Subscribe.
 *
 * @author Michael Harhen
 */
public interface CloudIF extends Serializable {

    String getDomain();

    void setDomain(String domain);

    int getPort();

    void setPort(int port);

    String getPath();

    void setPath(String path);

    String getRegisterProcedure();

    void setRegisterProcedure(String registerProcedure);

    String getProtocol();

    void setProtocol(String protocol);

}
