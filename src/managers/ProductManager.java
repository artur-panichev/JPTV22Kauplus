package managers;

import entity.Customer;
import entity.Product;
import entity.SoldHistory;
import tools.InputFromKeyboard;
import tools.IsTrueTask;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductManager {
    private PersistToDatabase  persistToDatabase = new PersistToDatabase();
    
    public Product addProduct(Scanner scanner){
        System.out.println("\n-------- Add new product --------\n");

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product price: ");
        int price = scanner.nextInt(); scanner.nextLine();

        System.out.print("Enter product count: ");
        int count = scanner.nextInt(); scanner.nextLine();

        Product product = new Product(name, price, count);

        persistToDatabase.saveProduct(product);
        
        return product;
    }
    public void printListProducts(List<Product> products){
        IntStream.range(0, products.size()).forEach(i -> {
            Product product = products.get(i);
            System.out.printf("%d. %s $%d %dpcs. - %d sells\n", i+1, product.getName(), product.getPrice(), product.getCount(), product.getBoughtTimes());
        });
    }
    public SoldHistory buyProduct(List<Product> products, List<Customer> customers, Scanner scanner){
        System.out.println("\n-------- Buy product --------\n");
        CustomerManager customerManager = new CustomerManager();
        System.out.println("0. Return");

        System.out.println("Customers list:");
        customerManager.printListCustomers(customers);
        System.out.print("Select customer: ");
        int customerNum = InputFromKeyboard.inputFromRange(0, customers.size(), scanner);
        if(customerNum == 0) return null;

        System.out.println("Products list:");
        this.printListProducts(products);
        System.out.print("Select product: ");
        int productNum = InputFromKeyboard.inputFromRange(0, products.size(), scanner);
        if(productNum == 0) return null;

        Customer customer = customers.get(customerNum-1);
        Product product = products.get(productNum-1);
        if(!(product.getCount() > 0)){
            System.out.println("Товара нет в наличии!");
            return null;
        }
        if(customer.getBalance() >= product.getPrice()){
            customer.setBalance(customer.getBalance()-product.getPrice());
            product.setCount(product.getCount()-1);
            customer.setProductsPurchanced(customer.getProductsPurchanced()+1);
            product.setBoughtTimes(product.getBoughtTimes()+1);

            List<Product> customerProducts = customer.getBoughtProducts();
            customerProducts.add(product);
            customer.setBoughtProducts(customerProducts);
            
            persistToDatabase.updateCustomer(customer);
            persistToDatabase.updateProduct(product);
            
            return new SoldHistory(product, product.getPrice(), customer, LocalDateTime.now());
        } else{
            System.out.println("Not enough money!");
        }
        return null;
    }
    public void printProductsRating(List<SoldHistory> soldHistories, Scanner scanner){
        System.out.println("Products rating:");
        System.out.println("Select period of rating:");
        System.out.println("1. Day");
        System.out.println("2. Month");
        System.out.println("3. Year");
        System.out.print("Enter number of period: ");
        int period = InputFromKeyboard.inputFromRange(1, 3, scanner);

        HashMap<Product, Integer> ratingProducts = new HashMap<>();
        LocalDateTime minDate;
        switch (period){
            case 1:
                minDate = LocalDateTime.now().minusDays(1);
                break;
            case 2:
                minDate = LocalDateTime.now().minusMonths(1);
                break;
            case 3:
                minDate = LocalDateTime.now().minusYears(1);
                break;
            default:
                minDate = LocalDateTime.now();
        }
        soldHistories.forEach(soldHistory -> {
            if(soldHistory.getDate().isAfter(minDate)){
                if(ratingProducts.containsKey(soldHistory.getProduct())){
                    ratingProducts.put(soldHistory.getProduct(), ratingProducts.get(soldHistory.getProduct()) + 1);
                } else{
                    ratingProducts.put(soldHistory.getProduct(), 1);
                }
            }
        });
        List<Product> sortedProducts = ratingProducts.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if(sortedProducts.size() == 0){
            System.out.println("No rating for this period");
            return;
        }
        printListProducts(sortedProducts);
    }

    public void editProduct(List<Product> products, Scanner scanner){
        System.out.println("\n-------- Product edit --------\n");

        System.out.println("Products list:");
        this.printListProducts(products);
        System.out.print("Select product: ");
        int productNum = InputFromKeyboard.inputFromRange(1, products.size(), scanner);
        Product product = products.get(productNum-1);
        System.out.println("Select what you want to edit:");
        System.out.println("0. Return");
        System.out.println("1. Name");
        System.out.println("2. Price");
        System.out.println("3. Count");
        System.out.println("4. Sells");
        boolean repeat = true;
        do{
            int task = InputFromKeyboard.inputFromRange(0, 4, scanner);

            switch (task){
                case 0:
                    if(!IsTrueTask.checkTask("0. Return", scanner)) break;
                    return;
                case 1:
                    if(!IsTrueTask.checkTask("1. Name", scanner)) break; repeat = false;
                    System.out.print("Enter new name: ");
                    String name = scanner.nextLine();
                    product.setName(name);
                    break;
                case 2:
                    if(!IsTrueTask.checkTask("2. Price", scanner)) break; repeat = false;
                    System.out.print("Enter new price: ");
                    int price = scanner.nextInt(); scanner.nextLine();
                    product.setPrice(price);
                    break;
                case 3:
                    if(!IsTrueTask.checkTask("3. Count", scanner)) break; repeat = false;
                    System.out.print("Enter new count: ");
                    int count = scanner.nextInt(); scanner.nextLine();
                    product.setCount(count);
                    break;
                case 4:
                    if(!IsTrueTask.checkTask("4. Sells", scanner)) break; repeat = false;
                    System.out.print("Enter new sells: ");
                    int sells = scanner.nextInt(); scanner.nextLine();
                    product.setBoughtTimes(sells);
                    break;
            }
        } while (repeat);

    }
}
