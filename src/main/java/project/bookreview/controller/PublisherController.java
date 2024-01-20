package project.bookreview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.bookreview.domain.Publisher;
import project.bookreview.service.PublisherSerivce;

import java.util.List;

@RestController
@RequestMapping(value = "/publisher", method = RequestMethod.GET)
@CrossOrigin(origins = "http://localhost:3000")
public class PublisherController {

    private final PublisherSerivce publisherSerivce;

    @Autowired
    public PublisherController(PublisherSerivce publisherSerivce) {
        this.publisherSerivce = publisherSerivce;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Publisher>> getAllBooks() {

        List<Publisher> publishers = publisherSerivce.findAll();
        //fill the authors
        return ResponseEntity.ok(publishers);

    }
}
