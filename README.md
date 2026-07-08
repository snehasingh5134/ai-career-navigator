# AI Career Navigator

![React](https://img.shields.io/badge/Frontend-React.js-blue)
![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-brightgreen)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange)
![Gemini API](https://img.shields.io/badge/AI-Gemini%20API-purple)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)

An AI-powered Resume Analysis and Job Matching Platform that helps job seekers optimize their resumes, uncover skill gaps, improve their ATS score, and discover career opportunities that genuinely match their profile.

## Project Overview

Job hunting is increasingly competitive, and Applicant Tracking Systems (ATS) are widely used to organize and filter applications before recruiter review. AI Career Navigator helps users evaluate and improve their resumes through automated analysis, skill-gap detection, and personalized recommendations.

Users can upload their resume in PDF format, and the platform automatically parses it, extracts key information (skills, experience, education), evaluates it against ATS standards, and uses AI to suggest concrete improvements. It also matches the resume against job requirements to calculate a job-fit percentage and recommends learning resources to close any skill gaps — turning a static resume into an active career-planning tool.

The application follows a full-stack architecture where a React.js frontend communicates with a Spring Boot backend via REST APIs, MySQL handles persistent data storage, and the Gemini API powers the AI-driven analysis and recommendations.

## Features

### Resume Management
- Upload resume in PDF format
- Automated resume parsing
- Extract structured data (skills, experience, education)

### ATS & Skill Analysis
- ATS Score Analysis
- Skill Extraction from resume content
- Missing Skills Detection based on target roles
- AI-Powered Resume Improvement Suggestions

### Job Matching
- Job Match Percentage calculation
- Comparison of resume profile against job requirements

### Career Growth
- Personalized Learning Recommendations
- Skill-gap-based upskilling suggestions

## Tech Stack

### Frontend
- React.js
- JavaScript (ES6+)
- HTML5
- CSS3

### Backend
- Java
- Spring Boot
- RESTful APIs
- Spring Security + JWT (authentication)
- Apache PDFBox (PDF text extraction)

### Database
- MySQL

### AI Integration
- Gemini API (resume analysis, suggestions, and recommendations)

### Development Tools

| Tool | Purpose |
|---|---|
| VS Code / IntelliJ IDEA | Code editor |
| Java JDK 17+ | Run Spring Boot |
| Node.js | Run React |
| MySQL + MySQL Workbench | Database |
| Postman | API testing |
| Maven | Build and dependency management |
| Git & GitHub | Version control |

## System Architecture

```text
User
  │
  ▼
React Frontend
  │
  │ REST API
  ▼
Spring Boot Backend
  │             │
  ▼             ▼
MySQL       Gemini API
Database    AI Analysis
```

## Planned REST API Endpoints

> _Update this table with your actual backend endpoints._

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user and return token |
| POST | `/api/resume/upload` | Upload resume (PDF) |
| GET | `/api/resume/{id}` | Get parsed resume data |
| GET | `/api/resume/{id}/ats-score` | Get ATS score analysis |
| GET | `/api/resume/{id}/skills` | Get extracted and missing skills |
| POST | `/api/resume/{id}/suggestions` | Get AI-powered resume suggestions |
| POST | `/api/job/match` | Get job match percentage for a resume |

## Application Workflow

1. User uploads their resume (PDF) through the React frontend.
2. The backend parses the resume and extracts structured data.
3. Extracted data is analyzed for ATS compatibility and skill content.
4. The Gemini API is used to generate resume improvement suggestions.
5. The system compares the resume against job requirements to calculate a match percentage.
6. Missing skills are identified, and personalized learning resources are recommended.
7. Results are displayed on the dashboard for the user to review and act on.

## Installation

### Clone the Repository
```bash
git clone https://github.com/snehasingh5134/ai-career-navigator.git
```

### Navigate to the Project
```bash
cd ai-career-navigator
```

### Backend Setup (Spring Boot)
```bash
cd backend
```
Configure your database and API key in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/career_navigator
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

gemini.api.key=your_gemini_api_key
```
Run the backend server:
```bash
./mvnw spring-boot:run
```

### Frontend Setup (React)
```bash
cd ../frontend
npm install
npm start
```

The frontend will run on `http://localhost:3000` and the backend on `http://localhost:8080` by default.

## Development Roadmap

Built solo, following a fixed phase-by-phase order so each layer is stable before the next one is added on top of it.

**Phase 0 — Prerequisites**
- Core web concepts (HTTP, request/response cycle, REST APIs, JSON)
- Java revision
- JavaScript revision
- Git and GitHub basics
- **Milestone:** Understand how the frontend, backend, database, and external APIs communicate

**Phase 1 — Backend Foundation (Spring Boot)**
- Spring Boot project setup (Spring Initializr)
- Project structure: Controller, Service, Repository
- REST Controllers (`@RestController`, `@GetMapping`, `@PostMapping`)
- Request and response handling with JSON
- Basic exception handling
- API testing with Postman
- **Milestone:** Build and test basic REST APIs that return JSON responses

**Phase 2 — Database Integration (MySQL)**
- MySQL setup and basic SQL operations
- Spring Data JPA
- Entity and Repository layers
- Core tables: `users`, `resumes`, `job_listings`, `analysis_results`
- Basic entity relationships
- Spring Boot–MySQL configuration
- **Milestone:** Store and retrieve application data through Spring Boot APIs

**Phase 3 — Frontend Foundation (React)**
- Components, JSX, Props, and State
- `useState` and `useEffect`
- React Router
- Forms and file input handling
- API calls using Axios
- CORS configuration
- **Milestone:** Build a React interface that communicates successfully with the Spring Boot backend

**Phase 4 — Resume Processing Pipeline**
- File upload handling using `MultipartFile`
- PDF file validation
- Apache PDFBox integration
- Extracting raw text from uploaded PDF resumes
- Structuring extracted resume content for analysis
- **Milestone:** Upload PDF → Spring Boot receives file → PDFBox extracts resume text

**Phase 5 — AI Integration (Gemini API)**
- Gemini API key configuration
- Calling Gemini API from Spring Boot
- Prompt engineering for resume analysis
- Request and response handling
- Structured AI response parsing
- Resume improvement suggestions
- **Milestone:** Extracted resume text → Gemini analysis → structured analysis response

**Phase 6 — Career Intelligence & Full-Stack Integration**
- ATS compatibility analysis
- Skill extraction and missing-skill detection
- Job-description and resume skill matching
- Job match percentage calculation
- AI-assisted resume improvement suggestions
- Personalized learning roadmap generation
- React dashboard for displaying analysis results
- **Milestone:** Complete flow from resume upload to analysis, matching, recommendations, and dashboard results

**Phase 7 — Authentication, Testing & Release**
- User registration and login
- Spring Security basics
- JWT authentication
- Protected API endpoints
- End-to-end testing
- Error handling for invalid PDFs and API failures
- UI polish
- Deployment
- Demo screenshots or short demo video
- Final README update
- **Milestone:** Complete, tested, and demo-ready application

## Project Status

🚧 **Currently Under Development**

Being built solo, one phase at a time — backend foundation first, followed by database integration, frontend development, resume processing, AI integration, full-stack career intelligence, and final testing and release (see Development Roadmap above).

## Planned Enhancements

- Multi-format resume support (DOCX, TXT)
- Resume builder / templates
- Interview preparation module
- Company-specific resume tailoring
- Analytics dashboard for application tracking
- Cloud deployment

## Technical Areas Covered

- Full Stack Web Development
- Spring Boot & REST API Development
- React Component Architecture
- Database Design (MySQL)
- AI/LLM Integration (Gemini API)
- Resume Parsing & AI-Assisted Information Extraction
- Client-Server Communication

## Author

Solo project — designed, built, and maintained end-to-end by me.

Developed as an AI-powered Career Assistance Project.

Feel free to fork, contribute, and improve the project.

