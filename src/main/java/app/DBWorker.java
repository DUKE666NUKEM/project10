package app;

import java.sql.*;

public class DBWorker {
    public static PreparedStatement preparedStatement = null;
    public static Connection connection = null;

    public static Integer insertToDb(String tableName, String column, String value) {
        try {
            connection = getConnection();
            String insertTableSQL = "INSERT INTO " + tableName + " (" + column + ")"
                    + " VALUES "
                    + "(?)";
            preparedStatement = connection.prepareStatement(insertTableSQL);


            preparedStatement.setString(1, value);
            preparedStatement.executeUpdate();
            String readIdSQL = "select @@IDENTITY from " + tableName;
            Integer id=null;

            preparedStatement = connection.prepareStatement(readIdSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            preparedStatement.close();
            connection.close();
            return id;
        } catch (SQLException err) {
            System.out.println("ERROR: " + err);
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void updateRecord(String tableName, String column, String value, int id, String pKeyColumn) {
        String updateRecordSQL = "UPDATE " + tableName + " SET " + column + "=? WHERE " + pKeyColumn + "=?";
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        String database =
                "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=FFS.mdb;";
        return DriverManager.getConnection(database, "", "");
    }
}