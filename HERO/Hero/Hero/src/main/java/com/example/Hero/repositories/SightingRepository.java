
package com.example.Hero.repositories;

import com.example.Hero.entities.Sighting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface SightingRepository  extends JpaRepository<Sighting, Integer>{

}
