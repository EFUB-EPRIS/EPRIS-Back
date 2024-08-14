package com.epris.homepage.generation.greeting_card.repository;

import com.epris.homepage.generation.greeting_card.domain.GreetingCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingCardRepository extends JpaRepository<GreetingCard, Long> {
}
