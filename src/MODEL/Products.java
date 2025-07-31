package MODEL;

public class Products {

    private int id;
    private String name;
    private String code;
    private int category_id;
    private int operation_id;

    public Products(int id,String name,String code,int category_id,int operation_id){

        this.id = id;
        this.name = name;
        this.code = code;
        this.category_id = category_id;
        this.operation_id = operation_id;

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

    public String code(){
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }

    @Override
    public String toString(){
        return "ID: "+id+", Nombre: "+name+", Codigo: "+code+", ID de la categoria: "+category_id+", ID de la operacion: "+operation_id;
    }
}
