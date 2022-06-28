
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Round;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface RoundDao {
    Round add(Round round);

    List<Round> getAll();

    Round findById(int id);

    // true if item exists and is updated
    boolean update(Round round);

    // true if item exists and is deleted
    boolean deleteById(int id);
}
