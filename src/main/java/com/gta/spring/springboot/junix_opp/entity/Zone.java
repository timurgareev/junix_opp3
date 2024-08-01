package com.gta.spring.springboot.junix_opp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String fullname;

    @Column(unique = true, nullable = false)
    private String code;

    private int ordernumber;

    private String declaration;

    private String comment;

    private Boolean isArchive;
    private Boolean isOnDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_id")
    private Project project;

    @Builder.Default
    @OneToMany(mappedBy = "zone")
    private List<Drawing> drawings = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.isArchive = false;
        this.isOnDelete = false;
    }


}
