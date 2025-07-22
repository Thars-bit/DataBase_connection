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


}
