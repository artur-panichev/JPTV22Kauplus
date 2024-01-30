package jptv22kauplus;
import entity.Customer;
import entity.Discount;
import entity.Product;
import entity.SoldHistory;
import managers.CustomerManager;
import managers.DiscountManager;
import managers.ProductManager;
import tools.InputFromKeyboard;
import tools.IsTrueTask;

import java.util.List;
import java.util.Scanner;
import managers.PersistToDatabase;

public class App {
    private final Scanner scanner;
    private PersistToDatabase persistToDatabase;
    private ProductManager productManager;
    private CustomerManager customerManager;
    private DiscountManager discountManager;
    private List<Product> products;
    private List<Customer> customers;
    private List<Discount> discounts;
    private List<SoldHistory> soldHistories;

    public App() {
        this.scanner = new Scanner(System.in);
        this.persistToDatabase = new PersistToDatabase();
        this.productManager = new ProductManager();
        this.discountManager = new DiscountManager();
        this.customerManager = new CustomerManager();
        this.products = this.persistToDatabase.loadProducts();
        this.customers = this.persistToDatabase.loadCustomers();
        this.discounts = this.persistToDatabase.loadDiscounts();
        this.soldHistories = this.persistToDatabase.loadSoldHistories();
    }

    void run() throws Exception {
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
            System.out.println("13. Add discount");
            System.out.println("14. Time before next discount");

            System.out.print("Enter task number: ");
            int task = InputFromKeyboard.inputFromRange(0, 14, this.scanner);

            switch (task){
                case 0:
                    if(!IsTrueTask.checkTask("0. Exit", scanner)) break;
                    repeat = false;
                    break;
                case 1:
                    if(!IsTrueTask.checkTask("1. Add product", scanner)) break;
                    products.add(this.productManager.addProduct(this.scanner));
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
                    break;
                case 4:
                    if(!IsTrueTask.checkTask("4. List customers", scanner)) break;
                    System.out.println("\n-------- List customers --------\n");
                    this.customerManager.printListCustomers(this.customers);
                    System.out.println("\n");
                    break;
                case 5:
                    if(!IsTrueTask.checkTask("5. Sell product to customer", scanner)) break;
                    SoldHistory history = this.productManager.buyProduct(this.products, this.customers, this.scanner);
                    if(history != null){
                        this.soldHistories.add(history);
                        this.persistToDatabase.saveSoldHistory(history);
                    }

                    break;
                case 6:
                    if(!IsTrueTask.checkTask("6. Total sold price", this.scanner)) break;
                    System.out.println("Total products sold price: " /*вызов функции оборота*/);
                    break;
                case 7:
                    if(!IsTrueTask.checkTask("7. Add money to customer", this.scanner)) break;
                    this.customerManager.addMoneyToCustomer(this.customers, this.scanner);
                    break;
                case 8:
                    if(!IsTrueTask.checkTask("8. Rating customers", this.scanner)) break;
                    System.out.println("\n-------- Customers rating --------\n");
                    this.customerManager.printCustomersRating(this.soldHistories, this.scanner);
                    System.out.println("\n");
                    break;
                case 9:
                    if(!IsTrueTask.checkTask("9. Rating products", this.scanner)) break;
                    System.out.println("\n-------- Products rating --------\n");
                    this.productManager.printProductsRating(this.soldHistories, this.scanner);
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
                case 13:
                    if(!IsTrueTask.checkTask("13. Add discount", scanner)) break;
                    discounts.add(this.discountManager.addDiscount(this.scanner));
                    break;
                case 14:
                    if(!IsTrueTask.checkTask("14. Time before next discount", scanner)) break;
                    System.out.println(discountManager.nextDiscount(discounts));
                    break;
            }
        } while(repeat);
    }
}
