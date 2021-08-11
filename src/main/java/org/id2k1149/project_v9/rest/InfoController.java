package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.service.InfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/info")
public class InfoController {

    private final InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping
    public List<Info> getAllInfo() {
        return infoService.getAllInfo();
    }

    @GetMapping(path = "{id}")
    public Info getInfo(@PathVariable("id") Long id) {
        return infoService.getInfo(id);
    }

    @PostMapping(value = "/{answerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addInfo(@RequestBody Info newInfo, @PathVariable Long answerId) {
        infoService.addInfo(newInfo, answerId);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateInfo(
            @RequestBody Info Info,
            @PathVariable("id") Long id
    ) {
        infoService.updateInfo(Info, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteInfo(@PathVariable("id") Long id) {
        infoService.deleteInfo(id);
    }
}