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

public enum ChannelUpdatePeriod {

    /**
     * Update of channel expected to be specified in number of hours
     */
    UPDATE_HOURLY("hourly", 60 * 60),

    /**
     * Update of channel expected to be specified in number of days
     */
    UPDATE_DAILY("daily", 60 * 60 * 24),

    /**
     * Update of channel expected to be specified in number of weeks
     */
    UPDATE_WEEKLY("weekly", 60 * 60 * 24 * 7),

    /**
     * Update of channel expected to be specified in number of months
     */
    UPDATE_MONTHLY("monthly", 60 * 60 * 24 * 30),

    /**
     * Update of channel expected to be specified in number of years
     */
    UPDATE_YEARLY("yearly",  60 * 60 * 24 * 365);

    /**
     * Text representation of the period.
     */
    private String text;

    /**
     * Seconds in the period.
     */
    private long secsInPeriod;

    /**
     * @param text       Text representation of the period.
     * @param secsInPeriod Milliseconds in the period.
     */
    ChannelUpdatePeriod(String text, long secsInPeriod) {
        this.text = text;
        this.secsInPeriod = secsInPeriod;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.text;
    }

    /**
     * @return Milliseconds in the period.
     */
    public long getMsInPeriod() {
        return this.secsInPeriod * 1000;
    }

    /**
     * @return Minutes in the period.
     */
    public int getMinutesInPeriod() {
        return (int) (this.secsInPeriod / 60);
    }

    /**
     * @param text Text representation of the period.
     * @return The channel update period specified by the text representation.
     */
    public static ChannelUpdatePeriod valueFromText(String text) {
        for (ChannelUpdatePeriod updatePeriod : values()) {
            if (updatePeriod.text.equals(text)) {
                return updatePeriod;
            }
        }

        throw new IllegalArgumentException("The text representation '" + text
                + "' is not valid for ChannelUpdatePeriod.");
    }
}
