package com.gta.spring.springboot.junix_opp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    private String fullname;

    private String code;

    private int ordernumber=0;

    private String declaration;

    private Boolean isArchive;
    private Boolean isOnDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objects_id")
    private Object object;

    @Builder.Default
    @OneToMany(mappedBy = "project")
    @Fetch(FetchMode.SUBSELECT)
    private List<Zone> zones = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.isArchive = false;
        this.isOnDelete = false;
    }

}
