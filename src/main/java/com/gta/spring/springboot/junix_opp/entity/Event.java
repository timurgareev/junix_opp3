package com.gta.spring.springboot.junix_opp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "event_types_id")
    private EventType eventType;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "drawings_id")
    private Drawing drawing;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "revisions_id")
    private Revision revision;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "event_date")
    private LocalDate eventDate;

    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User createdUser;

    private Boolean isSystemCreated;


    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }
}

