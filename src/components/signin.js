import logointro from "../assets/logointro.png";
import "../styles/signIn.css";
import React from "react";
import facebookicon from "../assets/facebookicon.png";
import gmailicon from "../assets/gmailicon.png";
import { useState } from "react";
export default function SignIn() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const handleSubmit = (e) => {
    e.preventDefault();
    if (!formData.email || !formData.password) {
      alert("Please fill in all fields before signing in.");
      return;
    }
    else{
      alert("You have successfully signed in.");
      console.log("Form data submitted:", formData);
setFormData({
        email: "",
        password: "",
      });
    }
    }
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    }

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
            <h4>New one ?  .... <a href="/signup" target="">Sign Up</a></h4>
    
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />

    </div>
  );
}
