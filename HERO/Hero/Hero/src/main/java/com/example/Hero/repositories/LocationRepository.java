
package com.example.Hero.repositories;

import com.example.Hero.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
