package MAINS;

import SERVICE.SuppliersService;
import java.util.*;

public class mainSuppliers {
    public void menuProveedor() {
        SuppliersService supplierService = new SuppliersService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    supplierService.createSupplier();
                    break;
                case 2:
                    supplierService.listAllSuppliers();
                    break;
                case 3:
                    supplierService.updateSupplier();
                    break;
                case 4:
                    supplierService.deleteSupplier();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        supplierService.close();
        scanner.close();
        System.out.println("Gracias y adiós.");
    }

    private static void printMenu() {
        System.out.println("\n--- SISTEMA DE GESTIÓN DE PROVEEDORES ---");
        System.out.println("1. Crear proveedor");
        System.out.println("2. Listar proveedores");
        System.out.println("3. Actualizar proveedor");
        System.out.println("4. Eliminar proveedor");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
