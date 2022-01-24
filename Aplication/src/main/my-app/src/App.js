import React, { Component, useState, useEffect } from 'react';
import './App.css';
import ReactDOM from 'react-dom';
import TopBar from "./TopBar";
import AccountPage from "./AccountPage";

import {connectToChats, getAccount} from "./services";
import Login from './Login';
import MessageIcon from "./MessageIcon";
import LastChats from "./LastChats";
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import useSound from 'use-sound';
import messageSound from "./media/juntos-607.mp3"
import {getAccountChats} from "./services";
import Newsfeed from "./newsfeed/Newsfeed";

function App(prop) {
    const [pcn, setPcn] = useState(prop.pcn);
    const [hasRendered, setHasRendered] = useState(false);
    const [hasRendered2, setHasRendered2] = useState(false);
    const [hasRendered3, setHasRendered3] = useState(false);
    const [hasRendered4, setHasRendered4] = useState(false);
    const rendered = {hasRendered, setHasRendered, hasRendered2, setHasRendered2, hasRendered3, setHasRendered3}
    const renderedStoryes = { hasRendered4, setHasRendered4};
    const [myAccount, setMyAccount] = useState(true);
    const [openedChat, setOpenedChat] = useState(0);
    const [openNewsfeed, setOpenNewsfeed] = useState(false);



    useEffect(() => {
        connectToChats(getAccount().pcn).then();
    }, []);

    function setPcnStates(pcn) {
        setPcn(pcn);
        setHasRendered(false);
        setHasRendered2(false);
        setHasRendered3(false);
        setHasRendered4(false);
        setMyAccount(false)
    }

    if (pcn != undefined) {
        return (
            <div className={"app"}>
                <div><TopBar setNewsfeed={setOpenNewsfeed} setPcn={setPcn} setPcnStates={setPcnStates} setMyAccount={setMyAccount}/></div>
                <div>
                    {!openNewsfeed ?
                        <AccountPage renderedStoryes={renderedStoryes} pcn={pcn} setPcn={setPcn} rendered={rendered} setPcnStates={setPcnStates}
                                     myAccount={myAccount}/>
                        :
                        <Newsfeed pcn={pcn}/>
                    }

                </div>
                <MessageIcon openChat={setOpenedChat} openedChat={openedChat} pcn={pcn}/>
            </div>
        )
    }
}
export default App;