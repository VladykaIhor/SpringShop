package com.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "product_cart",
            joinColumns = {@JoinColumn(name = "cart_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products;

    public Cart() {
    }

    public Cart(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getSizeOfCart(Cart cart) {
        return cart.getProducts().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
                Objects.equals(user, cart.user) &&
                Objects.equals(products, cart.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                '}';
    }
}
