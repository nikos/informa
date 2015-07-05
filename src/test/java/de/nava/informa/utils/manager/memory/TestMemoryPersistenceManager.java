//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.manager.memory;

import de.nava.informa.utils.manager.PersistenceManagerIF;
import de.nava.informa.utils.manager.TestAbstractPersistenceManager;

/**
 * In-Memory persistence manager test.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 * @see PersistenceManager
 */
public class TestMemoryPersistenceManager extends TestAbstractPersistenceManager {
    /**
     * Returns manager to be tested.
     *
     * @return manager to be tested.
     */
    protected PersistenceManagerIF getManager() {
        return new PersistenceManager();
    }
}
