package SERVICE;

import DAO.CategoriesDAO;
import MODEL.Categories;
import java.util.*;

public class CategoriesService {

    private CategoriesDAO categoriesDAO;
    private Scanner scanner;

    public CategoriesService() {
        this.categoriesDAO = new CategoriesDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createCategory() {
        System.out.println("CREAR NUEVA CATEGORÍA");

        System.out.print("Ingrese el nombre de la categoría: ");
        String name = scanner.nextLine();

        Categories newCategory = new Categories(0, name);

        if (categoriesDAO.createCategory(newCategory)) {
            System.out.println("Categoría creada con ID: " + newCategory.getId());
        } else {
            System.out.println("Error al crear la categoría.");
        }
    }

    public void listAllCategories() {
        System.out.println("LISTA DE CATEGORÍAS");
        List<Categories> categories = categoriesDAO.getAllCategories();

        if (categories.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            categories.forEach(System.out::println);
        }
    }

    public void updateCategory() {
        System.out.println("ACTUALIZACIÓN DE CATEGORÍA");
        listAllCategories();

        System.out.print("Ingrese el ID de la categoría a actualizar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Categories category = categoriesDAO.getCategoryById(id);
        if (category == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        System.out.print("Nuevo nombre de la categoría (" + category.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            category.setName(newName);
        }

        if (categoriesDAO.updateCategory(category)) {
            System.out.println("Categoría actualizada exitosamente.");
        } else {
            System.out.println("Error al actualizar la categoría.");
        }
    }

    public void deleteCategory() {
        System.out.println("ELIMINAR CATEGORÍA");
        listAllCategories();

        System.out.print("Ingrese el ID de la categoría que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("¿Está seguro que desea eliminar esta categoría? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")) {
            if (categoriesDAO.deleteCategory(id)) {
                System.out.println("Categoría eliminada exitosamente.");
            } else {
                System.out.println("Error al eliminar la categoría o no se encontró.");
            }
        }
    }

    public void close() {
        scanner.close();
    }
}
