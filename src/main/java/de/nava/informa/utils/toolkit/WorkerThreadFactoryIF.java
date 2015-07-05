//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.toolkit;

/**
 * Factory of <code>WorkerThread</code> objects, necessary to <code>WorkersManager</code>
 * to initialize new instances. Implementation of this interface is passed to the manager
 * during construction.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface WorkerThreadFactoryIF {

    /**
     * Creates new worker thread object.
     *
     * @return worker thread object.
     */
    WorkerThread create();
}
