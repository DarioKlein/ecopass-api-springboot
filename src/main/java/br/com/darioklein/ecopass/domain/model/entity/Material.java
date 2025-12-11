package br.com.darioklein.ecopass.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_MATERIAL")
@Getter
@Setter
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, name = "price_per_kg")
    private BigDecimal pricePerKg;

    @Column(nullable = false)
    private Boolean active;

    @ManyToMany(mappedBy = "favoriteMaterials")
    private List<User> users = new ArrayList<>();
}
