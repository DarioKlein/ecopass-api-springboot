package br.com.darioklein.ecopass.utils;

import br.com.darioklein.ecopass.domain.model.enumTypes.Status;

import java.time.LocalDateTime;

public class RecyclingValidator {

    public static LocalDateTime recyclingStatusValidator(Status status) {
        return switch (status) {
            case PENDING -> null;
            case COMPLETED, CANCELED -> LocalDateTime.now();
        };
    }
}
