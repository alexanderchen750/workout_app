import React, { useContext, useState } from "react";
import Workout from "../components/Workout/Workout";
import { WorkoutContext } from "../context/WorkoutContext"
import CreateWorkoutForm from "../components/Form/Form";
import "./WorkoutsPage.css"
import { createWorkout, addActivity } from "../services/workoutService";
import Profile from "../components/Profile/Profile";
import Sidebar from "../components/Sidebar/Sidebar";

const WorkoutsPage = () => {
    const {workouts, loading, addWorkout} = useContext(WorkoutContext);

    const onCreate = async (newWorkout) => {
        const { date: workoutDate, activities } = newWorkout;
        try {
            const workoutId = await createWorkout(workoutDate);
            for (const activity of activities) {
                await addActivity(workoutId, activity);
            }
            addWorkout();
            console.log("Workout and activities created successfully.");
            closePopup(); // Close the popup after successful creation
        } catch (error) {
            console.error("Error creating workout and activities", error);
        }
    };

    const [showPopup, setShowPopup] = useState(false);

    const handleCreateClick = () => {
        setShowPopup(true);
    };

    const closePopup = () => {
        setShowPopup(false);
    };
    

    if(loading){
        return <div><h1>loading...</h1></div>
    }

    return(
        <div className="workout-page">
            <div className="workout-page-container">
                
                <Sidebar/>
               
                <div className="page">
                    <div className="header">
                        <Profile/>
                    </div>
                    <div className = "body-container">
                        <div className="create-workout">
                            <button className="create-button" onClick={handleCreateClick}>
                                New Workout
                            </button>
                            {showPopup && (
                                <div className="popup-overlay" onClick={closePopup}>
                                    <div className="popup-content" onClick={e => e.stopPropagation()}>
                                        <button className="close-button" onClick={closePopup}>X</button>
                                        <CreateWorkoutForm onCreate={onCreate}/>
                                    </div>
                                </div>
                            )}
                        </div>
                        <div className="workout-container">
                            {workouts.map(workout =>(
                                <div className="workout-item">
                                    <Workout key = {workout.id} workoutId={workout.id} workoutDate={workout.date}/>
                                </div>
                            ))
                            }
                        </div> 
                    </div>
                </div>
            </div>
        </div>
    )
}

export default WorkoutsPage