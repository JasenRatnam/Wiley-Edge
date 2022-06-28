
package com.mycompany.guessnumber.service;

import com.mycompany.guessnumber.data.GameDao;
import com.mycompany.guessnumber.data.RoundDao;
import com.mycompany.guessnumber.models.Game;
import com.mycompany.guessnumber.models.Round;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service layer of application
 * manage the game rules, 
 * @author Jasen Ratnam
 */
@Service
public class GuessNumberService {

    //DAOs
    @Autowired
    private GameDao gameDao;
    @Autowired
    private RoundDao roundDao;
    
    /**
     * start a new game
     * generates an answer,
     * sets the correct status.
     * @return the game object created
     */
    public Game newGame() {
        Game newGame = new Game();
        newGame.setStatus(false);
        //get and set the answer
        newGame.setAnswer(getNewAnswer());
        
        //add to storage
        newGame = gameDao.add(newGame);
        
        return newGame;
    }

    /**
     * Calculate the results of the guess 
     * mark the game finished if the guess is correct. 
     * @param round with the guess information given
     * @return the Round object with the results filled in.
     */
    public Round guess(Round round) {
        //get guess information and correct answer
        Game game = gameDao.findById(round.getGameId());
        int guess = round.getGuess();
        int ans = game.getAnswer();
        
        //get result
        String result = getResult(guess, ans);
        
        //if correct
        if(result.equals("e:4:p:0")){
            //ans found
            //mark the game finished and update
            game.setStatus(true);
            gameDao.update(game);
        }
        
        //set result and add round to storage
        round.setResult(result);
        
        round = roundDao.add(round);
        
        return round;
    }

    /**
     * get a list of all games in storage
     * in-progress games do not display their answer.
     * @return a list of all games
     */
    public List<Game> getAllGames() {
        //get all games
        List<Game>  games = gameDao.getAll();
        
        //hide answers of in-progress games
        //answers of in-progress games to '-1'
        List<Game>  gamesHidden = new ArrayList<>();
        for(Game game : games){
            if(!game.isStatus())
                game.setAnswer(-1);
            gamesHidden.add(game);
        }
        
        return gamesHidden;
    }
    
    /**
     * get a game of given id
     * in-progress games do not display their answer
     * @param id of game wanted
     * @return specific game based on ID.
     */
    public Game findById(int id) {
        Game game = gameDao.findById(id);
        if(!game.isStatus())
                game.setAnswer(-1);
        return game;
    }

    /**
     * get rounds for a given game
     * @param id of game rounds are wanted for
     * @return a list of rounds for the specified game sorted by time.
     */
    public List<Round> getAllRounds(int id) {
        
        //get list of all rounds and filter to rounds of specific game id
        List<Round> rounds = roundDao.getAll().stream()
                .filter((r) ->  r.getGameId() == id)
                .collect(Collectors.toList());
        
        return rounds;
    }

    /**
     * The answer will be a 4-digit number with no duplicate digits.
     * get answer for a game
     * @return a 4 digit number answer for a game
     */
    private int getNewAnswer() {
        int numberOfDigitts = 4;
        
        //get 4 unique degits
        Random rnd = new Random();
        List<Integer> digits = new ArrayList<>();
        int counter = 0;
        while(counter<numberOfDigitts){
            
            int temp = rnd.nextInt(10);
            if(!digits.contains(temp)){
                digits.add(temp);
                counter++;
            }
        }
        
        //create int with unique digits
        int ans = 0;
        for(int d : digits){    
            counter--;
            ans += d*(int) Math.pow(10,counter);
        }
        return ans;
    }

    /**
     * get result of a guess
     * in the format "e:0:p:0"
     * "e" stands for exact matches
     * "p" stands for partial matches.
     * 
     * compare given guess to given ans
     * 
     * @param guess of the user
     * @param ans of the game
     * @return a string with result
     */
    private String getResult(int guess, int ans) {
        //initialisation 
       List<Integer> guessDigits = new ArrayList<>();
       List<Integer> ansDigits = new ArrayList<>();
       int e = 0;
       int p = 0;
       
       //get ArrayList with digits of guess number
       int i = 0;
       while (guess > 0) {
            guessDigits.add(guess % 10);
            guess = guess / 10;
            i++;
        }
       
       //get ArrayList with digits of ans number
       i = 0;
       while (ans > 0) {
            ansDigits.add(ans % 10);
            ans = ans / 10;
            i++;
        }
       
       //get mathces of digits
       int j = 0;
       for(int ansD : ansDigits){
           //if current digit of ans is in the guess
           if(guessDigits.contains(ansD)){
               //if it is in the correct location
               if(guessDigits.get(j) == ansD)
                   e++;
               else
                   p++;
           }
           j++;
           
       }
       
       return "e:" + e + ":p:" + p;
    }
}
