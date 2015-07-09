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
 * This interface is implemented by objects representing an image assigned
 * to a channel in the news channel object model.
 *
 * <p>{@link WithLocationMIF#getLocation()} here denotes the URL where the image
 * can be retrieved from</p>
 * <p>{@link WithLinkMIF#getLink()} denotes here the URL to which the image
 * file will link when rendered in HTML</p>
 *
 * @author Niko Schmuck
 */
public interface ImageIF extends WithIdMIF, WithTitleMIF, WithLocationMIF, WithDescriptionMIF, WithLinkMIF {

    int getWidth();

    void setWidth(int width);

    int getHeight();

    void setHeight(int height);

}
