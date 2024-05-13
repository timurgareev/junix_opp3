package com.gta.spring.springboot.junix_opp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "revisions")
public class Revision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawings_id")
    private Drawing drawing;

    @Column(nullable = false)
    private String name;

    private String status;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_inbox")
    private Date dateInbox;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_outbox")
    private Date dateOutbox;

    @Temporal(TemporalType.DATE)
    @Column(name = "inproduction_date_system")
    private Date inproductionDateSystem;

    @Temporal(TemporalType.DATE)
    @Column(name = "inproduction_date_manual")
    private Date inproductionDateManual;

    @Column(name = "rate_number")
    private Integer rateNumber;

    @Column(name = "is_latest")
    private boolean isLatest;

    private String comment1;

    private String comment2;

    @Column(name = "id_drawing_rev")
    private String idDrawingRev;

    @Builder.Default
    @OneToMany(mappedBy = "revision")
    private List<Scope> scopes = new ArrayList<>();


}
