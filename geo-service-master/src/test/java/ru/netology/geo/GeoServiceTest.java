package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceTest {

    private GeoService geoService;

    @BeforeEach
    void setUp(){
        geoService = new GeoServiceImpl();
    }

    /**
     * Unit tests to check how GeoServiceImpl works
     * Covered method -> public Location byIp(String ip)
     *   */

    @Test
    void testLocationByMoscowIP(){
        // given:
        String MOSCOW_IP = "172.0.32.11";
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        // when:
        Location returnedLocation = geoService.byIp(MOSCOW_IP);
        // then:
        Assertions.assertEquals(expectedLocation, returnedLocation);
    }
    @Test
    void testLocationByNYIP(){
        // given:
        String NEW_YORK_IP = "96.44.183.149";
        Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        // when:
        Location returnedLocation = geoService.byIp(NEW_YORK_IP);
        // then:
        Assertions.assertEquals(expectedLocation, returnedLocation);
    }
    @Test
    void testLocationByLocalHostIP(){
        // given:
        String LOCALHOST = "127.0.0.1";
        Location expectedLocation = new Location(null, null, null, 0);
        // when:
        Location returnedLocation = geoService.byIp(LOCALHOST);
        // then:
        Assertions.assertEquals(expectedLocation, returnedLocation);
    }
}
