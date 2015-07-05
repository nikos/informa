//
// Informa -- RSS Library for Java
// Copyright (c) 2002 by Niko Schmuck
//
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//

package de.nava.informa.utils;

import de.nava.informa.core.ChannelIF;
import de.nava.informa.core.ItemIF;
import de.nava.informa.impl.basic.ChannelBuilder;
import de.nava.informa.parsers.FeedParser;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;
import java.util.TimeZone;

public class TestItemComparator extends InformaTestCase {

    public TestItemComparator(String name) {
        super("TestItemComparator", name);
    }

    public void testSort() throws Exception {

        File inpFile = new File(getDataDir(), "snipsnap-org.rss");
        ChannelIF channel = FeedParser.parse(new ChannelBuilder(), inpFile);

        // convert from List to Array
        Set<ItemIF> itemsSet = channel.getItems();
        ItemIF[] items = itemsSet.toArray(new ItemIF[itemsSet.size()]);
        // sort news items
        Arrays.sort(items, new ItemComparator(true));

        // compare dates
        Calendar cal = Calendar.getInstance();

        cal.set(2002, Calendar.OCTOBER, 16, 0, 0, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        compareDates(cal, items, 0);

        cal.set(2002, Calendar.OCTOBER, 14, 0, 0, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        compareDates(cal, items, 1);

        cal.set(2002, Calendar.OCTOBER, 10, 0, 0, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        compareDates(cal, items, 2);

        cal.set(2002, Calendar.OCTOBER, 1, 0, 0, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        compareDates(cal, items, 8);

        cal.set(2002, Calendar.SEPTEMBER, 30, 0, 0, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        compareDates(cal, items, 9);

    /*
    for (int i = 0; i < items.length; i++) {
      ItemIF item = (ItemIF) items[i];
      System.out.println("--> title: " + item.getTitle() +
                         ", date: " + item.getDate());
    }
    */
    }

    private void compareDates(Calendar expectedCal, Object[] actualItems,
                              int index) {
        ItemIF item = (ItemIF) actualItems[index];
        // ignore milliseconds
        long milliExp = expectedCal.getTime().getTime();
        long milliAct = item.getDate().getTime();

        assertEquals("Wrong date for item " + (index + 1),
                milliExp / 1000, milliAct / 1000);
    }

}
