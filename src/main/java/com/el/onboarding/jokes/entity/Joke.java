package com.el.onboarding.jokes.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="joke")
public class Joke {

    @Id
    @GeneratedValue
    private int id;

    private String joke;
    private String punchline;
    private int rating;

    public Joke() {}

    public Joke(String joke, String punchline, int rating) {
        this.joke = joke;
        this.punchline = punchline;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

