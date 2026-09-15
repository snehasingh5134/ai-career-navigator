import { useState } from 'react';
import axios from 'axios';

function App() {
  // File jo user select karega, usko yaha store karenge
  const [file, setFile] = useState(null);

  // Backend se jo result aayega, usko yaha store karenge
  const [result, setResult] = useState(null);

  // Jab tak backend response na de, "loading" dikhane ke liye
  const [loading, setLoading] = useState(false);

  // Jab "Upload & Analyze" button dabega, ye function chalega
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

  return (
    <div style={{ textAlign: 'center', marginTop: '50px', fontFamily: 'Arial' }}>
      <h1>AI Career Navigator</h1>
      <p>Apna resume (PDF) upload karo</p>

      <input
        type="file"
        accept=".pdf"
        onChange={(e) => setFile(e.target.files[0])}
      />
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
        </div>
      )}
    </div>
  );
}

export default App;