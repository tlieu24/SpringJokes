package com.el.onboarding.jokes.service;

import com.el.onboarding.jokes.entity.Joke;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class MenuService {

    public static final int LIST_JOKES = 1;
    public static final int CREATE_JOKE = 2;
    public static final int VIEW_JOKE = 3;
    public static final int EDIT_JOKE = 4;
    public static final int DELETE_JOKE = 5;
    public static final int QUIT = 6;

    private Scanner scanner = new Scanner(System.in);

    private int waitForInt(String message, int min, int max) {

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

            if(!(value >= min && value <= max)){
                throw new Exception("Invalid option.");
            }
        } catch(Exception e){
            System.out.println("\nPlease provide a number between " + min + " and " + max + ".\n");

            value = waitForInt(message, min, max);
        }

        return value;
    }

    private int waitForInt(String message, int defaultValue, int min, int max) {

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

            if(!(value >= min && value <= max)){
                throw new Exception("Invalid option.");
            }
        } catch(Exception e){
            value = defaultValue;
        }

        return value;
    }

    private int waitForInt(String message) {

        System.out.println(message);

        String input = scanner.nextLine();

        int value;
        try {
            value = Integer.parseInt(input);

        } catch(Exception e){
            System.out.println("\nPlease provide a number.\n");

            value = waitForInt(message);
        }

        return value;
    }

    private Double waitForDouble(String message, boolean required){
        System.out.println(message);

        String input = scanner.nextLine();

        Double value = null;
        try {
            value = Double.parseDouble(input);

        } catch(Exception e){
            if(required) {
                System.out.println("\nPlease provide a number.\n");

                value = waitForDouble(message, required);
            }
        }

        return value;
    }

    private Double waitForDouble(String message, Double defaultValue){
        Double value = waitForDouble(message, false);

        if(value == null){
            return defaultValue;
        } else {
            return value;
        }
    }

    private String waitForString(String message, boolean required) {
        System.out.println(message);

        String value =  scanner.nextLine();

        if(required && value.trim().length() == 0){
            System.out.println("\nPlease provide a value.\n");

            value = waitForString(message, required);
        }

        return value.trim();
    }

    private String waitForString(String message, String defaultValue){
        String value = waitForString(message, false);

        if(value.isEmpty()){
            return defaultValue;
        } else {
            return value;
        }
    }

    public int promptForMainMenuSelection() {
        System.out.println("\n-- Main Menu --\n" +
                "\n" +
                "1) List Jokes\n" +
                "2) Create a Joke\n" +
                "3) View a Joke\n" +
                "4) Edit a Joke\n" +
                "5) Delete a Joke\n" +
                "6) Quit\n");

        return waitForInt("Please choose an option:", 1, 6);
    }

    public void displayJokes(List<Joke> jokes) {
        System.out.println("\n-- List Jokes --\n");

        if(jokes.size() == 0){
            System.out.println("\nNo Jokes were found.\n");
        } else {
            for (Joke joke : jokes) {
                System.out.printf("%s) %s\n", joke.getId(), joke.getJoke());
            }
        }
    }

    public Joke promptForJokeData() {
        System.out.println("\n-- Create a Joke --\n");

        String joke = waitForString("Tell us a joke: ", true);
        String punchLine = waitForString("What's the punchline?: ", true);
        int rating = waitForInt("On a scale of 1 to 10, how funny is this joke?", 1, 10);

        return new Joke(joke, punchLine, rating);
    }

    public Joke promptForJokeData(Joke joke) {
        System.out.println("\n-- Edit a Joke --\n");

        String jokeText  = waitForString(
                String.format("Tell us a joke: [%s]: ", joke.getJoke()),
                joke.getJoke());
        String punchLine = waitForString(
                String.format("What's the punchline? [%s]: ", joke.getPunchline()),
                joke.getPunchline());
        int rating = waitForInt(
                String.format("On a scale of 1 to 10, how funny is this joke? [%s]: ", joke.getRating()),
                joke.getRating(), 1, 10);

        joke.setJoke(jokeText);
        joke.setPunchline(punchLine);
        joke.setRating(rating);

        return joke;
    }

    public int promptForJokeID() {
        return waitForInt("Which joke do you want?: ");
    }

    public void displayJoke(Joke joke) {
        System.out.printf("\n-- View a Joke --\n" +
                        "\n" +
                        "Joke: %s\n" +
                        "Punchline: %s\n" +
                        "Rating: %s\n",
                joke.getJoke(),
                joke.getPunchline(),
                joke.getRating());
    }

    public void displayNoSuchJoke() {
        System.out.println("\nSorry, I couldn't find that joke.\n");
    }
}