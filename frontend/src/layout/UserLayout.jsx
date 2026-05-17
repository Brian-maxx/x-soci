import { Routes, Route, Navigate, useLocation  } from "react-router-dom";
import Login from "../pages/user/Auth";
import Header from '../components/user/Header';
import Social from '../pages/user/Social';
import Explore from '../pages/user/Explore';
import AiChat from '../pages/user/AiChat';
import Practice from '../pages/user/Practice';
import PageWrapper from "../components/user/PageWrapper";

function UserLayout() {
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

export default UserLayout;