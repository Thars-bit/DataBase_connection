package DAO;

import MODEL.User;
import java.sql.*;
import java.util.*;

public class UserDAO {

    //creacion de un nuevo usuario
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

    //liste los usuarios  (TODOSSS)

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        String sql = "SELECT id, names, last_names, document_number, role_id FROM users";

        try (Connection connection = Connect_DB.getConnection();   //poll de Connect_DB
            Statement statement = connection.createStatement();   //Es el objeto para consultas estaticas no se usa "PreparedStatement" por que no hay parametros
            ResultSet result = statement.executeQuery(sql)) {   //contiene los resultados de la consulta sql

            while (result.next()) {   //con next() avanza al siguiente registro
                users.add(new User(
                   result.getInt("id"),
                   result.getString("names"),
                   result.getString("last_names"),
                   result.getInt("document_number"),
                   result.getInt("role_id")
                ));
            }
        }catch (SQLException e) {
            System.err.println("Error al obtener los usuarios: "+ e.getMessage());
        }
        return users;
    }

    //Obtener un usuario especifico con el ID

    public User getUserId(int id){
        String sql = "SELECT id, names, last_names, document_number, role_id FROM users WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {  // objeto para ejecutar consultas parametrizadas (mas segura que Statement)

            statement.setInt(1, id);  // asigna un valor al parametro SQL
            try (ResultSet result = statement.executeQuery()){   //el executeQuery ejecuta las consultas SELECT
                if (result.next()) {
                    return new User(
                            result.getInt("id"),
                            result.getString("names"),
                            result.getString("last_names"),
                            result.getInt("document_number"),
                            result.getInt("role_id")
                    );
                }
            }
        }catch (SQLException e){
            System.err.println("Error al obtener el usuario: "+ e.getMessage());
        }
        return null;
    }

    //Actualiozar usuario

    public boolean updateUser(User user){
        String sql = "UPDATE users SET names = ?, last_names = ?, document_number = ?, role_id = ?";

        try (Connection connection = Connect_DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, user.getNames());
            statement.setString(1, user.getLast_names());
            statement.setInt(3, user.getDocument_number());
            statement.setInt(4, user.getRole_id());

            return statement.executeUpdate() > 0;  // 1: se actualizo una fila 0: no se encontro >1 actualizo varias filas

        }catch (SQLException e){
            System.err.println("Error al obtener el usuario: "+ e.getMessage());
        }
        return false;
    }

    //Eliminar Usuariosss

    public boolean deleteUser(int id){
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = Connect_DB.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            return statement.executeUpdate() > 0; // 1: se elimino un solo usuario 0: no se encontro >1 se eliminaron varias OJOOO
        }catch (SQLException e){
            System.err.println("Error al eliminar el usuario: "+ e.getMessage());
        }
        return false;
    }
}
