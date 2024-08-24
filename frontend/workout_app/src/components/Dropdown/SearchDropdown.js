import React, { useState, useEffect } from 'react';
import { findExercise } from '../../services/workoutService';
import './SearchDropdown.css';

const SearchDropdown = ({ value, onChange, onSelect }) => {
    const [searchTerm, setSearchTerm] = useState(value || '');
    const [options, setOptions] = useState([]);
    const [showDropdown, setShowDropdown] = useState(false);

    useEffect(() => {
        if (searchTerm.length > 0) {
            const fetchOptions = async () => {
                try {
                    const results = await findExercise(searchTerm);
                    setOptions(results);
                    setShowDropdown(true);
                } catch (error) {
                    console.error('Error fetching exercise names:', error);
                }
            };

            fetchOptions();
        } else {
            setOptions([]);
            setShowDropdown(false);
        }
    }, [searchTerm]);

    const handleSelect = (option) => {
        setSearchTerm(option);
        setShowDropdown(false);
        if (onSelect) {
            onSelect(option);
        }
        if (onChange) {
            onChange(option);
        }
    };

    const handleInputChange = (e) => {
        setSearchTerm(e.target.value);
        if (onChange) {
            onChange(e.target.value);
        }
    };

    return (
        <div className="search-dropdown">
            <input
                type="text"
                value={searchTerm}
                onChange={handleInputChange}
                placeholder="Search for an exercise..."
                onFocus={() => setShowDropdown(searchTerm.length > 0)}
                onBlur={() => setTimeout(() => setShowDropdown(false), 100)}
            />
            {showDropdown && (
                <ul className="dropdown-list">
                    {options.map((option, index) => (
                        <li key={index} onClick={() => handleSelect(option)}>
                            {option}
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
};

export default SearchDropdown;
