import React, {  useState, useContext } from "react";
import Set from '../Set/Set'
import "./Activity.css"
import { removeActivity } from "../../services/workoutService";
import { WorkoutContext } from "../../context/WorkoutContext";


const Activity = ({onDeleteClick, activityId, activity, showSets}) =>{
    const {deleteActivity, unitImperial} = useContext(WorkoutContext);

   /* const handleDeleteActivity = async (e) => {
        try{
            e.stopPropagation();
            await removeActivity(workoutId, activityId);
            deleteActivity();
        }catch(error){
            console.log("Fail to Delete Activity");
        }
    }*/

    return (
        <div className="activity-tile">
            <div className="activity-group">
                <h3 className="activity-text">{activity.exercise || 'Exercise'}</h3>
                <button className="delete-activity-button" onClick={(event) => onDeleteClick(event, activityId)}>X</button>
            </div>
            
            <div className={`sets-container ${showSets ? 'show' : ''}`}>
                <div className="sets-header">
                    <div>Set#</div>
                    <div>Reps </div>
                    <div>Weight{unitImperial ? "(lbs)" : "(kgs)"}</div>
                </div>
                {activity.sets.map((set, index) => (
                    <div key={set.id} className="set-row">
                        <div>{index + 1}</div>  {/* Set # */}
                        <div>{set.reps}</div>   {/* Reps */}
                        <div>{set.weight}</div> {/* Weight */}
                    </div>
                ))}
            </div>

        </div>
    );
}

export default Activity