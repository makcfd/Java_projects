import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Set;

class AdviceServiceTest {

    /*
    DEFAULT test in which:
    - methods of the interfaces are defined in the test itself
     */

    @Test void default_test_get_advice_in_bad_weather() {
        WeatherService weatherService = new WeatherService() {
            @Override
            public Weather currentWeather() {
                return Weather.STORMY;
            }
        };
        PreferencesService preferencesService = new PreferencesService() {
            @Override
            public Set<Preference> get(String userId) {
                return Set.of(Preference.READING, Preference.WATCHING_FILMS);
            }
        };
        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");
        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
        Assertions.assertEquals(expected, preferences);
    }

    /*
    Small improvement on DEFAULT test in which:
    - mock classes are created and mothods of interface are defined in those classes
     */

    @Test
    void mock_test_get_advice_in_bad_weather() {
        WeatherServiceMock weatherService = new WeatherServiceMock();
        weatherService.setValue(Weather.STORMY);
        PreferencesServiceMock preferencesService = new PreferencesServiceMock();
        preferencesService.setValue(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS,Preference.READING));
        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        Set<Preference> preferences = adviceService.getAdvice("user1");
        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);
    Assertions.assertEquals(expected, preferences);
    }

    /*
    MOCKITO example
     */

    @Test
    void test_get_advice_in_bad_weather() {

        /* instead of interface method override we mock it
           by passing interface as a class -> Mockito.mock(...interface.class...)
           then we define which method to call -> Mokito.when(...method...)
           and what value to return -> thenReturn(...value...)
        */

        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.currentWeather())
                .thenReturn(Weather.STORMY);

        PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
        Mockito.when(preferencesService.get("user1"))
                .thenReturn(Set.of(Preference.FOOTBALL, Preference.WATCHING_FILMS, Preference.READING));

        AdviceService adviceService = new AdviceService(preferencesService, weatherService);

        Set<Preference> preferences = adviceService.getAdvice("user1");

        Set<Preference> expected = Set.of(Preference.READING, Preference.WATCHING_FILMS);

        Assertions.assertEquals(expected, preferences);
    }

    /*
    Example of spy use.
    To use spy we should have a real object.
    We call spy on that object and later mockito will use methods of this object to perform the test
     */

    @Test
    void test_spy_weather_service() {
        // class WeatherServiceImpl has been created in advance with WeatherService interface implementation
        // once class has been created we can use spy on that class
        WeatherService weatherService = Mockito.spy(WeatherServiceImpl.class);
        Assertions.assertEquals(Weather.SUNNY, weatherService.currentWeather());
    }

    @Test
    void test_with_Mockito_verify_use() {
        WeatherService weatherService = Mockito.mock(WeatherService.class);
        Mockito.when(weatherService.currentWeather()).thenReturn(Weather.STORMY);
        PreferencesService preferencesService = Mockito.mock(PreferencesService.class);
        Mockito.when(preferencesService.get(Mockito.any())).thenReturn(Set.of(Preference.FOOTBALL));
        AdviceService adviceService = new AdviceService(preferencesService, weatherService);
        adviceService.getAdvice("user1");

        Mockito.verify(preferencesService, Mockito.times(1)).get("user1");
        Mockito.verify(preferencesService, Mockito.times(0)).get("user2");}
}