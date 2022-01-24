import React, {useEffect, useState} from "react";
import "./Newsfeed.css"
import {postStory, getAccountData, getNewsfeed, getAccount, getLanguage} from "../services";



export default function Newsfeed(prop) {
    const [stories, setStories] = useState([]);
    const [myAccount, setMyAccount] = useState(null);
    const [title, setTitle] = useState("");

    function updateTitleVal (evt)
    {
        const val = evt.target.value;
        setTitle(val);
    }


    function submitStory()
    {
        const description = window.document.getElementById("storyDescription").innerText;
        postStory(title,description,prop.pcn);
        window.location.reload();
    }


    useEffect( () => {
        getNewsfeed(prop.pcn)
            .then(items => {
                    setStories(items);
            })
    }, [])

    useEffect(() => {
        let mounted = true;
        getAccountData(getAccount().pcn)
            .then(items => {
                if (mounted) {
                    setMyAccount(items);
                }
            })
        return () => mounted = false;
    }, [])


    if (myAccount != null)
    return (
        <div className="NewsfeedWrapper">
            <div className="storyWrapper ">
                <div className="imgHolder">
                    <img src={myAccount.binaryImage} alt="profile picture"/>
                </div>
                <div className="inputContainer">
                    {getLanguage() === "ned"
                        ? <>
                            <input placeholder="Titel" onChange={updateTitleVal} value={title} type="text" />
                            <span className="textareaNed" id="storyDescription" role="textbox" contentEditable></span>
                            <button onClick={submitStory}>Verzend</button>
                        </>
                        : <>
                            <input placeholder="Title" onChange={updateTitleVal} value={title} type="text" />
                            <span className="textareaEng" id="storyDescription" role="textbox" contentEditable></span>
                            <button onClick={submitStory}>Submit</button>
                        </>
                    }
                </div>
            </div>
            {stories.map(story => {
                return (<div className="storyWrapper">
                    <div className="storyProfileInfo">
                        <div className="imgHolder">
                            <img src={story.image} alt="profile picture"/>
                        </div>
                        <div className="nameAndDate">
                            <div className="name">
                                {story.name}
                            </div>
                            <div className="date">
                                {story.date}
                            </div>
                        </div>
                        <div className="clear"></div>
                    </div>
                    <div className="storyBody">
                        <h2>{story.title}</h2>
                        {story.description}
                    </div>
                </div>)
            })}
        </div>
    )
    else return <>Loading</>
}