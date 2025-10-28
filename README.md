### Project: Blood Donation Camp Manager

## Overview
Blood Donation Camp Manager is a Java Swing desktop application that helps organizers manage blood donation drives. It supports donor registration, searching by multiple criteria, scheduling donations, tracking statuses, and viewing summary reports. Data is stored locally in CSV files, so no database setup is required.

## Features
- Donor registration with automatic ID generation (D001, D002, …) and age eligibility checks (18–65)
- Search donors by blood group, location, or name; show-all results
- Schedule donation appointments; update status (Scheduled, Completed, Cancelled)
- Reports: summary metrics, all donors, all donations, blood group distribution
- Modernized UI with quick-action home buttons and Cmd+H Home shortcut (macOS)
- Portable: runs on Windows, macOS, and Linux with JDK 8+

## Tech Stack
- Java (JDK 8+; recommended JDK 21)
- Swing (desktop UI)
- CSV (File I/O for persistence)

## Project Structure
```
BloodDonationManager/
├── src/
│   ├── model/
│   │   ├── Donor.java
│   │   └── Donation.java
│   ├── service/
│   │   ├── FileHandler.java
│   │   └── DonorService.java
│   └── ui/
│       ├── MainFrame.java
│       ├── WelcomePanel.java
│       ├── RegistrationForm.java
│       ├── SearchForm.java
│       ├── ScheduleForm.java
│       └── ReportForm.java
├── bin/                 # compiled class files (generated)
├── donors.csv           # created at runtime
├── donations.csv        # created at runtime
├── compile.sh           # macOS/Linux compile
├── run.sh               # macOS/Linux run
├── compile.bat          # Windows compile
└── run.bat              # Windows run
```

## Prerequisites
- Java JDK 8 or higher (recommended: Temurin 21)
- macOS/Linux: bash; Windows: cmd/PowerShell

### macOS (Homebrew)
```bash
brew install temurin@21
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v 21)' >> ~/.zshrc
echo 'export PATH="$JAVA_HOME/bin:$PATH"' >> ~/.zshrc
source ~/.zshrc
java -version
```

### Windows
- Install “Temurin 21 (Hotspot)” or Oracle JDK 17/21
- Ensure java and javac are on PATH
- Verify: java -version

## Build and Run

### macOS/Linux
```bash
cd BloodDonationManager
chmod +x compile.sh run.sh
./compile.sh
./run.sh
```

### Windows
```bat
cd BloodDonationManager
compile.bat
run.bat
```

### Direct (without scripts)
```bash
mkdir -p bin
javac -d bin src/model/*.java src/service/*.java src/ui/*.java
java -cp bin ui.MainFrame
```

## How to Use

### Home (Welcome) Page
- Click one of the large buttons:
  - Register Donor
  - Search Donors
  - Schedule Donation
  - View Reports
- Shortcut: Cmd+H (macOS) to return Home from any page.

### Register a Donor
1. Open Register Donor.
2. Enter Name, Age (18–65), select Blood Group, Contact, Location.
3. Click “Register Donor”.
4. Note the auto-generated Donor ID (e.g., D001).

### Search Donors
1. Open Search Donors.
2. Choose “Blood Group”, “Location”, or “Name”.
3. Enter a value (e.g., “A+”, “Mumbai”, “John”).
4. Click “Search” or use “Show All”.
5. Results display in a styled table (sortable via header UI controls per platform).

### Schedule Donation
1. Open Schedule Donation.
2. Enter an existing Donor ID (e.g., D001).
3. Pick a date and click “Schedule Donation”.
4. To update status later, choose a status and click “Update Status”.

### Reports
- Summary: total donors, total donations, completed/pending counts, average age.
- All Donors: table of all registered donors.
- Donations: table of all scheduled/completed/cancelled donations.
- Blood Group Stats: per-group counts.

## Data Persistence
- `donors.csv`: donor info
- `donations.csv`: donation schedules and statuses
- CSVs are created in the project root on first run. You can open them in Excel/Numbers/Sheets.

## Keyboard Shortcuts
- Home: Cmd+H (macOS) from any screen to return to the welcome page.

## Troubleshooting
- “Unable to locate a Java Runtime”
  - Install JDK (see Prerequisites) and ensure JAVA_HOME/PATH are set.
- “package does not exist” on compile
  - Make sure you’re compiling from project root with `-d bin` and correct `src/...` glob paths.
- No donors appear in Search
  - Ensure at least one donor is registered and inputs (e.g., blood group “A+”) match exactly for group searches; name/location are case-insensitive partial matches.
- CSV permission issues
  - Run from a folder where you have write permissions.

## Contributing
- Fork the repo, create a feature branch, and open a PR.
- Keep code readable, follow existing naming and formatting conventions.

## License
- MIT License (or your preferred license).

## Acknowledgments
- Built with Java Swing. Designed for quick, local, database-free management of donation camps.

You can paste this directly into your README.md.
