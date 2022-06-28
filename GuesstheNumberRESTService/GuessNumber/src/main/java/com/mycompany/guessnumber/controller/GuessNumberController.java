
package com.mycompany.guessnumber.controller;

import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import com.mycompany.guessnumber.service.GuessNumberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller of the REST Application
 * Controls the REST functions required
 * @author Jasen Ratnam
 */
@RestController
@RequestMapping("/api/guess")
public class GuessNumberController {

    //service layer that controles the DAO's and the game rules
    @Autowired
    private GuessNumberService service;
    
    /**
     * begin" 
     * - POST 
     * – Starts a game, 
     * generates an answer, 
     * sets the correct status. 
     * @return a 201 CREATED message as well as the created gameId.
     */
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int begin() {
        return service.newGame().getId();
    }
    
    /**
     * "guess" 
     * – POST 
     * – Makes a guess by passing the guess and gameId in as JSON. 
     * calculate the results of the guess 
     * mark the game finished if the guess is correct. 
     * @param round with guess and gameId given
     * @return the Round object with the results filled in.
     */
    @PostMapping("/guess")
    public ResponseEntity<Round> guess(@RequestBody Round round) {
        Round result =  service.guess(round);
        
        //if result is null, then the game with matching gameId cannot be found
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    /**
     * "game" 
     * – GET 
     * in-progress games do not display their answer.
     * @return a list of all games.
     */
    @GetMapping("/game")
    public List<Game> allGames() {
        return service.getAllGames();
    }
    
    /**
     * "game/{gameId}" 
     * - GET 
     * in-progress games do not display their answer.
     * @param gameId of game to find
     * @return a specific game based on ID
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<Game> findById(@PathVariable int gameId) {
        Game result = service.findById(gameId);
        
        //if result is null, then the game with matching gameId cannot be found
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    /**
     * "rounds/{gameId} 
     * – GET 
     * @param gameId of rounds to find
     * @return list of rounds for the specified game sorted by time
     */
    @GetMapping("/rounds/{gameId}")
    public List<Round> allRounds(@PathVariable int gameId) {
        return service.getAllRounds(gameId);
    }
}
