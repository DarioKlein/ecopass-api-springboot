package br.com.darioklein.ecopass.utils;

import br.com.darioklein.ecopass.service.exception.InvalidDateFormatException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class MultiLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final List<DateTimeFormatter> FORMATTERS = List.of(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    );

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        String text = p.getText();

        if (text == null || text.isBlank()) {
            return null;
        }

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(text, formatter);

                if (date.isAfter(LocalDate.now())) {
                    throw new InvalidDateFormatException("A data de nascimento não pode ser maior que a data atual: " + text);
                }

                return date;
            } catch (DateTimeParseException ignored) {
            }
        }

        throw new InvalidDateFormatException("Formato de data inválido: " + text +
                ". São aceitos: dd/MM/yyyy, yyyy-MM-dd, dd-MM-yyyy, MM/dd/yyyy, yyyy/MM/dd");
    }
}
