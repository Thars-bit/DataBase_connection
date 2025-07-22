package SERVICE;

import DAO.RoleDAO;
import MODEL.Role;
import java.util.*;

public class RoleService {

    private RoleDAO roleDAO;
    private Scanner scanner;

    public  RoleService(){
        this.roleDAO = new RoleDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createRole(){
        System.out.println("CREAR NUEVO ROL");

        System.out.println("Ingrese el nombre del rol");
        String names = scanner.nextLine();

        System.out.println("Ingrese la descripcion");
        String description = scanner.nextLine();


        Role newRole = new Role(0, names, description);

        if (roleDAO.createRole(newRole)){
            System.out.println("Rol creado correctamente con ID: " + newRole.getId());
        } else {
            System.out.println("Error al crear el rol");
        }
    }
}
