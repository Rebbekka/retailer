package com.service;

import com.impl.RewardsPoints;
import com.model.response.RewardsPointsResponse;
import org.springframework.stereotype.Service;

@Service
public class RewardsService {

    public RewardsService() { }

    public RewardsPointsResponse getRewardsPoints(String ID) {
        RewardsPoints rewardsPointsService = new RewardsPoints();
        RewardsPointsResponse rewardsPoints = rewardsPointsService.getRewardsPoints(ID);
        //return RewardsPointsResponse
        return rewardsPoints;
    }
}
