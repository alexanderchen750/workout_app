import axios from 'axios';
import { endpoints } from '../utils/utils.js';
import { AuthContext } from '../context/AuthContext.js';


export const createUser = async (name, email, password, authContext) => {
    const userData = {
        name,
        email,
        password,
    };
    try {
        const response = await axios.post(endpoints.registerUser, userData, {
            headers: {
                "Content-Type": "application/json",
            },
        });
    
        if (response.status === 200 || response.status === 201) {
            const data = response.data;
            authContext.login(data.token, data.name);  // Assuming the token is returned in response.data
            return true;
        } else {
            console.log("Registration failed");
            return false;
        }
    } catch (error) {
        if (error.response && error.response.status === 409) {
            // Handle the 409 Conflict error (email already exists)
            return { success: false, message: 'Email already exists' };
        } else {
            // Handle other errors
            console.error("Error:", error);
            return { success: false, message: 'An unexpected error occurred' };
        }
      }
};

export const login = async (email, password, authContext) => {
    const userData = {
        email,
        password,
    };
    try{
        const response = await axios.post(endpoints.authenticateUser, userData, {
            headers: {
                "Content-Type": "application/json",
            },
        });
        if (response.status === 200 || response.status === 201) {
            const data = response.data;
            authContext.login(data.token, data.name);  // Assuming the token is returned in response.data

            return true;
        } else {
            console.log("Login Failed");
            return false;   
        }

    } catch (error){
        if(error.response && error.response.status === 401){
            return { success: false, message: 'Incorrect Email or Password' };
        }
        else{
            console.error("Error:", error);
            return {success: false, message: 'An unexpected error occurred'};
        }
    }

}

export const fetchUserDetails = async (token) => {
    try {
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.get(endpoints.getUserDetails, config);
        return response.data.name;
    } catch (error) {
        console.error('Error fetching user details:', error);
        throw error;
    }
};