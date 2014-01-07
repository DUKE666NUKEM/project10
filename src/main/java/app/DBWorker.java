package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBWorker {
    public void writeToDB(String str){
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database =
                    "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=FFS.mdb;";
            Connection conn = DriverManager.getConnection(database, "", "");
            Statement stmt = conn.createStatement();
            stmt.execute("select * from Measurement");
            ResultSet rs = stmt.getResultSet();
            while(rs.next()){
                System.out.println(rs.getString("Data"));
            }
            stmt.close();
            conn.close();
        } catch (Exception err) {
            System.out.println("ERROR: " + err);

        }
    }
}
