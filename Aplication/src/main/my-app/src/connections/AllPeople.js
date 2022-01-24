import React, { useState, useEffect } from 'react'
import { getAccount, getALlAccounts, getLanguage, isAccountVisible, makeConnectionRequest } from '../services';
import OnePerson from './OnePerson';
import Popup from '../components/Popup';
import './AllPeople.css';
import { url } from '../config/config';

import axios from "axios";

function AllPeople(prop) {
    const [accounts, setAccounts] = useState();
    const [buttonPopup, setButtonPopup] = useState(false);
    const [requestPcn, setRequestPcn] = useState();
    const [stories, setStories] = useState([]);

    // useEffect(() => {
    //     axios.get(url + `/story/account/${prop.pcn}`).then((response) => {
    //         if (response.status === 200) {

    //             setStories(response.data);

    //         } else {
    //             console.log("stories are not loading")
    //         }
    //     });
    // }, []);
    useEffect(() => {
        if (!prop.renderedStoryes.hasRendered4) {
            prop.renderedStoryes.setHasRendered4(true);
            axios.get(url + `/story/account/${prop.pcn}`).then((response) => {
                if (response.status === 200) {

                    setStories(response.data);

                } else {
                    console.log("stories are not loading")
                }
            });
            return;
        }
    })

    if (stories != undefined) {
        return (
            <div id='AllPeopleWrapper'>
                {getLanguage() === "ned"
                    ? <div id="PepopeTxt">Verhalen</div>
                    : <div id="PepopeTxt">Stories</div>
                }

                {stories.map(story => (
                    <>

                        <OnePerson myAccount={prop.myAccount} key={story.id} person={story} />

                    </>
                ))}

            </div>

        )
    }
    return "";
}

export default AllPeople
