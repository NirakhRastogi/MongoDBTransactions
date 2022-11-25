package com.mongodb.transaction.TrasactionDemo.service;

import com.mongodb.transaction.TrasactionDemo.Repository.Spaceship;
import com.mongodb.transaction.TrasactionDemo.Repository.SpaceshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceshipService {

    private final SpaceshipRepository spaceshipRepository;

    @Transactional
    public void addSpaceshipTransactionalWithError(){
        this.spaceshipRepository.save(new Spaceship("twe1", 4000));
        this.spaceshipRepository.save(new Spaceship("twe2", 4000));
        if(true) {
            throw new RuntimeException("Some error occurred");
        }
        this.spaceshipRepository.save(new Spaceship("twe3", 4000));
        this.spaceshipRepository.save(new Spaceship("twe4", 4000));
    }

    @Transactional
    public void addSpaceshipTransactionalWithoutError(){
        this.spaceshipRepository.save(new Spaceship("twoe1", 4000));
        this.spaceshipRepository.save(new Spaceship("twoe2", 4000));
    }

    public void addSpaceshipWihtoutTransactional(){
        this.spaceshipRepository.save(new Spaceship("wt1", 4000));
        this.spaceshipRepository.save(new Spaceship("wt2", 4000));
    }

}
