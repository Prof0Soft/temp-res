package by.profsoft.work.repository;

import by.profsoft.work.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository for user.
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /**
     * Find by social id.
     *
     * @param socialId the social id.
     * @return searched user object.
     */
    Optional<User> findBySocialId(String socialId);

    /**
     * Findby name user.
     *
     * @param name the name user.
     * @return searched user object.
     */
    User findByName(String name);
}
