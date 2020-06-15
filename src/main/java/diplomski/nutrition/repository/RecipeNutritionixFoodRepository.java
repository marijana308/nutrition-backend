package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.RecipeNutritionixFood;

@Repository
public interface RecipeNutritionixFoodRepository extends JpaRepository<RecipeNutritionixFood, Long>{

}
