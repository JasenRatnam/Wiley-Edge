
package com.mycompany.guessnumber.models;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Each Round will have a guess, 
 * the time of the guess, 
 * and the result of the guess in the format "e:0:p:0" 
 * where "e" stands for exact matches 
 * and "p" stands for partial matches.
 * @author Jasen Ratnam
 */
public class Round {
    private int id;
    private int guess;
    private LocalDateTime time;
    private String result;
    private int gameId;
    
    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuess() {
        return guess;
    }

    public void setGuess(int guess) {
        this.guess = guess;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    //hashcode and equals
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        hash = 71 * hash + this.guess;
        hash = 71 * hash + Objects.hashCode(this.time);
        hash = 71 * hash + Objects.hashCode(this.result);
        hash = 71 * hash + this.gameId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.guess != other.guess) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        return Objects.equals(this.time, other.time);
    }

    @Override
    public String toString() {
        return "Round{" + "id=" + id + ", guess=" + guess + ", time=" + time + ", result=" + result + ", gameId=" + gameId + '}';
    }
    
    
}
