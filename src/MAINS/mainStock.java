package MAINS;

import SERVICE.StockService;
import java.util.*;

public class mainStock {

    public void menuStock() {
        StockService stockService = new StockService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    stockService.createStock();
                    break;
                case 2:
                    stockService.listAllStock();
                    break;
                case 3:
                    stockService.updateStock();
                    break;
                case 4:
                    stockService.deleteStock();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        stockService.close();
        scanner.close();
        System.out.println("Gracias y adiosssss");
    }

    private static void printMenu() {
        System.out.println("\n--- SISTEMA DE GESTIÓN DE STOCK ---");
        System.out.println("1. Crear entrada de stock");
        System.out.println("2. Listar stock");
        System.out.println("3. Actualizar stock");
        System.out.println("4. Eliminar entrada de stock");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
