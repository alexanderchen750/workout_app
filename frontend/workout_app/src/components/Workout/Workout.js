import React, { useEffect, useState, useContext } from "react";
import { fetchWorkoutDetails, removeWorkout } from "../../services/workoutService";
import Activity from '../Activity/Activity';
import './Workout.css'
import checkBox from "../../assets/images/checkbox.png"
import { WorkoutContext } from "../../context/WorkoutContext";

const Workout = ({workoutId, workoutDate}) => {
    const {deleteWorkout} = useContext(WorkoutContext);
    const [workout, setWorkout] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showSetsMap, setShowSetsMap] = useState({});

    useEffect(()=>{
        const workoutDetails = async () => {
            try{
                const data = await fetchWorkoutDetails(workoutId);

                if (Array.isArray(data)) {
                    if(data.length===0){
                        const del = await removeWorkout(workoutId);
                        deleteWorkout();
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
    }, [workoutId])

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
                <button onClick={handleDeleteClick}>X</button>
            </div>
            <div className="activities-tile">
                {workout.map(activity => (
                    <div key={activity.id} className="activity-row"  onClick={() => handleActivityClick(activity.id)}  >
                        {/* <button className="edit-button"><img src={checkBox} alt="check" className="edit-icon"/></button> -->*/}
                        <Activity
                        key={activity.id}
                        workoutId ={workoutId}
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