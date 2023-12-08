package pl.mt.cookbook.category;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mt.cookbook.recipe.Recipe;

import java.util.List;
import java.util.Optional;

@RequestMapping("/category")
@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{url}")
    public String category(@PathVariable String url, Model model) {
        Optional<Category> categoryOptional = categoryService.findByUrl(url);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            List<Recipe> recipeList = category.getRecipes();
            if (recipeList.isEmpty()) {
                model.addAttribute("emptyMessage", "Brak przepisów w tej kategorii.");
            }
            model.addAttribute("filteredCategory", category);
            model.addAttribute("recipeList", recipeList);
        }
        return "category-recipes";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping("/add")
    public String add(Category category) {
        Category returnedCategory = categoryService.save(category);
        String url = returnedCategory.getUrl();
        return "redirect:/category/" + url;
    }

    @GetMapping("/remove")
    public String removeForm(Model model) {
        model.addAttribute("categoryToRemove", new Category());
        return "category-remove";
    }

    @PostMapping("/remove")
    public String remove(Category category) {
        Long id = category.getId();
        categoryService.remove(id);
        return "redirect:/";
    }
}
