import React,{useState,useEffect} from 'react'
import { Page, Text, View, Document ,StyleSheet } from '@react-pdf/renderer';
import axios from "axios";
import "./ProjectsExporter.css"
import ExportHelper from './ExportHelper';
import SkillsExportHelper from './SkillsExportHelper';
import InterestsExportHelper from './InterestsExportHelper';
import ExperiencesExportHelper from './ExperiencesExportHelper';
import {getAccount} from "../services";
import { url } from '../config/config';


export function ProjectsExporter() {

    const [post, setPost] = useState([]); 
    const [skills, setSkills] = useState([]);
    const [interests, setInterests] = useState([]);
    const [experiences, setExperiences] = useState([]);
    const [account, setAccount] = useState();

    const styles = StyleSheet.create({
      page: {
        backgroundColor: 'rgb(248, 229, 241)'
      },
      section: {
        margin: 20,
        padding: 20,
        flexGrow: 1
      },
      text: {
        fontSize:25 ,


        
      },
      item: {
        marginBottom: 10
      }
    });

    

    useEffect(() => { 
      axios.get(url + `/account/${getAccount().pcn}/projects`).then((response) => { 
        if(response.status===200){
          console.log(response.data);
          setPost(response.data);
        }else{
          console.log("projects are not loading")
        }
      }); 
    }, []); 

    useEffect(() => { 
      axios.get(url + `/account/${getAccount().pcn}/skills`).then((response) => { 
        if(response.status===200){
          console.log(response.data);
          setSkills(response.data);
        }else{
          console.log("skills are not loading")
        }
      }); 
    }, []); 

    useEffect(() => { 
      axios.get(url + `/account/${getAccount().pcn}/interests`).then((response) => { 
        if(response.status===200){
          console.log(response.data);
          setInterests(response.data);
        }else{
          console.log("interests are not loading")
        }
      }); 
    }, []); 

    useEffect(() => { 
      axios.get(url + `/account/${getAccount().pcn}/experiences`).then((response) => { 
        if(response.status===200){
          console.log(response.data);
          setExperiences(response.data);
        }else{
          console.log("experiences are not loading")
        }
      }); 
    }, []); 
  
    return (
     <Document>   
           <Page size="A4" title="Portfolio" style={styles.page}>              
          <View style={styles.section}> 
          <Text> <h1 style={styles.text}>Projects:</h1>{'\n'}
            {post.map((project, id)=> {           
                    return( 
                      <ExportHelper
                      title={project.title}
                      description={project.description}
                      link={project.link} />
                 )
                })} 
                </Text>
              </View>        
              <View style={styles.section}>
              <Text><h1 style={styles.text}>Skills:</h1>{'\n'}
                {skills.map((skill, id)=>{
                    return(
                      <SkillsExportHelper
                      skill={skill.skill}
                      description={skill.description}/>
                  )
                })}
                </Text>     
            </View>
            <View style={styles.section}>
            <Text><h1 style={styles.text}>Interests:</h1>{'\n'}
            {interests.map((interest, id)=>{
                    return(
                      <InterestsExportHelper
                      interest={interest.interest} />          
                  )
                })}
                </Text> 
            </View>
            <View style={styles.section}>
            <Text><h1 style={styles.text}>Experiences:</h1>{'\n'}
            {experiences.map((experience, id)=>{
                    return(
                      <ExperiencesExportHelper
                      type={experience.type}
                      duration={experience.duration}
                      description={experience.description}
                      institute={experience.institute}/>
                  )
                })}
                </Text>  
            </View>
         
         </Page>
     </Document>
    )
}

export default ProjectsExporter


