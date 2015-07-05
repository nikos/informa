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
import de.nava.informa.impl.basic.ChannelBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Date;

public class TestChannelRegistry extends InformaTestCase {

    private static Log logger = LogFactory.getLog(InformaTestCase.class);

    public TestChannelRegistry(String name) {
        super("TestChannelRegistry", name);
    }

    public void XXXtestCreate() throws Exception {
        ChannelRegistry reg = new ChannelRegistry(new ChannelBuilder());
        // first channel
        File inpFile = new File(getDataDir(), "xmlhack-0.91.xml");
        ChannelIF chA = reg.addChannel(inpFile.toURL(), 2, true);
        Date dateA = chA.getLastUpdated();
        assertNull("channel shouldn't be parsed now", dateA);
        // second channel
        inpFile = new File(getDataDir(), "pro-linux.rdf");
        ChannelIF chB = reg.addChannel(inpFile.toURL(), 2, true);
        // third channel
        inpFile = new File(getDataDir(), "snipsnap-org.rss");
        ChannelIF chC = reg.addChannel(inpFile.toURL(), 2, true);
        // some basic assertions
        assertEquals("channel exists", 3, reg.getChannels().size());
        assertTrue("channel A", reg.getChannels().contains(chA));
        assertTrue("channel B", reg.getChannels().contains(chB));
        assertTrue("channel C", reg.getChannels().contains(chC));
        logger.info("starting to sleep ...");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.warn("Interrupted waiting thread");
        }
        logger.info("... stopped sleeping");
        // check that they are still active
        assertTrue("channel A active", reg.isActiveChannel(chA));
        assertTrue("channel B active", reg.isActiveChannel(chB));
        assertTrue("channel C active", reg.isActiveChannel(chC));
        // verify update
        assertNotNull("channel should have been updated in the meantime",
                chA.getLastUpdated());
    }

    public void testParseProblem() throws Exception {
        ChannelRegistry reg = new ChannelRegistry(new ChannelBuilder());
        reg.setAcceptNrOfErrors(1);
        // first channel
        File inpFile = new File(getDataDir(), "xmlhack-0.91.xml");
        File chFile = new File(getOutputDir(), "xmlhack-0.91.xml");
        synchronized (chFile) {
            FileUtils.copyFile(inpFile, chFile);
        }
        ChannelIF chA = reg.addChannel(chFile.toURL(), 2 /* secs */, true);
        // be sure channel is read in
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            logger.warn("Interrupted waiting thread");
        }
        // some basic assertions
        assertEquals("channel exists", 1, reg.getChannels().size());
        assertTrue("channel A", reg.getChannels().contains(chA));
        UpdateChannelInfo info = reg.getUpdateInfo(chA);
        assertTrue("channel A active", reg.isActiveChannel(chA));
        assertNull("no exception", info.getLastException());
        assertEquals("NrProblems", 0, info.getNrProblemsOccurred());
        logger.info("deleting channel file");
        // simulate defect by deleting channel file
        synchronized (chFile) {
            chFile.delete();
        }
        logger.info("starting to sleep ...");
        try {
            // while we are sleep a new update should detect the lack of the file
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            logger.warn("Interrupted waiting thread");
        }
        logger.info("... stopped sleeping");
        // check that it's not any longer active
        info = reg.getUpdateInfo(chA);
        assertTrue("channel A should be deactive", !reg.isActiveChannel(chA));
        logger.debug("exception: " + info.getLastException());
        assertNotNull("Exception", info.getLastException());
        assertEquals("NrProblems", 1, info.getNrProblemsOccurred());
    }

}
