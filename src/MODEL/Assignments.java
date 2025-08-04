package MODEL;

public class Assignments {

    private int id;
    private String date;
    private int quantity;
    private String ticket;
    private String serial;
    private int user_id;
    private int operation_id;
    private int product_id;

    public  Assignments(int id,String date,int quantity,String ticket,String serial,int user_id,int operation_id,int product_id){
        this.id = id;
        this.date =  date;
        this.quantity = quantity;
        this.ticket = ticket;
        this.serial = serial;
        this.user_id = user_id;
        this.operation_id = operation_id;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override

    public String toString(){
        return "ID: "+id+", fecha de asugnacion: "+date+". cantidad: "+quantity+", ticket: "+ticket+", serial: "+serial+", Id del usuario: "+user_id+", Id de la operacion: "+operation_id+", Id del producto "+product_id;
    }
}
