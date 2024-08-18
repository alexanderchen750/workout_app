import React, {useState, useContext} from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../context/AuthContext";
import { login } from "../../services/AuthService";
import "./SignIn.js";

const SignIn = ({}) => {
    // State to store input values
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null); // State to store error messages
    const authContext = useContext(AuthContext);
    const navigate = useNavigate();

    // Function to handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevent the default form submission behavior

        const result = await login(email, password, authContext);

        if (result.success) {
            console.log("Login Sucess")
            navigate("/workouts"); // Redirect to the Workouts page if registration is successful
        } else {
            setError(result.message); // Set the error message to display to the user
        }
    };

    return (
        <div className="register-container">
            <div className="register-header">
                <h2>Sign in to your account</h2>
            </div>
            <form onSubmit={handleSubmit} className="form-container">
                {error && <div className="error-message">{error}</div>} {/* Display error message */}
                <div className="input-row">
                    <div className="input-row-text">
                        <p>Email</p>
                    </div>
                    <div className="input-row-box">
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)} // Update state on input change
                            required
                   
                        />
                    </div>
                </div>
                <div className="input-row">
                    <div className="input-row-text">
                        <p>Password</p>
                    </div>
                    <div className="input-row-box">
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)} // Update state on input change
                            required
                        />
                    </div>
                </div>
                <div className="register-base">
                    <button type="submit" className="button-signup">Sign In</button>
                </div>
            </form>
        </div>
    );
};

export default SignIn;
