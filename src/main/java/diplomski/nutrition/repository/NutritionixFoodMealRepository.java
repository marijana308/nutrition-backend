package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.NutritionixFoodMeal;

@Repository
public interface NutritionixFoodMealRepository extends JpaRepository<NutritionixFoodMeal, Long>{

}
