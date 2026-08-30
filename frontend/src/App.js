import { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [message, setMessage] = useState('Loading...');

  useEffect(() => {
    axios.get('http://localhost:8080/api/hello')
      .then((response) => {
        setMessage(response.data);
      })
      .catch((error) => {
        setMessage('Error: Backend se connect nahi ho paya');
        console.log(error);
      });
  }, []);

  return (
    <div style={{ textAlign: 'center', marginTop: '50px' }}>
      <h1>AI Career Navigator</h1>
      <p>Backend says: {message}</p>
    </div>
  );
}

export default App;