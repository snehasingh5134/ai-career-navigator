package com.snehasingh.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    // application.properties se API key yaha automatically aa jayegi
    @Value("${gemini.api.key}")
    private String apiKey;

    // Resume text bhejke AI se suggestions maangega
    public String getResumeSuggestions(String resumeText) {
        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.6-flash:generateContent?key=" + apiKey;

            // Gemini ko prompt (instruction) bhej rahe hain
            String prompt = "You are a resume expert. Analyze this resume text and give 3 concrete, short bullet-point suggestions to improve it. Resume:\n\n" + resumeText;

            Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                    Map.of("parts", List.of(Map.of("text", prompt)))
                )
            );

            WebClient client = WebClient.create();
            Map response = client.post()
                    .uri(url)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block(); // Response aane tak wait karo

            // Response ke andar se text nikalna (Gemini ka response structure)
            List candidates = (List) response.get("candidates");
            Map firstCandidate = (Map) candidates.get(0);
            Map content = (Map) firstCandidate.get("content");
            List parts = (List) content.get("parts");
            Map firstPart = (Map) parts.get(0);

            return (String) firstPart.get("text");

        } catch (Exception e) {
            e.printStackTrace(); // Terminal me poora error dikhayega debug karne ke liye
            return "AI suggestions abhi available nahi hain. Basic tip: apne resume me quantifiable achievements aur relevant keywords zaroor add karo.";
        }
    }
}