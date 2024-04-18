package models;

import javax.persistence.*;

@Entity(name = "products")
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private boolean status;
    private String cate;

    public Product() {
    }

    public Product(Long id, String name, double price, boolean status, String cate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.cate = cate;
    }

    public Product(String name, double price, boolean status, String cate) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.cate = cate;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }
    
    
}
