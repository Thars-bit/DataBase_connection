package DAO;

import MODEL.Suppliers;
import MODEL.User;

import java.sql.*;
import java.util.*;

public class SuppliersDAO {

    public boolean createSuppliers(Suppliers suppliers){
        String sql = "INSERT INTO suppliers (name, nit, email, phone, status) VALUES (?,?,?,?,?)";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, suppliers.getName());
            statement.setInt(2, suppliers.getNit());
            statement.setString(3, suppliers.getEmail());
            statement.setInt(4, suppliers.getPhone());
            statement.setInt(5, suppliers.getStatus());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0){
                try (ResultSet generatedKeys = statement.getGeneratedKeys()){
                    if (generatedKeys.next()){
                        suppliers.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        }catch (SQLException e){
            System.err.println("Error al crear un proveedor: " + e.getMessage());
        }
        return false;
    }

    public List<Suppliers> getAllSuppliers(){

        List<Suppliers> suppliers = new ArrayList<>();
        String sql = "SELECT id, name, nit, email, phone, status FROM suppliers";

        try (Connection connection = Connect_DB.getConnection();   //poll de Connect_DB
             Statement statement = connection.createStatement();   //Es el objeto para consultas estaticas no se usa "PreparedStatement" por que no hay parametros
             ResultSet result = statement.executeQuery(sql)){

            while (result.next()){
                suppliers.add(new Suppliers(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("nit"),
                        result.getString("email"),
                        result.getInt("phone"),
                        result.getInt("status")
                ));
            }
        }catch (SQLException e) {
            System.err.println("Error al obtener los proveedores: "+ e.getMessage());
        }
        return suppliers;

    }

    public Suppliers getSuppliersId(int id){
        String sql = "SELECT id, name, nit, email, phone, status FROM suppliers WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {  // objeto para ejecutar consultas parametrizadas (mas segura que Statement)

            statement.setInt(1, id);  // asigna un valor al parametro SQL
            try (ResultSet result = statement.executeQuery()){   //el executeQuery ejecuta las consultas SELECT
                if (result.next()) {
                    return new Suppliers(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getInt("nit"),
                            result.getString("email"),
                            result.getInt("phone"),
                            result.getInt("status")
                    );
                }
            }
        }catch (SQLException e){
            System.err.println("Error al obtener el proveedor: "+ e.getMessage());
        }
        return null;

    }

    public boolean updateSuppliers(Suppliers suppliers){

        String sql = "UPDATE suppliers SET names = ?, nit = ?, email = ?, phone = ?, status = ? WHERE id = ?";

        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, suppliers.getName());
            statement.setInt(2, suppliers.getNit());
            statement.setString(3, suppliers.getEmail());
            statement.setInt(4, suppliers.getPhone());
            statement.setInt(5, suppliers.getStatus());
            statement.setInt(6, suppliers.getId());

            return statement.executeUpdate() >0;

        }catch (SQLException e){
            System.err.println("Error al actualizar el proveedor: " + e.getMessage());
        }
        return false;

    }

    public boolean delete(int id){

        String sql = "DELETE FROM suppliers WHERE id = ?";
        try (Connection connection = Connect_DB.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            System.err.println("Error al eliminar el proveedor: "+ e.getMessage());
        }
        return false;
    }
}
