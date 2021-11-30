package com.epam.historicmicr;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Evgeny Borisov
 */
public interface QuestionRepo extends JpaRepository<Question,Long> {
}
