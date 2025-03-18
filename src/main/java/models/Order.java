package models;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private String dni; // Identificador del usuario que realiza el pedido
    private Map<String, Integer> products; // Mapa de productos (Product -> cantidad)


    public Order(String dni) {
        this.dni = dni;
        this.products = new HashMap<String, Integer>();
    }

    public String getUser() {

        return dni;
    }
    public Map<String, Integer> getProducts() {
        return products;
    }


    public void addProduct(int i, String product) {
        products.put(product, products.getOrDefault(product, 0) + i);
    }
}
