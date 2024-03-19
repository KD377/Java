package com.example.lecturescheduler.controller;

import com.example.lecturescheduler.exception.ResourceNotFoundException;
import com.example.lecturescheduler.model.Classroom;
import com.example.lecturescheduler.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    // GET All Classrooms
    @GetMapping
    public List<Classroom> getAllClassrooms() {
        return classroomService.findAllClassrooms();
    }

    // GET Classroom by ID
    @GetMapping("/{id}")
    public ResponseEntity<Classroom> getClassroomById(@PathVariable Long id) {
        Classroom classroom = classroomService.findClassroomById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Classroom not found for this id :: " + id));
        return ResponseEntity.ok().body(classroom);
    }

    // POST Create Classroom
    @PostMapping
    public Classroom createClassroom(@RequestBody Classroom classroom) {
        return classroomService.saveClassroom(classroom);
    }

    // PUT Update Classroom
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> updateClassroom(@PathVariable Long id, @RequestBody Classroom classroomDetails) {
        Classroom updatedClassroom = classroomService.updateClassroom(id, classroomDetails);
        return ResponseEntity.ok(updatedClassroom);
    }

    // DELETE Classroom
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.ok().build();
    }
}
