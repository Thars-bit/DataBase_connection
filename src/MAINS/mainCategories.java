package MAINS;

import SERVICE.CategoriesService;
import java.util.Scanner;

public class mainCategories {

    public void menuCategorias() {
        CategoriesService categoriesService = new CategoriesService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    categoriesService.createCategory();
                    break;
                case 2:
                    categoriesService.listAllCategories();
                    break;
                case 3:
                    categoriesService.updateCategory();
                    break;
                case 4:
                    categoriesService.deleteCategory();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        categoriesService.close();
        scanner.close();
        System.out.println("Gracias y adiós.");
    }

    private static void printMenu() {
        System.out.println("\n--- SISTEMA DE GESTIÓN DE CATEGORÍAS ---");
        System.out.println("1. Crear categoría");
        System.out.println("2. Listar categorías");
        System.out.println("3. Actualizar categoría");
        System.out.println("4. Eliminar categoría");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
