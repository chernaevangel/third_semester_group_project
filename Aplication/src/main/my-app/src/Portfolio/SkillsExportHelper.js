import React from 'react'

export function SkillsExportHelper({skill, description}) {
    return (
        <div>
               {'\n'}
            <ul>    
               <li>Skill: {skill }</li> {'\n'}
                <li>Description: {description}</li>{'\n'}
                     
           </ul>
     
        </div>
    )
}

export default SkillsExportHelper
