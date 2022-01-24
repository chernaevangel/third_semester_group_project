import React from 'react'

function ExperiencesExportHelper({type,duration,description,institute}) {
    return (
        <div>
        {'\n'}
     <ul>    
         <li>Type of experience: {type}</li>{'\n'}
         <li>Duration: {duration}</li>{'\n'}
         <li>Description: {description}</li>{'\n'}
         <li>Institute: {institute}</li>{'\n'}           
    </ul>

 </div>
    )
}

export default ExperiencesExportHelper
