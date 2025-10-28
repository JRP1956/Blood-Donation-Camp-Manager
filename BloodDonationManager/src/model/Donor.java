package model;

public class Donor {
    private String donorId;
    private String name;
    private int age;
    private String bloodGroup;
    private String contact;
    private String location;

    public Donor(String donorId, String name, int age, String bloodGroup, String contact, String location) {
        this.donorId = donorId;
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.location = location;
    }

    public boolean isEligible() {
        return age >= 18 && age <= 65;
    }

    // Getters and Setters
    public String getDonorId() { return donorId; }
    public void setDonorId(String donorId) { this.donorId = donorId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    @Override
    public String toString() {
        return donorId + "," + name + "," + age + "," + bloodGroup + "," + contact + "," + location;
    }

    public String displayDetails() {
        return "ID: " + donorId + " | Name: " + name + " | Age: " + age + 
               " | Blood Group: " + bloodGroup + " | Contact: " + contact + " | Location: " + location;
    }
}

