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
@Table(name = "marks")
public class Mark {

    @Id
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false,name = "marks_key")
    private String markkey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "disciplines_id")
    private Discipline discipline;

    @Builder.Default
    @OneToMany(mappedBy = "mark")
    private List<DrawingsMark> drawingsMarks = new ArrayList<>();
}
