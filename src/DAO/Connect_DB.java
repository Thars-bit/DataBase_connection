package DAO;

import java.sql.*;

public class Connect_DB {

    //contsantes de conexion
    private static final String URL = "jdbc:mysql://localhost:3306/laravel";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //variable de conexion
    private static Connection connection;

    //patron Singleton: una sola instancia de conexion

    public static Connection getConnection() throws  SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        return connection;
    }

    //cerrar la conexion

    public static void closeConnection(){
        try{
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        }catch (SQLException e) {
            System.err.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }




}
