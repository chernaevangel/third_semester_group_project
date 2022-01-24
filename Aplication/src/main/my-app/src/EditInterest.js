import React, { useState } from 'react'
import './EditInterest.css'

import InterestEdit from './InterestEdit'
import { addInterest, getLanguage } from './services'

function EditInterest(prop) {
    const [input, setInput] = useState()
    
    return (
        <div className={"edit-interest"}>
            <div className={"add"}>
                <input id={"add-interest"} onChange={(e) => {setInput(e.target.value)}} maxLength={14}></input>
                {getLanguage() === "ned"
                    ? <button id={"add-interest-button"} onClick={() => prop.addInterestAsync(input)}>➕</button>
                    : <button id={"add-interest-button"} onClick={() => prop.addInterestAsync(input)}>➕</button>
                }
            </div>
            
            <div id={"interest-list"}>
                {prop.interests.map(item => (
                    <li className={"interest-item"}><InterestEdit key={item.id} interest={item} deleteInterestAsync={prop.deleteInterestAsync}/> </li>
                ))}
            </div>
        </div>
    )
}

export default EditInterest
