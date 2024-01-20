package project.bookreview.service;

import org.springframework.stereotype.Service;
import project.bookreview.domain.Publisher;
import project.bookreview.repository.PublisherRepository;

import java.util.List;

@Service
public class PublisherSerivce {
    private  final PublisherRepository publisherRepository;


    public PublisherSerivce(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public List<Publisher> findAll(){

        List <Publisher> publishers = publisherRepository.findAll();

        return  publishers;
    }
}
