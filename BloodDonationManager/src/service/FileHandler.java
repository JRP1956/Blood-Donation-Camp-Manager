package service;

import model.Donor;
import model.Donation;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String DONORS_FILE = "donors.csv";
    private static final String DONATIONS_FILE = "donations.csv";

    public void saveDonorData(List<Donor> donors) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONORS_FILE))) {
            for (Donor donor : donors) {
                writer.write(donor.toString());
                writer.newLine();
            }
        }
    }

    public List<Donor> readDonorData() throws IOException {
        List<Donor> donors = new ArrayList<>();
        File file = new File(DONORS_FILE);
        
        if (!file.exists()) {
            file.createNewFile();
            return donors;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Donor donor = new Donor(parts[0], parts[1], Integer.parseInt(parts[2]), 
                                           parts[3], parts[4], parts[5]);
                    donors.add(donor);
                }
            }
        }
        return donors;
    }

    public void saveDonationData(List<Donation> donations) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DONATIONS_FILE))) {
            for (Donation donation : donations) {
                writer.write(donation.toString());
                writer.newLine();
            }
        }
    }

    public List<Donation> readDonationData() throws IOException {
        List<Donation> donations = new ArrayList<>();
        File file = new File(DONATIONS_FILE);
        
        if (!file.exists()) {
            file.createNewFile();
            return donations;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Donation donation = new Donation(parts[0], LocalDate.parse(parts[1]), parts[2]);
                    donations.add(donation);
                }
            }
        }
        return donations;
    }
}

