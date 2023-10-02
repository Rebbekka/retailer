package com.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RewardsPointsPerMonth {
    @JsonProperty("Month")
    String thisMonth;
    @JsonProperty("PointsEarned")
    int thisMonthsPoints;

    public RewardsPointsPerMonth(int thisMonthsPoints, String thisMonth) {
        this.thisMonthsPoints = thisMonthsPoints;
        this.thisMonth = thisMonth;

    }

    public int getThisMonthsPoints() {
        return this.thisMonthsPoints;
    }

    public void setThisMonthsPoints(int thisMonthsPoints) {
        this.thisMonthsPoints = thisMonthsPoints;
    }

    public String getThisMonth() {
        return this.thisMonth;
    }

    public void setThisMonth(String thisMonth) {
        this.thisMonth = thisMonth;
    }

}
