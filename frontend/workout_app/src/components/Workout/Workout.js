import React, { useEffect, useState, useContext } from "react";
import { fetchWorkoutDetails, removeWorkout, removeActivity, findExercise } from "../../services/workoutService";
import Activity from '../Activity/Activity';
import './Workout.css'
import checkBox from "../../assets/images/checkbox.png"
import { WorkoutContext } from "../../context/WorkoutContext";

const Workout = ({workoutId, workoutDate}) => {
    const {deleteWorkout, deleteActivity} = useContext(WorkoutContext);
    const [workout, setWorkout] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showSetsMap, setShowSetsMap] = useState({});
    const [dummyState, setDummyState] = useState(0);

    useEffect(()=>{
        const workoutDetails = async () => {
            try{
                const data = await fetchWorkoutDetails(workoutId);
                console.log("DATA", data)
                if (Array.isArray(data)) {
                    if(data.length===0){
                        console.log("DELETING ARRAY")
                        const del = await removeWorkout(workoutId);
                        deleteWorkout(del.id);
                    }
                    else{
                        setWorkout(data);
                    }
                    
                } else {
                    console.error('Expected data to be an array but got:', data);
                    setError(new Error('Invalid data format'));
                }
                
            }catch(error){
                setError(error)
                console.log("error fetching: ",workoutId)
            }finally{
                setLoading(false)
            }
        };
        workoutDetails()
    }, [workoutId, dummyState])

    const handleActivityClick = (activityId) => {
        setShowSetsMap(prevShowSetsMap => ({
            ...prevShowSetsMap,
            [activityId]: !prevShowSetsMap[activityId]
        }));
    };

    const handleDeleteClick = async () =>{
        try {
            await removeWorkout(workoutId);
            deleteWorkout(); // Update the context after the workout is deleted
        } catch (error) {
            console.error("Failed to delete workout:", error);
        }
    }

    const handleDeleteActivity = async (e,activityId) => {
        console.log("here")
        try{
            e.stopPropagation();
            await removeActivity(workoutId, activityId);
            setDummyState(prevState => prevState+1)
            deleteActivity();
        }catch(error){
            console.log("Fail to Delete Activity");
        }
    }

    const formatDate = (date) => {
        const d = new Date(date);
        const month = d.getMonth() + 1; // getMonth() returns month from 0 to 11
        const day = d.getDate();
        const year = d.getFullYear().toString().slice(-2); // Get last two digits of the year
    
        return `${month}/${day}/${year}`;
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error loading workout details</div>;
    }

    if (!workout || workout.length === 0) {
        return <div><h2>No workouts found</h2></div>;
    }
    
    return(
        <div className="workout-tile">
            <div className="workout-header">
                <h2 className="workout-text">Workout: {formatDate(workoutDate)}</h2>
                <button className="delete-workout-button" onClick={handleDeleteClick}>X</button>
            </div>
            <div className="activities-tile">
                {workout.map(activity => (
                    <div key={activity.id} className="activity-row"  onClick={() => handleActivityClick(activity.id)}  >
                        {/* <button className="edit-button"><img src={checkBox} alt="check" className="edit-icon"/></button> -->*/}
                        <Activity
                        key={activity.id}
                        onDeleteClick={handleDeleteActivity}
                        activityId={activity.id}
                        activity={activity}
                        showSets={!!showSetsMap[activity.id]}  
                    />
                    </div> 
                ))}
            </div>
        </div>
    );

};

export default Workout;