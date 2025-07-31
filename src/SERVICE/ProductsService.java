package SERVICE;

import DAO.ProductsDAO;
import MODEL.Products;
import java.util.*;

public class ProductsService {

    private ProductsDAO productsDAO;
    private Scanner scanner;

    public ProductsService() {
        this.productsDAO = new ProductsDAO();
        this.scanner = new Scanner(System.in);
    }

    // Crear producto
    public void createProduct() {
        System.out.println("CREAR NUEVO PRODUCTO");

        System.out.print("Ingrese el nombre del producto: ");
        String name = scanner.nextLine();

        System.out.print("Ingrese el código del producto: ");
        String code = scanner.nextLine();

        int categoryId;
        try {
            System.out.print("Ingrese el ID de la categoría: ");
            categoryId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: el ID de la categoría debe ser numérico.");
            return;
        }

        int operationId;
        try {
            System.out.print("Ingrese el ID de la operación: ");
            operationId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: el ID de la operación debe ser numérico.");
            return;
        }

        Products newProduct = new Products(0, name, code, categoryId, operationId);

        if (productsDAO.createProduct(newProduct)) {
            System.out.println("Producto creado correctamente con ID: " + newProduct.getId());
        } else {
            System.out.println("Error al crear el producto.");
        }
    }

    // Listar productos
    public void listAllProducts() {
        System.out.println("LISTA DE PRODUCTOS");
        List<Products> products = productsDAO.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No hay productos en la base de datos.");
        } else {
            products.forEach(System.out::println);
        }
    }

    // Actualizar producto
    public void updateProduct() {
        System.out.println("ACTUALIZACIÓN DE PRODUCTO");
        listAllProducts();

        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Products product = productsDAO.getProductById(id);
        if (product == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Nuevo nombre (" + product.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            product.setName(newName);
        }

        System.out.print("Nuevo código (" + product.code() + "): ");
        String newCode = scanner.nextLine();
        if (!newCode.isEmpty()) {
            product.setCode(newCode);
        }

        System.out.print("Nuevo ID de categoría (" + product.getCategory_id() + "): ");
        String categoryInput = scanner.nextLine();
        if (!categoryInput.isEmpty()) {
            try {
                product.setCategory_id(Integer.parseInt(categoryInput));
            } catch (NumberFormatException e) {
                System.out.println("Error: ID de categoría inválido. No se actualizará.");
            }
        }

        System.out.print("Nuevo ID de operación (" + product.getOperation_id() + "): ");
        String operationInput = scanner.nextLine();
        if (!operationInput.isEmpty()) {
            try {
                product.setOperation_id(Integer.parseInt(operationInput));
            } catch (NumberFormatException e) {
                System.out.println("Error: ID de operación inválido. No se actualizará.");
            }
        }

        if (productsDAO.updateProduct(product)) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar el producto.");
        }
    }

    // Eliminar producto
    public void deleteProduct() {
        System.out.println("ELIMINAR PRODUCTO");
        listAllProducts();

        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro de que desea eliminar este producto? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (productsDAO.deleteProduct(id)) {
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el producto o producto no encontrado.");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
