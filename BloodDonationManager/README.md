# Blood Donation Camp Manager

A Java-based desktop application for managing blood donation camps, donor registration, and donation scheduling.

## Features

- **Donor Registration**: Register new blood donors with eligibility checking (age 18-65)
- **Donor Search**: Search donors by blood group, location, or name
- **Donation Scheduling**: Schedule and track blood donation appointments
- **Reports & Statistics**: View comprehensive reports including:
  - Summary statistics
  - All donors list
  - Donation schedules
  - Blood group distribution

## Requirements

- Java JDK 8 or higher
- Operating System: Windows, macOS, or Linux

## Installation & Usage

### Method 1: Using Scripts (Easiest)

**For Windows:**
```bash
# Compile the project
compile.bat

# Run the application
run.bat
```

**For Mac/Linux:**
```bash
# Make scripts executable
chmod +x compile.sh run.sh

# Compile the project
./compile.sh

# Run the application
./run.sh
```

### Method 2: Manual Command Line

```bash
# Navigate to project directory
cd BloodDonationManager

# Compile
mkdir bin
javac -d bin src/model/*.java src/service/*.java src/ui/*.java

# Run
cd bin
java ui.MainFrame
cd ..
```

### Method 3: Using IDE

Open this project in Eclipse, IntelliJ IDEA, or NetBeans and run the `MainFrame` class.

## Project Structure

```
BloodDonationManager/
├── src/
│   ├── model/
│   │   ├── Donor.java          # Donor data model
│   │   └── Donation.java       # Donation scheduling model
│   ├── service/
│   │   ├── FileHandler.java    # CSV file operations
│   │   └── DonorService.java   # Business logic
│   └── ui/
│       ├── MainFrame.java      # Main application window
│       ├── WelcomePanel.java   # Welcome screen
│       ├── RegistrationForm.java # Donor registration
│       ├── SearchForm.java      # Donor search interface
│       ├── ScheduleForm.java    # Donation scheduling
│       └── ReportForm.java      # Reports and statistics
├── bin/                         # Compiled class files (created after compilation)
├── donors.csv                   # Donor database (created at runtime)
├── donations.csv                # Donation records (created at runtime)
├── compile.bat                  # Windows compile script
├── compile.sh                   # Unix/Mac compile script
├── run.bat                      # Windows run script
├── run.sh                       # Unix/Mac run script
└── README.md                    # This file
```

## Usage Guide

### Registering a Donor

1. Click **Manage → Register Donor** from the menu
2. Fill in the form:
   - Name: Donor's full name
   - Age: Must be between 18-65 years
   - Blood Group: Select from dropdown
   - Contact: Phone number or email
   - Location: City or area
3. Click **Register** button
4. A unique Donor ID will be assigned automatically

### Searching for Donors

1. Click **Manage → Search Donors**
2. Select search type: Blood Group, Location, or Name
3. Enter search value
4. Click **Search** button
5. Results appear in the table below
6. Click **Show All** to display all registered donors

### Scheduling a Donation

1. Click **Manage → Schedule Donation**
2. Enter the Donor ID
3. Select donation date using the date picker
4. Click **Schedule** button
5. To update donation status, select status and click **Update Status**

### Viewing Reports

1. Click **Manage → View Reports**
2. Browse different tabs:
   - **Summary**: Key statistics
   - **All Donors**: Complete donor list
   - **Donations**: All scheduled/completed donations
   - **Blood Group Stats**: Distribution by blood type

## Data Persistence

All data is automatically saved to CSV files:
- `donors.csv` - Contains all registered donors
- `donations.csv` - Contains all donation records

These files are created automatically when you first run the application.

## Troubleshooting

### "javac is not recognized"
- Install Java JDK from https://www.oracle.com/java/technologies/downloads/
- Add JDK to your system PATH

### "package does not exist" error
- Ensure all files are in correct package folders
- Clean and recompile the project

### Application doesn't start
- Make sure you're running from the correct directory
- Verify MainFrame class has the main method
- Check that all dependencies compiled successfully

## Features Implemented

✅ Donor registration with eligibility checking
✅ Automatic donor ID generation
✅ Search functionality (by blood group, location, name)
✅ Donation scheduling with date picker
✅ Status tracking for donations
✅ Comprehensive reports and statistics
✅ Data persistence via CSV files
✅ Modern Swing GUI with CardLayout
✅ Menu-based navigation

## License

This project is provided as-is for educational and practical use.

## Support

For issues or questions, please refer to the troubleshooting section above.

