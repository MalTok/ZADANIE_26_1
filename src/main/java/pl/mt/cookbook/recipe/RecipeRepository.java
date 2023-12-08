package pl.mt.cookbook.recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAllByTitleContainingIgnoreCase(String word);

    @Query("SELECT r FROM Recipe r ORDER BY r.likes DESC ")
    List<Recipe> findAllSortedByLikes();
}