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
@Table(name = "objects")
public class Object {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(unique = true, nullable = false)
        private String name;

        private String fullname;

        private String comment;

        private int ordernumber;

        private Boolean isArchive;
        private Boolean isOnDelete;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "group_of_objects_id")
        private GroupOfObject group_of_objects;

        @Builder.Default
        @OneToMany(mappedBy = "object")
//        @Fetch(value = FetchMode.SUBSELECT)
        private List<Project> projects = new ArrayList<>();

        @PrePersist
        protected void onCreate() {
                this.isArchive = false;
                this.isOnDelete = false;
        }

}
