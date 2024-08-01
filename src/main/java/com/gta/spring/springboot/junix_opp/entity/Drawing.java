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
@Table(name = "drawings")
public class Drawing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false,name = "code_normal")
    private String code;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zones_id")
    private Zone zone;

    private int state;

    private String description;

    @Column(name = "code_string")
    private String codestring;

    @Column(name = "mark_drawing_key")
    private String markDrawingKey;

    private Boolean isArchive;
    private Boolean isOnDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawings_marks_id")
    private DrawingsMark drawingsMark;

    @Builder.Default
    @OneToMany(mappedBy = "drawing")
    private List<Revision> revisions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "drawing")
    private List<Scope> scopes = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.isArchive = false;
        this.isOnDelete = false;
    }


}
