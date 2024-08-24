import React from "react";
import favicon from "../../assets/favicon.svg"
import "./Sidebar.css";
import { WorkoutContext } from "../../context/WorkoutContext";
import { useContext } from "react";
import white from "../../assets/white.svg"



const Sidebar = () =>{
    const {changeUnit, unitImperial} = useContext(WorkoutContext);

    const changeToMetric = () =>{
        changeUnit(false);
    }

    const changeToImperial = () =>{
        changeUnit(true);
    }


    return(
        <div className="sidebar-container">
            <div className="sidebar-header">
                <div className="sidebar-logo-container">
                    <img src={white} alt="Logo" className="sidebar-logo"/>
                </div>
                <h2 className="logo-text">Exercise Log</h2>
            </div>
            <div className="bottom-container">
                <div className="unit-text">
                    <p>Weight Unit</p>
                </div>
                
                <div className="weather-button-container">
                    <button
                        className={`metric-button ${!unitImperial ? 'active' : ''}`}
                        onClick={changeToMetric}
                    >
                        Metric
                    </button>
                    <button
                        className={`imperial-button ${unitImperial ? 'active' : ''}`}
                        onClick={changeToImperial}
                    >
                        Imperial
                    </button>    
                </div>
                <div className="creator-text">
                    <p>Made by Alexander</p>
                </div>
                
            </div>
                        
        </div>
    )

}

export default Sidebar;