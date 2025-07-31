package SERVICE;

import DAO.OperationsDAO;
import MODEL.Operations;
import java.util.*;

public class OperationsService {

    private OperationsDAO operationsDAO;
    private Scanner scanner;

    public OperationsService() {
        this.operationsDAO = new OperationsDAO();
        this.scanner = new Scanner(System.in);
    }

    // Crear una nueva operación
    public void createOperation() {
        System.out.println("CREAR NUEVA OPERACIÓN");

        System.out.print("Ingrese el nombre de la operación: ");
        String name = scanner.nextLine();

        Operations newOperation = new Operations(0, name);

        if (operationsDAO.createOperation(newOperation)) {
            System.out.println("Operación creada correctamente con ID: " + newOperation.getId());
        } else {
            System.out.println("Error al crear la operación.");
        }
    }

    // Listar todas las operaciones
    public void listAllOperations() {
        System.out.println("LISTA DE OPERACIONES");
        List<Operations> operationsList = operationsDAO.getAllOperations();

        if (operationsList.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
        } else {
            operationsList.forEach(System.out::println);
        }
    }

    // Actualizar una operación
    public void updateOperation() {
        System.out.println("ACTUALIZACIÓN DE OPERACIÓN");
        listAllOperations();

        System.out.print("Ingrese el ID de la operación a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Operations operation = operationsDAO.getOperationById(id);
        if (operation == null) {
            System.out.println("Operación no encontrada.");
            return;
        }

        System.out.print("Nuevo nombre (" + operation.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            operation.setName(newName);
        }

        if (operationsDAO.updateOperation(operation)) {
            System.out.println("Operación actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la operación.");
        }
    }

    // Eliminar una operación
    public void deleteOperation() {
        System.out.println("ELIMINAR OPERACIÓN");
        listAllOperations();

        System.out.print("Ingrese el ID de la operación a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro de que quiere eliminar esta operación? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (operationsDAO.deleteOperation(id)) {
                System.out.println("Operación eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la operación o no encontrada.");
            }
        }
    }

    public void close() {
        scanner.close();
    }

}
