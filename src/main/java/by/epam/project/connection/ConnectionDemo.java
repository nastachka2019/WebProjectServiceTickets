package by.epam.project.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Deprecated
public class ConnectionDemo {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/service?useUnicode=true&serverTimezone=UTC&useSSL=false";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection root = DriverManager.getConnection(url, "root", "210789")) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }
}