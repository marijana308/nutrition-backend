package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.FoodMeal;

@Repository
public interface FoodMealRepository extends JpaRepository<FoodMeal, Long>{

}
