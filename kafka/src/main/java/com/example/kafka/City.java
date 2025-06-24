package com.example.kafka;

public class City {
    private String city;
    private String city_ascii;
    private Double latitude;
    private Double longitude;
    private String country;
    private String country_code2;
    private String country_code3;
    private String admin_name;
    private String capital;
    private Long population_2015;
    private Long id;

    public City(
        String _city,
        String _city_ascii,
        Double _latitude,
        Double _longitude,
        String _country,
        String _country_code2,
        String _country_code3,
        String _admin_name,
        String _capital,
        Long _population_2015,
        Long _id
        ){
            this.city = _city;
            this.city_ascii = _city_ascii;
            this.latitude = _latitude;
            this.longitude = _longitude;
            this.country = _country;
            this.country_code2 = _country_code2;
            this.country_code3 = _country_code3;
            this.admin_name = _admin_name;
            this.capital = _capital;
            this.population_2015 = _population_2015;
            this.id = _id;
    }

    public String City_name(){
        return this.city;
    }

    public String City_ascii(){
        return this.city_ascii;
    }

    public Double Latitude(){
        return this.latitude;
    }

    public Double Longitude(){
        return this.longitude;
    }

    public String Country(){
        return this.country;
    }

    public String Country_code2(){
        return this.country_code2;
    }

    public String Country_code3(){
        return this.country_code3;
    }

    public String AdministrativeArea(){
        return this.admin_name;
    }

    public String Capital(){
        return this.capital;
    }

    public Long Population_2015(){
        return this.population_2015;
    }

    public Long Id(){
        return this.id;
    }
}