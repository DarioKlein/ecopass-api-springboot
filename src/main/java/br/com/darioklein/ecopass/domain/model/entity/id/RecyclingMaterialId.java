package br.com.darioklein.ecopass.domain.model.entity.id;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class RecyclingMaterialId implements Serializable {
    private Long recyclingId;
    private Long materialId;
}
