// src/components/ProtectedRoute.js
import React, { useContext } from 'react';
import { Navigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';

const ProtectedRoute = ({ children }) => {
    const { auth } = useContext(AuthContext);

    if (!auth.isAuthenticated) {
        // Redirect to the login page if not authenticated
        return <Navigate to="/signin" />;
    }

    return children;
};

export default ProtectedRoute;
