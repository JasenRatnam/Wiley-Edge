
package com.example.Hero.repositories;

import com.example.Hero.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{

}
