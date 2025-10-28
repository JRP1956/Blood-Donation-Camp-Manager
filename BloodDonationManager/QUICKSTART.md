# Quick Start Guide

## 🚀 Running the Application

### For Mac/Linux Users:

```bash
# Navigate to the project directory
cd BloodDonationManager

# Compile the project (first time only)
./compile.sh

# Run the application
./run.sh
```

### For Windows Users:

```bash
# Navigate to the project directory
cd BloodDonationManager

# Compile the project (first time only)
compile.bat

# Run the application
run.bat
```

### Alternative: Run from Command Line

```bash
# Compile
javac -d bin src/model/*.java src/service/*.java src/ui/*.java

# Run
cd bin && java ui.MainFrame && cd ..
```

## 📱 First Use

When you run the application for the first time:

1. **Welcome Screen**: You'll see the welcome screen with "Saving Lives, One Donation at a Time"

2. **Register a Donor**:
   - Click **Manage → Register Donor**
   - Fill in the form:
     - Name: John Doe
     - Age: 25
     - Blood Group: A+
     - Contact: 9876543210
     - Location: Mumbai
   - Click **Register**
   - Note the Donor ID (e.g., D001)

3. **Search for Donors**:
   - Click **Manage → Search Donors**
   - Select "Blood Group" from dropdown
   - Enter "A+" and click **Search**
   - Your registered donor will appear

4. **Schedule a Donation**:
   - Click **Manage → Schedule Donation**
   - Enter the Donor ID (D001)
   - Select a future date
   - Click **Schedule**

5. **View Reports**:
   - Click **Manage → View Reports**
   - Browse different tabs for statistics

## 📁 Data Storage

Data is automatically saved to:
- `donors.csv` - All registered donors
- `donations.csv` - All donation records

These files are created in the same directory where you run the application.

## 🎯 Key Features

✅ Donor registration with age validation (18-65 years)
✅ Automatic Donor ID generation
✅ Search by Blood Group, Location, or Name
✅ Donation scheduling with date picker
✅ Status tracking (Scheduled, Completed, Cancelled)
✅ Comprehensive reports and statistics
✅ Blood group distribution charts

## ⚠️ Troubleshooting

**Issue**: Java not found
**Solution**: Install Java JDK 8+ from https://www.oracle.com/java/technologies/downloads/

**Issue**: Permission denied (Mac/Linux)
**Solution**: Run `chmod +x compile.sh run.sh`

**Issue**: Application window doesn't appear
**Solution**: Make sure you're running from the correct directory

## 📞 Menu Navigation

- **File → Exit**: Close the application
- **Manage → Register Donor**: Add new donors
- **Manage → Search Donors**: Find existing donors
- **Manage → Schedule Donation**: Book donation appointments
- **Manage → View Reports**: See statistics and data

Enjoy managing your blood donation camp! 🩸

