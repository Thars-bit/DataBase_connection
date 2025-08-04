package SERVICE;

import java.util.*;
import DAO.StockDAO;
import MODEL.Stock;

public class StockService {

    private StockDAO stockDAO;
    private Scanner scanner;

    public StockService() {
        this.stockDAO = new StockDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createStock() {
        System.out.println("CREAR NUEVO REGISTRO DE STOCK");

        int remain;
        try {
            System.out.print("Ingrese la cantidad disponible: ");
            remain = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: la cantidad debe ser un número entero.");
            scanner.nextLine();
            return;
        }

        int product_id;
        try {
            System.out.print("Ingrese el ID del producto: ");
            product_id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: el ID del producto debe ser un número entero.");
            scanner.nextLine();
            return;
        }

        Stock newStock = new Stock(0, remain, product_id);
        if (stockDAO.createStock(newStock)) {
            System.out.println("Stock creado correctamente con ID: " + newStock.getId());
        } else {
            System.out.println("Error al crear el registro de stock.");
        }
    }

    public void listAllStock() {
        System.out.println("LISTA DE STOCK");
        List<Stock> stocks = stockDAO.getAllStock();

        if (stocks.isEmpty()) {
            System.out.println("No hay registros de stock en la base de datos.");
        } else {
            stocks.forEach(System.out::println);
        }
    }

    public void updateStock() {
        System.out.println("ACTUALIZACIÓN DE STOCK");
        listAllStock();

        System.out.print("Ingrese el ID del stock a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Stock stock = stockDAO.getStockById(id);
        if (stock == null) {
            System.out.println("Registro de stock no encontrado.");
            return;
        }

        System.out.print("Nueva cantidad disponible (" + stock.getRemain() + "): ");
        String newRemainInput = scanner.nextLine();
        if (!newRemainInput.isEmpty()) {
            try {
                int newRemain = Integer.parseInt(newRemainInput);
                stock.setRemain(newRemain);
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
                return;
            }
        }

        System.out.print("Nuevo ID del producto (" + stock.getProduct_id() + "): ");
        String newProductIdInput = scanner.nextLine();
        if (!newProductIdInput.isEmpty()) {
            try {
                int newProductId = Integer.parseInt(newProductIdInput);
                stock.setProduct_id(newProductId);
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número válido.");
                return;
            }
        }

        if (stockDAO.updateStock(stock)) {
            System.out.println("Stock actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar el stock.");
        }
    }

    public void deleteStock() {
        System.out.println("ELIMINAR REGISTRO DE STOCK");
        listAllStock();

        System.out.print("Ingrese el ID del stock que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro de que desea eliminar este stock? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (stockDAO.deleteStock(id)) {
                System.out.println("Stock eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el stock o ID no encontrado.");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
