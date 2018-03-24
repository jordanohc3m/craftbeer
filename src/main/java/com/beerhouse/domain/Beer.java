package com.beerhouse.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by jordano on 24/03/2018.
 */

@Entity
@Table(name = "BEER")
public class Beer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "INGREDIENTS")
    private String ingredients;

    @Column(name = "ALCOHOLCONTENT")
    private String alcoholContent;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CATEGORY")
    private String category;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(String alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beer)) return false;

        Beer beer = (Beer) o;

        return getId().equals(beer.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
