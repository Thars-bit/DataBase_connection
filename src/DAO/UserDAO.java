package DAO;

import MODEL.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    public boolean createUser(User user){
        String sql = "INSERT INTO users (names, last_names, document_number, role_id) VALUES (?,?,?,?)";

        try (Connection connection = Connect_DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, user.getNames());
            statement.setString(1, user.getLast_names());
            statement.setInt(3, user.getDocument_number());
            statement.setInt(4, user.getRole_id());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        user.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        }catch (SQLException e){
            System.err.println("Error al crear un usuario: " + e.getMessage());
        }
        return false;
    }
}
