package felipe.modeloprojetofinal.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import felipe.modeloprojetofinal.users.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     @Query("SELECT u FROM User u WHERE UPPER(u.email) LIKE UPPER(:email)")
     List<User> findByEmailLikeIgnoreCase(@Param("email") String email);

     @Query("SELECT u FROM User u WHERE UPPER(u.name) LIKE UPPER(:name)")
     List<User> findByNameLikeIgnoreCase(@Param("name") String name);

     @Query("SELECT u FROM User u WHERE UPPER(u.email) = UPPER(:email)")
     Optional<User> findByEmailIgnoreCase(@Param("email") String email);

     @Query("SELECT COUNT(u) > 0 FROM User u WHERE UPPER(u.email) = UPPER(:email)")
     boolean existsByEmailIgnoreCase(@Param("email") String email);

     @Query("SELECT COUNT(u) > 0 FROM User u WHERE UPPER(u.email) = UPPER(:email) AND u.id <> :id")
     boolean existsByEmailIgnoreCaseAndIdNot(@Param("email") String email, @Param("id") Long id);
}