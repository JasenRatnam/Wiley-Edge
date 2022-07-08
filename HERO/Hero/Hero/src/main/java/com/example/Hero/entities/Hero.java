
package com.example.Hero.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jasen Ratnam
 */
@Entity
public class Hero {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id 
    private int id;
    
    @Column(nullable = false)
    private boolean ishero;
    
    @Column(nullable = false)
    private String name;

    @Column
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "superpowerid", nullable = false)
    private Superpower superpower;
    
    /*
    two @Entity objects with a many to many relationship 
    causing infinite json to be generated
    fix with @jsonIgnore
    */
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "heroorganization",
            joinColumns = {@JoinColumn(name = "heroid")},
            inverseJoinColumns = {@JoinColumn(name = "organizationid")})
    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIshero() {
        return ishero;
    }

    public void setIshero(boolean ishero) {
        this.ishero = ishero;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Superpower getSuperpower() {
        return superpower;
    }

    public void setSuperpower(Superpower superpower) {
        this.superpower = superpower;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        hash = 47 * hash + (this.ishero ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.description);
        hash = 47 * hash + Objects.hashCode(this.superpower);
        hash = 47 * hash + Objects.hashCode(this.organizations);
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
        final Hero other = (Hero) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.ishero != other.ishero) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.superpower, other.superpower)) {
            return false;
        }
        return Objects.equals(this.organizations, other.organizations);
    }

    

}
