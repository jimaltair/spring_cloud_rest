package com.example.examinatormicr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.POST;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Evgeny Borisov
 */
@RestController
public class ExamController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${exam.title}")
    private String title;

//    POST http://localhost:8082/exam
//    Content-Type: application/json
//
//    {
//        "MATH": 5,
//        "history": 2
//    }

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> spec) {
        List<Section> sections = spec.entrySet().stream()
                .map(entry -> restTemplate.getForObject(getUrl(entry), Question[].class))
                // результат выполнения restTemplate.getForObject(...) -> http://MATH/api/questions?amount=5
                //                                                     -> http://history/api/questions?amount=2
                .map(questions -> Section.builder().questions(List.of(questions)).build())
                // в результате получаем Section с вопросами по математике - содержит List<Question> c 5-ю вопросами по математике
                //                       Section c вопросами по истории - содержит List<Question> c 2-мя вопросами по истории
                .collect(Collectors.toList());
        // возвращаем объект Exam - вместе с заголовком и List<Section>, который содержит две секции вопросов - по математике и истории
        return Exam.builder().section(sections).title(title).build();
    }

    private String getUrl(Map.Entry<String, Integer> entry) {
        //entry.getKey() = "MATH" или "history"
        return "http://" + entry.getKey() + "/api/questions?amount=" + entry.getValue();
    }
}
