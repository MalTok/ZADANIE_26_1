package pl.mt.cookbook.recipe;

import pl.mt.cookbook.category.Category;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeDto {
    private Long id;
    private String title;
    private String description;
    private int portion;
    private String ingredients;
    private String preparation;
    private String hints;
    private String img;
    private LocalDateTime dateAdded;
    private int likes;
    private List<Category> categories;

    public RecipeDto() {
    }

    public RecipeDto(Long id, String title, String description, int portion, String ingredients, String preparation,
                     String hints, String img, LocalDateTime dateAdded, int likes, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.portion = portion;
        this.ingredients = ingredients;
        this.preparation = preparation;
        this.hints = hints;
        this.img = img;
        this.dateAdded = dateAdded;
        this.likes = likes;
        this.categories = categories;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
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
}