package diplomski.nutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diplomski.nutrition.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
