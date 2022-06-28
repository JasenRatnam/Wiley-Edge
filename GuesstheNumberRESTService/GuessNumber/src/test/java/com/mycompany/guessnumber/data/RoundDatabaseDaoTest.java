/*
 * Copyright Jasen Ratnam
 */
package com.mycompany.guessnumber.data;

import com.mycompany.guessnumber.TestApplicationConfiguration;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Jasen Ratnam
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDatabaseDaoTest {
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    GameDao gameDao;
    
    public RoundDatabaseDaoTest() {
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
     * Test of add and findById method, of class RoundDatabaseDao.
     */
    @Test
    public void testAddGetRound() {
        
        Game game = new Game();
        game.setAnswer(1999);
        
        game = gameDao.add(game);
        
        Round round = new Round();
        round.setGameId(game.getId());
        round.setGuess(2121);
        round.setResult("e:0:p:0");
        
        round = roundDao.add(round);
        
        
        Round fromDao = roundDao.findById(round.getId());
        
        assertEquals(round, fromDao);
    }

    /**
     * Test of getAll method, of class RoundDatabaseDao.
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
        
        
        //create our two round objects with full details 
        //add them both to the database.
        Round round1 = new Round();
        round1.setGameId(game1.getId());
        round1.setGuess(2121);
        round1.setResult("e:0:p:0");
        round1 = roundDao.add(round1);
        
        Round round2 = new Round();
        round2.setGameId(game2.getId());
        round2.setGuess(1999);
        round2.setResult("e:0:p:0");
        round2 = roundDao.add(round2);
        
        
        //retrieve the List of Round objects using
        List<Round> rounds = roundDao.getAll();
        
        //assert that we've retrieved two Game objects.
        //assert that each object is in our List.
        assertEquals(2, rounds.size());
        assertTrue(rounds.contains(round1));
        assertTrue(rounds.contains(round2));
    }

    /**
     * Test of update method, of class RoundDatabaseDao.
     */
    @Test
    public void testUpdate() {
        //creating our Game object with full details and saving it to the database.
        Game game1 = new Game();
        game1.setAnswer(1999);
        game1 = gameDao.add(game1);
        
        //creating our Round object with full details and saving it to the database.
        Round round1 = new Round();
        round1.setGameId(game1.getId());
        round1.setGuess(2121);
        round1.setResult("e:0:p:0");
        round1 = roundDao.add(round1);
        
        
        Round fromDao = roundDao.findById(round1.getId());
        
        assertEquals(round1, fromDao);
        
        
        //make a change to the local Game
        round1.setGuess(2020);
        
        roundDao.update(round1);
        
        assertNotEquals(round1, fromDao);
        
        fromDao = roundDao.findById(round1.getId());
        
        //ssert that the local Game is equal
        assertEquals(round1, fromDao);
    }

    /**
     * Test of deleteById method, of class RoundDatabaseDao.
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
     
        
        roundDao.deleteById(round1.getId());
        
        // trying to retrieve the Employee back
        Round fromDao = roundDao.findById(round1.getId());
        
        assertNull(fromDao);
    }
    
}
