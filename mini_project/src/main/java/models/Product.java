package models;

import java.io.Serializable;
import javax.persistence.*;

@Entity(name = "products")
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;

    // 1 product belongs to 1 category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    //1 product belongs to 1 brand
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Product() {
    }

    public Product(Long id, String name, Double price, Long categoryId, Long brandId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = new Category();
        this.category.setId(categoryId);
        this.brand = new Brand();
        this.brand.setId(brandId);
    }

    public Product(String name, Double price, Long categoryId, Long brandId) {
        this.name = name;
        this.price = price;
        this.category = new Category();
        this.category.setId(categoryId);
        this.brand = new Brand();
        this.brand.setId(brandId);
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    
    
}
