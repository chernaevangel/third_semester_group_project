import React, { useEffect, useState } from "react";
import Interest from "./Interest";
import InterestDropdown from "./InterestDropdown";
import Profile from "./Profile";
import './Stories.css'
import SingularStory from "./SingularStory";
import {
    getAccount,
    getAccountData,
    getInterests,
    getProjectsFromAccount,
    addProjectToAccount,
    deleteProject,
    editOneProject,
    getChat,
    getLanguage
} from "./services";
import AddProject from "./AddProject";
import EditProject from "./EditProject";
import ProjectsExporter from './Portfolio/ProjectsExporter';
import { PDFViewer } from '@react-pdf/renderer';
import Popup from './components/Popup';
import ChatContainer from "./ChatContainer";
import Chat from "./Chat";
import ChatBox from "./ChatBox";


function Stories(prop) {

    const [buttonPopupPdf, setButtonPopupPdf] = useState(false);
    const [projects, setProjects] = useState([]);
    const [edit, setedit] = useState(false);
    const [data, setdata] = useState();
    const [chat, setChat] = useState(undefined);
    const [openedChat, setOpenedChat] = useState(true);

    function pdfHandler (){
       setButtonPopupPdf(true);
    }

    const addProjectasync = async (title, description, link, pcn) => {
        await addProjectToAccount(title, description, link, pcn);
        window.location.reload(false);
    }

    const deleteProjectasync = async (item) => {
        await deleteProject(item.id, item.title, item.description, item.link, getAccount().pcn);
        window.location.reload(false);
    }

    const selectProject = async (item) => {
        setdata(item);
        setedit(true);
        window.scrollTo(0, 500);
    }

    const editProjectasync = async (id, title, description, link, pcn) => {
        // console.log(id, title, description, link, 1234);
        await editOneProject(id, title, description, link, pcn);
        setedit(false);
        window.location.reload(false);
    }

    const openChat = async () => {
            const openChat = await getChat(getAccount().pcn, prop.pcn);
            setChat(openChat);
            setOpenedChat(true)
    }

    const closeChat = () => {
        setOpenedChat(false);
    }

    useEffect(() => {
        if (!prop.rendered.hasRendered) {
            prop.rendered.setHasRendered(true);
            getProjectsFromAccount(prop.pcn)
                .then(items => {
                    setProjects(items);
                })
            return;
        }
    })

    if (projects.length != 0 && getLanguage() === "eng") {
        return (
            <div id="Stories">
               <div id="Stories_g">
                   <Popup trigger={buttonPopupPdf} setTrigger={setButtonPopupPdf}>
                       <PDFViewer><ProjectsExporter/></PDFViewer>
                   </Popup>
                 {(getAccount().pcn == prop.pcn) ? <></> : <button onClick={() => {openChat()}} id={"send-msg-button"}>Message</button>}
                    <div id="Profile" className="Profile">
                        <div id="StudentInfo">
                            <Profile pdf={pdfHandler} rendered={prop.rendered} pcn={prop.pcn} myAccount={prop.myAccount} />
                        </div>
                    </div>
                    <div className="StoriesBg">
                        <rect id="StoriesBg" >
                            <div id="Stories_s">
                                <span>Projects</span>
                            </div>
                            {prop.myAccount === true &&
                                <div id="AddProject">
                                    <AddProject addProjectasync={addProjectasync} />
                                </div>
                            }
                            {prop.myAccount === true && edit === true &&
                                <div id="AddProject">
                                    <EditProject editProjectasync={editProjectasync} story={data}/>
                                </div>
                            }
                            {prop.myAccount === true &&
                                <div id="SingleStoriesWrapper">
                                    {projects.map(item => (
                                        <div id="SingleStory">
                                            <div className="SingleStoryWrapper">
                                                <div id="SingleStoryWrapper" >
                                                    <button id='project-button-delete' onClick={e => deleteProjectasync(item)}>🗑</button>
                                                    <button id='project-button-edit' onClick={e => selectProject(item)}>✎</button>
                                            <SingularStory key={item.id} story={item} />

                                        </div>
                                            </div>
                                        </div>

                                    ))}
                                </div>
                            }
                            {prop.myAccount === false &&
                            <div id="SingleStoriesWrapper">
                                {projects.map(item => (
                                    <div id="SingleStory">
                                        <div className="SingleStoryWrapper">
                                            <div id="SingleStoryWrapper" >
                                                <SingularStory key={item.id} story={item} />
                                            </div>
                                        </div>
                                    </div>

                                ))}
                            </div>
                            }
                        </rect>
                    </div>
                </div>
                <div style={{visibility : openedChat ? 'visible' : 'hidden'}}>
                    {chat !== undefined ? <ChatBox close={setOpenedChat} chat={(getAccount().pcn === chat.pcn1) ? chat.pcn2 : chat.pcn1}/> : ''}
                </div>
            </div>
        )
    }
    else if(projects.length != 0 ){      
        return (
        <div id="Stories">
           <div id="Stories_g">
               <Popup trigger={buttonPopupPdf} setTrigger={setButtonPopupPdf}>
                   <PDFViewer><ProjectsExporter/></PDFViewer>
               </Popup>
             {(getAccount().pcn == prop.pcn) ? <></> : <button onClick={() => {openChat()}} id={"send-msg-button"}>Berichten</button>}
                <div id="Profile" className="Profile">
                    <div id="StudentInfo">
                        <Profile pdf={pdfHandler} rendered={prop.rendered} pcn={prop.pcn} myAccount={prop.myAccount} />
                    </div>
                </div>
                <div className="StoriesBg">
                    <rect id="StoriesBg" >
                        <div id="Stories_s">
                            <span>Projecten</span>
                        </div>
                        {prop.myAccount === true &&
                            <div id="AddProject">
                                <AddProject addProjectasync={addProjectasync} />
                            </div>
                        }
                        {prop.myAccount === true && edit === true &&
                            <div id="AddProject">
                                <EditProject editProjectasync={editProjectasync} story={data}/>
                            </div>
                        }
                        {prop.myAccount === true &&
                            <div id="SingleStoriesWrapper">
                                {projects.map(item => (
                                    <div id="SingleStory">
                                        <div className="SingleStoryWrapper">
                                            <div id="SingleStoryWrapper" >
                                                <button id='project-button-delete' onClick={e => deleteProjectasync(item)}>🗑</button>
                                                <button id='project-button-edit' onClick={e => selectProject(item)}>✎</button>
                                        <SingularStory key={item.id} story={item} />

                                    </div>
                                        </div>
                                    </div>

                                ))}
                            </div>
                        }
                        {prop.myAccount === false &&
                        <div id="SingleStoriesWrapper">
                            {projects.map(item => (
                                <div id="SingleStory">
                                    <div className="SingleStoryWrapper">
                                        <div id="SingleStoryWrapper" >
                                            <SingularStory key={item.id} story={item} />
                                        </div>
                                    </div>
                                </div>

                            ))}
                        </div>
                        }
                    </rect>
                </div>
            </div>
            <div style={{visibility : openedChat ? 'visible' : 'hidden'}}>
                {chat !== undefined ? <ChatBox close={setOpenedChat} chat={(getAccount().pcn === chat.pcn1) ? chat.pcn2 : chat.pcn1}/> : ''}
            </div>
        </div>
    )}
    else return(
        <div id="Stories">
                <div id="Stories_g">
                    <div id="Profile" className="Profile">
                        <div id="StudentInfo">
                            <Profile rendered={prop.rendered} pcn={prop.pcn} myAccount={prop.myAccount} />
                        </div>
                    </div>
                    <div className="StoriesBg">
                        <rect id="StoriesBg" >
                            <div id="Stories_s">
                                <span>Projects</span>
                            </div>

                            {prop.myAccount === true &&
                                <div id="AddProject">
                                    <AddProject addProjectasync={addProjectasync}/>
                                </div>
                            }
                            <div id="SingleStoriesWrapper">
                                </div>
                        </rect>
                    </div>
                </div>
            <div style={{visibility : openedChat ? 'visible' : 'hidden'}}>
                {chat !== undefined ? <ChatBox close={setOpenedChat} chat={(getAccount().pcn === chat.pcn1) ? chat.pcn2 : chat.pcn1}/> : ''}
            </div>
            </div>
        )

}

export default Stories;
