package br.com.vcampanholi.vehicledata.chassi.service.bo;

import br.com.vcampanholi.vehicledata.exception.BusinessException;
import br.com.vcampanholi.vehicledata.exception.util.VehicleDataErrorCode;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static java.util.Objects.nonNull;
import static java.util.regex.Pattern.compile;
import static org.apache.commons.lang3.StringUtils.right;

public class ChassiBO {

    private Integer unitsProduced;
    private String chassiNumber;

    public Integer getUnitsProduced() {
        return unitsProduced;
    }

    public String getChassiNumber() {
        return chassiNumber;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private Integer unitsProduced;
        private String chassiNumber;

        public Builder unitsProduced(final Integer unitsProduced) {
            this.unitsProduced = unitsProduced;
            return this;
        }

        public Builder chassiNumber(final String chassiNumber) {
            this.chassiNumber = chassiNumber;
            return this;
        }

        private void validateLengthChassiNumber(String chassiNumber) {
            if (StringUtils.isBlank(chassiNumber))
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_NOT_FOUND.withoutParams());
            if (StringUtils.length(chassiNumber) > 17)
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_MAX_SIZE.withoutParams());
        }

        private void validateFirstDigitWithZero(String chassiNumber) {
            Pattern pattern = compile("^0");
            if (pattern.matcher(chassiNumber).find())
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_FIRST_CHARACTER.withoutParams());
        }

        private void validateChassiNumberBlank(String chassiNumber) {
            Pattern pattern = compile(" ");
            if (pattern.matcher(chassiNumber).find())
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_EMPTY_SPACES.withoutParams());
        }

        private void validateRepeteadValuesStartingFourthDigits(String chassiNumber) {
            //Se, a partir do 4º dígito, houver uma repetição consecutiva, por mais de seis vezes, do mesmo dígito (alfabético ou numérico). Exemplos: 9BW11111119452687 e 9BWZZZ5268AAAAAAA.
            Pattern pattern = compile("^.{4,}([0-9A-Z])\\1{5,}");
            if (pattern.matcher(chassiNumber).find())
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_REPETIONS_FROM_FOURTH_DIGIT.withoutParams());
        }

        private void validateSpecificCharacters(String chassiNumber) {
            //Apresente os caracteres "i", "I", "o", "O", "q", "Q".
            Pattern pattern = compile("[iIoOqQ]");
            if (pattern.matcher(chassiNumber).find())
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_ChARACTERES_NOT_ALLOWED.withoutParams());
        }

        private void validateLastSixCharacters(String chassiNumber) {
            //Os seis últimos caracteres devem ser obrigatoriamente numéricos
            String lastCharacters = right(chassiNumber, 6);
            if (!lastCharacters.matches("[0-9]*"))
                throw new BusinessException(VehicleDataErrorCode.CHASSI_NUMBER_VALIDATE_LAST_SIX_CHARACTERS.withoutParams());
        }

        public ChassiBO build() {
            if (nonNull(unitsProduced) && unitsProduced > 1000000)
                throw new BusinessException(VehicleDataErrorCode.LIMIT_UNITS_PRODUCED.withoutParams());

            if (nonNull(chassiNumber)) {
                validateLengthChassiNumber(chassiNumber);
                validateFirstDigitWithZero(chassiNumber);
                validateChassiNumberBlank(chassiNumber);
                validateRepeteadValuesStartingFourthDigits(chassiNumber);
                validateSpecificCharacters(chassiNumber);
                validateLastSixCharacters(chassiNumber);
            }

            ChassiBO chassi = new ChassiBO();
            chassi.unitsProduced = unitsProduced;
            chassi.chassiNumber = chassiNumber;

            return chassi;
        }
    }
}
