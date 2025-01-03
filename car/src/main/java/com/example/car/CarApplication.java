package com.example.car;

import com.example.car.entities.Car;
import com.example.car.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class CarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);
        restTemplate.setRequestFactory(requestFactory);

        return restTemplate;
    }

    @Bean
        CommandLineRunner initializeH2Database(CarRepository carRepository) {
        return args -> {
            carRepository.save(new Car(Long.parseLong("1"),"BMW", "X6", "A231BC", Long.parseLong("1")));
            carRepository.save(new Car(Long.parseLong("2"),"Mercedes", "C63", "B231BC", Long.parseLong("2")));
            carRepository.save(new Car(Long.parseLong("3"), "Audi", "A3", "C231BC", Long.parseLong("3")));
        };
    }
}
