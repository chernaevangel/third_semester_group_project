import React from "react";
import "./Message.css";

import {getAccount} from "./services";

function Message(props) {
    return (
        <div className={"msg"}>
            <div className={(props.message.senderPCN === getAccount().pcn) ? "message-sent" : "message-received"}>
                <div id={"message"}>{props.message.message}</div>
                <div id={"sender"}>{props.message.senderName}</div>
            </div>
        </div>


    )
}

export default Message
