import React, {useEffect, useState} from "react";
import "./ChatContainer.css";
import IMGPlaceholder from "./logo512.png"

import {getAccount} from "./services";

function ChatContainer(props) {


    return (
        <div className={"chat-container"} onClick={() => {(getAccount().pcn === props.pcn1) ? props.open(props.pcn2) : props.open(props.pcn1)}}>
            <img id={"img"} src={IMGPlaceholder}/>
            <div id={"chat-name"}>
                {getAccount().pcn === props.pcn1 ? props.account2 : props.account1}
            </div>
        </div>
    )
}

export default ChatContainer;