import { Routes, Route, Navigate } from "react-router-dom";
import './App.css';
import Login from "./pages/Auth";
import Header from './components/Header';
import Social from './pages/Social';
import Explore from './pages/Explore';
import AiChat from './pages/AiChat';
import Practice from './pages/Practice';

function App() {
  return (
      <div className="app-container">
        <Routes> 
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />

          <Route path="/" element={<Header />}>
            <Route path="social" element={<Social />} />
            <Route path="explore" element={<Explore />} />
            <Route path="ai-chat" element={<AiChat />} />
            <Route path="practice" element={<Practice />} />
          </Route>

          <Route path="*" element={<Navigate to="/social" replace />} />
        </Routes>
      </div>
  );
}

export default App;