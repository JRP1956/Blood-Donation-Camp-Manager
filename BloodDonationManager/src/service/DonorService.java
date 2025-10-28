package service;

import model.Donor;
import model.Donation;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DonorService {
    private List<Donor> donors;
    private List<Donation> donations;
    private FileHandler fileHandler;

    public DonorService() {
        fileHandler = new FileHandler();
        try {
            donors = fileHandler.readDonorData();
            donations = fileHandler.readDonationData();
        } catch (IOException e) {
            donors = new ArrayList<>();
            donations = new ArrayList<>();
        }
    }

    public boolean registerDonor(Donor donor) {
        if (donor == null || !donor.isEligible()) {
            return false;
        }
        
        // Check for duplicate donor ID
        for (Donor d : donors) {
            if (d.getDonorId().equals(donor.getDonorId())) {
                return false;
            }
        }
        
        donors.add(donor);
        try {
            fileHandler.saveDonorData(donors);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Donor> searchDonorByBloodGroup(String bloodGroup) {
        return donors.stream()
                .filter(d -> d.getBloodGroup().equalsIgnoreCase(bloodGroup))
                .collect(Collectors.toList());
    }

    public List<Donor> searchDonorByLocation(String location) {
        return donors.stream()
                .filter(d -> d.getLocation().toLowerCase().contains(location.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Donor> searchDonorByName(String name) {
        return donors.stream()
                .filter(d -> d.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean scheduleDonation(String donorId, LocalDate date) {
        // Check if donor exists
        boolean donorExists = donors.stream().anyMatch(d -> d.getDonorId().equals(donorId));
        if (!donorExists) {
            return false;
        }

        Donation donation = new Donation(donorId, date, "Scheduled");
        donations.add(donation);
        
        try {
            fileHandler.saveDonationData(donations);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateDonationStatus(String donorId, String status) {
        for (Donation d : donations) {
            if (d.getDonorId().equals(donorId)) {
                d.setStatus(status);
                try {
                    fileHandler.saveDonationData(donations);
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    public List<Donor> getAllDonors() {
        return new ArrayList<>(donors);
    }

    public List<Donation> getAllDonations() {
        return new ArrayList<>(donations);
    }

    public String generateNextDonorId() {
        if (donors.isEmpty()) {
            return "D001";
        }
        int maxId = 0;
        for (Donor d : donors) {
            String id = d.getDonorId().substring(1);
            try {
                int num = Integer.parseInt(id);
                if (num > maxId) maxId = num;
            } catch (NumberFormatException e) {
                continue;
            }
        }
        return String.format("D%03d", maxId + 1);
    }
}

