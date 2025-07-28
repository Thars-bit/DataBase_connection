
import MAINS.mainUsers;
import MAINS.mainRole;

import java.util.Scanner;

public class mainDef {
    public static void main(String[] args){
        mainRole mainrole = new mainRole();
        mainUsers mainusers = new mainUsers();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit){
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option){
                case 1:
                    mainusers.menuUsuario();
                    break;
                case 2:
                    mainrole.menuRol();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }


        scanner.close();
        System.out.println("Gracias y adiosssss");
    }

    private static void printMenu(){
        System.out.println("\n--- SISTEMA DE GESTIÓN DE DATOS ---");
        System.out.println("1. Usuarios");
        System.out.println("2. Roles");

    }
}
