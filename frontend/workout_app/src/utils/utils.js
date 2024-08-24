const API_BASE_URL = "http://localhost:8080/api/v1";

export const endpoints = {
    registerUser: `${API_BASE_URL}/auth/register`,
    authenticateUser: `${API_BASE_URL}/auth/authenticate`,
    getUserDetails: `${API_BASE_URL}/user/login-details`,

    getWorkouts: `${API_BASE_URL}/workout`,
    getWorkoutDetails : (workoutId) => `${API_BASE_URL}/workout/${workoutId}/activity`,
    postWorkout: `${API_BASE_URL}/workout`,
    postActivities: (workoutId) => `${API_BASE_URL}/workout/${workoutId}/activity`,
    
    deleteWorkout: (workoutId) => `${API_BASE_URL}/workout/${workoutId}`,
    deleteActivity: (workoutId,activityId) => `${API_BASE_URL}/workout/${workoutId}/activity/${activityId}`,
    deleteSet: (workoutId,activityId, setId) => `${API_BASE_URL}/workout/${workoutId}/activity/${activityId}/set/${setId}`,

    searchExercise: (search) => `${API_BASE_URL}/exercise/search?search=${search}`
};