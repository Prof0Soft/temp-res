package by.profsoft.work.repository;

import by.profsoft.work.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Role repository.
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    /**
     * Get role by name.
     *
     * @param nameRole the name role.
     * @return seached role object.
     */
    Role findByNameRole(String nameRole);
}
