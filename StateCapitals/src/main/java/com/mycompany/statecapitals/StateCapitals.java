/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.statecapitals;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jasen
 */
public class StateCapitals {

    public static void main(String[] args) {
        //map for capitals of states
        //states are the key
        Map<String, String> stateCapitals = new HashMap<>();
        
        stateCapitals.put("Alabama", "Montgomery");
        stateCapitals.put("Alaska",  "Juneau");
        stateCapitals.put("Arizona", "Phoenix");
        stateCapitals.put("Arkansas", "Little Rock");
        stateCapitals.put("California", "Sacramento");
        stateCapitals.put("Colorado", "Denver");
        stateCapitals.put("Connecticut", "Hartford");
        stateCapitals.put("Delaware", "Dover");
        stateCapitals.put("Florida","Tallahassee");
        stateCapitals.put("Georgia", "Atlanta");
        stateCapitals.put("Hawaii", "Honolulu");
        stateCapitals.put("Idaho",  "Boise");
        stateCapitals.put("Illinois", "Springfield");
        stateCapitals.put("Indiana", "Indianapolis");
        stateCapitals.put("Iowa",  "Des Moines");
        stateCapitals.put("Kansas",  "Topeka");
        stateCapitals.put("Kentucky", "Frankfort");
        stateCapitals.put("Louisiana",  "Baton Rouge");
        stateCapitals.put("Maine", "Augusta");
        stateCapitals.put("Maryland", "Annapolis");
        stateCapitals.put("Massachusetts",  "Boston");
        stateCapitals.put("Michigan", "Lansing");
        stateCapitals.put("Minnesota",  "St. Paul");
        stateCapitals.put("Mississippi","Jackson");
        stateCapitals.put("Missouri", "Jefferson City");
        stateCapitals.put("Montana",  "Helena");
        stateCapitals.put("Nebraska", "Lincoln");
        stateCapitals.put("Nevada","Carson City");
        stateCapitals.put("New Hampshire", "Concord");
        stateCapitals.put("New Jersey", "Trenton");
        stateCapitals.put("New Mexico", "Santa Fe");
        stateCapitals.put("New York", "Albany");
        stateCapitals.put("North Carolina", "Raleigh");
        stateCapitals.put("Ohio", "Columbus");
        stateCapitals.put("Oklahoma", "Oklahoma City");
        stateCapitals.put("Oregon", "Salem");
        stateCapitals.put("Pennsylvania", "Harrisburg");
        stateCapitals.put("Rhode Island", "Providence");
        stateCapitals.put("South Carolina", "Columbia");
        stateCapitals.put("South Dakota", "Pierre");
        stateCapitals.put("Tennessee" ,"Nashville");
        stateCapitals.put("Texas", "Austin");
        stateCapitals.put("Utah", "Salt Lake City");
        stateCapitals.put("Vermont", "Montpelier");
        stateCapitals.put("Virginia", "Richmond");
        stateCapitals.put("Washington", "Olympia");
        stateCapitals.put("West Virginia", "Charleston");
        stateCapitals.put("Wisconsin", "Madison");
        stateCapitals.put("Wyoming", "Cheyenne");
        
        System.out.println("STATES:");
        System.out.println("========");
        
        // get the Set of keys from the map
        Set<String> keys = stateCapitals.keySet();

        // print the states to the screen
        for (String k : keys) {
            System.out.println(k);
        }
        
        System.out.println("");
        
        System.out.println("CAPITALS:");
        System.out.println("========");
        
        // get the Collection of values from the Map
        Collection<String> capitals = stateCapitals.values();
        // list all of the capital values
        for (String p : capitals) {
            System.out.println(p);
        }
        
         System.out.println("");
        
        System.out.println("STATE/CAPITAL PAIRS:");
        System.out.println("=====================");
        
        // print the keys and associated values to the screen
        for (String k : keys) {
            System.out.println(k + " - " + stateCapitals.get(k));
        }


        
        
    }
}
