import React from 'react'
import axios from "axios";
import { url } from '../config/config';
import { getLanguage } from '../services';

function OnePerson(prop) {

    const deleteStory = () =>{
        axios.
        delete(url + `/story/${prop.person.id}`)
        .then((response) =>{
            if(response.status===200){
                  window.location.reload();
            }
        }
        )
     }

     if(getLanguage() === "ned"){
        return (
            <div id='SinglePersonWrapper'>
                <div id='Name'> {prop.person.title}</div>
                <p>
                    {prop.person.description}
                </p>
                <div id="storryAccountDate">
                    <p>Datum: {prop.person.date}</p>
                </div>
                {prop.myAccount ?
                        <buton id="deleteAccStoryButton" onClick={deleteStory}>Verwijderen</buton>
                        : <></>
                    }
                
            </div>
        )
     }
     else{

     }
    return (
        <div id='SinglePersonWrapper'>
                <div id='Name'> {prop.person.title}</div>
                <p>
                    {prop.person.description}
                </p>
                <div id="storryAccountDate">
                    <p>Date: {prop.person.date}</p>
                </div>
                {prop.myAccount ?
                        <buton id="deleteAccStoryButton" onClick={deleteStory}>Delete</buton>
                        : <></>
                    }
                
            </div>
    )
}

export default OnePerson
