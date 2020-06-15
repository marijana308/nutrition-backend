package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{

}
