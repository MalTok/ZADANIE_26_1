package pl.mt.cookbook.recipe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/recipe")
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public String showRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> foundRecipe = recipeService.find(id);
        if (foundRecipe.isPresent()) {
            Recipe recipe = foundRecipe.get();
            model.addAttribute("recipe", recipe);
        }
        return "recipe";
    }

    @GetMapping("/add")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new RecipeDto());
        return "recipe-form";
    }

    @PostMapping("/add")
    public String add(RecipeDto recipeDto) {
        Long id = recipeService.saveRecipe(recipeDto);
        return "redirect:/recipe/" + id;
    }

    @GetMapping("/edit/{id}")
    public String editRecipe(@PathVariable Long id, Model model) {
        Optional<Recipe> foundRecipe = recipeService.find(id);
        if (foundRecipe.isPresent()) {
            Recipe recipe = foundRecipe.get();
            RecipeDto recipeDto = recipeService.mapRecipeToDto(recipe);
            model.addAttribute("recipe", recipeDto);
        }
        return "recipe-form-edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, RecipeDto recipeDto) {
        recipeService.update(id, recipeDto);
        return "redirect:/recipe/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recipeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        recipeService.like(id);
        return "redirect:/recipe/" + id;
    }
}