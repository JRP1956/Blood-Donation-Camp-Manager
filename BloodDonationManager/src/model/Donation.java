package model;

import java.time.LocalDate;

public class Donation {
    private String donorId;
    private LocalDate date;
    private String status;

    public Donation(String donorId, LocalDate date, String status) {
        this.donorId = donorId;
        this.date = date;
        this.status = status;
    }

    public void markCompleted() {
        this.status = "Completed";
    }

    public String getDonationInfo() {
        return "Donor ID: " + donorId + " | Date: " + date + " | Status: " + status;
    }

    // Getters and Setters
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return donorId + "," + date + "," + status;
    }
}

