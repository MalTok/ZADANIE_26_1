package pl.mt.cookbook.recipe;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mt.cookbook.IngredientAmount;
import pl.mt.cookbook.category.Category;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int portion;
    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<IngredientAmount> ingredients = new ArrayList<>();
    @Column(columnDefinition = "TEXT")
    private String preparation;
    private String hints;
    private String img;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAdded;
    private int likes;
    private boolean liked;
    @ManyToMany
    private List<Category> categories = new ArrayList<>();

    public Recipe() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPortion() {
        return portion;
    }

    public void setPortion(int portion) {
        this.portion = portion;
    }

    public List<IngredientAmount> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientAmount> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        categories.add(category);
        category.addRecipe(this);
    }

    public void addIngredientAmount(IngredientAmount ingredientAmount) {
        ingredients.add(ingredientAmount);
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", portion=" + portion +
                ", preparation='" + preparation + '\'' +
                ", hints='" + hints + '\'' +
                ", img='" + img + '\'' +
                ", dateAdded=" + dateAdded +
                ", likes=" + likes +
                ", liked=" + liked +
                '}';
    }
}