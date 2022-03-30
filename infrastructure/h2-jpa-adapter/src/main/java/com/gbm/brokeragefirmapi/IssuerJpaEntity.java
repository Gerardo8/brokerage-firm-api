package com.gbm.brokeragefirmapi;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ISSUERS")
public class IssuerJpaEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "TOTAL_SHARES", nullable = false)
    private Integer totalShares;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private AccountJpaEntity account;

    @ManyToOne(optional = false)
    @JoinColumn(name = "STOCK_ID", nullable = false)
    private StockJpaEntity stock;

}
