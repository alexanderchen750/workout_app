import React, { useState } from 'react';
import "./Form.css"

const CreateWorkoutForm = ({ onCreate }) => {
    const [workoutDate, setWorkoutDate] = useState('');
    const [activities, setActivities] = useState([{ name: '', sets: [{ weight: 0, reps: 0 }] }]);

    const handleAddActivity = () => {
        setActivities([...activities, { name: '', sets: [{ weight: 0, reps: 0 }] }]);
    };

    const handleActivityChange = (index, field, value) => {
        const updatedActivities = activities.map((activity, i) =>
            i === index ? { ...activity, [field]: value } : activity
        );
        setActivities(updatedActivities);
    };

    const handleAddSet = (activityIndex) => {
        const updatedActivities = activities.map((activity, i) =>
            i === activityIndex
                ? { ...activity, sets: [...activity.sets, { weight: 0, reps: 0 }] }
                : activity
        );
        setActivities(updatedActivities);
    };

    const handleSetChange = (activityIndex, setIndex, field, value) => {
        const updatedActivities = activities.map((activity, i) =>
            i === activityIndex
                ? {
                      ...activity,
                      sets: activity.sets.map((set, j) =>
                          j === setIndex ? { ...set, [field]: value } : set
                      ),
                  }
                : activity
        );
        setActivities(updatedActivities);
    };

    const handleDeleteSet = (activityIndex, setIndex) => {
        const updatedActivities = activities.map((activity, i) =>
            i === activityIndex
                ? {
                      ...activity,
                      sets: activity.sets.filter((set, j) => j !== setIndex),
                  }
                : activity
        );
        setActivities(updatedActivities);
    };

    const handleDeleteActivity = (activityIndex) => {
        const updatedActivities = activities.filter((_, i) => i !== activityIndex);
        setActivities(updatedActivities);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const newWorkout = {
            date: workoutDate,
            activities: activities,
        };
        onCreate(newWorkout);
        setWorkoutDate('');
        setActivities([{ name: '', sets: [{ weight: 0, reps: 0 }] }]);
    };

    // Check if the form can be submitted
    const canSubmit = () => {
        // Check if there is at least one activity and each activity has at least one set
        return (
            activities.length > 0 &&
            activities.every(activity => activity.sets.length > 0)
        );
    };

    return (
        <form onSubmit={handleSubmit} className="create-workout-form">
            <div>
                <h3 className='form-header' >Create Workout</h3>
            </div>
            <div className="form-group">
                <label htmlFor="workout-date" className='form-title-row-text'>Date</label>
                <input
                    type="date"
                    id="workout-date"
                    value={workoutDate}
                    onChange={(e) => setWorkoutDate(e.target.value)}
                    required
                />
            </div>
            <div className="form-group">
                {activities.map((activity, index) => (
                    <div key={index} className="activity-section">
                        <div className='form-title-row'>
                            <label className="form-title-row-text">Exercise</label>
                            <button
                                type="button"
                                onClick={() => handleDeleteActivity(index)}
                                className="add-delete-button"
                            >
                                -
                            </button>
                        </div>
                        
                        <div className="activity-input">
                            <input
                                type="text"
                                value={activity.name}
                                onChange={(e) =>
                                    handleActivityChange(index, 'name', e.target.value)
                                }
                                required
                            />
                        </div>
                        <div>
                            <div className='form-title-row'>
                                <label className="form-title-row-text">Sets</label>
                                <button
                                    type="button"
                                    onClick={() => handleAddSet(index)}
                                    className="add-delete-button"
                                >
                                    +
                                </button>
                            </div>
                            {activity.sets.map((set, setIndex) => (
                                <div key={setIndex} className="set-input">
                                    <input
                                        type="number"
                                        placeholder="Weight"
                                        value={set.weight === 0 ? '' : set.weight}
                                        onChange={(e) =>
                                            handleSetChange(index, setIndex, 'weight', e.target.value)
                                        }
                                        min="0"
                                        required
                                    />
                                    <input
                                        type="number"
                                        placeholder="Reps"
                                        value={set.reps === 0 ? '' : set.reps}
                                        onChange={(e) =>
                                            handleSetChange(index, setIndex, 'reps', e.target.value)
                                        }
                                        min="1"
                                        required
                                    />
                                    <button
                                        type="button"
                                        onClick={() => handleDeleteSet(index, setIndex)}
                                        className="delete-set-button"
                                    >
                                        -
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>
                ))}
                <button type="button" onClick={handleAddActivity}>
                    Add Activity
                </button>
            </div>

            <button type="submit" disabled={!canSubmit()}>Create Workout</button>
        </form>
    );
};

export default CreateWorkoutForm;
