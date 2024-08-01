package com.gta.spring.springboot.junix_opp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "supply_requests")
public class SupplyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "supply_request_date")
    private LocalDate supplyRequestDate;

    @Column(name = "group_of_supply")
    private String groupOfSupply; /// потом может перевести в справочник?? эти группа мтр 1,2,3 и др. а на других объектах все равно будет что то иначе

    private String description;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projects_id")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawings_id")
    private Drawing drawing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisions_id")
    private Revision revision;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_supplyrequest",
            joinColumns = @JoinColumn(name = "supplyrequest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();



    private String reg_number;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "reg_date")
    private LocalDate regDate;

    private String status;
    private String condition;
    private String comment_1c;
    private String drawing_1c;
    private String status_sign;

    private Integer year;

    @PrePersist
    protected void onCreate() {

        this.createdDate = LocalDateTime.now();
        this.year = supplyRequestDate.getYear();
    }



}

