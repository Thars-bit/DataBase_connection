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

    public void listAllRoles(){
        System.out.println("Lista de roles");
        List<Role> roles = roleDAO.getAllRoles();

        if (roles.isEmpty()){
            System.out.println("No hay roles en la Base de Datos");
        }else {
            roles.forEach(System.out::println);
        }
    }

    public void updateRole(){
        System.out.println("ACTUALIZACION DE ROLES");
        listAllRoles();

        System.out.println("Ingrese el ID del rol que quiere modificar");
        int id = Integer.parseInt(scanner.nextLine());

        Role role = roleDAO.getRoleId(id);
        if (role == null){
            System.out.println("Rol no encontrado");
            return;
        }

        System.out.println("Nuevo nombre (" + role.getName() + "): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()){
            role.setName(newName);
        }

        System.out.println("Nueva descripcion (" + role.getDescription() + "): ");
        String newDesgription = scanner.nextLine();
        if (!newDesgription.isEmpty()){
            role.setDescription(newDesgription);
        }

        if (roleDAO.updateRole(role)){
            System.out.println("Rol actualizado correctamente");
        }else {
            System.out.println("error al alcualizar el rol");
        }
    }

    public void deleteRole(){
        System.out.println("ELIMINAR ROL");
        listAllRoles();

        System.out.println("Ingrese el ID del rol que quiere eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Esta seguro que quiere eliminar este rol? (s/n): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("s")){
            if (roleDAO.deleteRole(id)){
                System.out.println("Rol eliminado correctamente");
            }else {
                System.out.println("Error al eliminar el rol pedazo de retrasado");
            }
        }
    }

    public void close(){
        scanner.close();
    }
}
