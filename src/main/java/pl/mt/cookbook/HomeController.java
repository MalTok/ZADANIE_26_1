package pl.mt.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mt.cookbook.category.Category;
import pl.mt.cookbook.category.CategoryService;
import pl.mt.cookbook.recipe.Recipe;
import pl.mt.cookbook.recipe.RecipeDto;
import pl.mt.cookbook.recipe.RecipeService;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public HomeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) Long categoryId,
                       @RequestParam(required = false) boolean best,
                       @RequestParam(required = false) String search,
                       Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);

        List<Recipe> recipeList = null;
        if (categoryId != null) {
            Optional<Category> categoryOptional = categoryService.findById(categoryId);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                recipeList = category.getRecipes();
                model.addAttribute("filteredCategory", category);
                if (recipeList.isEmpty()) {
                    model.addAttribute("emptyMessage", "Brak przepisów w tej kategorii.");
                }
            }
        } else if (best) {
            recipeList = recipeService.findAllSortedByLikes();
            if (recipeList.isEmpty()) {
                model.addAttribute("emptyMessage", "Brak przepisów.");
            }
        } else if (search != null) {
            recipeList = recipeService.findByTitleContainingWord(search);
            if (recipeList.isEmpty()) {
                model.addAttribute("emptyMessage", "Nie znaleziono żadnych przepisów zawierających: " + search);
            }
        } else {
            recipeList = recipeService.findAll();
            if (recipeList.isEmpty()) {
                model.addAttribute("emptyMessage", "Brak przepisów.");
            }
        }
        model.addAttribute("recipeList", recipeList);
        return "index";
    }

    @GetMapping("/add")
    public String addRecipe(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        model.addAttribute("recipe", new RecipeDto());
        return "recipe-form";
    }

    @PostMapping("/add")
    public String add(RecipeDto recipeDto) {
        Long id = recipeService.saveRecipe(recipeDto);
        return "redirect:/recipe?id=" + id;
    }

    @GetMapping("/like")
    public String like(@RequestParam Long id) {
        recipeService.like(id);
        return "redirect:/";
    }

    @GetMapping("/unlike")
    public String unlike(@RequestParam Long id) {
        recipeService.unlike(id);
        return "redirect:/";
    }
}