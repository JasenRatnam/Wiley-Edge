
package com.mycompany.guessnumber.models;

/**
 * A Game should have an answer 
 * and a status (in progress or finished). 
 * While the game is in progress, users should not be able to see the answer. 
 * The answer will be a 4-digit number with no duplicate digits.
 * @author Jasen Ratnam
 */
public class Game {
    private int id;
    private int answer;
    private boolean status;
    // false: not finished, true: finished
    
    
    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //hashcode and equals
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.answer;
        hash = 83 * hash + (this.status ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.answer != other.answer) {
            return false;
        }
        return this.status == other.status;
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", answer=" + answer + ", status=" + status + '}';
    }
    
    
}
