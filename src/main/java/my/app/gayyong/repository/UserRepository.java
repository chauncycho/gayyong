package my.app.gayyong.repository;

import my.app.gayyong.entiity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
