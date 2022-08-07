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
        communityService.createCommunity(communityDTO);
        return new ResponseEntity<>("Community created", HttpStatus.CREATED);
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
        communityService.joinCommunity(community_id, user_id);
        return new ResponseEntity<>("User joined to community", HttpStatus.OK);
    }

    @PatchMapping("/exit/{community_id}/{user_id}")
    public ResponseEntity<String> exitCommunity(@PathVariable Long community_id, @PathVariable Long user_id) {
        communityService.exitCommunity(community_id, user_id);
        return new ResponseEntity<>("User exited from community", HttpStatus.OK);
    }

    @PatchMapping("/{community_id}/{user_id}/{community_newName}")
    public ResponseEntity<String> changeCommunityName(@PathVariable Long community_id, @PathVariable Long user_id,
                                                      @PathVariable String community_newName) {
        communityService.changeCommunityName(community_id, user_id, community_newName);
        return new ResponseEntity<>("Community name changed", HttpStatus.OK);
    }

    @DeleteMapping("/{community_id}/{admin_id}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long community_id, @PathVariable Long admin_id) {
        communityService.deleteCommunity(community_id, admin_id);
        return new ResponseEntity<>("Community not deleted", HttpStatus.OK);
    }
}

