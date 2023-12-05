package pl.mt.cookbook.recipe;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import pl.mt.cookbook.ingredient.Ingredient;
import pl.mt.cookbook.IngredientAmount;
import pl.mt.cookbook.category.CategoryService;
import pl.mt.cookbook.ingredient.IngredientService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final CategoryService categoryService;

    public RecipeService(
            RecipeRepository recipeRepository,
            IngredientService ingredientService, CategoryService categoryService
    ) {
        this.recipeRepository = recipeRepository;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
    }

    public Optional<Recipe> find(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> findByTitleContainingWord(String word) {
        return recipeRepository.findAllByTitleContainingIgnoreCase(word);
    }

    public List<Recipe> findAll() {
        return (List<Recipe>) recipeRepository.findAll();
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }

    @Transactional
    public Long saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe();
        recipe.setTitle(recipeDto.getTitle());
        recipe.setDescription(recipeDto.getDescription());
        recipe.setPortion(recipeDto.getPortion());
        recipeDto.getCategories().stream()
                .map(category -> categoryService.findById(category.getId()))
                .forEach(categoryOptional -> categoryOptional.ifPresent(recipe::addCategory));
        getIngredients(recipeDto, recipe).forEach(recipe::addIngredientAmount);
        recipe.setPreparation(recipeDto.getPreparation());
        recipe.setHints(recipeDto.getHints());
        recipe.setImg(recipeDto.getImg());
        recipe.setDateAdded(LocalDateTime.now());
        recipe.setLikes(0);
        recipe.setLiked(false);
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    private List<IngredientAmount> getIngredients(RecipeDto recipeDto, Recipe recipe) {
        List<IngredientAmount> list = new ArrayList<>();
        String ingredients = recipeDto.getIngredients();
        String[] ingredientsArray = ingredients.split(";");
        for (String element : ingredientsArray) {
            String[] split = element.split("-");
            Ingredient ingredient = getIngredient(split);
            IngredientAmount ingredientAmount = new IngredientAmount(recipe, ingredient, split[1]);
            ingredient.addIngredientAmount(ingredientAmount);
            list.add(ingredientAmount);
            ingredientService.save(ingredient);
        }
        return list;
    }

    private Ingredient getIngredient(String[] split) {
        String name = split[0];
        Ingredient ingredient;
        Optional<Ingredient> optionalIngredient = ingredientService.findByName(name);
        if (optionalIngredient.isPresent()) {
            ingredient = optionalIngredient.get();
        } else {
            ingredient = new Ingredient();
            ingredient.setName(split[0]);
        }
        return ingredient;
    }

    public RecipeDto mapRecipeToDto(Recipe recipe) {
        String ingredientsOutputFormat = getIngredientsOutputFormat(recipe);
        return new RecipeDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getPortion(),
                ingredientsOutputFormat,
                recipe.getPreparation(),
                recipe.getHints(),
                recipe.getImg(),
                recipe.getDateAdded(),
                recipe.getLikes(),
                recipe.getCategories()
        );
    }

    private String getIngredientsOutputFormat(Recipe recipe) {
        StringBuilder stringBuilder = new StringBuilder();
        for (IngredientAmount ingredientAmount : recipe.getIngredients()) {
            stringBuilder.append(ingredientAmount.getIngredient().getName())
                    .append("-")
                    .append(ingredientAmount.getAmount())
                    .append(";");
        }
        return stringBuilder.toString();
    }

    @Transactional
    @Modifying
    public void like(Long id) {
        Optional<Recipe> optionalRecipe = find(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            int likes = recipe.getLikes() + 1;
            recipe.setLikes(likes);
            recipe.setLiked(true);
        }
    }

    @Transactional
    @Modifying
    public void unlike(Long id) {
        Optional<Recipe> optionalRecipe = find(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            int likes = recipe.getLikes() - 1;
            recipe.setLikes(likes);
            recipe.setLiked(false);
        }
    }

    public List<Recipe> findAllSortedByLikes() {
        return recipeRepository.findAllSortedByLikes();
    }
}