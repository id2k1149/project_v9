package org.id2k1149.project_v9.rest;


import org.id2k1149.project_v9.model.Diner;
import org.id2k1149.project_v9.service.DinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/answers")
public class DinerController {

    private final DinerService dinerService;

    @Autowired
    public DinerController(DinerService dinerService) {
        this.dinerService = dinerService;
    }

    @GetMapping
    public List<Diner> getDiners() {
        return dinerService.getDiners();
    }

    @GetMapping(path = "{id}")
    public Diner getDiner(@PathVariable("id") Long id) {
        return dinerService.getDiner(id);
    }

    @PostMapping
    public void addDiner(@RequestBody Diner newDiner) {
        dinerService.addDiner(newDiner);
    }

}
