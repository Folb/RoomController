package models;

import java.time.LocalDateTime;

public class Timeslot {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Timeslot(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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

    public boolean isWithinTimeslot(LocalDateTime s) {
        return this.startDate.isBefore(s) && this.endDate.isBefore(s);
    }
}
