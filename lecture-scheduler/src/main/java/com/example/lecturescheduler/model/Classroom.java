package com.example.lecturescheduler.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "classrooms", fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @Builder
    public Classroom(String name, List<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
    }
}
