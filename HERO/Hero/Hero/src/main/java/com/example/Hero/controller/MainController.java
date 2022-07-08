
package com.example.Hero.controller;

import com.example.Hero.entities.Hero;
import com.example.Hero.entities.Location;
import com.example.Hero.entities.Organization;
import com.example.Hero.entities.Sighting;
import com.example.Hero.entities.Superpower;
import com.example.Hero.repositories.HeroRepository;
import com.example.Hero.repositories.LocationRepository;
import com.example.Hero.repositories.OrganizationRepository;
import com.example.Hero.repositories.SightingRepository;
import com.example.Hero.repositories.SuperpowerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jasen Ratnam
 */
@RestController
@RequestMapping("/api/hero")
public class MainController {
    
    @Autowired
    HeroRepository heros;
    
    @Autowired
    LocationRepository locations;
    
    @Autowired
    OrganizationRepository organizations;
    
    @Autowired
    SightingRepository sightings;
    
    @Autowired
    SuperpowerRepository superpowers;
    
    
    @GetMapping("/heros")
    public List<Hero> displayHeroes() {
        return heros.findAll();
    }
    
    @PostMapping("/addHero")
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
        Hero result = heros.save(hero);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/viewHero/{id}")
    public ResponseEntity<Hero> viewHero(@PathVariable int id) {
        Hero hero = heros.findById(id).orElse(null);
        
        if (hero == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(hero);
    }
    
    @GetMapping("/deleteHero/{id}")
    public void deleteHero(@PathVariable int id) {

        heros.deleteById(id);
    }
    
    
    @GetMapping("/locations")
    public List<Location> displayLocations() {
        return locations.findAll();
    }
    
    @PostMapping("/addLocation")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        Location result = locations.save(location);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    //The link from the front page will send an ID
    @GetMapping("/viewLocation/{id}")
    public ResponseEntity<Location> viewLocation(@PathVariable int id) {
        Location location = locations.findById(id).orElse(null);
        
        if (location == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(location);
    }
    
    @GetMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable int id) {
        locations.deleteById(id);
    }
    
    
    @GetMapping("/organizations")
    public List<Organization> displayOrganizations() {
        return organizations.findAll();
    }
    
    @PostMapping("/addOrganization")
    public ResponseEntity<Organization> addOrganization(@RequestBody Organization organization) {
        Organization result = organizations.save(organization);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    //The link from the front page will send an ID
    @GetMapping("/viewOrganization/{id}")
    public ResponseEntity<Organization> viewOrganization(@PathVariable int id) {
        Organization organization = organizations.findById(id).orElse(null);
        
        if (organization == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(organization);
    }
    
    @GetMapping("/deleteOrganization/{id}")
    public void deleteOrganization(@PathVariable int id) {
        organizations.deleteById(id);
    }
    
    
    @GetMapping("/sightings")
    public List<Sighting> displaySightings(Model model) {
       return sightings.findAll();
    }
    
    @PostMapping("/addSighting")
    public ResponseEntity<Sighting> addSighting(@RequestBody Sighting sighting) {
        Sighting result  = sightings.save(sighting);
        
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    //The link from the front page will send an ID
    @GetMapping("/viewSighting/{id}")
    public ResponseEntity<Sighting> viewSighting(@PathVariable int id) {
        Sighting sighting = sightings.findById(id).orElse(null);
        
        if (sighting == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(sighting);
    }
    
    @GetMapping("/deleteSighting/{id}")
    public void deleteSighting(Integer id) {

        sightings.deleteById(id);
    }
    
    
    @GetMapping("/superpowers")
    public List<Superpower> displaySuperpowers(Model model) {
        return superpowers.findAll();
    }
    
    @PostMapping("/addSuperpower")
    public ResponseEntity<Superpower> addSuperpower(@RequestBody Superpower superpower) {
        Superpower result = superpowers.save(superpower);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    //The link from the front page will send an ID
    @GetMapping("/viewSuperpower/{id}")
    public ResponseEntity<Superpower> viewSuperpower(@PathVariable int id) {
        Superpower superpower = superpowers.findById(id).orElse(null);
        
        if (superpower == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(superpower);
    }
    
    @GetMapping("/deleteSuperpower/{id}")
    public void deleteSuperpower(@PathVariable int id) {

        superpowers.deleteById(id);
    }
    
    
    
}

