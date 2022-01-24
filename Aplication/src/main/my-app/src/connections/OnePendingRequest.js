import React, { useState, useEffect } from 'react'
import { getAccountData } from '../services';

function OnePendingRequest(prop) {
    const [person, setPerson] = useState();

    useEffect(() => {
        let mounted = true;
        getAccountData(prop.request.pcn1)
            .then(items => {
                if (mounted) {
                    setPerson(items);
                }
            })
        return () => mounted = false;
    }, [])

    const acceptRequest = () => {
        prop.acceptRequest(prop.request);

    }

    if (person !== undefined){
        return (
            <div className="request-list-item">

                <div className="user-main-info">
                    <div className="user-pic">
                        <img id="UserPic" src="../logo512.png">
                        </img>
                    </div>
                    <div className="name-box">
                        <div className="request-list-item-name"><div>{person.name}</div></div>
                        <div className="request-list-item-type">{person.type}</div>
                    </div>
                </div>
                <div className="request-list-item-bio">
                    <div>Description: {person.bio}</div>
                </div>

                <div className="action-menu">
                    <div className="btn-accept">
                        <button className="request-list-item-accept" onClick={acceptRequest}>Accept</button>
                    </div>
                    <div className="btn-decline">
                        <button className="request-list-item-decline" onClick={() => prop.acceptRequest(prop.request)}>Decline</button>
                    </div>
                </div>

            </div>
        )
    }
    return "";
    
}

export default OnePendingRequest
