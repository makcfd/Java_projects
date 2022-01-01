package ru.netology.entity;

public class Location {

    private final String city;

    private final Country country;

    private final String street;

    private final int builing;

    public Location(String city, Country country, String street, int builing) {
        this.city = city;
        this.country = country;
        this.street = street;
        this.builing = builing;
    }

    public String getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

    public String getStreet() {
        return street;
    }

    public int getBuiling() {
        return builing;
    }

    @Override
    public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Location location = (Location) obj;
            if (location.city == null) {
                return city == location.city &&
                        country == location.country &&
                        street == location.street &&
                        builing == location.builing;
            }
            else {
            return city.equals(location.city) &&
                    country.equals(location.country) &&
                    street.equals(location.street) &&
                    builing == location.builing; }

    }
}
