import { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);

  const [jobDescription, setJobDescription] = useState('');
  const [matchResult, setMatchResult] = useState(null);

  const handleUpload = async () => {
    if (!file) {
      alert('Pehle PDF file select karo!');
      return;
    }
    setLoading(true);
    const formData = new FormData();
    formData.append('file', file);

    try {
      const response = await axios.post('http://localhost:8080/api/resume/upload', formData);
      setResult(response.data);
    } catch (error) {
      alert('Upload failed: ' + error.message);
    }
    setLoading(false);
  };

  const handleJobMatch = async () => {
    if (!result || !jobDescription) {
      alert('Pehle resume upload karo aur job description likho!');
      return;
    }
    try {
      const response = await axios.post('http://localhost:8080/api/job/match', {
        resumeText: result.extractedText || '',
        jobDescription: jobDescription,
      });
      setMatchResult(response.data);
    } catch (error) {
      alert('Matching failed: ' + error.message);
    }
  };

  return (
    <div className="app-container">
      <div className="header">
        <h1>AI Career Navigator</h1>
        <p>Apna resume upload karo aur AI-powered analysis paao</p>
      </div>

      <div className="card upload-section">
        <input type="file" accept=".pdf" onChange={(e) => setFile(e.target.files[0])} />
        <br />
        <button onClick={handleUpload} disabled={loading}>
          {loading ? 'Analyzing...' : 'Upload & Analyze'}
        </button>
      </div>

      {result && (
        <>
          <div className="card">
            <h3>ATS Score</h3>
            <div className="score-badge">{result.atsScore} / 100</div>

            <h3>Skills Found</h3>
            <div className="skills-list">
              {result.skillsFound.split(',').filter(s => s.trim()).map((skill, i) => (
                <span key={i} className="skill-tag">{skill.trim()}</span>
              ))}
            </div>

            <h3>AI Suggestions</h3>
            <div className="ai-suggestions">{result.aiSuggestions}</div>
          </div>

          <div className="card">
            <h3>Job Description Match</h3>
            <textarea
              placeholder="Job description yaha paste karo..."
              rows="5"
              value={jobDescription}
              onChange={(e) => setJobDescription(e.target.value)}
            />
            <button onClick={handleJobMatch}>Check Job Match</button>

            {matchResult && (
              <div className="match-result">
                <p><b>Match:</b> {matchResult.matchPercentage}%</p>
                <p><b>Matched Keywords:</b> {matchResult.matchedKeywords} / {matchResult.totalKeywords}</p>
              </div>
            )}
          </div>
        </>
      )}
    </div>
  );
}

export default App;