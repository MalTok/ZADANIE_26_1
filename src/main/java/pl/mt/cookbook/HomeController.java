package pl.mt.cookbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mt.cookbook.recipe.Recipe;
import pl.mt.cookbook.recipe.RecipeService;

import java.util.List;

@Controller
public class HomeController {
    private final RecipeService recipeService;

    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(Model model) {
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
        List<Recipe> recipeList = recipeService.findByTitleContainingWord(word);
        if (recipeList.isEmpty()) {
            model.addAttribute("emptyMessage", "Nie znaleziono żadnych przepisów zawierających: " + word);
        }
        model.addAttribute("recipeList", recipeList);
        return "search-result";
    }

    @GetMapping("/best")
    public String best(Model model) {
        List<Recipe> recipeList = recipeService.findAllSortedByLikes();
        if (recipeList.isEmpty()) {
            model.addAttribute("emptyMessage", "Brak przepisów.");
        }
        model.addAttribute("recipeList", recipeList);
        return "best-recipes";
    }
}