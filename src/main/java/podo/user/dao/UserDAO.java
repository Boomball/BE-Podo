package podo.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import podo.user.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, String> {
}
