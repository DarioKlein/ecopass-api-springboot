package br.com.darioklein.ecopass.domain.model.entity;

import br.com.darioklein.ecopass.domain.model.enumTypes.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "TB_RECICLAGEM")
@Getter
@Setter
@NoArgsConstructor
public class Recycling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime registerDate;

    private LocalDateTime validationDate;

    @Column(name = "obs")
    private String observation;

    @OneToMany(mappedBy = "recycling", cascade = CascadeType.ALL)
    private List<RecyclingMaterial> materialList;

    @PrePersist
    public void prePersist() {
        status = Status.PENDING;
    }
}
