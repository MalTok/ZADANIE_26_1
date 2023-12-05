package pl.mt.cookbook.ingredient;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Optional<Ingredient> findByName(String name) {
        return ingredientRepository.findByNameIgnoreCase(name);
    }

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }
}
