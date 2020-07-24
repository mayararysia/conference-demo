package com.rysia.conferencedemo.dto;

public class WorkshopDTO {
    private String workshopName;
    private String description;
    private String requirements;
    private String room;
    private Integer capacity;

    public WorkshopDTO() {
    }

    public WorkshopDTO(String workshopName, String description, String requirements, String room, Integer capacity) {
        this.workshopName = workshopName;
        this.description = description;
        this.requirements = requirements;
        this.room = room;
        this.capacity = capacity;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
