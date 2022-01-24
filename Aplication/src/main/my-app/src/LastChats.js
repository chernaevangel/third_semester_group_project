import React, {useEffect, useState} from "react";
import "./LastChats.css";

import imgPlaceholder from "./logo512.png"
import {getAccount, getAccountChats} from "./services";

function LastChats(props) {

    const [Chats, setChats] = useState();

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

    return (
        <div>
            <div id={"first"}>
                <img src={imgPlaceholder}/>
            </div>
            <div id={"second"}>
                <img src={imgPlaceholder}/>
            </div>
        </div>
    )
}

export default LastChats