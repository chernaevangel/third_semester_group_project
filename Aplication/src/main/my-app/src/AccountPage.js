import React, { useState } from "react";
import Stories from "./Stories"
import './AccountPage.css'
import AllPeople from "./connections/AllPeople";


export default function AccountPage(prop) {

    return (
        <div id="AccountPageWrapper">
            
            <div id="StoriesWrapper">     
                <Stories rendered={prop.rendered} pcn={prop.pcn} myAccount={prop.myAccount} />
            </div>
            <div id="AccountWrapper">
                <AllPeople setPcnStates={prop.setPcnStates} hasRendered={"false"} myAccount={prop.myAccount} pcn={prop.pcn} renderedStoryes={prop.renderedStoryes} />
            </div>
        </div>
    )
}