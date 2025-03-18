package models;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id; // Identificador único del usuario
    private String dni; // DNI del usuario
    private String name; // Nombre del usuario
    private List<Order> orders;

    public User(String id, String dni, String name) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.orders = new ArrayList<>();
    }

    // Obtener el ID del usuario
    public String getId() {
        return id;
    }

    // Obtener el DNI del usuario
    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    // Obtener la lista de pedidos del usuario
    public List<Order> getOrders() {
        return orders;
    }


}

