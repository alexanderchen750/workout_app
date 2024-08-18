import React, { useState, useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import SignIn from '../components/Signin/SignIn';
import Register from '../components/Signin/Register';
import { AuthContext } from '../context/AuthContext';
import "./LoginPage.css";
import favicon from "../assets/favicon.svg"

const LoginPage = () => {
    const { auth } = useContext(AuthContext);
    const navigate = useNavigate();
    const [isSignIn, setIsSignIn] = useState(true);

    useEffect(() => {
        if (auth.isAuthenticated) {
            navigate('/workouts'); // Redirect to workouts page if authenticated
        }
    }, [auth.isAuthenticated, navigate]);

    return (
        <div className="page-container">
            <div className="logo-container">
                <img src={favicon} alt="Logo" className="logo" />
            </div>
            <div>
                {/* Render SignIn or Register based on isSignIn state */}
                {isSignIn ? <SignIn /> : <Register />}
            </div>
            <div className="toggle-text-container">
                {isSignIn ? (
                    <p>
                        Not a member?{" "}
                        <span
                            className="toggle-text"
                            onClick={() => setIsSignIn(false)}
                        >
                            Sign Up
                        </span>
                    </p>
                ) : (
                    <p>
                        Already have an account?{" "}
                        <span
                            className="toggle-text"
                            onClick={() => setIsSignIn(true)}
                        >
                            Sign In
                        </span>
                    </p>
                )}
            </div>
        </div>
    );
};

export default LoginPage;
