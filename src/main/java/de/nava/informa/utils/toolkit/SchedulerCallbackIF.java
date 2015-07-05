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
 * Using this callback scheduler lets know when time to process channel comes.
 *
 * @author Aleksey Gureev (spyromus@noizeramp.com)
 */
public interface SchedulerCallbackIF {
    /**
     * Invoked by scheduler when time to process channel information comes.
     *
     * @param record channel record.
     */
    void process(ChannelRecord record);
}
