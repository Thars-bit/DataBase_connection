package DAO;

import MODEL.Operations;
import java.sql.*;
import java.util.*;

public class OperationsDAO {

    public boolean createOperation(Operations operation) {
        String sql = "INSERT INTO operations (name) VALUES (?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, operation.getName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        operation.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear una operación: " + e.getMessage());
        }
        return false;
    }

    // Listar todas las operaciones
    public List<Operations> getAllOperations() {
        List<Operations> operations = new ArrayList<>();
        String sql = "SELECT id, name FROM operations";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                operations.add(new Operations(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las operaciones: " + e.getMessage());
        }
        return operations;
    }

    // Obtener una operación por ID
    public Operations getOperationById(int id) {
        String sql = "SELECT id, name FROM operations WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Operations(
                            result.getInt("id"),
                            result.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la operación: " + e.getMessage());
        }
        return null;
    }

    // Actualizar una operación
    public boolean updateOperation(Operations operation) {
        String sql = "UPDATE operations SET name = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, operation.getName());
            statement.setInt(2, operation.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la operación: " + e.getMessage());
        }
        return false;
    }

    // Eliminar una operación
    public boolean deleteOperation(int id) {
        String sql = "DELETE FROM operations WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la operación: " + e.getMessage());
        }
        return false;
    }
}
