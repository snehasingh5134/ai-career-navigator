package com.snehasingh.backend.controller;

import com.snehasingh.backend.entity.Resume;
import com.snehasingh.backend.repository.ResumeRepository;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/resume") // Sabhi resume-related APIs "/api/resume" se shuru honge
@CrossOrigin(origins = "http://localhost:3000") // React (port 3000) ko is controller ko call karne ki permission
public class ResumeController {

    // ResumeRepository ko yaha "inject" kar rahe hain - iske through database me save/fetch karenge
    @Autowired
    private ResumeRepository resumeRepository;

    // Ye function tab chalega jab React "/api/resume/upload" pe POST request bhejega (file ke saath)
    @PostMapping("/upload")
    public Map<String, Object> uploadResume(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>(); // Response me jo bhejna hai, usko yaha store karenge

        try {
            // STEP A: PDF file ko load karke uska pura text nikalo
            PDDocument document = Loader.loadPDF(file.getBytes());
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close(); // File ko band karna zaroori hai memory bachane ke liye

            // STEP B: Ek naya Resume object banao aur usme data bharo
            Resume resume = new Resume();
            resume.setFileName(file.getOriginalFilename());
            resume.setExtractedText(text);
            resumeRepository.save(resume); // Database me save karo (H2 me)

            // STEP C: Basic skill detection - resume text me se common skills dhoondo
            // (Ye temporary/simple logic hai, baad me Gemini AI isko replace karega)
            String[] skillsToCheck = {"Java", "Python", "React", "Spring Boot", "SQL", "JavaScript", "HTML", "CSS", "Git"};
            StringBuilder foundSkills = new StringBuilder();
            for (String skill : skillsToCheck) {
                if (text.toLowerCase().contains(skill.toLowerCase())) {
                    foundSkills.append(skill).append(", ");
                }
            }

            // STEP D: Success response taiyaar karo
            response.put("success", true);
            response.put("fileName", file.getOriginalFilename());
            response.put("skillsFound", foundSkills.toString());
            response.put("textLength", text.length());
            response.put("extractedText", text); // Job matching ke liye pura text bhi bhej rahe hain

        } catch (Exception e) {
            // Agar kuch galat ho jaye (jaise corrupt PDF), to error message bhejo
            response.put("success", false);
            response.put("error", e.getMessage());
        }
        return response;
    }
    // Ye endpoint database me saare saved resumes dikhayega (H2 console ke bina test karne ke liye)
    @GetMapping("/all")
    public java.util.List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }
}