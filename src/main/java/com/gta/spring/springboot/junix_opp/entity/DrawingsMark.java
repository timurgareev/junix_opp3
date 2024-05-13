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
@Table(name = "drawings_marks")
public class DrawingsMark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false,name = "mark_drawing")
    private String markdrawing;

    @Column(unique = true, nullable = false,name = "marks_key")
    private String markkey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marks_id")
    private Mark mark;

    @Builder.Default
    @OneToMany(mappedBy = "drawingsMark")
    private List<Drawing> drawings = new ArrayList<>();

}
