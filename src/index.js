import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router } from 'react-router-dom'; 
import { RouterProvider } from 'react-router-dom';
import { router } from './Router';
import './index.css';
import App from './App';
const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <RouterProvider router={router} >
    <Router>
      <App />
    </Router>
  </RouterProvider>
);
