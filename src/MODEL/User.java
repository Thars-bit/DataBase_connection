package MODEL;

public class User {

    //atributos
    private int id;
    private String names;
    private String last_names;
    private int document_number;
    private int role_id;


    //contructor
    public User(int id, String names, String last_names, int document_number, int role_id) {
        this.id = id;
        this.names = names;
        this.last_names = last_names;
        this.document_number = document_number;
        this.role_id = role_id;
    }


    //getter y setter

    //id
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //name

    public String getNames(){
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    //last_names

    public String getLast_names(){
        return last_names;
    }

    public void setLast_names(String last_names) {
        this.last_names = last_names;
    }

    //document_number

    public int getDocument_number() {
        return document_number;
    }

    public void setDocument_number(int document_number) {
        this.document_number = document_number;
    }

    //role_id

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "ID: " +id+ ", Nombre: "+names+", Apellidos: "+last_names+", Numero de documento: "+document_number+", Rol: "+role_id;
    }


}
