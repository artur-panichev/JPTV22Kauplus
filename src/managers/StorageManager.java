package managers;

import entity.Customer;
import entity.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    public void saveProducts(List<Product> products){
        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = new FileOutputStream("products");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(products);
            oos.flush();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        }
    }
    public List<Product> loadProducts(){
        List<Product> products = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try{
            fis = new FileInputStream("products");
            ois = new ObjectInputStream(fis);
            products = (List<Product>) ois.readObject();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        } catch (ClassNotFoundException ex){
            System.out.println("Class not found");
        }
        return products;
    }

    public void saveCustomers(List<Customer> customers){
        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = new FileOutputStream("customers");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.flush();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        }
    }
    public List<Customer> loadCustomers(){
        List<Customer> customers = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try{
            fis = new FileInputStream("customers");
            ois = new ObjectInputStream(fis);
            customers = (List<Customer>) ois.readObject();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        } catch (ClassNotFoundException ex){
            System.out.println("Class not found");
        }
        return customers;
    }


    public void saveTotalSoldPrice(int totalSoldPrice){
        FileOutputStream fos;
        DataOutputStream dos;

        try{
            fos = new FileOutputStream("totalSoldPrice");
            dos = new DataOutputStream(fos);
            dos.writeInt(totalSoldPrice);
            dos.flush();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        }
    }
    public int loadTotalSoldPrice(){
        int totalSoldPrice = 0;
        FileInputStream fis;
        DataInputStream ois;
        try{
            fis = new FileInputStream("totalSoldPrice");
            ois = new DataInputStream(fis);
            totalSoldPrice = (int) ois.readInt();
        } catch (FileNotFoundException ex){
            System.out.println("File not found");
        } catch (IOException ex){
            System.out.println("I/O error");
        }
        return totalSoldPrice;
    }
}
