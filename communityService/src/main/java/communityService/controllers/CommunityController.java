package communityService.controllers;

import communityService.dtos.CommunityDTO;
import communityService.dtos.CreateCommunityDTO;
import communityService.models.Community;
import communityService.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;

    @PostMapping("/")
    public ResponseEntity<String> createCommunity(@RequestBody CreateCommunityDTO communityDTO) {
        String res = "Community not created";
        if (communityService.createCommunity(communityDTO)) {
            res = "Community created";
        }
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<CommunityDTO>> getAllCommunities() {
        return new ResponseEntity<>(communityService.getAllCommunities(), HttpStatus.OK);
    }

    @GetMapping("/{community_id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable Long community_id) {
        return new ResponseEntity<>(communityService.getCommunity(community_id), HttpStatus.OK);
    }

    @PatchMapping("/join/{community_id}/{user_id}")
    public ResponseEntity<String> joinCommunity(@PathVariable Long community_id, @PathVariable Long user_id) {
        String res = "User not joined to community";
        if (communityService.joinCommunity(community_id, user_id)) {
            res = "User joined to community";
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/exit/{community_id}/{user_id}")
    public ResponseEntity<String> exitCommunity(@PathVariable Long community_id, @PathVariable Long user_id) {
        String res = "User not exited from community";
        if (communityService.exitCommunity(community_id, user_id)) {
            res = "User exited from community";
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PatchMapping("/{community_id}/{user_id}/{community_newName}")
    public ResponseEntity<String> changeCommunityName(@PathVariable Long community_id, @PathVariable Long user_id,
                                                      @PathVariable String community_newName) {
        String res = "Community name not changed";
        if (communityService.changeCommunityName(community_id, user_id, community_newName)) {
            res = "Community name changed";
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}

