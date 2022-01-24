import React, { useState, useEffect } from 'react'
import { acceptRequest, getAccount, getAllPendingRequests, getLanguage } from '../services';
import OnePendingRequest from './OnePendingRequest';
import "./PendingRequests.css";

function PendingRequests({showReq}) {
    const [PendingRequests, setPendingRequests] = useState(false);

    useEffect(() => {
        let mounted = true;
        getAllPendingRequests(getAccount().pcn)
            .then(items => {
                if (mounted) {
                    setPendingRequests(items);
                }
            })
        return () => mounted = false;
    }, [])

    const acceptRequestAsync = async (request) => {
        await acceptRequest(request);
        getAllPendingRequests(getAccount().pcn)
            .then(items => {setPendingRequests(items)})
    }




    if(showReq)
    {
        return null;
    }

    if (PendingRequests === false) {
        return "";
    }
    else if (PendingRequests === undefined) {
        if(getLanguage() === "eng"){
        return (
            <div className="request-list">
                <p>No Pending Requests</p>
            </div>
        )}
        else return (
            <div className="request-list">
                <p>Geen vriendschapsverzoeken</p>
            </div>
        )
    }
    else {
        if(getLanguage() === "eng"){
            return (
                <div className="request-list">
                    <div id="PendingRequests">Pending requests</div>
                    {PendingRequests.map(item => (
                        <>
                            {item.pcn1 != getAccount().pcn &&
                                <OnePendingRequest request={item} acceptRequest={acceptRequestAsync} />
                            }
                        </>
                    ))}
                </div>
            )}
            else return (
                    <div className="request-list">
                        <div id="PendingRequests">vriendschapsverzoeken</div>
                        {PendingRequests.map(item => (
                            <>
                                {item.pcn1 != getAccount().pcn &&
                                    <OnePendingRequest request={item} acceptRequest={acceptRequestAsync} />
                                }
                            </>
                        ))}
                    </div>
            )
        
    }
}

export default PendingRequests
