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

import java.util.Date;

/**
 * <p>This interface is implemented by objects representing feeds
 * (containing metadata about channels, like for example OCS and OPML)
 * in the news channel object model.</p>
 *
 * <p>{@link WithLocationMIF#getLocation()} returns the destination of the feed
 * (most likely the XML source).</p>
 * <p>{@link WithChannelMIF#getChannel()} retrieves the channel this feed
 * represents.</p>
 * <p>{@link WithChannelMIF#setChannel(ChannelIF)} sets the channel that
 * this feed represents</p>
 *
 * @author Niko Schmuck
 */
public interface FeedIF extends WithIdMIF, WithTitleMIF, WithLocationMIF, WithChannelMIF, WithSiteMIF {

    String getText();

    void setText(String text);

    String getContentType();

    void setContentType(String contentType);

    Date getDateFound();

    void setDateFound(Date dateFound);

    Date getLastUpdated();

    void setLastUpdated(Date lastUpdated);

}
