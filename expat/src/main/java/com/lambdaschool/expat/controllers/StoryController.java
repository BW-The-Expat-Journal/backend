package com.lambdaschool.expat.controllers;

import com.lambdaschool.expat.models.Story;
import com.lambdaschool.expat.services.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/stories")
public class StoryController {

    @Autowired
    StoryService storyService;

    @GetMapping(value = "/stories", produces = {"application/json"})
    public ResponseEntity<?> listAllStories(HttpServletRequest request){
        List<Story> myStories = storyService.findAll();
        return new ResponseEntity<>(myStories, HttpStatus.OK);
    }

    @GetMapping(value = "/story/{storyid}", produces = {"application/json"})
    public ResponseEntity<?> getStoryById(HttpServletRequest request, @PathVariable Long storyid){
        Story s = storyService.findStoryById(storyid);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping(value ="/story", consumes = "application/json")
    public ResponseEntity<?> addNewStory(@Valid @RequestBody Story newStory) throws
            URISyntaxException{
        newStory.setStoryid(0);
        newStory = storyService.save(newStory);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newStoryURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{storyid}")
                .buildAndExpand(newStory.getStoryid())
                .toUri();
        responseHeaders.setLocation(newStoryURI);

        //will build method to add story to user depending on who is logged in

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/story/{storyid}", consumes = "application/json")
    public ResponseEntity<?> updateStory(@Valid @RequestBody Story updateStory, @PathVariable long storyid){
        updateStory.setStoryid(storyid);
        storyService.save(updateStory);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/story/{storyid}")
    public ResponseEntity<?> deleteStoryById(@PathVariable long id){
        storyService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}