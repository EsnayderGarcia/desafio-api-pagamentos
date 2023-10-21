package com.desafio.apipagamento.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Stream;

public class EnumValidator implements ConstraintValidator<EnumValidation, String> {
    private List<String> valoresEnum;

    @Override
    public void initialize(EnumValidation constraintAnnotation) {
        valoresEnum = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                        .map(Enum::name)
                        .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return valoresEnum.contains(value);
    }
}
