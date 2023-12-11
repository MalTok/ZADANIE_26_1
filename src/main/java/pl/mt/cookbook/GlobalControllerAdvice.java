package pl.mt.cookbook;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.mt.cookbook.category.Category;
import pl.mt.cookbook.category.CategoryController;
import pl.mt.cookbook.category.CategoryService;
import pl.mt.cookbook.recipe.RecipeDto;

import java.util.List;

@ControllerAdvice(assignableTypes = {CategoryController.class, RecipeDto.class, HomeController.class})
public class GlobalControllerAdvice {
    private final CategoryService categoryService;

    public GlobalControllerAdvice(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @ModelAttribute("categories")
    public List<Category> getAvailableCategories() {
        return categoryService.findAll();
    }
}
