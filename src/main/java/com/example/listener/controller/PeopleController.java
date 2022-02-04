package com.example.listener.controller;

import com.example.listener.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/find-all")
    private ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok().body(peopleService.getAll());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(peopleService.getById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/find-by-name")
    private ResponseEntity<?> getByName(@RequestParam String name) {
        try {
            return ResponseEntity.ok().body(peopleService.getByName(name));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteById(@PathVariable String id) {
        try {
            peopleService.deleteById(id);
            return ResponseEntity.ok().body("");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
