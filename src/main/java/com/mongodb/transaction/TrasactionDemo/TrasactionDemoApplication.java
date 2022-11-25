package com.mongodb.transaction.TrasactionDemo;

import com.mongodb.transaction.TrasactionDemo.Repository.Spaceship;
import com.mongodb.transaction.TrasactionDemo.Repository.SpaceshipRepository;
import com.mongodb.transaction.TrasactionDemo.service.SpaceshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class TrasactionDemoApplication implements CommandLineRunner {

	@Autowired
	private SpaceshipRepository spaceshipRepository;
	@Autowired
	private SpaceshipService service;

	public static void main(String[] args) {
		SpringApplication.run(TrasactionDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.spaceshipRepository.deleteAll();
		System.out.println("Before start: " + this.spaceshipRepository.count());

		try {
			this.service.addSpaceshipWihtoutTransactional();
			this.service.addSpaceshipTransactionalWithoutError();
			this.service.addSpaceshipTransactionalWithError();
            this.service.addSpaceshipTransactionalWithError();
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("Batch complete: " + this.spaceshipRepository.count());
		for(Spaceship spaceship: this.spaceshipRepository.findAll()){
			System.out.println(spaceship);
		}
	}

	@Bean
	MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

}
