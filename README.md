# AI Career Navigator

![React](https://img.shields.io/badge/Frontend-React.js-blue)
![Spring Boot](https://img.shields.io/badge/Backend-Spring%20Boot-brightgreen)
![MySQL](https://img.shields.io/badge/Database-MySQL-orange)
![Gemini API](https://img.shields.io/badge/AI-Gemini%20API-purple)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

An AI-powered Resume Analysis and Job Matching Platform that helps job seekers optimize their resumes, uncover skill gaps, improve their ATS score, and discover career opportunities that genuinely match their profile.

## Project Overview

Job hunting today is competitive, and most resumes never even reach a human recruiter — they get filtered out by Applicant Tracking Systems (ATS) before that. **AI Career Navigator** solves this problem by giving users an intelligent, data-driven way to evaluate and improve their resumes.

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
| Maven | Build Spring Boot (comes with Spring Initializr) |
| Git & GitHub | Version control |

## System Architecture

```
User
  │
  ▼
React Frontend
  │
  ▼
REST API Requests
  │
  ▼
Spring Boot Backend
  │
  ▼
MySQL Database
  │
  ▼
Gemini API (AI Analysis)
```

## Screenshots

> _Add screenshots or a short demo GIF of the dashboard, resume upload screen, and analysis results here once the UI is ready._

| Screen | Preview |
|---|---|
| Dashboard | _coming soon_ |
| Resume Upload | _coming soon_ |
| Analysis Report | _coming soon_ |

## REST API Endpoints

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
git clone https://github.com/your-username/ai-career-navigator.git
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
- Core web concepts (HTTP, REST, JSON)
- Java basics
- JavaScript basics

**Phase 1 — Backend Foundation (Spring Boot)**
- Spring Boot project setup (Spring Initializr)
- REST Controllers (`@RestController`, `@GetMapping`, `@PostMapping`)
- File upload handling (`MultipartFile`)
- Spring Data JPA (MySQL connection)
- Spring Security + JWT authentication
- ✅ Milestone: API that accepts a file upload, returns JSON, and is connected to MySQL

**Phase 2 — Database Design (MySQL)**
- Core tables: `users`, `resumes`, `job_listings`, `skill_gaps`, `recommendations`
- Table relationships (users → resumes → job matches)
- ✅ Milestone: Database schema designed and connected via `application.properties`

**Phase 3 — Frontend Foundation (React)**
- Components, JSX, Props & State
- `useState` / `useEffect` hooks
- React Router (multi-page navigation)
- API calls with Axios
- Resume upload form
- ✅ Milestone: React page with a form that uploads a file and shows a response

**Phase 4 — Resume Parsing**
- Apache PDFBox integration
- Extracting raw text from uploaded PDFs
- Sending extracted text to Gemini for skill extraction
- ✅ Milestone: Upload PDF → extract text → print detected skills

**Phase 5 — AI Integration (Gemini API)**
- Gemini API key setup (Google AI Studio)
- Sending prompts to Gemini from Java
- Prompt engineering for resume analysis
- Parsing the AI response
- ✅ Milestone: Hardcoded resume text sent to Gemini, AI analysis printed back

**Phase 6 — Full-Stack Integration**
- User registration & login (Spring Security + JWT + React login form)
- Resume upload (React → Spring Boot → MySQL + text extraction)
- ATS analysis (resume text → Gemini → ATS score + feedback)
- Skill-gap analysis (extracted skills vs. job requirements via Gemini)
- Job recommendations (skill match against MySQL job listings)
- Learning roadmap generation (Gemini-suggested resources for missing skills)
- Unified dashboard (React page showing all results together)

**Phase 7 — Polish & Testing**
- End-to-end manual testing (register → upload → analyze → view results)
- Graceful error handling (empty PDFs, Gemini failures, etc.)
- UI polish (Tailwind / Material UI)
- Final README and setup documentation

## Project Status

🚧 **Currently Under Development**

Being built solo, one phase at a time — frontend and backend foundations first, followed by database design, AI integration, resume parsing, full-stack wiring, and final polish (see Development Roadmap above).

## Planned Enhancements

- Multi-format resume support (DOCX, TXT)
- Resume builder / templates
- Interview preparation module
- Company-specific resume tailoring
- Analytics dashboard for application tracking
- Cloud deployment

## Skills Demonstrated

- Full Stack Web Development
- Spring Boot & REST API Development
- React Component Architecture
- Database Design (MySQL)
- AI/LLM Integration (Gemini API)
- Resume Parsing & NLP-based Data Extraction
- Client-Server Communication

## Author

Solo project — designed, built, and maintained end-to-end by me.

Developed as an AI-powered Career Assistance Project.

Feel free to fork, contribute, and improve the project.

## License

This project is licensed under the MIT License.