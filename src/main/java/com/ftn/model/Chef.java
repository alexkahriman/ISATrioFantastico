package com.ftn.model;

import com.ftn.protocol.HasUniform;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Alex on 10/28/2016.
 */
@Entity
public class Chef extends Employee implements HasUniform {

    private int dressSize;

    private int footwearSize;

    private Date birthDate;

    private String profile;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant", nullable = false)
    private Restaurant restaurant;

    public Chef(){
        this.setRole(Role.CHEF);
    }

    public int getDressSize() {
        return dressSize;
    }

    public void setDressSize(int dressSize) {
        this.dressSize = dressSize;
    }

    public int getFootwearSize() {
        return footwearSize;
    }

    public void setFootwearSize(int footwearSize) {
        this.footwearSize = footwearSize;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "dressSize=" + dressSize +
                ", footwearSize=" + footwearSize +
                ", birthDate=" + birthDate +
                ", profile='" + profile + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
