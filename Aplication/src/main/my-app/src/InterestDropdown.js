import React from "react";

function InterestDropdown(prop) {
    if (prop.interest.length > 0) {
        return (
            <>
                <select>
                    {prop.interest.map(item => (
                        <option key={item.id} value={item.id}>{item.name} </option>
                    ))}
                </select>
            </>
        )
    }
    return (<></>);

}
export default InterestDropdown;