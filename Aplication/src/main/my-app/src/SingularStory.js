import React, {useEffect, useState} from "react";
import {getProjectsFromAccount, getAccountData,getAccount} from "./services";
//rx="30" ry="30" x="0" y="0" width="1042" height="270"
export default function SingularStory (prop){
        if(prop.story != undefined){
            return (
                <div>

                    <div id="DateWrap">
                        <span>10Sep</span>
                    </div>
                    <div id="TitleWrap">
                        <span>{prop.story.title}</span>
                    </div>
                    <div id="StoryDescriptionWrap">
                        <span>{prop.story.description}</span>
                    </div>
                            <div id="StoryLinkWrap">
                                <a href={prop.story.link}>{prop.story.link}</a>
                            </div>


                </div>
            )
        }
        else return <></>


}