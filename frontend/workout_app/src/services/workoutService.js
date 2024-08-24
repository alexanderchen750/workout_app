import axios from 'axios';
import { endpoints } from '../utils/utils.js';
import { handleError } from '../utils/error.js';


export const fetchWorkouts = async () => {
    try{
        const token = localStorage.getItem('jwtToken');
        console.log(token)
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.get(endpoints.getWorkouts, config);
        console.log(response)
        return response.data;
    } catch (error){
        handleError(error);
        throw error;
    }
}

/* QUESITONS:
    - How will  workoutID be stored, in context, in the browerser, etc. */
export const fetchWorkoutDetails = async (workoutId) => {
    try{
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        console.log("workoutId", workoutId)
        const response = await axios.get(endpoints.getWorkoutDetails(workoutId), config);
        return response.data;
    } catch (error){
        handleError(error);
        throw error;
    }
}

// workoutService.js
export const createWorkout = async (workoutDate) => {
    try {
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                'Content-Type':'application/json',
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.post(endpoints.postWorkout, {
            date: workoutDate
        }, config);
        return response.data.id; // Assuming the response includes the created workoutId
    } catch (error) {
        throw new Error(`Failed to create workout: ${error.response ? error.response.data.message : error.message}`);
    }
};
export const addActivity = async (workoutId, activity) => {
    try {
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                'Content-Type':'application/json',
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.post(endpoints.postActivities(workoutId), {
            exercise: activity.name,
            sets: activity.sets.map(set => ({
                reps: set.reps,
                weight: set.weight
            }))
        }, config);
        return response.data; // Assuming your API returns some response data
    } catch (error) {
        throw new Error(`Failed to add activity: ${error.response ? error.response.data.message : error.message}`);
    }
};


/*****************************************
 * Delete Routes
*****************************************/


export const removeWorkout = async (workoutId) => {
    try{
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.delete(endpoints.deleteWorkout(workoutId),config);
        return true;
    } catch (error){
        handleError(error);
        throw error;
    }
}
export const removeActivity = async (workoutId, activityId) => {
    try{
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.delete(endpoints.deleteActivity(workoutId, activityId),config);
        console.log(response)
        return true;
    } catch (error){
        handleError(error);
        throw error;
    }
}


/* Search DropDown */
export const findExercise = async (searchTerm) =>{
    try{
        const token = localStorage.getItem('jwtToken');
        const config = {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        };
        const response = await axios.get(endpoints.searchExercise(searchTerm),config);
        return response.data
    } catch (error){
        handleError(error);
        throw error;
    }
}