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

import java.net.URL;
import java.util.Date;

/**
 * This interface is implemented by objects representing items (that are
 * links to articles) in the news channel object model.
 *
 * @author Niko Schmuck
 */
public interface ItemIF extends WithIdMIF, WithTitleMIF, WithElementsAndAttributesMIF,
        WithCreatorMIF, WithCategoriesMIF, WithDescriptionMIF,
        WithLinkMIF, WithChannelMIF, WithUnreadMIF {

    ItemGuidIF getGuid();

    void setGuid(ItemGuidIF guid);

    URL getComments();

    void setComments(URL comments);

    ItemSourceIF getSource();

    void setSource(ItemSourceIF source);

    ItemEnclosureIF getEnclosure();

    void setEnclosure(ItemEnclosureIF enclosure);

  /* Dublin Core Metadata, like Creator and Subject  */

    String getSubject();

    void setSubject(String subject);

    Date getDate();

    void setDate(Date date);

    Date getFound();

    void setFound(Date found);
}
