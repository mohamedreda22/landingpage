import logointro from "../assets/logointro.png";
import "../styles/signIn.css";
import React, { useState } from "react";
import facebookicon from "../assets/facebookicon.png";
import gmailicon from "../assets/gmailicon.png";
import { Link } from "react-router-dom";
import axios from "axios";

export default function SignIn() {  
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [error, setError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.email || !formData.password) {
      setError("Please fill in both fields.");
      return;
    }
    setIsLoading(true);

    try {
      const response = await axios.post(`http://localhost:9090/moaaz/api/modernhome/auth/login?email=${formData.email}&password=${formData.password}`);
      console.log("Response:", response );
      if (response&&response.status === 202) {
        alert("Sign-in successful");
        console.log("Form data submitted:", response.data);
        setFormData({
          email: "",
          password: "",
        });
      } else {
        alert("Sign-in unsuccessful");
        console.log("Response data:", response.data);
      }
    } catch (error) {
      console.error("Error:", error);
      console.log("Response data:", error.response?error.response.data:"" );
      alert("An error occurred. Please try again later.");
    } finally {
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

  return (
    <div className="container">
      <form onSubmit={handleSubmit}>
        <h1>Sign in</h1>
        <h5>Or use email account</h5>
        <div className="social-container">
          <a href="/" className="social">
            <img src={facebookicon} alt="facebook" />
          </a>
          <a href="/" className="social">
            <img src={gmailicon} alt="gmail" />
          </a>
        </div>

        <div>
          <input  
            type="email"
            id="email"
            name="email"
            placeholder="Email"
            value={formData.email}
            onChange={handleChange}
          />
        </div>
        <input 
          type="password"
          id="password" 
          name="password" 
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
        />
        <div>
          <button 
            type="submit"
            disabled={isLoading}
          >
            {isLoading ? 'Signing in...' : 'Sign In'}
          </button>
        </div>
        {error && <div className="error">{error}</div>}
        <div>
          <h4>
            New one ?  .... 
            <Link to="/signup">Sign Up</Link>
          </h4>
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />
    </div>
  );
}
