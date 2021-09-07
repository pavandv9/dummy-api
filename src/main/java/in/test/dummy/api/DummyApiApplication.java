package in.test.dummy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// TODO: Auto-generated Javadoc
/**
 * The Class DummyApiApplication.
 */
@EnableScheduling
@SpringBootApplication
public class DummyApiApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(DummyApiApplication.class,args);
	}
}
