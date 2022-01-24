import React from 'react'
import "./ExportHelper.css"

export function ExportHelper({ title,description, link}) {
    return (
        <div className="projectsWrapepr">
               {'\n'}
            <ul>    
               <li>Title: {title }</li> {'\n'}
                <li>Description: {description}</li>{'\n'}
                <li>Link: {link}</li>{'\n'}             
           </ul>
     
        </div>
    )
}

export default ExportHelper
