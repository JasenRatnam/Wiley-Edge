
package com.example.Hero.repositories;

import com.example.Hero.entities.Superpower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface SuperpowerRepository  extends JpaRepository<Superpower, Integer>{

}
