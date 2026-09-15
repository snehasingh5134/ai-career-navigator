package com.snehasingh.backend.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/job")
@CrossOrigin(origins = "http://localhost:3000")
public class JobMatchController {

    // Resume text aur Job description ko compare karke match % nikalega
    @PostMapping("/match")
    public Map<String, Object> matchJob(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        String resumeText = request.get("resumeText").toLowerCase();
        String jobDescription = request.get("jobDescription").toLowerCase();

        // Job description ko words me todo
        String[] jobWords = jobDescription.split("\\W+");

        int totalKeywords = 0;
        int matchedKeywords = 0;

        // Sirf 4+ letter wale meaningful words check karo
        for (String word : jobWords) {
            if (word.length() > 3) {
                totalKeywords++;
                if (resumeText.contains(word)) {
                    matchedKeywords++;
                }
            }
        }

        double matchPercentage = totalKeywords == 0 ? 0 : (matchedKeywords * 100.0 / totalKeywords);

        response.put("matchPercentage", Math.round(matchPercentage));
        response.put("matchedKeywords", matchedKeywords);
        response.put("totalKeywords", totalKeywords);

        return response;
    }
}