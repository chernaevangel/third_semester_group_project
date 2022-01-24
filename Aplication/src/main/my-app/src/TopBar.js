import React, { useEffect, useState } from "react";
import {getAccount, getAccountData, getInterests, getALlAccounts, isAccountVisible, makeConnectionRequest} from "./services";
import './TopBar.css'
import PendingRequests from "./connections/PendingRequests";
import { ReactSearchAutocomplete } from 'react-search-autocomplete'
import Popup from "./components/Popup";

function TopBar(prop) {
    const [account, setAccount] = useState();
    const [interests, setInterests] = useState([]);
    const [showReq, setShowReq] = useState(true);
    const [allPeople, setAllpeople] = useState([]);
    const [typedText, setTypedText] = useState("");
    const [buttonPopup, setButtonPopup] = useState(false);
    const [requestPcn, setRequestPcn] = useState()

    const profileImage = (acc) =>
    {

        if(acc.binaryImage == null)
        {
            return "./defaultPhoto.png";
        }
        else{
            return acc.binaryImage;
        }
    }


    useEffect(() => {
        let mounted = true;
        getALlAccounts()
            .then(items => {
                setAllpeople(items);
                console.log(items);
            })
        getInterests(getAccount().pcn)
            .then(items => {
                if (mounted) {
                    setInterests(items)
                }
            })
        return () => mounted = false;
    }, [])

    useEffect(() => {
        let Account = getAccount();

        let mounted = true;
        getAccountData(Account.pcn)
            .then(items => {
                if (mounted) {
                    setAccount(items)
                }
            })
        return () => mounted = false;
    }, [])


    const showRequests = () =>
    {
        setShowReq(!showReq);
    }

    const handleOnSearch = (string, results) => {
        // onSearch will have as the first callback parameter
        // the string searched and for the second the results.
        // console.log(string, results)
    }

    const handleOnHover = (result) => {
        // the item hovered
        //console.log(result)
    }

    const handleOnSelect = async (item) => {
        setTypedText(item.name);
        if (await isAccountVisible(item.pcn, account.pcn)) {
            prop.setPcnStates(item.pcn);
            prop.setNewsfeed(false);
        }
        else {
            setRequestPcn(item.pcn);
            setButtonPopup(true);
        }

    }

    const handleOnFocus = () => {
        //console.log('Focused')
    }

    const openNewsfeed = () => {
        prop.setNewsfeed(true);
    }

    const formatResult = (pcn) => {
        const account = allPeople.find(acc => acc.pcn == pcn);
        return (<p dangerouslySetInnerHTML={{__html:
                '<div class="search-list-img">' +
                '<img src="' + profileImage(account) + '" alt="">' +
                '</div>' +
                '<div class="search-list-name">' +
                '<strong>'+account.name+'</strong>' +
                '</div>'
        }}></p>); //To format result as html
    }

    if (account != undefined){
        return (
            <div id="TopBar">
                <div className="TopbarBg">

                    <div onClick={openNewsfeed} id="Logo">
                        <span>Linkedtys</span>
                    </div>

                    <div id="SearchBar">
                        <div className="SearchBg">
                            <ReactSearchAutocomplete
                                items={allPeople}
                                onSearch={handleOnSearch}
                                onHover={handleOnHover}
                                onSelect={handleOnSelect}
                                onFocus={handleOnFocus}
                                showIcon={false}
                                autoFocus
                                formatResult={formatResult}
                                inputSearchString={typedText}
                                styling={{
                                    zIndex:"3",
                                    height: "30px"
                                }}
                                resultStringKeyName="pcn"
                            />
                            {/*<input id="Search" type="text" value="Search...">*/}
                            {/*</input>*/}
                            <div id="SearchBg">
                            </div>
                        </div>
                        <Popup trigger={buttonPopup} setTrigger={setButtonPopup}>
                            <div>You don't have a connection with this person. Do you want to send a connection request?</div>
                            <button id="ConnectionButton" onClick={() => {
                                console.log(getAccount().pcn);
                                makeConnectionRequest(getAccount().pcn, requestPcn);
                                setButtonPopup(false);
                            }}>Send Connection request</button>
                        </Popup>
                    </div>
                    <div id="LoggedUser" className="LoggedUser">
                        <div className="small-panel">
                            <button className="pending-requests-btn" onClick={showRequests}></button>
                            <PendingRequests showReq={showReq}/>
                        </div>
                        <div id="UserWrapper" onClick={() => {
                            prop.setPcnStates(getAccount().pcn);
                            prop.setMyAccount(true);
                            prop.setNewsfeed(false);
                        }}>
                            <div id="UserName">
                                <span>{account.name}</span>
                            </div>
                            <div className="UserPic">
                                <img id="UserPic" alt="User" src={profileImage(account)}>
                                </img>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        )
    }
    else{
        return (
            <p>Loading Please Wait</p>
        )
    }

}

export default TopBar;