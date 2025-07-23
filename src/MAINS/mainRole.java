package MAINS;

import SERVICE.RoleService;
import java.util.*;

public class mainRole {
    public static void main(String[] args) {
        RoleService roleService = new RoleService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    roleService.createRole();
                    break;
                case 2:
                    roleService.listAllRoles();
                    break;
                case 3:
                    roleService.updateRole();
                    break;
                case 4:
                    roleService.deleteRole();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        roleService.close();
        scanner.close();
        System.out.println("Gracias vemos socito");

    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE RROLES ---");
        System.out.println("1. Crear rol");
        System.out.println("2. Listar roles");
        System.out.println("3. Actualizar rol");
        System.out.println("4. Eliminar rol");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
