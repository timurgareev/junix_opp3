package com.gta.spring.springboot.junix_opp.entity;

import com.gta.spring.springboot.junix_opp.entity.enums.EPriority;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_type_id")
    private TaskType taskType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drawings_id")
    private Drawing drawing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "revisions_id")
    private Revision revision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="projects_id")
    private Project project;

    private String description;

    private Boolean isQuestion;

    private String report;

    @Column(updatable = false)
    private LocalDateTime createdDate;

//    @Column(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    private User responsibleUser;


    private LocalDate deadlineDate;

    private Boolean isComplete;

    private LocalDateTime completeDate;

    @Enumerated(EnumType.ORDINAL)
    public EPriority priority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_status_private_id")
    public TaskStatusPrivate taskStatusPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_status_public_id")
    public TaskStatusPublic taskStatusPublic;

//    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "event_task",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"))
    private List<Event> events = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_supplyrequest",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "supplyrequest_id", referencedColumnName = "id"))
    private List<SupplyRequest> supplies = new ArrayList<>();

    private String link1;
    private String link2;



    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

//    comment table



}
