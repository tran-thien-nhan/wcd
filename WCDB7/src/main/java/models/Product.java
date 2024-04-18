package models;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "products")
@Table(name = "products")

public class Product implements Serializable {
    //id la khoa chinh
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(Long id, String name, double price, Long categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = new Category();
        this.category.setId(categoryId);
    }

    public Product(String name, double price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.category = new Category();
        this.category.setId(categoryId);
    }

    public Product() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    
}
