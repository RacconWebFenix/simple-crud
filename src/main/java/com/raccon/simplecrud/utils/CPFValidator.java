package com.raccon.simplecrud.utils;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {
        // Remove caracteres especiais e espaços em branco
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais, o que torna o CPF inválido
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula e verifica o primeiro dígito verificador
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int remainder = sum % 11;
        int digit1 = (remainder < 2) ? 0 : (11 - remainder);

        if (Character.getNumericValue(cpf.charAt(9)) != digit1) {
            return false;
        }

        // Calcula e verifica o segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        remainder = sum % 11;
        int digit2 = (remainder < 2) ? 0 : (11 - remainder);

        return Character.getNumericValue(cpf.charAt(10)) == digit2;
    }

}
