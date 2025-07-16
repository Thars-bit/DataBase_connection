import SERVICE.UserService;
import java.util.*;


public class main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    userService.createUser();
                    break;
                case 2:
                    userService.listAllUsers();
                    break;
                case 3:
                    userService.updateUser();
                    break;
                case 4:
                    userService.deleteUser();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        userService.close();
        scanner.close();
        System.out.println("Gracias y adiosssss");

    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE USUARIOS ---");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Actualizar usuario");
        System.out.println("4. Eliminar usuario");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
}
