import React, {useState} from "react";
import "./Set.css"

const Set = ({set}) => {
    

    return(
        <div className="set-info">
            <p>reps: {set.reps}, weight: {set.weight}</p>
        </div>
    )
}

export default Set;