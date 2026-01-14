import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        // PRODOTTI
        Product p1 = new Product(1L, "Java Book", "Books", 120.0);
        Product p2 = new Product(2L, "Baby Toy", "Baby", 30.0);
        Product p3 = new Product(3L, "Shirt", "Boys", 50.0);
        Product p4 = new Product(4L, "Advanced Book", "Books", 200.0);

        // CLIENTI
        Customer c1 = new Customer(1L, "Mario", 1);
        Customer c2 = new Customer(2L, "Luca", 2);

        // ORDINI
        Order o1 = new Order(
                1L,
                "DELIVERED",
                LocalDate.of(2021, 2, 15),
                LocalDate.of(2021, 2, 20),
                Arrays.asList(p1, p2),
                c2
        );

        Order o2 = new Order(
                2L,
                "DELIVERED",
                LocalDate.of(2021, 3, 10),
                LocalDate.of(2021, 3, 15),
                Arrays.asList(p3, p4),
                c1
        );

        List<Product> products = Arrays.asList(p1, p2, p3, p4);
        List<Order> orders = Arrays.asList(o1, o2);

        // ESERCIZIO 1
        // Books con prezzo > 100

        System.out.println("ESERCIZIO 1");

        List<Product> booksCostosi = products.stream()
                .filter(p -> p.getCategory().equals("Books"))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());

        booksCostosi.forEach(System.out::println);

        // ESERCIZIO 2
        // Ordini con prodotti Baby

        System.out.println("\nESERCIZIO 2");

        List<Order> ordiniBaby = orders.stream()
                .filter(o -> o.getProducts().stream()
                        .anyMatch(p -> p.getCategory().equals("Baby")))
                .collect(Collectors.toList());

        System.out.println("Ordini con prodotti Baby: " + ordiniBaby.size());

        // ESERCIZIO 3
        // Boys con sconto 10%

        System.out.println("\nESERCIZIO 3");

        List<Product> boysScontati = products.stream()
                .filter(p -> p.getCategory().equals("Boys"))
                .map(p -> {
                    p.setPrice(p.getPrice() * 0.9);
                    return p;
                })
                .collect(Collectors.toList());

        boysScontati.forEach(System.out::println);

        // ESERCIZIO 4
        // Prodotti ordinati da tier 2
        // tra 01-02-2021 e 01-04-2021

        System.out.println("\nESERCIZIO 4");

        List<Product> prodottiTier2 = orders.stream()
                .filter(o -> o.getCustomer().getTier() == 2)
                .filter(o -> !o.getOrderDate().isBefore(LocalDate.of(2021, 2, 1)))
                .filter(o -> !o.getOrderDate().isAfter(LocalDate.of(2021, 4, 1)))
                .flatMap(o -> o.getProducts().stream())
                .collect(Collectors.toList());

        prodottiTier2.forEach(System.out::println);
    }
}