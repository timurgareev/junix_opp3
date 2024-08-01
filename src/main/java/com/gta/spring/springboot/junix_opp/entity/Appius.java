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
@Table(name = "appius", schema = "public", catalog = "postgres")
public class Appius {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "object")
    private String object;
    @Basic
    @Column(name = "podobject")
    private String podobject;
    @Basic
    @Column(name = "nomer_poz_po_gp")
    private String nomerPozPoGp;
    @Basic
    @Column(name = "oboznachenie")
    private String oboznachenie;
    @Basic
    @Column(name = "razdel_proekta")
    private String razdelProekta;
    @Basic
    @Column(name = "izm")
    private String izm;
    @Basic
    @Column(name = "data_documenta")
    private Date dataDocumenta;
    @Basic
    @Column(name = "naimenovanie")
    private String naimenovanie;
    @Basic
    @Column(name = "sostoyanie")
    private String sostoyanie;
    @Basic
    @Column(name = "planovaya_data_vipuska")
    private Date planovayaDataVipuska;
    @Basic
    @Column(name = "data_vhodyashego_pisma")
    private Date dataVhodyashegoPisma;
    @Basic
    @Column(name = "nomer_vhodyashego_pisma")
    private String nomerVhodyashegoPisma;
    @Basic
    @Column(name = "data_ishodyashego_pisma")
    private Date dataIshodyashegoPisma;
    @Basic
    @Column(name = "nomer_ishodyashego_pisma")
    private String nomerIshodyashegoPisma;
    @Basic
    @Column(name = "web_ssilka")
    private String webSsilka;
    @Basic
    @Column(name = "stadia_proektirovaniya")
    private String stadiaProektirovaniya;
    @Basic
    @Column(name = "vid_documenta")
    private String vidDocumenta;
    @Basic
    @Column(name = "data_vpr")
    private Date dataVpr;
    @Basic
    @Column(name = "shifr_n")
    private String shifrN;
    @Basic
    @Column(name = "rev_n")
    private String revN;
    @Basic
    @Column(name = "project_string")
    private String projectString;

}
