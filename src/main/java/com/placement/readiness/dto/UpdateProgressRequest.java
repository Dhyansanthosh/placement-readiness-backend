package com.placement.readiness.dto;

import jakarta.validation.constraints.*;

public class UpdateProgressRequest {

    @Min(0)
    private int dsaCompleted;

    @Min(1)
    private int dsaTotal;

    @Min(0)
    @Max(40)
    private int mockScore;

    @NotBlank
    private String coreStatus;

    public int getDsaCompleted() {
        return dsaCompleted;
    }

    public void setDsaCompleted(int dsaCompleted) {
        this.dsaCompleted = dsaCompleted;
    }

    public int getDsaTotal() {
        return dsaTotal;
    }

    public void setDsaTotal(int dsaTotal) {
        this.dsaTotal = dsaTotal;
    }

    public int getMockScore() {
        return mockScore;
    }

    public void setMockScore(int mockScore) {
        this.mockScore = mockScore;
    }

    public String getCoreStatus() {
        return coreStatus;
    }

    public void setCoreStatus(String coreStatus) {
        this.coreStatus = coreStatus;
    }
}
