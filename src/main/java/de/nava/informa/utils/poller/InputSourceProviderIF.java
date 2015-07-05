//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils.poller;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.InputStream;

/**
 * Interface of <code>InputSource</code> provider which is intended to
 * return input source created from the given <code>InputStream</code>.
 * It can be used to customize the string processing by wrapping the
 * stream with custom <code>Reader</code>'s or another
 * <code>FilterInputStream</code>'s.
 * <p>
 * The source stream represents feed's stream of chars and
 * <code>InputSource</code> object will be used for parsing this feed.
 */
public interface InputSourceProviderIF {

    /**
     * Return <code>InputSource</code> to be used with given stream.
     *
     * @param stream input stream.
     * @return initialized input source ready for use in parsing.
     * @throws IOException in case of any problems.
     */
    InputSource getInputSourceFor(InputStream stream) throws IOException;
}
