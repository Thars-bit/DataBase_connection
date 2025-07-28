package MODEL;

public class Suppliers {

    private int id;
    private String name;
    private int nit;
    private String email;
    private int phone;
    private int status;


    public Suppliers(int id,String name,int nit,String email,int phone,int status){
        this.id = id;
        this.name = name;
        this.nit = nit;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return "ID: "+id+", Nombre: "+name+", Nit: "+nit+", Email: "+email+", Telefono: "+phone+", Status: "+status;
    }
}
