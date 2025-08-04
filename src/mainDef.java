
import MAINS.mainUsers;
import MAINS.mainRole;
import MAINS.mainSuppliers;
import MAINS.mainOperations;
import MAINS.mainCategories;
import MAINS.mainProducts;
import MAINS.mianAssignments;

import java.util.Scanner;

public class mainDef {
    public static void main(String[] args){
        mainRole mainrole = new mainRole();
        mainUsers mainusers = new mainUsers();
        mainSuppliers mainSuppliers = new mainSuppliers();
        mainOperations mainOperations = new mainOperations();
        mainCategories mainCategories = new mainCategories();
        mainProducts mainProducts = new mainProducts();
        mianAssignments mianAssignments = new mianAssignments();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    mainusers.menuUsuario();
                    break;
                case 2:
                    mainrole.menuRol();
                    break;
                case 3:
                    mainSuppliers.menuProveedor();
                    break;
                case 4:
                    mainOperations.menuOperacion();
                    break;
                case 5:
                    mainCategories.menuCategorias();
                    break;
                case 6:
                    mainProducts.menuProducto();
                    break;
                case 7:
                    mianAssignments.menuAsignaciones();
                    break;
                case 8:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }


        scanner.close();
        System.out.println("Gracias y adiosssss");
    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE DATOS ---");
        System.out.println("1. Usuarios");
        System.out.println("2. Roles");
        System.out.println("3. Proveedores");
        System.out.println("4. Operaciones");
        System.out.println("5. Categorias de producto");
        System.out.println("6. Productos");
        System.out.println("7. Asignaciones");
        System.out.print("Seleccione una opción: ");

    }
}
