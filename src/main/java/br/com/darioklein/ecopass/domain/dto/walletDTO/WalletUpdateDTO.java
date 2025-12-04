package br.com.darioklein.ecopass.domain.dto.walletDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record WalletUpdateDTO(

        @PositiveOrZero(message = "O saldo não pode ser negativo")
        @NotNull(message = "O saldo não pode ser nulo")
        BigDecimal availableBalance,

        @PositiveOrZero(message = "o saldo pendente não pode ser negativo")
        @NotNull(message = "O saldo pendente não pode ser nulo")
        BigDecimal pendingBalance
) {
}
