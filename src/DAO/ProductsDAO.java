package DAO;

import MODEL.Products;
import java.sql.*;
import java.util.*;

public class ProductsDAO {

    public boolean createProduct(Products product) {
        String sql = "INSERT INTO products (name, code, category_id, operation_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.code());
            statement.setInt(3, product.getCategory_id());
            statement.setInt(4, product.getOperation_id());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        product.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear el producto: " + e.getMessage());
        }
        return false;
    }

    // Obtener todos los productos
    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<>();
        String sql = "SELECT id, name, code, category_id, operation_id FROM products";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                products.add(new Products(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("code"),
                        result.getInt("category_id"),
                        result.getInt("operation_id")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener los productos: " + e.getMessage());
        }

        return products;
    }

    // Obtener un producto por ID
    public Products getProductById(int id) {
        String sql = "SELECT id, name, code, category_id, operation_id FROM products WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Products(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("code"),
                            result.getInt("category_id"),
                            result.getInt("operation_id")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener el producto: " + e.getMessage());
        }

        return null;
    }

    // Actualizar un producto
    public boolean updateProduct(Products product) {
        String sql = "UPDATE products SET name = ?, code = ?, category_id = ?, operation_id = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, product.getName());
            statement.setString(2, product.code());
            statement.setInt(3, product.getCategory_id());
            statement.setInt(4, product.getOperation_id());
            statement.setInt(5, product.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }

        return false;
    }

    // Eliminar un producto
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }

        return false;
    }

}
