import React, {  useState, useContext } from "react";
import Set from '../Set/Set'
import "./Activity.css"
import { removeActivity } from "../../services/workoutService";
import { WorkoutContext } from "../../context/WorkoutContext";

const Activity = ({workoutId, activityId, activity, showSets}) =>{
    const {deleteActivity} = useContext(WorkoutContext);

    const handleDeleteActivity = async (e) => {
        try{
            e.stopPropagation();
            await removeActivity(workoutId, activityId);
            deleteActivity();
        }catch(error){
            console.log("Fail to Delete Activity");
        }
    }

    return (
        <div className="activity-tile">
            <div className="activity-group">
                <h3 className="activity-text">{activity.exercise || 'Exercise'}</h3>
                <button onClick={handleDeleteActivity}>X</button>
            </div>
            
            <div className={`sets-container ${showSets ? 'show' : ''}`}>
                {activity.sets.map(set => (
                    <Set key={set.id} set={set} />
                ))}
            </div>
        </div>
    );
}

export default Activity