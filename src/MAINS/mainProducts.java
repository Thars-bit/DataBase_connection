package MAINS;

import SERVICE.ProductsService;
import java.util.*;

public class mainProducts {

    public void menuProducto() {
        ProductsService productService = new ProductsService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    productService.createProduct();
                    break;
                case 2:
                    productService.listAllProducts();
                    break;
                case 3:
                    productService.updateProduct();
                    break;
                case 4:
                    productService.deleteProduct();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        productService.close();
        scanner.close();
        System.out.println("Gracias y adiós.");
    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE PRODUCTOS ---");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Actualizar producto");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
