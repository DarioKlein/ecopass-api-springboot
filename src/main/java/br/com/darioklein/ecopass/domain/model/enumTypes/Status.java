package br.com.darioklein.ecopass.domain.model.enumTypes;

import java.util.Arrays;

public enum Status {
    PENDING,
    COMPLETED,
    CANCELED;

    public static Status from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Status não pode ser nulo.");
        }

        try {
            return Status.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Valor '" + value + "' não é um status válido. " +
                            "Valores aceitos: " + Arrays.toString(Status.values())
            );
        }
    }
}
