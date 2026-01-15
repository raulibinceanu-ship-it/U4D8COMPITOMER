import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // PRODOTTI
        Product p1 = new Product(1L, "Java Book", "Books", 120.0);
        Product p2 = new Product(2L, "Toy", "Baby", 30.0);
        Product p3 = new Product(3L, "T-shirt", "Boys", 50.0);
        Product p4 = new Product(4L, "Laptop", "Tech", 900.0);

        // CLIENTI
        Customer c1 = new Customer(1L, "Mario", 1);
        Customer c2 = new Customer(2L, "Luca", 2);

        // ORDINI
        Order o1 = new Order(
                1L,
                "DELIVERED",
                LocalDate.of(2021, 2, 10),
                LocalDate.of(2021, 2, 15),
                Arrays.asList(p1, p2),
                c1
        );

        Order o2 = new Order(
                2L,
                "DELIVERED",
                LocalDate.of(2021, 3, 5),
                LocalDate.of(2021, 3, 10),
                Arrays.asList(p3, p4),
                c2
        );

        Order o3 = new Order(
                3L,
                "DELIVERED",
                LocalDate.of(2021, 3, 20),
                LocalDate.of(2021, 3, 25),
                Arrays.asList(p1, p4),
                c1
        );

        List<Order> orders = Arrays.asList(o1, o2, o3);
        List<Product> products = Arrays.asList(p1, p2, p3, p4);

        // ESERCIZIO 1
// Ordini raggruppati per cliente

        System.out.println("ESERCIZIO 1");

        Map<Customer, List<Order>> ordiniPerCliente =
                orders.stream()
                        .collect(Collectors.groupingBy(Order::getCustomer));

        ordiniPerCliente.forEach((cliente, listaOrdini) ->
                System.out.println(cliente.getName() + " -> " + listaOrdini.size())
        );

        // ESERCIZIO 2
        // Totale vendite per cliente

        System.out.println("\nESERCIZIO 2");

        Map<Customer, Double> totalePerCliente =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCustomer,
                                Collectors.summingDouble(
                                        o -> o.getProducts().stream()
                                                .mapToDouble(Product::getPrice)
                                                .sum()
                                )
                        ));

        totalePerCliente.forEach((cliente, totale) ->
                System.out.println(cliente.getName() + " -> " + totale + "€")
        );

        // ESERCIZIO 3
        // Prodotti più costosi

        System.out.println("\nESERCIZIO 3");

        double prezzoMax = products.stream()
                .mapToDouble(Product::getPrice)
                .max()
                .orElse(0);

        List<Product> prodottiPiuCostosi = products.stream()
                .filter(p -> p.getPrice() == prezzoMax)
                .collect(Collectors.toList());

        prodottiPiuCostosi.forEach(System.out::println);

// ESERCIZIO 4
        // Media importi degli ordini

        System.out.println("\nESERCIZIO 4");

        double mediaOrdini = orders.stream()
                .mapToDouble(o ->
                        o.getProducts().stream()
                                .mapToDouble(Product::getPrice)
                                .sum()
                )
                .average()
                .orElse(0);

        System.out.println("Media importi ordini: " + mediaOrdini + "€");

        // ESERCIZIO 5
        // Somma importi per categoria

        System.out.println("\nESERCIZIO 5");

        Map<String, Double> totalePerCategoria =
                products.stream()
                        .collect(Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.summingDouble(Product::getPrice)
                        ));

        totalePerCategoria.forEach((categoria, totale) ->
                System.out.println(categoria + " -> " + totale + "€")
        );
    }
}