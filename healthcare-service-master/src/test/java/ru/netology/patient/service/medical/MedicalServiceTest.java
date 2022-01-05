package ru.netology.patient.service.medical;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoFileRepository;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MedicalServiceTest {

    @Test
    void bloodPressureTest() {
        // given:
        String expectedMessage = "Warning, patient with id: 555, need help";
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        BloodPressure bloodPressure = new BloodPressure(120,60);
        HealthInfo healthInfo = new HealthInfo(new BigDecimal("36.66"), bloodPressure);
        PatientInfo patientInfo = new PatientInfo("555",
                "Boba",
                "Fett",
                LocalDate.of(1980, 11, 26),
                healthInfo);

        BloodPressure currentPressure = new BloodPressure(180, 40);

        SendAlertService alertService = Mockito.spy(SendAlertServiceImpl.class);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("555")).thenReturn(patientInfo);

        // when:
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure("555", currentPressure);

        // then:
        // medicalService returns void so we cannot apply assertEquals
        // we can check if internal methods of medicalService has been invoked
        // in that case we check if getById() of patientInfoRepository has been called
        Mockito.verify(patientInfoRepository, Mockito.times(1)).
                getById("555");

        // we also check how alertService works by catching argument passed from medicalService
        Mockito.verify(alertService).send(argumentCaptor.capture());
        Assertions.assertEquals(expectedMessage, argumentCaptor.getValue());

    }

    @Test
    void temperatureTest() {
        // given:
        String expectedMessage = "Warning, patient with id: 555, need help";
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        BloodPressure bloodPressure = new BloodPressure(120,60);
        HealthInfo healthInfo = new HealthInfo(new BigDecimal("36.66"), bloodPressure);
        PatientInfo patientInfo = new PatientInfo("555",
                "Boba",
                "Fett",
                LocalDate.of(1980, 11, 26),
                healthInfo);

        BigDecimal currentTemperature = new BigDecimal("10.10");

        SendAlertService alertService = Mockito.spy(SendAlertServiceImpl.class);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("555")).thenReturn(patientInfo);

        // when:
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature("555", currentTemperature);

        // then:
        // medicalService returns void so we cannot apply assertEquals
        // we can check if internal methods of medicalService has been invoked
        // in that case we check if getById() of patientInfoRepository has been called
        Mockito.verify(patientInfoRepository, Mockito.times(1)).
                getById("555");

        // we also check how alertService works by catching argument passed from medicalService
        Mockito.verify(alertService).send(argumentCaptor.capture());
        Assertions.assertEquals(expectedMessage, argumentCaptor.getValue());

    }

    @Test
    void noMessagesForBloodPressure() {
        // given:
        BloodPressure bloodPressure = new BloodPressure(120,60);
        HealthInfo healthInfo = new HealthInfo(new BigDecimal("36.66"), bloodPressure);
        PatientInfo patientInfo = new PatientInfo("555",
                "Boba",
                "Fett",
                LocalDate.of(1980, 11, 26),
                healthInfo);

        BloodPressure currentPressure = new BloodPressure(120, 60);

        SendAlertService alertService = Mockito.spy(SendAlertServiceImpl.class);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("555")).thenReturn(patientInfo);

        // when:
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkBloodPressure("555", currentPressure);

        // then:
        // medicalService returns void so we cannot apply assertEquals
        // we can check if internal methods of medicalService has been invoked
        // in that case we check if getById() of patientInfoRepository has been called
        Mockito.verify(patientInfoRepository, Mockito.times(1)).
                getById("555");

        // we also check that alestService in this test has not been invoked
        Mockito.verify(alertService, Mockito.times(0)).send("anything");

    }

    @Test
    void noMessagesForTemperature() {
        // given:
        BloodPressure bloodPressure = new BloodPressure(120,60);
        HealthInfo healthInfo = new HealthInfo(new BigDecimal("36.66"), bloodPressure);
        PatientInfo patientInfo = new PatientInfo("555",
                "Boba",
                "Fett",
                LocalDate.of(1980, 11, 26),
                healthInfo);

        BigDecimal currentTemperature = new BigDecimal("36.66");

        SendAlertService alertService = Mockito.spy(SendAlertServiceImpl.class);

        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoFileRepository.class);
        Mockito.when(patientInfoRepository.getById("555")).thenReturn(patientInfo);

        // when:
        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);
        medicalService.checkTemperature("555", currentTemperature);


        // then:
        // medicalService returns void so we cannot apply assertEquals
        // we can check if internal methods of medicalService has been invoked
        // in that case we check if getById() of patientInfoRepository has been called
        Mockito.verify(patientInfoRepository, Mockito.times(1)).
                getById("555");

        // we also check that alestService in this test has not been invoked
        // we can use verifyNoInteractions() method instead of comparing with times of invocations
        Mockito.verifyNoInteractions(alertService);

        Mockito.verifyNoMoreInteractions(patientInfoRepository);
    }

}
