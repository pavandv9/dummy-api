package in.test.dummy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DummyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyApiApplication.class,args);
	}
}
