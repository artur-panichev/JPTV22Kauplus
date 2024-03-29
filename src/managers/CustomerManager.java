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

public class CustomerManager {
    private PersistToDatabase  persistToDatabase = new PersistToDatabase();
    
    public Customer addCustomer(Scanner scanner){
        System.out.println("\n-------- Add new customer --------\n");

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        Customer customer = new Customer(name, surname);
        
        persistToDatabase.saveCustomer(customer);

        return customer;
    }
    public void printListCustomers(List<Customer> customers){
        IntStream.range(0, customers.size()).forEach(i -> {
            Customer customer = customers.get(i);
            System.out.printf("%d. %s %s $%d - %d purchances\n", i+1, customer.getName(), customer.getSurname(), customer.getBalance(), customer.getProductsPurchanced());
        });
    }
    public void addMoneyToCustomer(List<Customer> customers, Scanner scanner){
        System.out.println("\n-------- Add money to customer --------\n");

        this.printListCustomers(customers);
        System.out.print("Select customer: ");
        int customerNum = InputFromKeyboard.inputFromRange(1, customers.size(), scanner);

        System.out.print("Select money count: ");
        int money = scanner.nextInt(); scanner.nextLine();

        customers.get(customerNum-1).setBalance(customers.get(customerNum-1).getBalance()+money);
        
        persistToDatabase.updateCustomer(customers.get(customerNum-1));
    }
    public void printCustomersRating(List<SoldHistory> soldHistories, Scanner scanner){
        System.out.println("Customers rating:");
        System.out.println("Select period of rating:");
        System.out.println("1. Day");
        System.out.println("2. Month");
        System.out.println("3. Year");
        System.out.print("Enter number of period: ");
        int period = InputFromKeyboard.inputFromRange(1, 3, scanner);

        HashMap<Customer, Integer> ratingCustomers = new HashMap<>();
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
                if(ratingCustomers.containsKey(soldHistory.getCustomer())){
                    ratingCustomers.put(soldHistory.getCustomer(), ratingCustomers.get(soldHistory.getCustomer()) + 1);
                } else{
                    ratingCustomers.put(soldHistory.getCustomer(), 1);
                }
            }
        });

        List<Customer> sortedCustomers = ratingCustomers.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if(sortedCustomers.size() == 0){
            System.out.println("No rating for this period");
            return;
        }
        IntStream.range(0, sortedCustomers.size()).forEach(i -> {
            Customer customer = sortedCustomers.get(i);
            System.out.printf("%d. %s %s $%d - %d purchances\n", i+1, customer.getName(), customer.getSurname(), customer.getBalance(), ratingCustomers.get(customer));
        });
    }
    public void editCustomer(List<Customer> customers, Scanner scanner){
        System.out.println("\n-------- Customer edit --------\n");

        System.out.println("Customers list:");
        this.printListCustomers(customers);
        System.out.print("Select customer: ");
        int customerNum = InputFromKeyboard.inputFromRange(1, customers.size(), scanner);
        Customer customer = customers.get(customerNum-1);
        System.out.println("Select what you want to edit:");
        System.out.println("0. Return");
        System.out.println("1. Name");
        System.out.println("2. Surname");
        System.out.println("3. Balance");
        System.out.println("4. Products purchanced");
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
                    customer.setName(name);
                    break;
                case 2:
                    if(!IsTrueTask.checkTask("2. Surname", scanner)) break; repeat = false;
                    System.out.print("Enter new surname: ");
                    String surname = scanner.nextLine();
                    customer.setSurname(surname);
                    break;
                case 3:
                    if(!IsTrueTask.checkTask("3. Balance", scanner)) break; repeat = false;
                    System.out.print("Enter new balance: ");
                    int balance = scanner.nextInt(); scanner.nextLine();
                    customer.setBalance(balance);
                    break;
                case 4:
                    if(!IsTrueTask.checkTask("4. Products purchanced", scanner)) break; repeat = false;
                    System.out.print("Enter new purchanced products count: ");
                    int purchances = scanner.nextInt(); scanner.nextLine();
                    customer.setProductsPurchanced(purchances);
                    break;
            }
        } while (repeat);

    }
    public void listProductBought(List<Customer> customers, Scanner scanner, ProductManager productManager){
        System.out.println("\n-------- List products customer bought --------\n");

        System.out.println("Customers list:");
        this.printListCustomers(customers);
        System.out.print("Select customer: ");
        int customerNum = InputFromKeyboard.inputFromRange(1, customers.size(), scanner);
        Customer customer = customers.get(customerNum-1);

        productManager.printListProducts(customer.getBoughtProducts());
    }
}
