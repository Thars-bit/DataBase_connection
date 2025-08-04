package DAO;

import MODEL.Assignments;
import java.sql.*;
import java.util.*;

public class AssignmentsDAO {

    public boolean createAssignment(Assignments assignment) {
        String sql = "INSERT INTO assignments (date, quantity, ticket, serial, user_id, operation_id, product_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, assignment.getDate());
            statement.setInt(2, assignment.getQuantity());
            statement.setString(3, assignment.getTicket());
            statement.setString(4, assignment.getSerial());
            statement.setInt(5, assignment.getUser_id());
            statement.setInt(6, assignment.getOperation_id());
            statement.setInt(7, assignment.getProduct_id());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        assignment.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error al crear la asignación: " + e.getMessage());
        }
        return false;
    }

    // Listar todas las asignaciones
    public List<Assignments> getAllAssignments() {
        List<Assignments> assignments = new ArrayList<>();
        String sql = "SELECT id, date, quantity, ticket, serial, user_id, operation_id, product_id FROM assignments";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                assignments.add(new Assignments(
                        result.getInt("id"),
                        result.getString("date"),
                        result.getInt("quantity"),
                        result.getString("ticket"),
                        result.getString("serial"),
                        result.getInt("user_id"),
                        result.getInt("operation_id"),
                        result.getInt("product_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las asignaciones: " + e.getMessage());
        }
        return assignments;
    }

    // Obtener una asignación por ID
    public Assignments getAssignmentById(int id) {
        String sql = "SELECT id, date, quantity, ticket, serial, user_id, operation_id, product_id FROM assignments WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Assignments(
                            result.getInt("id"),
                            result.getString("date"),
                            result.getInt("quantity"),
                            result.getString("ticket"),
                            result.getString("serial"),
                            result.getInt("user_id"),
                            result.getInt("operation_id"),
                            result.getInt("product_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener la asignación: " + e.getMessage());
        }
        return null;
    }

    // Actualizar una asignación
    public boolean updateAssignment(Assignments assignment) {
        String sql = "UPDATE assignments SET date = ?, quantity = ?, ticket = ?, serial = ?, user_id = ?, operation_id = ?, product_id = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, assignment.getDate());
            statement.setInt(2, assignment.getQuantity());
            statement.setString(3, assignment.getTicket());
            statement.setString(4, assignment.getSerial());
            statement.setInt(5, assignment.getUser_id());
            statement.setInt(6, assignment.getOperation_id());
            statement.setInt(7, assignment.getProduct_id());
            statement.setInt(8, assignment.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la asignación: " + e.getMessage());
        }
        return false;
    }

    // Eliminar una asignación
    public boolean deleteAssignment(int id) {
        String sql = "DELETE FROM assignments WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la asignación: " + e.getMessage());
        }
        return false;
    }


}
