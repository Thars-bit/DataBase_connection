public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // para MySQL
            System.out.println("Driver cargado correctamente.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: no se pudo cargar el driver JDBC.");
        }
    }
}
