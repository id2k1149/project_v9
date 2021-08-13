package org.id2k1149.project_v9.restControllers;

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


}