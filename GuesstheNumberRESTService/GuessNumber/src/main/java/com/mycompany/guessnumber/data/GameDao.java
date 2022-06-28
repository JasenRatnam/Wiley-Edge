
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.models.Game;
import java.util.List;

/**
 *
 * @author Jasen Ratnam
 */
public interface GameDao {
    Game add(Game game);

    List<Game> getAll();

    Game findById(int id);

    // true if item exists and is updated
    boolean update(Game game);

    // true if item exists and is deleted
    boolean deleteById(int id);
}
