package SERVICE;

import DAO.SuppliersDAO;
import MODEL.Suppliers;
import java.util.*;

public class SuppliersService {

    private SuppliersDAO supplierDAO;
    private Scanner scanner;

    public SuppliersService() {
        this.supplierDAO = new SuppliersDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createSupplier() {
        System.out.println("CREAR NUEVO PROVEEDOR");

        System.out.println("Ingrese el nombre: ");
        String name = scanner.nextLine();

        int nit;
        try {
            System.out.print("Ingrese el NIT: ");
            nit = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: el NIT debe tener solo números");
            scanner.nextLine();
            return;
        }

        System.out.println("Ingrese el email: ");
        String email = scanner.nextLine();

        int phone;
        try {
            System.out.print("Ingrese el teléfono: ");
            phone = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: el teléfono debe ser numérico");
            scanner.nextLine();
            return;
        }

        int status;
        try {
            System.out.print("Ingrese el estado (activo = 1 / inactivo = 0): ");
            status = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: el estado debe ser un número (1 o 0)");
            scanner.nextLine();
            return;
        }

        Suppliers newSupplier = new Suppliers(0, name, nit, email, phone, status);

        if (supplierDAO.createSuppliers(newSupplier)) {
            System.out.println("Proveedor creado correctamente con ID: " + newSupplier.getId());
        } else {
            System.out.println("Error al crear el proveedor");
        }
    }

    public void listAllSuppliers() {
        System.out.println("LISTA DE PROVEEDORES");
        List<Suppliers> suppliers = supplierDAO.getAllSuppliers();

        if (suppliers.isEmpty()) {
            System.out.println("No hay proveedores en la base de datos");
        } else {
            suppliers.forEach(System.out::println);
        }
    }

    public void updateSupplier() {
        System.out.println("ACTUALIZACIÓN DE PROVEEDOR");
        listAllSuppliers();

        System.out.println("Ingrese el ID del proveedor a actualizar");
        int id = Integer.parseInt(scanner.nextLine());

        Suppliers supplier = supplierDAO.getSuppliersId(id);
        if (supplier == null) {
            System.out.println("Proveedor no encontrado");
            return;
        }

        System.out.println("Nuevo nombre (" + supplier.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            supplier.setName(newName);
        }

        int newNit = supplier.getNit();
        boolean validNit = false;
        while (!validNit) {
            System.out.print("Nuevo NIT (" + supplier.getNit() + "): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                validNit = true;
            } else {
                try {
                    newNit = Integer.parseInt(input);
                    validNit = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: el NIT debe ser un número entero");
                }
            }
        }
        supplier.setNit(newNit);

        System.out.println("Nuevo Email (" + supplier.getEmail() + "): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            supplier.setEmail(newEmail);
        }

        int newPhone = supplier.getPhone();
        boolean validPhone = false;
        while (!validPhone) {
            System.out.print("Nuevo teléfono (" + supplier.getPhone() + "): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                validPhone = true;
            } else {
                try {
                    newPhone = Integer.parseInt(input);
                    validPhone = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: el teléfono debe ser un número entero");
                }
            }
        }
        supplier.setPhone(newPhone);

        int newStatus = supplier.getStatus();
        boolean validStatus = false;
        while (!validStatus) {
            System.out.print("Nuevo estado (" + supplier.getStatus() + "): ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                validStatus = true;
            } else {
                try {
                    newStatus = Integer.parseInt(input);
                    validStatus = true;
                } catch (NumberFormatException e) {
                    System.out.println("Error: el estado debe ser un número entero (1 o 0)");
                }
            }
        }
        supplier.setStatus(newStatus);

        if (supplierDAO.updateSuppliers(supplier)) {
            System.out.println("Proveedor actualizado exitosamente");
        } else {
            System.out.println("Error al actualizar el proveedor");
        }
    }

    public void deleteSupplier() {
        System.out.println("ELIMINAR PROVEEDOR");
        listAllSuppliers();

        System.out.println("Ingrese el ID del proveedor que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro de que desea eliminar este proveedor? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (supplierDAO.deleteSuppliers(id)) {
                System.out.println("Proveedor eliminado exitosamente");
            } else {
                System.out.println("Error al eliminar el proveedor o proveedor no encontrado");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}

