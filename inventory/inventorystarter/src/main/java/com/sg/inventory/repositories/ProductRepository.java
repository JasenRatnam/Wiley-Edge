
package com.sg.inventory.repositories;

import com.sg.inventory.entities.Product;
import com.sg.inventory.entities.Store;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jasen Ratnam
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    // can pass in a Store object
    // get a list of the Products associated with that Store.
    List<Product> findByStore(Store store);
}