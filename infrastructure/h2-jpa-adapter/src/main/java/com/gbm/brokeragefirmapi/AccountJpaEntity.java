package com.gbm.brokeragefirmapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNTS")
public class AccountJpaEntity {

    private static final long serialVersionUID = 1L;

    public AccountJpaEntity(final BigDecimal cash) {
        this.cash = cash;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "CASH", nullable = false)
    private BigDecimal cash;

}
