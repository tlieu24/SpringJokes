package com.el.onboarding.jokes.repository;

import com.el.onboarding.jokes.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JokeRepository extends JpaRepository<Joke, Integer> {
}

