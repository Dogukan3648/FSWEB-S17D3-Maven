package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class KoalaController {

    Map<Integer, Koala> koalas;

    @PostConstruct
    public void init() {
        koalas = new HashMap<>();
    }

    @GetMapping("/koalas")
    public List<Koala> getKoalas() {
        return new ArrayList<>(koalas.values());
    }

    @GetMapping("/koalas/{id}")
    public Koala getKoala(@PathVariable int id) {

        if (!koalas.containsKey(id)) {
            throw new ZooException(
                    "Koala with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return koalas.get(id);
    }

    @PostMapping("/koalas")
    public Koala addKoala(@RequestBody Koala koala) {

        if (koala.getId() <= 0 ||
                koala.getName() == null ||
                koala.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid koala data");
        }

        koalas.put(koala.getId(), koala);
        return koala;
    }

    @PutMapping("/koalas/{id}")
    public Koala updateKoala(@PathVariable int id, @RequestBody Koala koala) {

        if (!koalas.containsKey(id)) {
            throw new ZooException(
                    "Koala with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        koalas.put(id, koala);
        return koala;
    }

    @DeleteMapping("/koalas/{id}")
    public Koala deleteKoala(@PathVariable int id) {

        if (!koalas.containsKey(id)) {
            throw new ZooException(
                    "Koala with given id does not exist: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return koalas.remove(id);
    }
}