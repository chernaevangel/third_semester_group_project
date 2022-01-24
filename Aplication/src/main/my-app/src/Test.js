import React, {Component, useState, useEffect} from 'react';
import logo from './logo.svg';
import './App.css';
import { getAccount, setAccount } from '../src/services'


function App () {
    const [message, setMessage] = useState("");
    useEffect(() => {
        fetch('/api/hello')
            .then(response => response.text())
            .then(message => {
                setMessage(message);
            });
    },[])
    return (
        <div className="App">
            <div className="App-title">{message}</div>
        </div>
    )
}

export default App;