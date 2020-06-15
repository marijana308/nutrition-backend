package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.RegularUser;

@Repository
public interface RegularUserRepository extends JpaRepository<RegularUser, Long> {

}
