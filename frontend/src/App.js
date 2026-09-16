import { useState } from 'react';
import axios from 'axios';

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
    <div style={{ textAlign: 'center', marginTop: '50px', fontFamily: 'Arial' }}>
      <h1>AI Career Navigator</h1>
      <p>Apna resume (PDF) upload karo</p>

      <input type="file" accept=".pdf" onChange={(e) => setFile(e.target.files[0])} />
      <br /><br />
      <button onClick={handleUpload} disabled={loading}>
        {loading ? 'Analyzing...' : 'Upload & Analyze'}
      </button>

      {result && (
        <div style={{ marginTop: '30px', textAlign: 'left', maxWidth: '500px', margin: '30px auto' }}>
          <h3>Result:</h3>
          <p><b>File:</b> {result.fileName}</p>
          <p><b>Skills Found:</b> {result.skillsFound}</p>
          <p><b>Text Length:</b> {result.textLength} characters</p>

          <h3>AI Suggestions</h3>
          <p style={{ whiteSpace: 'pre-line' }}>{result.aiSuggestions}</p>

          <h3>Job Match</h3>
          <textarea
            placeholder="Job description yaha paste karo"
            rows="5"
            style={{ width: '100%' }}
            value={jobDescription}
            onChange={(e) => setJobDescription(e.target.value)}
          />
          <br /><br />
          <button onClick={handleJobMatch}>Check Job Match</button>

          {matchResult && (
            <div style={{ marginTop: '15px' }}>
              <p><b>Match Percentage:</b> {matchResult.matchPercentage}%</p>
              <p><b>Matched Keywords:</b> {matchResult.matchedKeywords} / {matchResult.totalKeywords}</p>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default App;