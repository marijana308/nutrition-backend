package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
