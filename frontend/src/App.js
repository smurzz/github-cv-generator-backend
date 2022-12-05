import React from 'react';
import './App.css';
import Home from './Home';
import Resume from './Resume';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route exact path="/" element={<Home/>}/>
        <Route path='/resume/:login' element={<Resume/>}/>
      </Routes>
    </Router>
  )
}

export default App;