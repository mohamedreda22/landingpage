import logointro from "../assets/logointro.png";
import "../styles/signIn.css";
import React from "react";
import facebookicon from "../assets/facebookicon.png";
import gmailicon from "../assets/gmailicon.png";
import { useState } from "react";
import { Link } from "react-router-dom";
export default function SignIn() {  
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.email || !formData.password) {
      alert("Please fill in both fields.");
      return;
    }

    try {
      const response = await fetch('localhost:9090/moaaz/api/modernhome/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("Sign-in successful");
        console.log("Form data submitted:", formData);
        setFormData({
          email: "",
          password: "",
        });
        return response.json();
      } else {
        alert("Invalid email or password. Please try again.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("An error occurred. Please try again later.");
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
          placeholder="Username"
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
          <button type="submit">Sign In</button>
        </div>
        <div>
            <h4>New one ?  .... 
              <Link  to="/signup">Sign Up</Link>
              </h4>
    
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />

    </div>
  );
}
