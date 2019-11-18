package my.app.gayyong.repository;

import my.app.gayyong.entiity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
