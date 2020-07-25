package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Nutrient;

@Repository
public interface NutrientRepository extends JpaRepository<Nutrient, Long>{

}
