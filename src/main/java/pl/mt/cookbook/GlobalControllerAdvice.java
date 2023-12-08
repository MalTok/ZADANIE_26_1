package pl.mt.cookbook;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.mt.cookbook.category.Category;
import pl.mt.cookbook.category.CategoryService;

import java.util.List;

@ControllerAdvice
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
