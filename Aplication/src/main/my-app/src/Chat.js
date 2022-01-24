import React, {useEffect, useState} from "react";

import {getChat, getAccount,getAccountData, sendMessage} from './services'



import "./Chat.css";
import Message from "./Message";
import sendImg from "./media/60525.png"

let chatRender = false;

function Chat(props) {

    const [chat, setChat] = useState({});
    const [messages, setMessages] = useState([]);
    const [hasRendered, setHasRendered] = useState(false);

    useEffect( () => {
        updateChat();
    }, [])

    const updateChat = async () => {
        if(!hasRendered) {
            await getChat(getAccount().pcn, props.id)
                .then(response => {
                    setChat(response);
                    setMessages(response.messages)
                    setHasRendered(true);
                    document.getElementById('scroller').scrollIntoView();
                })
        }
    }

    document.addEventListener('build', function () {updateChat()});


    window.onkeypress = function(event) {
        if (event.keyCode == 13) {
            send();
        }
    }

    const send = async () => {
        let msg = document.getElementById("text").value.trim();
        if(msg != "")
        {
            const account = await getAccountData(getAccount().pcn)
            const message = {
                chatId : chat.id,
                senderPCN : getAccount().pcn,
                message : msg,
                senderName : account.name
            }
            sendMessage(message);
            document.getElementById('text').value = ''
        }
    }

    return (
        <div id={"chat"}>
            <div id={"name"}>{(getAccount().pcn === chat.pcn1) ? chat.account2 : chat.account1}</div>
            <div className={"chat"}>
                    <div id={"messages"}>
                        {(messages !== null) ? messages.map(msg => (
                            <Message message={msg}/>
                        )) : ""}
                        <div id={"scroller"}></div>
                    </div>
                <div className={"sendbox"}>
                    <div className={"textbox"}><input id={"text"} type={"text"}/></div>
                    <img src={sendImg} id={"send"} onClick={() => {send()}}/>
                </div>
            </div>
        </div>
        )
}

export default Chat