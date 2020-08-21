package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.RecipeMeal;

@Repository
public interface RecipeMealRepository extends JpaRepository<RecipeMeal, Long>{

}
