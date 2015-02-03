package edu.javacourse.hibernate;

import javax.persistence.*;

/**
 *
 * @author Demo
 */
@Entity
@Table(name = "jc_city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;

    @Column(name = "city_name")
    private String cityName;


    public City() {
    }

    public City(Integer cityId, String name, Region region) {
        this.cityId = cityId;
        this.cityName = name;

    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" + "cityId=" + cityId + ", name=" + cityName + '}';
    }
}
