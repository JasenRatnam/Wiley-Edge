
package com.example.Hero.repositories;

import com.example.Hero.entities.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer>{


}
