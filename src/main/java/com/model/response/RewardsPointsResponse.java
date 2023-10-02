package com.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RewardsPointsResponse {
    @JsonProperty("totalPoints")
    int totalPoints;
    @JsonProperty("pointsPerMonth")
    List<RewardsPointsPerMonth> rewardsPointsPerMonth;

    public int getTotalPoints() {
        return this.totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<RewardsPointsPerMonth> getRewardsPointsPerMonth() {
        return this.rewardsPointsPerMonth;
    }

    public void setRewardsPointsPerMonth(List<RewardsPointsPerMonth> rewardsPointsPerMonth) {
        this.rewardsPointsPerMonth = rewardsPointsPerMonth;
    }

}
