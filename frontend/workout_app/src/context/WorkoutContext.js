import React from "react";
import { createContext, useContext, useEffect, useState } from "react";
import { fetchWorkouts } from "../services/workoutService";

export const WorkoutContext = createContext();

export const useWorkout = () => useContext(WorkoutContext);

export const WorkoutProvider = ({children}) => {
    const [workouts, setWorkouts] = useState([])
    const [loading, setLoading] = useState(true)
    const [contextReset, setContextReset] = useState(true);

    useEffect(()=>{
        
        const loadWorkouts = async () => {
            try{
                const data = await fetchWorkouts();
                setWorkouts(data);
            } catch(error){
                console.log("Fail to fetch workouts", error)
            } finally{
                setLoading(false);
            }
        };
        loadWorkouts();
    }, [contextReset]);

    const addWorkout = () => {
        setContextReset(!contextReset)
    };

    const deleteWorkout = () => {
        setContextReset(!contextReset)
    }

    const deleteActivity = () =>{
        console.log("DELTED!")
        setContextReset(!contextReset)
    }

    return (
        <WorkoutContext.Provider value={{workouts, loading, addWorkout, deleteWorkout, deleteActivity }}>
            {children}
        </WorkoutContext.Provider>
    )
}