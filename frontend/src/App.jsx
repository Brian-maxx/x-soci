import { Routes, Route, Navigate, useLocation  } from "react-router-dom";
import './App.css';
import UserLayout from "./layout/UserLayout";

function App() {
  const location = useLocation();
  return (
      <UserLayout />
  );
}

export default App;