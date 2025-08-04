package DAO;

import MODEL.Stock;
import java.sql.*;
import java.util.*;

public class StockDAO {

    public boolean createStock(Stock stock) {
        String sql = "INSERT INTO stock (remain, product_id) VALUES (?, ?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, stock.getRemain());
            statement.setInt(2, stock.getProduct_id());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        stock.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el stock: " + e.getMessage());
        }
        return false;
    }

    // Obtener todos los registros de stock
    public List<Stock> getAllStock() {
        List<Stock> stocks = new ArrayList<>();
        String sql = "SELECT id, remain, product_id FROM stock";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                stocks.add(new Stock(
                        result.getInt("id"),
                        result.getInt("remain"),
                        result.getInt("product_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener los stocks: " + e.getMessage());
        }

        return stocks;
    }

    // Obtener un stock por ID
    public Stock getStockById(int id) {
        String sql = "SELECT id, remain, product_id FROM stock WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Stock(
                            result.getInt("id"),
                            result.getInt("remain"),
                            result.getInt("product_id")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el stock por ID: " + e.getMessage());
        }

        return null;
    }

    // Actualizar registro de stock
    public boolean updateStock(Stock stock) {
        String sql = "UPDATE stock SET remain = ?, product_id = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, stock.getRemain());
            statement.setInt(2, stock.getProduct_id());
            statement.setInt(3, stock.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el stock: " + e.getMessage());
        }

        return false;
    }

    // Eliminar registro de stock
    public boolean deleteStock(int id) {
        String sql = "DELETE FROM stock WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar el stock: " + e.getMessage());
        }

        return false;
    }
}
