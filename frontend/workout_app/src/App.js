import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import { WorkoutProvider } from './context/WorkoutContext';
import WorkoutsPage from './pages/WorkoutsPage';
import LoginPage from './pages/LoginPage';
import ProtectedRoute from './components/ProtectedRoute/ProtectedRoute';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route
            path="/workouts"
            element={
              <ProtectedRoute>
                <WorkoutProvider>
                  <WorkoutsPage />
                </WorkoutProvider>
              </ProtectedRoute>
            }
          />
          <Route path="/signin" element={<LoginPage />} />
          <Route path="/" element={<LoginPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
