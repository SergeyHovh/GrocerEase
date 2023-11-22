import React from 'react';
import './App.css';
import {Navbar} from "./components/navbar/Navbar";
import {AllCustomersCard} from "./components/moderation/AllCustomersCard";

function App() {
    return (
        <div className="App">
            <Navbar/>
            <AllCustomersCard/>
        </div>
    );
}

export default App;
