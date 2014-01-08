package app;

import java.io.*;


public class Importer {
    public static void doImport(File file) {
        String sCurrentLine;

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            int i = 0;
            Integer measurementGroupId = null;
            Integer researcherId = null;
            Integer measurementId = null;
            Integer measurementId1 = null;
            Integer equipmentId = null;
            Integer parameterId = null;
            StringBuilder memo1Data = new StringBuilder("");
            StringBuilder memo2Data = new StringBuilder("");
            while ((sCurrentLine = br.readLine()) != null) {
                ++i;
                if (i == 4 || i == 5 || i == 9) {
                    if (i == 4) {
                        measurementGroupId = DBWorker.insertToDb("MeasurementGroup", "DateNow", separator1(sCurrentLine));
                    }
                    if (i == 5) {
                        DBWorker.updateRecord("MeasurementGroup", "TimeNow", separator1(sCurrentLine), measurementGroupId, "GroupId");
                    }
                    if (i == 9) {
                        DBWorker.updateRecord("MeasurementGroup", "GroupName", separator1(sCurrentLine), measurementGroupId, "GroupId");
                    }
                } else if (i == 7) {
                    if (i == 7) {
                        researcherId = DBWorker.insertToDb("Researcher", "UserName", separator1(sCurrentLine));
                    }

                } else if (i == 6 || i == 12 || i == 22 || i == 28) {
                    if (i == 6) {
                        measurementId = DBWorker.insertToDb("Measurement", "Type", separator1(sCurrentLine));
                    }
                    if (i == 12) {
                        DBWorker.updateRecord("Measurement", "StartTime", separator1(sCurrentLine), measurementId, "MeasurementId");
                        measurementId1 = DBWorker.insertToDb("Measurement", "StartTime", separator1(sCurrentLine));
                    }
                    if (i == 22) {
                        DBWorker.updateRecord("Measurement", "Duration", separator1(sCurrentLine), measurementId, "MeasurementId");
                        DBWorker.updateRecord("Measurement", "Duration", separator1(sCurrentLine), measurementId1, "MeasurementId");
                    }
                    if (i == 28) {
                        DBWorker.updateRecord("Measurement", "NumPoints", separator1(sCurrentLine), measurementId, "MeasurementId");
                    }
                } else if (i == 25) {
                    if (i == 25) {
                        equipmentId = DBWorker.insertToDb("Equipment", "Channels", separator1(sCurrentLine));
                    }
                } else if (i == 222 || i == 223 || i == 224) {
                    if (i == 222) {
                        DBWorker.updateRecord("Measurement", "Type", separator1(sCurrentLine), measurementId1, "MeasurementId");
                    }
                    if (i == 223) {
                        DBWorker.updateRecord("Measurement", "NumPoints", separator1(sCurrentLine), measurementId1, "MeasurementId");
                    }
                    if (i == 224) {
                        DBWorker.updateRecord("Measurement", "BinTime", separator1(sCurrentLine), measurementId1, "MeasurementId");
                    }
                } else if (i == 13) {
                    //parameterId = DBWorker.insertToDb("Parameters", "NamePar", separator0(sCurrentLine));

                } else if (isBetween(i, 30, 221)) {
                    //Data1
                    memo1Data.append(sCurrentLine);
                    memo1Data.append(System.getProperty("line.separator"));

                } else if (isBetween(i, 226, Integer.MAX_VALUE)) {
                    //Data2
                    memo2Data.append(sCurrentLine);
                    memo2Data.append(System.getProperty("line.separator"));
                }
                //System.out.println(memo2Data);

            }
            DBWorker.updateRecord("Measurement","Data",memo1Data.toString(),measurementId,"MeasurementId");
            DBWorker.updateRecord("Measurement","Data",memo2Data.toString(),measurementId1,"MeasurementId");
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

    public static String separator1(String sCurrentLine) {
        String[] separated = sCurrentLine.split("=");
        return (separated[1]);
    }

    public static String separator0(String sCurrentLine) {
        String[] separated = sCurrentLine.split("=");
        return (separated[0]);
    }
}
