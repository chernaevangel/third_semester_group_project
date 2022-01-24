import React, { useEffect, useState } from "react";
import Interest from "./Interest";
import InterestDropdown from "./InterestDropdown";
import { addInterest, deleteInterest, getAccount, getAccountData, getAllPendingRequests, getInterests, updateAccount, updateAccountPictrure, updateLanguage, getLanguage } from "./services";
import './Profile.css';
import VisibilitySwitch from "./components/VisibilitySwitch.js";
import LanguageSwitch from "./components/LanguageSwitch.js";
import Popup from './components/Popup';
import EditProfile from "./EditProfile";
import DeleteAccountWarning from "./DeleteAccountWarning";
import EditInterest from "./EditInterest";

function InfoPopup({ account, onClick, interests, myAcc, profileImage, pdf, addInterestAsync, deleteInterestAsync }) {
    const [showSubmit, setShowSubmit] = useState(false);
    const [selected, setSelected] = useState(null);
    const [selLanguage, setSelLanguage] = useState(null);
    const [showImageMenu, setShowImageMenu] = useState(false);
    const [changePicBtn, setChangePicBtn] = useState(true);
    const [binaryImage, setBinaryImage] = useState(profileImage);
    const [selectImageStyle, setSelectImageStyle] = useState("");
    const [buttonPopup, setButtonPopup] = useState(false);
    const [buttonPopupDel, setButtonPopupDel] = useState(false);
    const [interestPopup, setInterestPopup] = useState(false);


    const getVisibility = () => {
        switch (account.visibility) {
            case 0:
                return 'private';
            case 1:
                return 'friends-only';
            case 2:
                return 'public';
            default:
                return '';
        }
    }

    const showVisibilityStyle = () => {
        if (!myAcc) {
            return "switch_visibility disp-none";
        }
        else {
            return "switch_visibility";
        }
    }

    const getVisibilityNum = (prop) => {
        switch (prop) {
            case 'private':
                return 0;
            case 'friends-only':
                return 1;
            case 'public':
                return 2;
            default:
                return '';
        }
    }

    const getnewLanguage = () => {
        switch (account.language) {
            case 0:
                return 'english';
            case 1:
                return 'dutch';
            default:
                return '';
        }
    }

    const showLanguageStyle = () => {
        if (!myAcc) {
            return "switch_language disp-none";
        }
        else {
            return "switch_language";
        }
    }

    const getLanguageNum = (prop) => {
        switch (prop) {
            case 'english':
                return "eng";
            case 'dutch':
                return "ned";
            default:
                return '';
        }
    }
    const handleLanguageChange = () => {
        updateLanguage(getLanguageNum(selLanguage));
        window.location.reload();
    }

    const handleVisibilityChange = () => {
        updateAccount(account, getVisibilityNum(selected));
        window.location.reload();
    }

    const onImageClickHandler = () => {
        setShowImageMenu(!showImageMenu);
        if (!changePicBtn) {
            setChangePicBtn(true);
            setSelectImageStyle("");
        }
    }

    const onChangePicOption = (e) => {
        setChangePicBtn(false);
        setSelectImageStyle("center-image-select");
    }

    const onImageUploaded = (e) => {
        let file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = _handleReaderLoad.bind();
            reader.readAsBinaryString(file);

        }

    }

    const _handleReaderLoad = (readerEvt) => {
        let binaryString = readerEvt.target.result;
        setBinaryImage("data:image/png;base64," + btoa(binaryString));
    }

    const handleImageSubmission = () => {
        updateAccountPictrure(account, binaryImage);
        window.location.reload();
    }

    const handleAccountDelete = () => {

    }

    if (getLanguage() === "eng") {
        return (
            <>
                <div className="Overlay" />
                <div id="profileInfo" >
                    <div id="ProfileInfo" className="ProfileInfo">
                        <div id="InfoCard">
                            <Popup trigger={buttonPopup} setTrigger={setButtonPopup}>
                                <EditProfile setTrigger={setButtonPopup} />
                            </Popup>
                            <Popup trigger={buttonPopupDel} setTrigger={setButtonPopupDel}>
                                <DeleteAccountWarning pcn={account.pcn} />
                            </Popup>
                            <div className="InfoBg" id="InfoBg">
                                <div className="edit-options">
                                    {myAcc ?
                                        <div className={showVisibilityStyle()}>
                                            <p>Visibility level:</p>
                                            <VisibilitySwitch values={['private', 'friends-only', 'public']} selected={getVisibility()} setSubmit={setShowSubmit} setSelected={setSelected} />
                                            {showSubmit ? <button id={"change-visibility-button"} onClick={handleVisibilityChange}>Change</button> : null}
                                        </div>
                                        :
                                        <></>
                                    }
                                    {myAcc ?
                                        <div className={showLanguageStyle()}>
                                            <p id={"language-text"}>Language:</p>
                                            <LanguageSwitch values={['english', 'dutch']} selected={getnewLanguage()} setSubmit={setShowSubmit} setSelected={setSelLanguage} />
                                            {showSubmit ? <button id={"change-lang-button"} onClick={handleLanguageChange}>Change</button> : null}
                                        </div>
                                        :
                                        <></>
                                    }
                                    {myAcc ?
                                        <div className="edit-menu-wrapper" >
                                            <div className="edit-menu">
                                                <input type="checkbox" id="edit-menu-cb" />
                                                <label htmlFor="edit-menu-cb">Options</label>
                                                <ul className="edit-menu-list">
                                                    <li onClick={() => setButtonPopup(true)}>Edit Name & Bio</li>
                                                    <li onClick={() => { pdf(); onClick() }}>Export to PDF</li>
                                                    <li onClick={() => setButtonPopupDel(true)}>Delete my account</li>
                                                </ul>
                                            </div>
                                        </div>
                                        :
                                        <></>
                                    }

                                    <div className={"close-btn"}><button onClick={onClick}><i className="fa fa-close"></i></button></div>
                                </div>
                                <div className="ProfilePicHolder">

                                    <div className="ProfilePic">
                                        <img onClick={onImageClickHandler} id="profile_pic" className="imge" src={binaryImage} />
                                    </div>
                                    {showImageMenu && myAcc ? <div className={"change-pic " + selectImageStyle}>
                                        {changePicBtn ? <button onClick={onChangePicOption}>Change</button> :
                                            <div className="select-pic">
                                                <label>
                                                    <input onChange={(e) => onImageUploaded(e)} className={""} type="file" name="image" id="file" accept=".jpeg, .png, .jpg" />
                                                    Upload an image
                                                </label>
                                                <button onClick={handleImageSubmission}>Submit</button>
                                            </div>
                                        }
                                    </div> : <></>}
                                </div>

                                <div id="Info">
                                    <div id="Bio">
                                        <div className="BioBg">
                                            <div id="BioBg" >
                                                <div id="BioText">
                                                    <span>{account.bio}</span>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div id="IES">
                                        <div id="Interests">
                                            <div id="InterestsBtn">
                                                <div className="InterestsBtnBg">
                                                    <div id="InterestsBtnBg">
                                                        <div id="Interests_z">
                                                            <span>Interests</span>
                                                            {myAcc === true &&
                                                            <>
                                                                <button id={"edit-button-interests"} onClick={() => setInterestPopup(true)}>Edit</button>
                                                                <Popup trigger={interestPopup} setTrigger={setInterestPopup}>
                                                                    <EditInterest interests={interests} pcn={account.pcn} addInterestAsync={addInterestAsync} deleteInterestAsync={deleteInterestAsync} />
                                                                </Popup>
                                                            </>
                                                            }
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <ol id="IntertestsList">
                                                {interests.map(item => (
                                                    <li><Interest key={item.id} interest={item}/></li>
                                                ))}
                                            </ol>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );
    }
    else {
        return (
            <>
                <div className="Overlay" />
                <div id="profileInfo" >
                    <div id="ProfileInfo" className="ProfileInfo">
                        <div id="InfoCard">
                            <Popup trigger={buttonPopup} setTrigger={setButtonPopup}>
                                <EditProfile setTrigger={setButtonPopup} />
                            </Popup>
                            <Popup trigger={buttonPopupDel} setTrigger={setButtonPopupDel}>
                                <DeleteAccountWarning pcn={account.pcn} />
                            </Popup>
                            <div className="InfoBg" id="InfoBg">
                                <div className="edit-options">
                                    {myAcc ?
                                        <div className={showVisibilityStyle()}>
                                            <p>Zichtbaarheid:</p>
                                            <VisibilitySwitch values={['private', 'friends-only', 'public']} selected={getVisibility()} setSubmit={setShowSubmit} setSelected={setSelected} />
                                            {showSubmit ? <button id={"change-visibility-button"} onClick={handleVisibilityChange}>Verander</button> : null}
                                        </div>
                                        :
                                        <></>
                                    }
                                    {myAcc ?
                                        <div className={showLanguageStyle()}>
                                            <p id={"language-text"}>Taal:</p>
                                            <LanguageSwitch values={['english', 'dutch']} selected={getLanguage()} setSubmit={setShowSubmit} setSelected={setSelLanguage} />
                                            {showSubmit ? <button id={"change-lang-button"} onClick={handleLanguageChange}>Verander</button> : null}
                                        </div>
                                        :
                                        <></>
                                    }
                                    {myAcc ?
                                        <div className="edit-menu-wrapper" >
                                            <div className="edit-menu">
                                                <input type="checkbox" id="edit-menu-cb" />
                                                <label htmlFor="edit-menu-cb">Opties</label>
                                                <ul className="edit-menu-list">
                                                    <li onClick={() => setButtonPopup(true)}>Verander Naam & Bio</li>
                                                    <li onClick={() => { pdf(); onClick() }}>Exporteer naar PDF</li>
                                                    <li onClick={() => setButtonPopupDel(true)}>Verwijder account</li>
                                                </ul>
                                            </div>
                                        </div>
                                        :
                                        <></>
                                    }

                                    <div className={"close-btn"}><button onClick={onClick}><i className="fa fa-close"></i></button></div>
                                </div>
                                <div className="ProfilePicHolder">

                                    <div className="ProfilePic">
                                        <img onClick={onImageClickHandler} id="profile_pic" className="imge" src={binaryImage} />
                                    </div>
                                    {showImageMenu && myAcc ? <div className={"change-pic " + selectImageStyle}>
                                        {changePicBtn ? <button onClick={onChangePicOption}>Verander</button> :
                                            <div className="select-pic">
                                                <label>
                                                    <input onChange={(e) => onImageUploaded(e)} className={""} type="file" name="image" id="file" accept=".jpeg, .png, .jpg" />
                                                    Upload een afbeelding
                                                </label>
                                                <button onClick={handleImageSubmission}>Bevestigen</button>
                                            </div>
                                        }
                                    </div> : <></>}
                                </div>

                                <div id="Info">
                                    <div id="Bio">
                                        <div className="BioBg">
                                            <div id="BioBg" >
                                                <div id="BioText">
                                                    <span>{account.bio}</span>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                    <div id="IES">
                                        <div id="Interests">
                                            <div id="InterestsBtn">
                                                <div className="InterestsBtnBg">
                                                    <div id="InterestsBtnBg">
                                                        <div id="Interests_z">
                                                            <span>Intresses</span>
                                                            {myAcc === true &&
                                                            <>
                                                                <button id={"edit-button-interests"} onClick={() => setInterestPopup(true)}>Bewerken</button>
                                                                <Popup trigger={interestPopup} setTrigger={setInterestPopup}>
                                                                    <EditInterest interests={interests} pcn={account.pcn} addInterestAsync={addInterestAsync} deleteInterestAsync={deleteInterestAsync} />
                                                                </Popup>
                                                            </>
                                                            }
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <ol id="IntertestsList">
                                                {interests.map(item => (
                                                    <li><Interest key={item.id} interest={item} /></li>
                                                ))}
                                            </ol>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </>
        );
    }
}

