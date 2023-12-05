package pl.mt.cookbook.category;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByUrl(String url);

    Optional<Category> findByNameIgnoreCase(String name);
}