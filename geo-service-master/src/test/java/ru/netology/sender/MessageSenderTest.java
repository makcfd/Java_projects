package ru.netology.sender;

import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class MessageSenderTest {

    // Test case 1
    // MessageSenderImpl sends respond in russian if ip belongs to russian segment

    @Test
    void mockitoTestMessengerSendsRuTextForRuIP() {

        // given:
        Location location;
        String expected = "Добро пожаловать";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        // mock on geoService
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.123.12.19"))
                .thenReturn(location = new Location("", Country.RUSSIA, "", 00));

        // mock on LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn("Добро пожаловать");

        // when:
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String mockedResult = messageSender.send(headers);

        // then:
        Assertions.assertEquals(expected, mockedResult);
    }

    // Test case 2
    // MessageSenderImpl sends respond in english if ip belongs to non russian segment

    @Test
    void mockitoTestMessengerSendsEnTextForEnIP() {

        // given:
        Location location;
        String expected = "Welcome";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        // mock on geoService
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(location = new Location("", Country.RUSSIA, "", 00));

        // mock on LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn("Welcome");

        // when:
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String mockedResult = messageSender.send(headers);

        // then:
        Assertions.assertEquals(expected, mockedResult);
    }
}