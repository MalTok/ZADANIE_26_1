package pl.mt.cookbook.category;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByUrl(String url) {
        return categoryRepository.findByUrl(url);
    }

    @Transactional
    public Category save(Category category) {
        String name = category.getName();
        Optional<Category> categoryOptional = categoryRepository.findByNameIgnoreCase(name);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        } else {
            category.setUrl(category.createUrl());
            categoryRepository.save(category);
            return category;
        }
    }

    public void remove(Long id) {
        categoryRepository.deleteById(id);
    }

}