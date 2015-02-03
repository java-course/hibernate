package edu.javacourse.hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Author: Georgy Gobozov
 * Date: 23.06.13
 */
@Embeddable
public class Address implements Serializable{

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private int house;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", house=" + house +
                '}';
    }
}
