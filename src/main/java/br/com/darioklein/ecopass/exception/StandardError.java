package br.com.darioklein.ecopass.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardError {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private List<String> errors;
    private String path;

}
