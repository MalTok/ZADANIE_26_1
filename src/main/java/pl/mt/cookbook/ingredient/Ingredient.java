package pl.mt.cookbook.ingredient;

import jakarta.persistence.*;
import pl.mt.cookbook.IngredientAmount;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "ingredient")
    private List<IngredientAmount> ingredientAmounts = new ArrayList<>();

    public Ingredient() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientAmount> getIngredientAmounts() {
        return ingredientAmounts;
    }

    public void setIngredientAmounts(List<IngredientAmount> ingredientAmounts) {
        this.ingredientAmounts = ingredientAmounts;
    }

    public void addIngredientAmount(IngredientAmount ingredientAmount) {
        ingredientAmounts.add(ingredientAmount);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}