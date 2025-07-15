package SERVICE;

import DAO.UserDAO;
import MODEL.User;
import java.util.*;

public class UserService {

    private UserDAO userDAO;
    private Scanner scanner;

    public  UserService(){
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);  //lee desde la entrada estandar (teclado)
    }

    public  void createUser(){               //se usa void por que esto no devuelve ningun valor XD
        System.out.println("CREAR NUEVO USUARIO");

        System.out.println("Ingrese los nombres: ");
        String names = scanner.nextLine();  //lee la linea ingresada por el usuario

        System.out.println("Ingrese los Apellidos");
        String last_names = scanner.nextLine();

        int document_Number;
        try {
            System.out.print("Ingrese el documento: ");
            document_Number = scanner.nextInt();
            scanner.nextLine();
        }catch (InputMismatchException e){
            System.out.print("Error: el documento debe tener solo numeros");
            scanner.nextLine();
            return;
        }

        int role_id;
        try {
            System.out.print("Ingrese el rol del usuario: ");
            role_id = scanner.nextInt();    //lee un numero entero
            scanner.nextLine();   //limpia el buffer
        }catch (InputMismatchException e){     //se usa en caso de que la persona meta letras
            System.out.print("Error: el rol debe ser en numeros");
            scanner.nextLine();
            return;
        }

        User newUser = new User(0, names, last_names, document_Number, role_id);     //Crea el ibjeto User con sus parametros

        if (userDAO.createUser(newUser)){
            System.out.println("El usuario se creo de manera correcta con el ID: " + newUser.getId());
        }else {
            System.out.println("Error al momento de crear el usuario");
        }
    }

    public void listAllUsers() {
        System.out.println("LISTA DE USUARIOS");
        List<User> users = userDAO.getAllUsers();  // se usa una lista que puede estar vacia y con el objeto userDAO recuperamos los usuarios

        if (users.isEmpty()) {    //el "isEmpty" se usa para ver si la lista esta vacia jsjsjs
            System.out.println("No hay usuarios en la Base de Datos");
        }else{
            users.forEach(System.out::println);  //muestra los datos con un bucle
        }
    }
}
