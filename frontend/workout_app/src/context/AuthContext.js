// src/context/AuthContext.js
import React, { createContext, useState, useEffect } from 'react';
import { fetchUserDetails } from '../services/AuthService';

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [auth, setAuth] = useState({
        isAuthenticated: false,
        token: null,
        name: null,
    });

    useEffect(() => {
        const initializeAuth = async () => {
            const token = localStorage.getItem('jwtToken');
            if (token) {
                try {
                    // Fetch user details (name) with the token
                    const name = await fetchUserDetails(token);
                    // If successful, update auth state
                    setAuth({
                        isAuthenticated: true,
                        token,
                        name,
                    });
                } catch (error) {
                    // If there's an error (e.g., token is invalid/expired), clear auth state
                    console.error('Error fetching user details:', error);
                    logout();  // Call the logout function to clear the state
                }
            }
        };

        initializeAuth();
    }, []);

    const login = (token, name) => {
        localStorage.setItem('jwtToken', token);
        setAuth({
            isAuthenticated: true,
            token,
            name,
        });
    };

    const logout = () => {
        localStorage.removeItem('jwtToken');
            setAuth({
                isAuthenticated: false,
                token: null,
                name: null,
            });
    };

    return (
        <AuthContext.Provider value={{ auth, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};
