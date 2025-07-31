package DAO;

import MODEL.Categories;
import java.sql.*;
import java.util.*;

public class CategoriesDAO {

    public boolean createCategory(Categories category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, category.getName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        category.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error al crear la categoría: " + e.getMessage());
        }
        return false;
    }

    // Obtener todas las categorías
    public List<Categories> getAllCategories() {
        List<Categories> categories = new ArrayList<>();
        String sql = "SELECT id, name FROM categories";

        try (Connection connection = Connect_DB.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                categories.add(new Categories(
                        result.getInt("id"),
                        result.getString("name")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las categorías: " + e.getMessage());
        }
        return categories;
    }

    // Obtener una categoría por ID
    public Categories getCategoryById(int id) {
        String sql = "SELECT id, name FROM categories WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new Categories(
                            result.getInt("id"),
                            result.getString("name")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la categoría: " + e.getMessage());
        }
        return null;
    }

    // Actualizar categoría
    public boolean updateCategory(Categories category) {
        String sql = "UPDATE categories SET name = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar la categoría: " + e.getMessage());
        }
        return false;
    }

    // Eliminar categoría
    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar la categoría: " + e.getMessage());
        }
        return false;
    }
}