function Profile(prop) {
    const [account, setAccount] = useState();
    const [interests, setInterests] = useState([]);
    const [popupState, setPopupState] = React.useState({ open: false });

    const addInterestAsync = async (input) => {
        let interest = JSON.stringify({
            "interest": input,
            "pcn": account.pcn
        })
        if (await addInterest(interest)) {
            window.location.reload();
        }

        getInterests(prop.pcn).then(items => { //update the display
            setInterests(items)
        })

    }

    const deleteInterestAsync = async (id) => {
        await deleteInterest(id);

        getInterests(prop.pcn).then(items => { //update the display
            setInterests(items)
        })
    }

    useEffect(() => {
        if (!prop.rendered.hasRendered2) {
            getInterests(prop.pcn)
                .then(items => {
                    setInterests(items)
                    prop.rendered.setHasRendered2(true);
                })
        }
        return;
    })

    useEffect(() => {
        if (!prop.rendered.hasRendered3) {
            getAccountData(prop.pcn)
                .then(items => {
                    setAccount(items)
                    prop.rendered.setHasRendered3(true);

                })
        }
        return;
    })

    if (account != undefined) {
        //rx="117.5" ry="117.5" cx="117.5" cy="117.5"
        const profileImage = () => {

            if (account.binaryImage == null) {
                return "./logo512.png";
            }
            else {
                return account.binaryImage;
            }
        }
        return (
            <div id="ProfileInfo" className="ProfileInfo">
                <div className="ProfilePicture">
                    <img src={profileImage()} alt="profile picture" />
                </div>
                {<div id="InfoPopup">
                    <span id="NameSpan" href="#" onClick={showInfo(account)}>{account.name} ⓘ </span>
                </div>}{popupState.open === true && (
                    <InfoPopup
                        account={popupState.account}
                        myAcc={prop.myAccount}
                        onClick={() => setPopupState({ open: false })}
                        interests={interests}
                        profileImage={profileImage}
                        pdf={prop.pdf}
                        addInterestAsync={addInterestAsync}
                        deleteInterestAsync={deleteInterestAsync}
                    />
                )}
            </div>
        )
    }
    else {
        return (
            <p>Loading Please Wait</p>
        )
    }
    function showInfo(account) {
        return () => setPopupState({ open: true, account });
    }
}
export default Profile;