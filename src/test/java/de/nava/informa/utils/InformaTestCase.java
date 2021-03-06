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
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.Iterator;

/**
 * Base class for unit tests of the informa library. It provides some
 * convenience methods for easy data file retrieval.
 *
 * @author Niko Schmuck
 */
public abstract class InformaTestCase extends TestCase {

    private static Log logger = LogFactory.getLog(InformaTestCase.class);

    protected final static String FS = System.getProperty("file.separator");
    protected static String dataDir;
    protected static String indexDir;
    protected static String outputDir;
    protected static String baselineDir;

    protected String testcase_name;
    protected String method_name;

    static {
        // fall-back mechanism to get the base directory
        String baseDir = System.getProperty("INFORMA_HOME");
        logger.debug("retrieving property INFORMA_HOME: " + baseDir);
        if (baseDir == null) {
            baseDir = System.getProperty("basedir");
            logger.debug("retrieving property basedir: " + baseDir);
        }
        if (baseDir == null) {
            baseDir = System.getProperty("user.dir");
            logger.debug("retrieving property user.dir: " + baseDir);
        }
        // calculate other dirs from this
        String writeDir = baseDir + FS + "target" + FS + "test" + FS + "data";
        indexDir = writeDir + FS + "index";
        outputDir = writeDir + FS + "out";
        // the output directory is only used to write files generated by test
        // cases and may therefore not exist yet
        File out = new File(outputDir);
        if (!out.exists()) {
            out.mkdirs();
        }
        // Those directories are only used in read-only mode
        String refDir = baseDir + FS + "src" + FS + "test" + FS + "resources";
        dataDir = refDir + FS + "data";
        baselineDir = refDir + FS + "baseline";
    }


    /**
     * Constructor for a new informa specific test case. The base directory
     * is calculated from one of the following system properties (order matters):
     * <code>INFORMA_HOME, basedir, user.dir</code>
     *
     * @param testcase_name the name of the test case (read method, thanks JUnit)
     */
    public InformaTestCase(String testcase_name, String method_name) {
        super(method_name);
        this.method_name = method_name;
        this.testcase_name = testcase_name;
    }

    /**
     * @return The directory from which we can retrieve test relevant data.
     */
    public static String getDataDir() {
        return dataDir;
    }

    /**
     * @return The directory to store the full-text index in.
     */
    public static String getIndexDir() {
        return indexDir;
    }

    /**
     * @return The directory where our test might want to write a produced
     * file to.
     */
    public static String getOutputDir() {
        return outputDir;
    }

    /**
     * @return The directory containing the gold files to compare the
     * files generated by the current test runs with.
     */
    public static String getBaselineDir() {
        return baselineDir;
    }

    /**
     * Returns the name of the testcase inclusive the class.
     */
    public String getName() {
        return testcase_name + "." + method_name;
    }

    // =====================================================================
    // internal helper methods
    // =====================================================================

    protected ItemIF searchForItem(ChannelIF chn, String itmTitle) {
        ItemIF lookup_item = null;
        Iterator it = chn.getItems().iterator();
        while (it.hasNext()) {
            ItemIF item = (ItemIF) it.next();
            assertNotNull("Item has no title", item.getTitle());
            assertNotNull("Item has no link", item.getLink());
            // logger.debug("title: <" + item.getTitle() + ">");
            if (item.getTitle().startsWith(itmTitle)) {
                lookup_item = item;
                break;
            }
        }
        return lookup_item;
    }

}
