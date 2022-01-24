import React from 'react'
import './AddProject.css'
import {getAccount, getLanguage} from './services'

function AddProject(prop) {
    var title;
    var description;
    var link;
//the inputs need to be changed to textareas and that might break the submit
if(getLanguage() === "eng"){
    return (
        <div id='AddWrapper'>
            <input id='TitleBox' onChange={e => title = e.target.value} placeholder="title"></input>
            <textarea id='DescriptionBox' onChange={e => description = e.target.value} placeholder="description"></textarea>
            <input id='LinkBox' onChange={e => link = e.target.value} placeholder="link"></input>
            <button id='AddButton' onClick={e => prop.addProjectasync(title, description, link, getAccount().pcn) }>Submit</button>
        </div>
    )
}
else return(
    <div id='AddWrapper'>
    <input id='TitleBox' onChange={e => title = e.target.value} placeholder="Titel"></input>
    <textarea id='DescriptionBox' onChange={e => description = e.target.value} placeholder="Beschrijving"></textarea>
    <input id='LinkBox' onChange={e => link = e.target.value} placeholder="Link"></input>
    <button id='AddButton' onClick={e => prop.addProjectasync(title, description, link, getAccount().pcn) }>Plaats</button>
</div>
)}
 

export default AddProject
