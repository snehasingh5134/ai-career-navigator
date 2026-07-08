// package com.snehasingh.aicareernavigator;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;

// @RestController
// public class ResumeController {

//     @Value("${gemini.api.key}")   // ye application.properties se key uthata hai, seedha yahan likhne ki zarurat nahi
//     private String apiKey;

//     @PostMapping("/analyze-resume")  // jaise @GetMapping tha waise hi ye hai, bas ye POST request handle karta hai (matlab data bhejna padega, sirf URL type karne se nahi chalega)
//     public String analyzeResume(@RequestBody String resumeText) {   // @RequestBody String resumeText = jo bhi text tum bhejogi request mein, wo is variable mein aa jayega

//         RestTemplate restTemplate = new RestTemplate(new org.springframework.http.client.HttpComponentsClientHttpRequestFactory());   //ye Java ka ek built-in tool hai kisi doosre server (Gemini) ko call karne ke liye
//         String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;

//         String requestBody = "{"   //requestBody = Gemini API ko jis format mein data chahiye, wo bana rahe hain (ye Gemini ka fixed format hai, docs mein diya hota hai)
//             + "\"contents\": [{"
//             + "\"parts\": [{"
//             + "\"text\": \"Extract key skills from this resume text and suggest 2 improvements: " + resumeText.replace("\"", "'") + "\""
//             + "}]"
//             + "}]"
//             + "}";

//         org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//         headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

//         org.springframework.http.HttpEntity<String> request = new org.springframework.http.HttpEntity<>(requestBody, headers);

//         String response = restTemplate.postForObject(url, request, String.class);  //restTemplate.postForObject(...) — asli call yahan ho rahi hai, Gemini ko request bhejta hai aur response wapas leta hai
//         return response;
//     }
// }


package com.snehasingh.aicareernavigator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class ResumeController {

    @Value("${gemini.api.key}")
    private String apiKey;

    @PostMapping("/analyze-resume")
    public String analyzeResume(@RequestBody String resumeText) throws Exception {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash-lite:generateContent?key=" + apiKey;

        String requestBody = "{"
            + "\"contents\": [{"
            + "\"parts\": [{"
            + "\"text\": \"Extract key skills from this resume text and suggest 2 improvements: " + resumeText.replace("\"", "'") + "\""
            + "}]"
            + "}]"
            + "}";

        HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(requestBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}