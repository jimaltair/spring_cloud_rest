package com.epam.mathmicro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Evgeny Borisov
 */
@Service
@RefreshScope
public class QuestionService {

    private Random random = new Random();

    @Value("${math.max}")
    private int max;

    public Question getRandomQuestion() {
        int a = random.nextInt(max);
        int b = random.nextInt(max);
        return Question.builder()
                .question(a + " + " + b + " = ?")
                .answer(Integer.toString(a + b))
                .build();
    }
}
