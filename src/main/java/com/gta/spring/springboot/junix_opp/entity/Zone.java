package com.gta.spring.springboot.junix_opp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "name can't be a null")
    private String name;

    private String fullname;

    @NotNull(message = "code can't be a null")
    @Column(nullable = false)
    private String code;

    private int ordernumber;

    private String declaration;

    private String comment;

    private Boolean isArchive;
    private Boolean isOnDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_id", nullable = false)
    @NotNull(message = "project_id can't be a null")
    private Project project;

    @Builder.Default
    @OneToMany(mappedBy = "zone")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Drawing> drawings = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.isArchive = false;
        this.isOnDelete = false;
    }


}
