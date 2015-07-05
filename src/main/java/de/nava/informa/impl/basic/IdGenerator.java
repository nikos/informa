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

import de.nava.informa.core.IdGeneratorIF;

import java.util.Random;

/**
 * Identity generator implemented as singleton for generating positive
 * random integers to be used as identify uniquely news channels,
 * items.
 *
 * @author Niko Schmuck (niko@nava.de)
 */

public class IdGenerator implements IdGeneratorIF {

    private static IdGenerator instance;
    /**
     * used for creating unique item IDs.
     */
    private static transient Random rand;

    private IdGenerator() {
        rand = new Random(System.currentTimeMillis());
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            instance = new IdGenerator();
        }
        return instance;
    }

    public long getId() {
        return 100000l + Math.abs(rand.nextInt());
    }

}
