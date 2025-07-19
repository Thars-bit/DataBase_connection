package DAO;

import MODEL.Role;
import java.sql.*;
import java.util.*;

public class RoleDAO {

    public boolean createRole(Role role){
        String sql = "INSERT INTO roles (names, description) VALUES (?,?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, role.getName());
            statement.setString(1, role.getDescription());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        role.setId(generatedKeys.getInt(1));
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
