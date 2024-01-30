/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managers;

import entity.Customer;
import entity.Discount;
import entity.Product;
import entity.SoldHistory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author kortex
 */
public class PersistToDatabase implements Retentive{
    
    private final EntityManager em;
    private final EntityTransaction tx;
    
    public PersistToDatabase() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jptv22shopPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @Override
    public void saveCustomer(Customer customer) {
        tx.begin();
        em.persist(customer);
        tx.commit();
    }

    @Override
    public List<Customer> loadCustomers() {
        try {
            return em.createQuery("SELECT u FROM Customer u").getResultList();
        } catch (Exception e) {
            return new ArrayList<Customer>();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        tx.begin();
        em.merge(customer);
        tx.commit();
    }

    @Override
    public void saveDiscount(Discount discount) {
        tx.begin();
        em.persist(discount);
        tx.commit();
    }

    @Override
    public List<Discount> loadDiscounts() {
        try {
            return em.createQuery("SELECT u FROM Discount u").getResultList();
        } catch (Exception e) {
            return new ArrayList<Discount>();
        }
    }

    @Override
    public void updateDiscount(Discount discount) {
        tx.begin();
        em.merge(discount);
        tx.commit();
    }

    @Override
    public void saveProduct(Product product) {
        tx.begin();
        em.persist(product);
        tx.commit();
    }

    @Override
    public List<Product> loadProducts() {
        try {
            return em.createQuery("SELECT u FROM Product u").getResultList();
        } catch (Exception e) {
            return new ArrayList<Product>();
        }
    }

    @Override
    public void updateProduct(Product product) {
        tx.begin();
        em.merge(product);
        tx.commit();
    }

    @Override
    public void saveSoldHistory(SoldHistory soldHistory) {
        tx.begin();
        em.persist(soldHistory);
        tx.commit();
    }

    @Override
    public List<SoldHistory> loadSoldHistories() {
        try {
            return em.createQuery("SELECT u FROM SoldHistory u").getResultList();
        } catch (Exception e) {
            return new ArrayList<SoldHistory>();
        }
    }

    @Override
    public void updateSoldHistory(SoldHistory soldHistory) {
        tx.begin();
        em.merge(soldHistory);
        tx.commit();
    }
    
    @Override
    public void freeResources() {
        if(em != null) em.close();
    }
}
