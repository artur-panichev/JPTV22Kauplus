/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package managers;

import entity.Customer;
import entity.Discount;
import entity.Product;
import entity.SoldHistory;
import java.util.List;

/**
 *
 * @author kortex
 */
public interface Retentive {
    public void saveCustomer(Customer customer);
    public List<Customer> loadCustomers();
    public void updateCustomer(Customer customer);
    
    public void saveDiscount(Discount discount);
    public List<Discount> loadDiscounts();
    public void updateDiscount(Discount discount);
    
    public void saveProduct(Product product);
    public List<Product> loadProducts();
    public void updateProduct(Product product);
    
    public void saveSoldHistory(SoldHistory soldHistory);
    public List<SoldHistory> loadSoldHistories();
    public void updateSoldHistory(SoldHistory soldHistory);
    
    public void freeResources();
}
