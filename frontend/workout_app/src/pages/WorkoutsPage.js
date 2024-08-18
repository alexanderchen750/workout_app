import React, { useContext } from "react";
import Workout from "../components/Workout/Workout";
import { WorkoutContext } from "../context/WorkoutContext"
import CreateWorkoutForm from "../components/Form/Form";
import "./WorkoutsPage.css"
import { createWorkout, addActivity } from "../services/workoutService";
import Profile from "../components/Profile/Profile";

const WorkoutsPage = () => {
    const {workouts, loading, addWorkout} = useContext(WorkoutContext);

    const onCreate = async (newWorkout) => {
        const { date: workoutDate, activities } = newWorkout;
        try {
            const workoutId = await createWorkout(workoutDate);
            for (const activity of activities) {
                await addActivity(workoutId, activity);
            }
            addWorkout()
    
            console.log("Workout and activities created successfully.");
        } catch (error) {
            console.error("Error creating workout and activities", error);
        }
    };
    

    if(loading){
        return <div><h1>loading...</h1></div>
    }

    return(
        <div className="workout-page">
            <div className="header">
                <h1>Exercise Log</h1>
                <Profile/>
            </div>
            <div className = "body-container">
                <div className="workout-container">
                    {workouts.map(workout =>(
                        <div className="workout-item">
                            <Workout key = {workout.id} workoutId={workout.id} workoutDate={workout.date}/>
                        </div>
                    ))
                    }
                </div> 
                <div className = "workout-form">
                    <CreateWorkoutForm onCreate={onCreate}/>
                </div>
            </div>
        </div>
    )
}

export default WorkoutsPage