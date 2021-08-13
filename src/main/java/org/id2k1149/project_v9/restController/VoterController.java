package org.id2k1149.project_v9.restController;

import org.id2k1149.project_v9.model.Voter;
import org.id2k1149.project_v9.service.VoterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @GetMapping
    public List<Voter> getVoters() {
        return voterService.getVoters();
    }

    @GetMapping(path = "{id}")
    public Voter getVoter(@PathVariable("id") Long id) {
        return voterService.getVoter(id);
    }

    @PostMapping
    public void addVoter(@RequestBody Voter newVoter) {
        voterService.addVoter(newVoter);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateVoter(
            @RequestBody Voter voter,
            @PathVariable("id") Long id
    ) {
        voterService.updateVoter(id, voter);
    }

    @DeleteMapping(path = "{id}")
    public void deleteVoter(@PathVariable("id") Long id) {
        voterService.deleteVoter(id);
    }
}