package jptv22kauplus;
import entity.Customer;
import entity.Product;
import managers.CustomerManager;
import managers.ProductManager;
import managers.StorageManager;
import tools.InputFromKeyboard;
import tools.IsTrueTask;

import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private StorageManager storageManager;
    private ProductManager productManager;
    private CustomerManager customerManager;
    private List<Product> products;
    private List<Customer> customers;
    private int totalSoldPrice;

    public App() {
        this.scanner = new Scanner(System.in);
        this.storageManager = new StorageManager();
        this.productManager = new ProductManager();
        this.customerManager = new CustomerManager();
        this.totalSoldPrice = storageManager.loadTotalSoldPrice();
        this.products = storageManager.loadProducts();
        this.customers = storageManager.loadCustomers();
    }

    void run(){
        boolean repeat = true;
        System.out.println("----------  Jewelry shop ----------");
        do{
            System.out.println("List tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add product");
            System.out.println("2. List products");
            System.out.println("3. Add customer");
            System.out.println("4. List customers");
            System.out.println("5. Sell product to customer");
            System.out.println("6. Total sold price");
            System.out.println("7. Add money to customer");
            System.out.println("8. Rating customers");
            System.out.println("9. Rating products");
            System.out.println("10. Edit product");
            System.out.println("11. Edit customer");
            System.out.println("12. List products customer bought");

            System.out.print("Enter task number: ");
            int task = InputFromKeyboard.inputFromRange(0, 12, this.scanner);

            switch (task){
                case 0:
                    if(!IsTrueTask.checkTask("0. Exit", scanner)) break;
                    repeat = false;
                    break;
                case 1:
                    if(!IsTrueTask.checkTask("1. Add product", scanner)) break;
                    products.add(this.productManager.addProduct(this.scanner));
                    this.storageManager.saveProducts(this.products);
                    break;
                case 2:
                    if(!IsTrueTask.checkTask("2. List products", scanner)) break;
                    System.out.println("\n-------- List products --------\n");
                    this.productManager.printListProducts(this.products);
                    System.out.println("\n");
                    break;
                case 3:
                    if(!IsTrueTask.checkTask("3. Add customer", scanner)) break;
                    customers.add(this.customerManager.addCustomer(this.scanner));
                    this.storageManager.saveCustomers(this.customers);
                    break;
                case 4:
                    if(!IsTrueTask.checkTask("4. List customers", scanner)) break;
                    System.out.println("\n-------- List customers --------\n");
                    this.customerManager.printListCustomers(this.customers);
                    System.out.println("\n");
                    break;
                case 5:
                    if(!IsTrueTask.checkTask("5. Sell product to customer", scanner)) break;
                    this.totalSoldPrice += this.productManager.buyProduct(this.products, this.customers, this.scanner);
                    this.storageManager.saveCustomers(this.customers);
                    this.storageManager.saveProducts(this.products);
                    this.storageManager.saveTotalSoldPrice(this.totalSoldPrice);
                    break;
                case 6:
                    if(!IsTrueTask.checkTask("6. Total sold price", this.scanner)) break;
                    System.out.println("Total products sold price: " + this.totalSoldPrice);
                    break;
                case 7:
                    if(!IsTrueTask.checkTask("7. Add money to customer", this.scanner)) break;
                    this.customerManager.addMoneyToCustomer(this.customers, this.scanner);
                    this.storageManager.saveCustomers(this.customers);
                    break;
                case 8:
                    if(!IsTrueTask.checkTask("8. Rating customers", this.scanner)) break;
                    System.out.println("\n-------- Customers rating --------\n");
                    this.customerManager.printCustomersRating(this.customers);
                    System.out.println("\n");
                    break;
                case 9:
                    if(!IsTrueTask.checkTask("9. Rating products", this.scanner)) break;
                    System.out.println("\n-------- Products rating --------\n");
                    this.productManager.printProductsRating(this.products);
                    System.out.println("\n");
                    break;
                case 10:
                    if(!IsTrueTask.checkTask("10. Edit product", this.scanner)) break;
                    this.productManager.editProduct(this.products, this.scanner);
                    break;
                case 11:
                    if(!IsTrueTask.checkTask("11. Edit customer", this.scanner)) break;
                    this.customerManager.editCustomer(this.customers, this.scanner);
                    break;
                case 12:
                    if(!IsTrueTask.checkTask("12. List products customer bought", this.scanner)) break;
                    this.customerManager.listProductBought(this.customers, this.scanner, this.productManager);
            }
        } while(repeat);
    }
}
