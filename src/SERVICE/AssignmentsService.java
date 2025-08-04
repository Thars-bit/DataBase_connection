package SERVICE;

import DAO.AssignmentsDAO;
import MODEL.Assignments;
import java.util.*;

public class AssignmentsService {

    private AssignmentsDAO assignmentsDAO;
    private Scanner scanner;

    public AssignmentsService() {
        this.assignmentsDAO = new AssignmentsDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createAssignment() {
        System.out.println("CREAR NUEVA ASIGNACIÓN");

        System.out.print("Ingrese la fecha (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        int quantity;
        try {
            System.out.print("Ingrese la cantidad: ");
            quantity = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer
        } catch (InputMismatchException e) {
            System.out.println("Error: La cantidad debe ser un número entero");
            scanner.nextLine();
            return;
        }

        System.out.print("Ingrese el ticket: ");
        String ticket = scanner.nextLine();

        System.out.print("Ingrese el serial: ");
        String serial = scanner.nextLine();

        int user_id;
        try {
            System.out.print("Ingrese el ID del usuario: ");
            user_id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID del usuario debe ser un número");
            scanner.nextLine();
            return;
        }

        int operation_id;
        try {
            System.out.print("Ingrese el ID de la operación: ");
            operation_id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID de operación debe ser un número");
            scanner.nextLine();
            return;
        }

        int product_id;
        try {
            System.out.print("Ingrese el ID del producto: ");
            product_id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID del producto debe ser un número");
            scanner.nextLine();
            return;
        }

        Assignments newAssignment = new Assignments(0, date, quantity, ticket, serial, user_id, operation_id, product_id);

        if (assignmentsDAO.createAssignment(newAssignment)) {
            System.out.println("Asignación creada correctamente con ID: " + newAssignment.getId());
        } else {
            System.out.println("Error al crear la asignación.");
        }
    }




    public void listAllAssignments() {
        System.out.println("LISTA DE ASIGNACIONES");

        List<Assignments> assignments = assignmentsDAO.getAllAssignments();

        if (assignments.isEmpty()) {
            System.out.println("No hay asignaciones registradas.");
        } else {
            assignments.forEach(System.out::println);
        }
    }



    public void updateAssignment() {
        System.out.println("ACTUALIZAR ASIGNACIÓN");
        listAllAssignments();

        System.out.print("Ingrese el ID de la asignación a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Assignments assignment = assignmentsDAO.getAssignmentById(id);
        if (assignment == null) {
            System.out.println("Asignación no encontrada.");
            return;
        }

        System.out.print("Nueva fecha (" + assignment.getDate() + "): ");
        String newDate = scanner.nextLine();
        if (!newDate.isEmpty()) assignment.setDate(newDate);

        System.out.print("Nueva cantidad (" + assignment.getQuantity() + "): ");
        String quantityInput = scanner.nextLine();
        if (!quantityInput.isEmpty()) {
            try {
                assignment.setQuantity(Integer.parseInt(quantityInput));
            } catch (NumberFormatException e) {
                System.out.println("Cantidad inválida. Se mantendrá la actual.");
            }
        }

        System.out.print("Nuevo ticket (" + assignment.getTicket() + "): ");
        String newTicket = scanner.nextLine();
        if (!newTicket.isEmpty()) assignment.setTicket(newTicket);

        System.out.print("Nuevo serial (" + assignment.getSerial() + "): ");
        String newSerial = scanner.nextLine();
        if (!newSerial.isEmpty()) assignment.setSerial(newSerial);

        System.out.print("Nuevo ID de usuario (" + assignment.getUser_id() + "): ");
        String userIdInput = scanner.nextLine();
        if (!userIdInput.isEmpty()) {
            try {
                assignment.setUser_id(Integer.parseInt(userIdInput));
            } catch (NumberFormatException e) {
                System.out.println("ID de usuario inválido. Se mantendrá el actual.");
            }
        }

        System.out.print("Nuevo ID de operación (" + assignment.getOperation_id() + "): ");
        String operationIdInput = scanner.nextLine();
        if (!operationIdInput.isEmpty()) {
            try {
                assignment.setOperation_id(Integer.parseInt(operationIdInput));
            } catch (NumberFormatException e) {
                System.out.println("ID de operación inválido. Se mantendrá el actual.");
            }
        }

        System.out.print("Nuevo ID de producto (" + assignment.getProduct_id() + "): ");
        String productIdInput = scanner.nextLine();
        if (!productIdInput.isEmpty()) {
            try {
                assignment.setProduct_id(Integer.parseInt(productIdInput));
            } catch (NumberFormatException e) {
                System.out.println("ID de producto inválido. Se mantendrá el actual.");
            }
        }

        if (assignmentsDAO.updateAssignment(assignment)) {
            System.out.println("Asignación actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la asignación.");
        }
    }



    public void deleteAssignment() {
        System.out.println("ELIMINAR ASIGNACIÓN");
        listAllAssignments();

        System.out.print("Ingrese el ID de la asignación que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro que desea eliminar esta asignación? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (assignmentsDAO.deleteAssignment(id)) {
                System.out.println("Asignación eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la asignación o asignación no encontrada.");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
