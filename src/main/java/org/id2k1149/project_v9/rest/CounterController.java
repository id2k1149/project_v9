package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.VotesCounter;
import org.id2k1149.project_v9.service.CounterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/votes")
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping
    public List<VotesCounter> getCounters() {
        return counterService.getCounters();
    }

    @GetMapping(path = "{id}")
    public VotesCounter getCounter(@PathVariable("id") Long id) {
        return counterService.getCounter(id);
    }

    @PostMapping(path = "{id}")
    public void addCounter(@RequestBody VotesCounter newCounter) {
        counterService.addCounter(newCounter);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCounter(
            @RequestBody VotesCounter counter,
            @PathVariable("id") Long id
    ) {
        counterService.updateCounter(id, counter);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCounter(@PathVariable("id") Long id) {
        counterService.deleteCounter(id);
    }
}