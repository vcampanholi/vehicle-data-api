package br.com.vcampanholi.vehicledata.chassi.service.bo;

import br.com.vcampanholi.vehicledata.exception.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ChassiBOTest {

    @Test
    public void shouldReturnExceptionwhenUnitsProducedIsMoreThen_9999999() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .unitsProduced(999999999);

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-01", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenChassiNumberIsEmpty() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-02", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenChassiNumberIsGreaterThanSeventeen() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26AA0CE304382698");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-03", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenChassiNumberStartsWithZero() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("0BVN26AA0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-04", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionWhenChassiNumberContainsEmptySpaces() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVNNNN 0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-05", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenFromTheFourthDigitContainsRepetitionsNumber() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BW11111119452687");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-06", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenFromTheFourthDigitContainsRepetitionsLetter() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BWZZZ5268AAAAAAA");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-06", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_i() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVNi6AA0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_I() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN2IAA0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_o() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26AAoCE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_O() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26AAOCE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_q() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26qA0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberContain_Q() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26QA0CE304382");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-07", exception.getMessage());
    }

    @Test
    public void shouldReturnExceptionwhenChassiNumberEndsWithLetters() {
        ChassiBO.Builder chassi = ChassiBO.create()
                .chassiNumber("9BVN26AA0CE3043TR");

        BusinessException exception = assertThrows(BusinessException.class, chassi::build);
        assertEquals("VEI-CHA-08", exception.getMessage());
    }

}