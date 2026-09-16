package com.snehasingh.backend.service;

import org.springframework.stereotype.Service;

@Service
public class AtsService {

    // Simple ATS score calculator - resume me kuch important cheezein check karta hai
    public int calculateAtsScore(String resumeText) {
        int score = 0;
        String text = resumeText.toLowerCase();

        // Check 1: Contact info hai kya (email pattern)
        if (text.contains("@")) score += 20;

        // Check 2: Education section hai kya
        if (text.contains("education") || text.contains("degree") || text.contains("university")) score += 20;

        // Check 3: Experience section hai kya
        if (text.contains("experience") || text.contains("intern") || text.contains("project")) score += 20;

        // Check 4: Skills section hai kya
        if (text.contains("skills")) score += 20;

        // Check 5: Achievements/numbers hai kya (quantifiable results)
        if (text.matches(".*\\d+%.*") || text.matches(".*\\d+\\+.*")) score += 20;

        return score;
    }
}