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


/**
 * Enums to describe which syntax is used by a channel description.</p>
 *
 * @author Niko Schmuck (niko@nava.de), Italo Borssatto
 */
public enum ChannelFormat {

    /**
     * Convenient null value to make code more robust
     */
    UNKNOWN_CHANNEL_FORMAT("Unknown"),

    /**
     * Syntax according to RSS 0.9 specification.
     */
    RSS_0_90("RSS 0.90"),

    /**
     * Syntax according to RSS 0.91 specification.
     */
    RSS_0_91("RSS 0.91"),

    /**
     * Syntax according to RSS 0.92 specification.
     */
    RSS_0_92("RSS 0.92"),

    /**
     * Syntax according to RSS 0.93 specification.
     */
    RSS_0_93("RSS 0.93"),

    /**
     * Syntax according to RSS 0.94 specification.
     */
    RSS_0_94("RSS 0.94"),

    /**
     * Syntax according to RSS 1.0 specification.
     */
    RSS_1_0("RSS 1.0"),

    /**
     * Syntax according to RSS 2.0 specification.
     */
    RSS_2_0("RSS 2.0"),

    /**
     * Syntax according to the Atom 0.1 specification.
     */
    ATOM_0_1("Atom 0.1"),

    /**
     * Syntax according to the Atom 0.2 specification.
     */
    ATOM_0_2("Atom 0.2"),

    /**
     * Syntax according to the Atom 0.3 specification.
     */
    ATOM_0_3("Atom 0.3"),

    /**
     * Syntax according to the Atom 1.0 specification.
     */
    ATOM_1_0("Atom 1.0");

    private String formatSpec;

    ChannelFormat(String formatSpec) {
        this.formatSpec = formatSpec;
    }

    public String toString() {
        return formatSpec;
    }
}
