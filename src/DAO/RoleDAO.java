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
            statement.setString(2, role.getDescription());

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
            System.err.println("Error al crear un rol: " + e.getMessage());
        }
        return false;
    }

    public List<Role> getAllRoles(){
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT id, names, description FROM roles";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()){
                roles.add(new Role(
                        result.getInt("id"),
                        result.getString("names"),
                        result.getString("description")
                ));
            }
        }catch (SQLException e) {
            System.err.println("Error al obtener los roles: "+ e.getMessage());
        }
        return roles;
    }

    public Role getRoleId(int id){
        String sql = "SELECT id, names, description FROM roles WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            try(ResultSet result = statement.executeQuery()){
                if(result.next()){
                    return new Role(
                            result.getInt("id"),
                            result.getString("names"),
                            result.getString("description")
                    );
                }
            }
        }catch (SQLException e){
            System.err.println("Error al obtener el rol: "+ e.getMessage());
        }
        return null;
    }

    public boolean updateRole(Role role){
        String sql = "UPDATE roles SET names = ?, description = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, role.getName());
            statement.setString(2, role.getDescription());

            return  statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al actualizar el rol: "+ e.getMessage());
        }
        return false;
    }

    public boolean deleteRole(int id){
        String sql = "DELETE FROM roles WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            return  statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al eliminar el rol: "+ e.getMessage());
        }
        return false;
    }
}
