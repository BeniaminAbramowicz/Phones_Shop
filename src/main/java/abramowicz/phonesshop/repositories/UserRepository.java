package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM `user` WHERE user_id = 2", nativeQuery = true)
    User getUser();

    @Query(value = "SELECT * FROM `user` u WHERE u.email=:email", nativeQuery = true)
    User getUserByEmail(String email);
}
