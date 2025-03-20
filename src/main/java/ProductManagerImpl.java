import models.Order;
import models.Product;
import models.User;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
    private static ProductManagerImpl instance;
    private List<Product> productList;
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;


    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    public static ProductManagerImpl getInstance() {
        if (instance == null) {
            instance = new ProductManagerImpl(); // Crear la instancia si no existe
        }
        return instance; // Devolver siempre la misma instancia
    }
    @Override
    public void addProduct(String id, String name, double price) {
        productList.add(new Product(id, name, price));

    }

    @Override
    public List<Product> getProductsByPrice() {

        productList.sort(Comparator.comparingDouble(Product::getPrice).reversed()); // Ordenar por precio descendente
        return new ArrayList<>(productList);
    }

    @Override
    public void addOrder(Order order) {
        if (!users.containsKey(order.getUser())) {
            throw new IllegalArgumentException("User not found: " + order.getUser());
        }
        orderQueue.add(order);

        User user = users.get(order.getUser());
        user.getOrders().add(order);

    }

    @Override
    public int numOrders() {

        return orderQueue.size();
    }

    @Override
    public Order deliverOrder() {

        if (orderQueue.isEmpty()) {
            return null; // No hay pedidos para servir
        }
        Order order = orderQueue.poll();// Obtener y eliminar el siguiente pedido de la cola

        //Procesar productos del pedido
        for (Map.Entry<String, Integer> entry : order.getProducts().entrySet()) {
            String productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = getProduct(productId);
            if (product !=null) {
                product.incrementSales(quantity); // Incrementar las ventas según la cantidad
            }
        }

        return order;
    }

    @Override
    public Product getProduct(String c1) {
        for (Product product : productList) {
            if (product.getId().equals(c1)) {
                return product;
            }
        }
        return null; // Producto no encontrado
    }

    @Override
    public User getUser(String number) {

        return users.get(number);
    }

    @Override
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public List<Order> getUserOrders(String userId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        List<Order> userOrders = new ArrayList<>();
        for (Order order : orderQueue) {
            if (order.getUser().equals(userId)) {
                userOrders.add(order);
            }
        }
        return userOrders;
    }
    public List<Product> getProductsBySales() {
        productList.sort((p1, p2) -> Integer.compare(p2.getSales(), p1.getSales())); // Ordenar por ventas descendente
        return new ArrayList<>(productList); // Devuelve una copia para evitar modificaciones externas
    }

}
