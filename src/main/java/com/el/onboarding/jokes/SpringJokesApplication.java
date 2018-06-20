package com.el.onboarding.jokes;

import com.el.onboarding.jokes.entity.Joke;
import com.el.onboarding.jokes.repository.JokeRepository;
import com.el.onboarding.jokes.service.MenuService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJokesApplication implements CommandLineRunner {

	@Autowired
	private JokeRepository jokeRepository;
	
	@Autowired
	private MenuService menuService;

	public static void main(String[] args) {
		SpringApplication.run(SpringJokesApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.printf("The database contains %s jokes.\n", jokeRepository.count());

		while (true) {
			int primaryAction = menuService.promptForMainMenuSelection();

			if (primaryAction == MenuService.LIST_JOKES) {
				List<Joke> jokes = jokeRepository.findAll();
				menuService.displayJokes(jokes);

			} else if (primaryAction == MenuService.CREATE_JOKE) {
				// collect the joke data
				Joke joke = menuService.promptForJokeData();

				// save the joke
				jokeRepository.save(joke);

			} else if (primaryAction == MenuService.VIEW_JOKE) {
				// which joke?
				int id = menuService.promptForJokeID();

				// read the joke
				Joke joke = jokeRepository.findOne(id);

				if (joke != null) {
					// display the joke
					menuService.displayJoke(joke);
				} else {
					menuService.displayNoSuchJoke();
				}

			} else if (primaryAction == MenuService.EDIT_JOKE) {
				// which joke?
				int index = menuService.promptForJokeID();

				// read the joke
				Joke joke = jokeRepository.findOne(index);

				if (joke != null) {
					// update the joke data
					joke = menuService.promptForJokeData(joke);

					// update the joke
					jokeRepository.save(joke);
				} else {
					menuService.displayNoSuchJoke();
				}

			} else if (primaryAction == MenuService.DELETE_JOKE) {
				// which joke?
				int index = menuService.promptForJokeID();

				// delete the joke
				jokeRepository.delete(index);

			} else if (primaryAction == MenuService.QUIT) {

				// break out of the loop
				break;

			}
		}
	}
}