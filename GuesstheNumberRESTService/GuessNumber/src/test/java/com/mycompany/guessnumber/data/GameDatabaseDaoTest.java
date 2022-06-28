/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.TestApplicationConfiguration;
import com.mycompany.guessnumber.models.Game;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Jasen Ratnam
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDatabaseDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public GameDatabaseDaoTest() {
    }
    
    @BeforeEach
    public void setUp() {
        
        List<Round> rounds = roundDao.getAll();
        
        for(Round round : rounds) {
            roundDao.deleteById(round.getId());
        }
        
        List<Game> gamess = gameDao.getAll();
        
        for(Game game : gamess) {
            gameDao.deleteById(game.getId());
        }
    }
    
    /**
     * Test of add and findById method, of class GameDatabaseDao.
     */
    @Test
    public void testAddGetGame() {
        
        Game game = new Game();
        game.setAnswer(1999);
        
        game = gameDao.add(game);
        
        Game fromDao = gameDao.findById(game.getId());
        
        assertEquals(game, fromDao);
    }

    /**
     * Test of getAll method, of class GameDatabaseDao.
     */
    @Test
    public void testGetAll() {
        
        //create our two Game objects with full details 
        //add them both to the database.
        Game game1 = new Game();
        game1.setAnswer(1999);
        game1 = gameDao.add(game1);
        
        Game game2 = new Game();
        game2.setAnswer(2020);
        game2 = gameDao.add(game2);
        
        //retrieve the List of Game objects using
        List<Game> games = gameDao.getAll();
        
        //assert that we've retrieved two Game objects.
        //assert that each object is in our List.
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));
        
    }

    /**
     * Test of update method, of class GameDatabaseDao.
     */
    @Test
    public void testUpdate() {
        //creating our Game object with full details and saving it to the database.
        Game game1 = new Game();
        game1.setAnswer(1999);
        game1 = gameDao.add(game1);
        
        Game fromDao = gameDao.findById(game1.getId());
        
        assertEquals(game1, fromDao);
        
        
        //make a change to the local Game
        game1.setAnswer(2020);
        
        gameDao.update(game1);
        
        assertNotEquals(game1, fromDao);
        
        fromDao = gameDao.findById(game1.getId());
        
        //ssert that the local Game is equal
        assertEquals(game1, fromDao);
    }

    /**
     * Test of deleteById method, of class GameDatabaseDao.
     */
    @Test
    public void testDeleteById() {
        Game game1 = new Game();
        game1.setAnswer(1999);
        game1.setStatus(true);
        game1 = gameDao.add(game1);
        
        Round round1 = new Round();
        round1.setGameId(game1.getId());
        round1.setGuess(2121);
        round1.setTime(LocalDateTime.MIN);
        round1.setResult("e:0:p:0");
        round1 = roundDao.add(round1);
     
        
        gameDao.deleteById(game1.getId());
        
        // trying to retrieve the Employee back
        Game fromDao = gameDao.findById(game1.getId());
        
        assertNull(fromDao);
    }
    
}
