import React, { useState } from 'react'
import AccountCreation from './AccountCreation';
import App from './App';
import Login from './Login';
import { getAccount, getAccountData, getLanguage } from './services';

function AccountCheck() {
    const [account, setAccount] = useState();
    const [language, setlanguage] = useState();
    const [pcn, setPcn] = useState();
    const [checking, setChecking] = useState(false)

    const getAccountAsync = async () => {
        setAccount(await getAccountData(pcn));
        setlanguage(await getLanguage());
    }
    if (pcn != undefined && checking === false) {
        setChecking(true);
        getAccountAsync()
    }
    console.log(getAccount());
    if (account !== undefined && account.name === "") {
        return (
            <div>
                <AccountCreation account={account}/>
            </div>
        )
    } else {
        if (account !== undefined) {
            return (
                <App pcn={pcn} />
            )
        }
        else return <Login setPcn={setPcn} />
    }
}

export default AccountCheck