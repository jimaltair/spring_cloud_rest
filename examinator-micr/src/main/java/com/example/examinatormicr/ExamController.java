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

    @PostMapping("/exam")
    public Exam getExam(@RequestBody Map<String, Integer> spec) {
        List<Section> sections = spec.entrySet().stream()
                .map(entry -> restTemplate.getForObject(getUrl(entry), Question[].class))
                .map(questions -> Section.builder().questions(List.of(questions)).build())
                .collect(Collectors.toList());
        return Exam.builder().section(sections).title(title).build();
    }

    private String getUrl(Map.Entry<String, Integer> entry) {
        return "http://" + entry.getKey() + "/api/questions?amount=" + entry.getValue();
    }
}
