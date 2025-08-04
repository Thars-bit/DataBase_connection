package MAINS;

import SERVICE.AssignmentsService;
import java.util.*;

public class mianAssignments {

    public void menuAsignaciones() {
        AssignmentsService assignmentService = new AssignmentsService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    assignmentService.createAssignment();
                    break;
                case 2:
                    assignmentService.listAllAssignments();
                    break;
                case 3:
                    assignmentService.updateAssignment();
                    break;
                case 4:
                    assignmentService.deleteAssignment();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        assignmentService.close();
        scanner.close();
        System.out.println("Gracias por usar el sistema de asignaciones.");
    }

    private static void printMenu() {
        System.out.println("\n--- SISTEMA DE GESTIÓN DE ASIGNACIONES ---");
        System.out.println("1. Crear asignación");
        System.out.println("2. Listar asignaciones");
        System.out.println("3. Actualizar asignación");
        System.out.println("4. Eliminar asignación");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
