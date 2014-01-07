package app;

import java.io.*;


public class Importer {
    public static void doImport(File file) {
        String sCurrentLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {
                ++i;
                if (i == 4 || i == 5 || i == 9) {
                    //MeasurementGroup
                } else if (i == 7) {
                    //Researcher
                } else if (i == 6 || i == 12 || i == 22 || i == 28 || i == 30) {
                    //Measurement
                    if (i == 12 || i == 22) {
                        //common fields
                    }
                } else if (isBetween(i, 13, 21) || i == 23 || i == 26) {
                    //Parameters
                } else if (i == 25) {
                    //Equipment
                } else if (isBetween(i, 30, 221)) {
                    //Data1
                } else if (isBetween(i, 222, 224)) {
                    //Measurement 2 record
                } else if (isBetween(i, 226, Integer.MAX_VALUE)) {
                    //Data2
                }
            }
        } catch (
                IOException e
                )

        {
            e.printStackTrace();
        }

    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
