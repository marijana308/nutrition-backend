package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.RecipeFood;

@Repository
public interface RecipeFoodRepository extends JpaRepository<RecipeFood, Long>{

}
