package com.gbm.brokeragefirmapi;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class OrderJpaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "ORDER_OPERATION", nullable = false)
    private String orderOperation;

    @Column(name = "ISSUER_NAME", nullable = false)
    private String issuerName;

    @Column(name = "TOTAL_SHARES", nullable = false)
    private Integer totalShares;

    @Column(name = "SHARE_PRICE", nullable = false)
    private BigDecimal sharePrice;

    @Column(name = "OPERATION_DATE", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime operationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountJpaEntity account;
}
