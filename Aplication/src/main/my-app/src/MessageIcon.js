import React from "react";
import {useState, useEffect} from "react";

import "./MessageIcon.css"
import ChatIcon from "./media/img_156439.png"
import ChatBox from "./ChatBox";
import LastChats from "./LastChats";

function MessageIcon(props) {

    const [chatVisibilityState, setChatVisibilityState] = useState(false);

    const toggleChatVisibilityState = () => {
        setChatVisibilityState(!chatVisibilityState)
    }
    return(
        <div className={"message-icon"}>
            <button onClick={toggleChatVisibilityState}>
                <img id={"icon"} src={ChatIcon}/>
            </button>
            <div id={"chats"} style={{visibility : chatVisibilityState ? 'visible' : 'hidden'}}>
                <ChatBox close={setChatVisibilityState}/>
            </div>
        </div>
    )
}

export default MessageIcon;