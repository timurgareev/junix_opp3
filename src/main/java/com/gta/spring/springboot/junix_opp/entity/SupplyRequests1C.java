package com.gta.spring.springboot.junix_opp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "supply_requests_1c", schema = "public", catalog = "postgres")
public class SupplyRequests1C {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "drawings_id")
    private Long drawingsId;
    @Basic
    @Column(name = "revision_id")
    private Integer revisionId;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "number")
    private Integer number;
    @Basic
    @Column(name = "reg_number")
    private String regNumber;
    @Basic
    @Column(name = "reg_date")
    private Date regDate;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "condition")
    private String condition;
    @Basic
    @Column(name = "author")
    private String author;
    @Basic
    @Column(name = "comment")
    private String comment;
    @Basic
    @Column(name = "drawing_string")
    private String drawingString;
    @Basic
    @Column(name = "status_sign")
    private String statusSign;
    @Basic
    @Column(name = "year")
    private Integer year;
    @Basic
    @Column(name = "stock")
    private String stock;
    @Basic
    @Column(name = "project")
    private String project;
    @Basic
    @Column(name = "date_plan")
    private Date datePlan;
    @Basic
    @Column(name = "supply_requests_id")
    private Long supplyRequestsId;
    @Basic
    @Column(name = "project_id")
    private Integer projectId;

}
