import React, {useEffect, useState} from "react";

import "./ChatBox.css"
import ChatList from "./ChatList";
import Chat from "./Chat.js";


function ChatBox(props) {

    const [openedChat, setOpenedChat] = useState(0);

    useEffect(() => {
        if(props.chat != null) setOpenedChat(props.chat)
    }, [])

    const handleOpenChat = (e) => {
        setOpenedChat(e);
    }
    const closeChat = () => {
        setOpenedChat(0);
    }
    const closeContainer = () => {
        props.close(false);
    }


    return (
        <div className={"Chats"}>
            <div id={"ChatBox-menu"}>
                <div className={"Class-button"}>
                    <button onClick={closeChat}>&lt;</button>
                    <button id={"close-button"} onClick={closeContainer}>x</button>
                </div>
                {(openedChat == 0) ?
                    <div id={"list"}>
                        <ChatList chat={handleOpenChat}/>
                    </div> :
                    <div id={"opened-chat"}>
                        <Chat key={openedChat} id={openedChat}/>
                    </div>
                }
            </div>
        </div>
    )
}

export default ChatBox;