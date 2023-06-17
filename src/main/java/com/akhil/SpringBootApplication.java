package com.akhil;

import com.akhil.entity.User;
import com.akhil.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;

	/**
	 * Callback used to run the bean.
	 *
	 * @param args incoming main method arguments
	 * @throws Exception on error
	 */
	@Override
	public void run(String... args) throws Exception {
		User user1 = new User("akhil", 5000, 3000, "ak@km.com");
		userRepo.save(user1);
		User user2 = new User("kumar", 5000, 8000, "km@ak.com");
		userRepo.save(user2);
	}
}
