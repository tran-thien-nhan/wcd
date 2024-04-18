package models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity(name = "categories")
@Table(name = "categories")
public class Category implements Serializable {
    //id la khoa chinh
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    //list products
    @OneToMany(mappedBy = "category", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    //mappedBy: ten cua truong trong Product, cascade: xoa category thi xoa luon product,
    // all: tat ca cac truong,
    // MERGE: cap nhat, PERSIST: them moi, REMOVE: xoa, REFRESH: lam moi, DETACH: ngat ket noi
    // fetch: EAGER: lay het, LAZY: lay khi can thiet, lazy loading
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
