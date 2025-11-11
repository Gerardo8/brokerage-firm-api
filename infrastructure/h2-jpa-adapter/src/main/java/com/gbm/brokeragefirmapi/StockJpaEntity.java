package com.gbm.brokeragefirmapi;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STOCKS")
public class StockJpaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "ISSUER_NAME", nullable = false)
    private String issuerName;

    @Column(name = "SHARE_PRICE", nullable = false)
    private BigDecimal sharePrice;
}
