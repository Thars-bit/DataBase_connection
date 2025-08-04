package MODEL;

public class Stock {

    private int id;
    private int remain;
    private int product_id;

    public Stock (int id,int remain,int product_id){
        this.id = id;
        this.remain = remain;
        this.product_id = product_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    @Override

    public String toString(){
        return "ID: "+id+", Unidades disponibles: "+remain+", Id del producto: "+product_id;
    }
}
