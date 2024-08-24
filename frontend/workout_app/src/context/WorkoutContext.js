import React from "react";
import { createContext, useContext, useEffect, useState } from "react";
import { fetchWorkouts } from "../services/workoutService";

export const WorkoutContext = createContext();

export const useWorkout = () => useContext(WorkoutContext);

export const WorkoutProvider = ({children}) => {
    const [workouts, setWorkouts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [fetchTrigger, setFetchTrigger] = useState(true);
    const [unitImperial, setUnitImperial] = useState(true);

    useEffect(()=>{
        const loadWorkouts = async () => {
            try{
                const data = await fetchWorkouts();
                setWorkouts(data);
            } catch(error){
                console.log("Fail to fetch workouts", error);
            } finally{
                setLoading(false);
            }
        };
        loadWorkouts();
    }, [fetchTrigger]);

    useEffect(() => {
        console.log("Updated workouts state:", workouts);
    }, [workouts]);

    const addWorkout = () => {
        setFetchTrigger(prev => !prev);
    };

    const changeUnit = (imperial) =>{
        setUnitImperial(imperial);
    }

    const deleteWorkout = (id= null) => {
        console.log("HERE")
        if(id!=null){
            setWorkouts((prevWorkouts) => {
                return prevWorkouts.filter(workout => workout.id !== id);
            });
        } 
        setFetchTrigger(prev => !prev);
    };

    const deleteActivity = () => {
        setFetchTrigger(prev => !prev);
    };

    return (
        <WorkoutContext.Provider 
            value={{workouts, loading, 
                addWorkout, deleteWorkout, 
                deleteActivity, unitImperial, changeUnit 
            }}>
            {children}
        </WorkoutContext.Provider>
    )
}