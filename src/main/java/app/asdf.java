package app;

import java.sql.*;

/**
 * Created by Somebody on 7.1.14.
 */
public class asdf {
    public static void main(String[] args) {
//        Integer id = DBWorker.insertToDb("MeasurementGroup", "GroupName", "32/10/66");
//        System.out.println(id);
        DBWorker.updateRecord("MeasurementGroup", "DateNow", "08/01/14", 40, "GroupId");
    }

    public static void qwer() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database =
                    "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=FFS.mdb;";
            Connection conn = DriverManager.getConnection(database, "", "");
            String insertTableSQL = "INSERT INTO MeasurementGroup"
                    + "(GroupName) VALUES"
                    + "(?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);


            preparedStatement.setString(1, "asdf");
            //preparedStatement.setString(2, "12:10:03");
            //preparedStatement.setString(2, "99/10/22");
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (Exception err) {
            System.out.println("ERROR: " + err);

        }
        read();
    }

    public static void read() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database =
                    "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=FFS.mdb;";
            Connection conn = DriverManager.getConnection(database, "", "");
            Statement stmt = conn.createStatement();
            stmt.execute("select * from MeasurementGroup");
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString("GroupName"));
            }
            stmt.close();
            conn.close();
        } catch (Exception err) {
            System.out.println("ERROR: " + err);

        }
    }
}
