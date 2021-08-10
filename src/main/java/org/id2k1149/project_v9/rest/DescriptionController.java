package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.Description;
import org.id2k1149.project_v9.service.DescriptionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/description")
public class DescriptionController {

    private final DescriptionService descriptionService;

    public DescriptionController(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    @GetMapping
    public List<Description> getAllDescription() {
        return descriptionService.getAllDescription();
    }

    @GetMapping(path = "{id}")
    public Description getDescription(@PathVariable("id") Long id) {
        return descriptionService.getDescription(id);
    }

    @PostMapping
    public void addDescription(@RequestBody Description newDescription) {
        descriptionService.addDescription(newDescription);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDescription(
            @RequestBody Description description,
            @PathVariable("id") Long id
    ) {
        descriptionService.updateDescription(description, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDescription(@PathVariable("id") Long id) {
        descriptionService.deleteDescription(id);
    }
}