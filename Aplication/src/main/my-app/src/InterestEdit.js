import React from "react";
import { getLanguage } from "./services";

function InterestEdit(prop) {
        return (
            <>
                <>{prop.interest.interest} </>
                <button id={"delete-interest-button"} onClick={() => prop.deleteInterestAsync(prop.interest.id)}>🗑</button>
            </>
        )
}

export default InterestEdit;