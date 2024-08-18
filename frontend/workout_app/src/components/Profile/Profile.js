import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import './Profile.css'; // Create a CSS file for styling

const Profile = () => {
    const { auth, logout } = useContext(AuthContext);
    const [isDropdownOpen, setIsDropdownOpen] = useState(false);
    const navigate = useNavigate();

    const handleLogout = () => {
        logout();
        navigate('/signin');
    };

    const toggleDropdown = () => {
        setIsDropdownOpen(!isDropdownOpen);
    };

    return(
        <div className="profile-container">
            <div className="profile-item">
                <p>{auth.name}</p>
            </div>
            <div className="profile-item avatar">
                <span className="avatar-letter">
                    {auth.name ? auth.name.charAt(0).toUpperCase() : ''}
                </span>
            </div>
            <div className='profile-item dropdown'>
                <span className="triangle" onClick={toggleDropdown}>▼</span>
                {isDropdownOpen && (
                    <div className="dropdown-menu">
                        <button onClick={handleLogout}>Sign Out</button>
                    </div>
                )}
            </div>
        </div>
    );
};

export default Profile;
