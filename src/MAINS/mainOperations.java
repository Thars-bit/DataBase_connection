package MAINS;

import SERVICE.OperationsService;
import java.util.*;

public class mainOperations {

    public void menuOperacion() {
        OperationsService operationService = new OperationsService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    operationService.createOperation();
                    break;
                case 2:
                    operationService.listAllOperations();
                    break;
                case 3:
                    operationService.updateOperation();
                    break;
                case 4:
                    operationService.deleteOperation();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        operationService.close();
        scanner.close();
        System.out.println("Gracias y adiosssss");
    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE OPERACIONES ---");
        System.out.println("1. Crear operación");
        System.out.println("2. Listar operaciones");
        System.out.println("3. Actualizar operación");
        System.out.println("4. Eliminar operación");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
