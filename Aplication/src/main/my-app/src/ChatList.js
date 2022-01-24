import React, {useEffect, useState} from "react";
import "./ChatList.css"

import {getAccount, getAccountChats, searchForChats, getLanguage} from "./services";
import OnePendingRequest from "./connections/OnePendingRequest";
import ChatContainer from "./ChatContainer";

function ChatList(props) {

    const [Chats, setChats] = useState(undefined);

    useEffect(() => {
        let mounted = true;
        getAccountChats(getAccount().pcn)
            .then(items => {
                if (mounted) {
                    setChats(items)
                }
            })
        return () => mounted = false;
    }, [])

    const openChat = (e) => {
        props.chat(e);
    }

    const searchChats = async (search) => {
        if(search !== '')
        {
            let pcn = getAccount().pcn
            let newChats = await searchForChats(search, pcn);
            if (newChats != undefined) {
                setChats(newChats);
            }
        }
        if(search === '') {
            getAccountChats(getAccount().pcn)
                .then(items => {
                        setChats(items)
                })
        }
        /*setChats(newChats);*/

    }

    if(Chats === undefined) {
        return "";
    }
        else {
            if(getLanguage() === "eng"){
                return (
                    <div className="chat-list">
                        <div id={"title"}>Chats</div>
                        <div id={"search"}><input type={"text"} placeholder={"Search for people..."} onChange={event => searchChats(event.target.value)}/></div>
                        <div id={"list-of-user-chats"}>
                            {Chats.map(message => (
                                <ChatContainer id={(getAccount().pcn === message.pcn1) ? message.pcn2 : message.pcn1} {...message} open={openChat} key={message.id}/>
                            ))
                            }
                        </div>
                    </div>
            )}
            else return (
                <div className="chat-list">
                    <div id={"title"}>Chats</div>
                    <div id={"search"}><input type={"text"} placeholder={"Zoek voor mensen..."} onChange={event => searchChats(event.target.value)}/></div>
                    <div id={"list-of-user-chats"}>
                        {Chats.map(message => (
                            <ChatContainer id={(getAccount().pcn === message.pcn1) ? message.pcn2 : message.pcn1} {...message} open={openChat} key={message.id}/>
                        ))
                        }
                    </div>
                </div>
            )
        }
}

export default ChatList;