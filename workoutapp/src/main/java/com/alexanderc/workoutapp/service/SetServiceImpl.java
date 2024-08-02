package com.alexanderc.workoutapp.service;

import com.alexanderc.workoutapp.entity.ActivityEntity;
import com.alexanderc.workoutapp.entity.SetEntity;
import com.alexanderc.workoutapp.model.SetReq;
import com.alexanderc.workoutapp.model.SetResp;
import com.alexanderc.workoutapp.repository.ActivityRepository;
import com.alexanderc.workoutapp.repository.SetRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SetServiceImpl implements SetService {
    private SetRepository setRepository;
    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public SetResp createSet(Long activityId, SetReq setReq) {
        ActivityEntity activityEntity = activityRepository.findById(activityId)
                .orElseThrow(() -> new RuntimeException("Activity not found"));
        SetEntity setEntity = new SetEntity();
        setEntity.setActivity(activityEntity);
        setEntity.setReps(setReq.getReps());
        setEntity.setWeight(setReq.getWeight());
        setEntity = setRepository.save(setEntity);

        SetResp setResp = new SetResp();
        setResp.setReps(setEntity.getReps());
        setResp.setWeight(setEntity.getWeight());
        setResp.setId(setEntity.getId());
        return setResp;
    }
}
