package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class LocalizationServiceTest {

    private LocalizationService localizationService;

    @BeforeEach
    void setUp(){
        localizationService = new LocalizationServiceImpl();
    }

    //Написать тесты для проверки возвращаемого текста (класс LocalizationServiceImpl)
    //Проверить работу метода

    /**
     * Unit tests to check how LocalizationServiceImpl works
     * Covered method -> public String locale(Country country)
     *   */

    @Test
    void testTextLanguageByCountryRU() {
        // given:
        Country country = Country.RUSSIA;
        String expected = "Добро пожаловать";
        // when:
        String returnedResult = localizationService.locale(country);
        // then:
        Assertions.assertEquals(expected, returnedResult);

    }
    @Test
    void testTextLanguageByCountryEN() {
        // given:
        Country country = Country.USA;
        String expected = "Welcome";
        // when:
        String returnedResult = localizationService.locale(country);
        // then:
        Assertions.assertEquals(expected, returnedResult);

    }
}
