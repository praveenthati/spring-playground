package com.example.controller;

import com.example.model.entity.Lesson;
import com.example.model.entityrepository.LessonRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public Lesson getLesson(@PathVariable Long id) {
        return this.repository.findOne(id);
    }

    @PatchMapping("/{id}")
    public Lesson updateLesson(@PathVariable Long id,@RequestBody Lesson body) {

        // fetch lesson before updating
        // because body might be for diff id
        Lesson lesson = this.repository.findOne(id);
        lesson.setTitle(body.getTitle());
        return this.repository.save(lesson);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable Long id) {
        this.repository.delete(id);
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

}