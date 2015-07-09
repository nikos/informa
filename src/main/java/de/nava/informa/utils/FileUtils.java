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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

/**
 * Utility class providing some convenience methods when handling files.
 */
public final class FileUtils {

    private static Log logger = LogFactory.getLog(FileUtils.class);

    private FileUtils() {
    }

    public static boolean compare(String nameExpected, String nameActual)
            throws IOException {

        return compare(new File(nameExpected), new File(nameActual));
    }

    public static boolean compare(File fileExpected, File fileActual)
            throws IOException {

        BufferedReader readExpected;
        try {
            logger.debug("Comparing golden file " + fileExpected +
                    " to " + fileActual);
            readExpected = new BufferedReader(new FileReader(fileExpected));
        } catch (IOException e) {
            logger.error("Could not read baseline: " + e);
            return false;
        }
        BufferedReader readActual =
                new BufferedReader(new FileReader(fileActual));
        return compare(readExpected, readActual);
    }

    private static boolean compare(BufferedReader readerExpected,
                                   BufferedReader readerActual)
            throws IOException {

        String lineExpected = readerExpected.readLine();
        String lineActual = readerActual.readLine();
        while (lineExpected != null && lineActual != null) {
            if (!lineExpected.equals(lineActual)) {
                return false;
            }
            lineExpected = readerExpected.readLine();
            lineActual = readerActual.readLine();
        }
        readerExpected.close();
        readerActual.close();
        return lineExpected == null && lineActual == null;
    }

    /**
     * Copies a file from <code>inFile</code> to <code>outFile</code>.
     */
    public static void copyFile(File inFile, File outFile) {
        try {
            logger.debug("Copying file " + inFile + " to " + outFile);
            InputStream in = new FileInputStream(inFile);
            OutputStream out = new FileOutputStream(outFile);
            byte[] buf = new byte[8 * 1024];
            int n;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
                out.flush();
            }
            in.close();
            out.close();
        } catch (Exception e) {
            logger.warn("Error occurred while copying file " + inFile + " to " + outFile);
            e.printStackTrace();
        }
    }

}
