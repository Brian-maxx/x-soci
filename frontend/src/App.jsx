import { Routes, Route, Navigate, useLocation  } from "react-router-dom";
import './App.css';
import Login from "./pages/Auth";
import Header from './components/Header';
import Social from './pages/Social';
import Explore from './pages/Explore';
import AiChat from './pages/AiChat';
import Practice from './pages/Practice';
import PageWrapper from "./components/PageWrapper";

function App() {
  const location = useLocation();
  return (
      <div className="app-container">
        <Routes location={location} key={location.pathname}> 
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />

          <Route path="/" element={<Header />}>
            <Route path="social" element={
              <PageWrapper>
                <Social />
              </PageWrapper>
            } 
            />
            <Route path="explore" element={
              <PageWrapper>
                <Explore />
              </PageWrapper>
            } />
            <Route path="ai-chat" element={
              <PageWrapper>
                <AiChat />
              </PageWrapper>
            } />
            <Route path="practice" element={
              <PageWrapper>
                <Practice />
              </PageWrapper>
            } />
          </Route>

          <Route path="*" element={<Navigate to="/social" replace />} />
        </Routes>
      </div>
  );
}

export default App;