package br.com.darioklein.ecopass.domain.model.entity;

import br.com.darioklein.ecopass.domain.model.entity.id.RecyclingMaterialId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "RECICLAGEM_MATERIAL")
@Getter
@Setter
@NoArgsConstructor
public class RecyclingMaterial {

    @EmbeddedId
    private RecyclingMaterialId id = new RecyclingMaterialId();

    @ManyToOne
    @MapsId("recyclingId")
    @JoinColumn(name = "recycling_id")
    private Recycling recycling;

    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(name = "quantidade_kg", nullable = false)
    private BigDecimal quantityKg;
}
