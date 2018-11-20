package models;

import java.time.LocalDate;

public class Room {

    private int id;
    private int ownedBy;
    private String roomNumber;
    private LocalDate publicEndDate;

    public Room(int id, int ownedBy, String roomNumber, LocalDate publicEndDate) {
        this.id = id;
        this.ownedBy = ownedBy;
        this.roomNumber = roomNumber;
        this.publicEndDate = publicEndDate;
    }

    public int getId() {
        return id;
    }

    public String getRoomNumber() { return roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public int getOwnedBy() { return ownedBy; }

    public void setOwnedBy(int ownedBy) { this.ownedBy = ownedBy;}

    public LocalDate getPublicEndDate() { return publicEndDate; }

    public void setPublicEndDate(LocalDate publicEndDate) { this.publicEndDate = publicEndDate; }

    public void makeRoomPublicUntil(LocalDate endDate) {
        setPublicEndDate(endDate);
    }
}