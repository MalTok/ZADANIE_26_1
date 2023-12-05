package pl.mt.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mt.cookbook.category.Category;
import pl.mt.cookbook.category.CategoryService;
import pl.mt.cookbook.recipe.Recipe;
import pl.mt.cookbook.recipe.RecipeService;

import java.util.List;

@Controller
public class HomeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public HomeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        List<Recipe> recipeList = recipeService.findAll();
        if (recipeList.isEmpty()) {
            model.addAttribute("emptyMessage", "Brak przepisów.");
        }
        model.addAttribute("recipeList", recipeList);
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam String word,
                         Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        List<Recipe> recipeList = recipeService.findByTitleContainingWord(word);
        if (recipeList.isEmpty()) {
            model.addAttribute("emptyMessage", "Nie znaleziono żadnych przepisów zawierających: " + word);
        }
        model.addAttribute("recipeList", recipeList);
        return "search-result";
    }

    @GetMapping("/best")
    public String best(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        List<Recipe> recipeList = recipeService.findAllSortedByLikes();
        if (recipeList.isEmpty()) {
            model.addAttribute("emptyMessage", "Brak przepisów.");
        }
        model.addAttribute("recipeList", recipeList);
        return "best-recipes";
    }
}