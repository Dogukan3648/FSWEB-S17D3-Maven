package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KangarooController {
    Map<Integer, Kangaroo> kangaroos;

    @PostConstruct
    public void init(){
        kangaroos = new HashMap<>();
    }
    @GetMapping("/kangaroos")
    public List<Kangaroo> getKangaroos(){
        return new ArrayList<>(kangaroos.values());
    }
    @GetMapping("/kangaroos/{id}")
    public Kangaroo getKangaroo(@PathVariable int id){
        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroos.get(id);
    }
    @PostMapping("/kangaroos")
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo){

        if (kangaroo.getId() <= 0 ||
                kangaroo.getName() == null ||
                kangaroo.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid kangaroo data");
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }
    @PutMapping("/kangaroos/{id}")
    public Kangaroo updateKangaroo(@PathVariable int id, @RequestBody Kangaroo kangaroo) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        kangaroos.put(id, kangaroo);
        return kangaroo;
    }
    @DeleteMapping("/kangaroos/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroos.remove(id);
    }
}
