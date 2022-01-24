import React, { useEffect, useState } from "react";
import './AddProject.css'
import {getAccount, getLanguage} from './services'

function EditProject(prop) {
    const [title, settitle] = useState(prop.story.title);
    const [description, setdescription] = useState(prop.story.description);
    const [link, setlink] = useState(prop.story.link);
    const [id, setid] = useState(prop.story.id);

//the inputs need to be changed to textareas and that might break the submit
if(getLanguage() === "eng"){
    return (
        <div id='AddWrapper'>
          <label>Edit project {prop.story.title}</label>
          <input type="text" value={title} onChange={(e)=>{settitle(e.target.value)}} id='TitleBox'/> 
          <textarea type="text" value={description} onChange={(e)=>{setdescription(e.target.value)}} id='DescriptionBox'/>
          <input type="text" value={link}  onChange={(e)=>{setlink(e.target.value)}} id='LinkBox'/> 
          <button onClick={e => prop.editProjectasync(id, title, description, link, getAccount().pcn)} id='AddButton'>Update Project</button>  
        </div>
    )
}
else return (
    <div id='AddWrapper'>
      <label>Verander project {prop.story.title}</label>
      <input type="text" value={title} onChange={(e)=>{settitle(e.target.value)}} id='TitleBox'/> 
      <textarea type="text" value={description} onChange={(e)=>{setdescription(e.target.value)}} id='DescriptionBox'/>
      <input type="text" value={link}  onChange={(e)=>{setlink(e.target.value)}} id='LinkBox'/> 
      <button onClick={e => prop.editProjectasync(id, title, description, link, getAccount().pcn)} id='AddButton'>Update Project</button>  
    </div>
)}
export default EditProject
