package pl.mt.cookbook.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.mt.cookbook.category.Category;
import pl.mt.cookbook.category.CategoryService;

import java.util.List;
import java.util.Optional;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipe")
    public String showRecipe(@RequestParam Long id, Model model) {
        Optional<Recipe> foundRecipe = recipeService.find(id);
        if (foundRecipe.isPresent()) {
            Recipe recipe = foundRecipe.get();
            model.addAttribute("recipe", recipe);
        }
        return "recipe";
    }

    @GetMapping("/edit")
    public String editRecipe(@RequestParam Long id, Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categories", categoryList);
        Optional<Recipe> foundRecipe = recipeService.find(id);
        if (foundRecipe.isPresent()) {
            Recipe recipe = foundRecipe.get();
            RecipeDto recipeDto = recipeService.mapRecipeToDto(recipe);
            model.addAttribute("recipe", recipeDto);
        }
        return "recipe-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        recipeService.delete(id);
        return "redirect:/";
    }
}