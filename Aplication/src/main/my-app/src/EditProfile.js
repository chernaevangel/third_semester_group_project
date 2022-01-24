import React, { useEffect, useState } from 'react'
import { editAccount, getAccount, getAccountData } from './services';

function EditProfile(prop) {
    const [account, setAccount] = useState()
    const [name, setName] = useState()
    const [bio, setBio] = useState()

    useEffect(() => {
        let mounted = true;
        getAccountData(getAccount().pcn)
            .then(items => {
                if (mounted) {
                    setAccount(items)
                    setName(items.name)
                    setBio(items.bio)
                }
            })
        return () => mounted = false;
    }, [])

    const edit = async () => {
        var newAccount = account;
        newAccount.name = name;
        newAccount.bio = bio;
        await editAccount(newAccount);
        window.location.reload();
    }
    

    if (account !== undefined) {
        return (
            <div id="EditWrapper">
                <div id="NameDiv">Name</div>
                <input id="NameInput" onChange={e => setName(e.target.value)} value={name} ></input> <br/>
                <div id="BioDiv">Bio</div>
                <textarea id="BioInput" onChange={e => setBio(e.target.value)} value={bio} ></textarea> <br/>
                <button id="SaveButton" onClick={() => edit()} >Edit</button>
            </div>
        )
    } else return "";
}

export default EditProfile
