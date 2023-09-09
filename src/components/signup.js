import logointro from "../assets/logointro.png";
import "../styles/signup.css";
import React, { useState } from "react";
import facebookicon from "../assets/facebookicon.png";
import gmailicon from "../assets/gmailicon.png";
import { Link } from "react-router-dom";

export default function SignIn() {
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
  });

  const handleSubmit = async (e) => {
    e.preventDefault();

 if (!formData.username || !formData.email || !formData.password) {
      alert("Please fill in all fields before signing up.");
      return;
    }
    try {
      const response = await fetch('localhost:9090/moaaz/api/modernhome/users/register', {
        method: 'post',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("Sign-up successful");
        console.log("Form data submitted:", formData);
        setFormData({
          email: "",
          password: "",
        });
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
        <h4 >Or Sign up with </h4>
        <div className="social-container">
          <a href="/" className="social">
            <img src={facebookicon} alt="facebook" />
          </a>
          <a href="/" className="social">
            <img src={gmailicon} alt="gmail" />
          </a>
        </div>
        <div>
          <button  type="submit">Sign Up</button>
        </div>
        <div>
          <h4>
            Have one? .... 
            <Link to="/signin">Sign In</Link>
          </h4>
        </div>
      </form>
      <img src={logointro} alt="logo" className="img" />
    </div>
  );
}
