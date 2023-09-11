import logointro from "../assets/logointro.png";
import "../styles/signup.css";
import React, { useState } from "react";
import facebookicon from "../assets/facebookicon.png";
import gmailicon from "../assets/gmailicon.png";
import { Link } from "react-router-dom";
import axios from "axios";
export default function SignUp() {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });
  const [isLoading, setIsLoading] = useState(false);

  const [errorMessage, setErrorMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.username || !formData.email || !formData.password) {
      setErrorMessage("Please fill in all fields before signing up."); 
            return;
    }    
    if (!isStrongPassword(formData.password)) {
      setErrorMessage("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
      return;
    }
    setIsLoading(true);

    try {
      const response = await axios.post(
        "http://localhost:9090/moaaz/api/modernhome/users/register",
        {
          name: formData.username,
          email: formData.email,
          password: formData.password,
        }
      );

      if (response.status === 200){
        alert("Sign-up successful");
        console.log("Form data submitted:", formData);
        setFormData({
          username: "", 
          email: "",
          password: "",
        });
        setErrorMessage(""); 
      } else {
        setErrorMessage("Invalid email or password. Please try again."); 
      }
    } catch (error) {
      console.error("Error:", error);
      console.log("Response data:", error.response.data);
      setErrorMessage("An error occurred. Please try again later."); 
    }
    finally {
      setIsLoading(false);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };
  const isStrongPassword = (password) => {
    const regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return regex.test(password);
  };
  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        <h1>Sign Up</h1>
          <div>
          <input
            type="text"
            id="username"
            name="username"
            placeholder="Username"
            value={formData.username}
            onChange={handleChange}
          />
        </div>
        <input
          type="email"
          id="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
        />
        <input
          type="password"
          id="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
        />
        <h4>Or Sign up with </h4>
        <div className="social-container">
          <a href="/" className="social">
            <img src={facebookicon} alt="facebook" />
          </a>
          <a href="/" className="social">
            <img src={gmailicon} alt="gmail" />
          </a>
        </div>
        <div>
          <button type="submit"
          disabled={isLoading}
          > 
          {isLoading ? 'Signing up...' : 'Sign Up'}
          </button>
        </div>
        {errorMessage && <div className="error">{errorMessage}</div>}
        <div>
          <h4>
            Have one? .... <Link to="/signin">Sign In</Link>
          </h4>
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />
    </div>
  );
}