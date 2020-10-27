package com.company;

import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {
        String insert = "Insert into students (name,phone_number)values(?,?)";
        String read = "select * from students";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        //DriverManager -> give me a connection
        // Class.forName("com.mysql.cj.jdbc.Driver"); to load the driver but on mac its not required
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "");
        stmt = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, "pardeep");
        stmt.setLong(2, 778678591);
        int affectedRows = stmt.executeUpdate();
        ResultSet fetchSize = stmt.getGeneratedKeys();
        fetchSize.next();
        System.out.println(fetchSize.getInt(1));
        System.out.println(affectedRows);


    }

    public static void close(ResultSet rs) {
        try {

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close result set: " + e.getMessage());
        }
    }

    public static void close(Connection connection) {
        try {

            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public static void close(Statement statement) {
        try {

            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close statement: " + e.getMessage());
        }
    }
}

