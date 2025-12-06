package br.com.darioklein.ecopass.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "TB_WALLET")
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "available_balance", precision = 10, scale = 2, nullable = false)
    private BigDecimal availableBalance;

    @Column(name = "pending_balance", precision = 10, scale = 2,  nullable = false)
    private BigDecimal pendingBalance;

    @Column(name = "created_at",  nullable = false)
    private LocalDate createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDate.now();
    }
}
