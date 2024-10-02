package com.example.danceClasses.DTOS;

import java.time.LocalDateTime;

public class DatesRequestDTO {

    LocalDateTime startDate;
    LocalDateTime endDate;

    public DatesRequestDTO() {
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
