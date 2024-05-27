import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// import javafx.scene.text.Font;

public class eventPlanner extends JFrame {
    // Variables
    // Bold font
    Font boldFont = new Font("Arial", Font.BOLD, 14);

    // Non-bold (Regular Weight) font
    Font regularFont = new Font("Arial", Font.PLAIN, 14);
    public static String currentEventName = "";
    public static String currentEventDate = "";
    public static float eventBudgetAllocated = 0;
    public static int totalInflows = 0;
    public static int totalOutflows = 0;
    public static int attendeeCount = 0;

    public static List<String> eventNames = new ArrayList<>();
    public static List<String> eventDates = new ArrayList<>();
    public static List<String> programNames = new ArrayList<>();
    public static List<String> programDate = new ArrayList<>();
    public static List<String> programTime = new ArrayList<>();
    public static List<String> outflowNames = new ArrayList<>();
    public static List<Float> outflowPrices = new ArrayList<>();
    public static List<String> inflowNames = new ArrayList<>();
    public static List<Float> inflowPrices = new ArrayList<>();

    JButton financesToggle, peopleToggle, programsToggle;
    Color blueColor = new Color(0x2F4F4F);
    Border border = BorderFactory.createLineBorder(blueColor, 1);

    JLabel titleLabel = new JLabel(currentEventName);
    JLabel dateLabel = new JLabel("Date");
    JLabel attendeesLabel = new JLabel("Attendees");
    JLabel attendeesNumberLabel = new JLabel("500");
    JLabel attendeesPeopleLabel = new JLabel("people");
    JLabel budgetLabel = new JLabel("Budget Allocated");
    JLabel budgetNumberLabel = new JLabel("P5,000");
    JLabel remainingLabel = new JLabel("Remaining Budget");
    JLabel remainingFirstNumberLabel = new JLabel("P5,000");
    JLabel remaingingSecondNumberLabel = new JLabel("/ P10,000");
    JLabel checklistLabel = new JLabel("Checklist");
    JLabel dashboardLabel = new JLabel("Dashboard");
    JLabel welcomeLabel = new JLabel("Welcome back, Andrew!");
    JLabel totalExpensesLabel = new JLabel("Total Expenses");
    JLabel totalExpensesAmountLabel = new JLabel("P500");
    JLabel totalRevenueLabel = new JLabel("Total Revenue");
    JLabel totalRevenueAmountLabel = new JLabel("P500");
    JLabel revenueLabel = new JLabel("Revenue");
    JLabel revenueNameLabel = new JLabel("Name");
    JLabel revenuePriceLabel = new JLabel("Price");
    JLabel expensesLabel = new JLabel("Expenses");
    JLabel expensesNameLabel = new JLabel("Name");
    JLabel expensesPriceLabel = new JLabel("Price");
    JLabel programLabel = new JLabel("Program");

    // - > Icons
    ImageIcon financesIcon = new ImageIcon(getClass().getResource("/assets/Icons/Finances.png"));
    ImageIcon addProgramIcon = new ImageIcon(getClass().getResource("/assets/Icons/Add Program.png"));
    ImageIcon addResourceIcon = new ImageIcon(getClass().getResource("/assets/Icons/Add Resource.png"));
    ImageIcon addStaffIcon = new ImageIcon(getClass().getResource("/assets/Icons/Add Staff.png"));
    ImageIcon addAttendeeIcon = new ImageIcon(getClass().getResource("/assets/Icons/Add Attendee.png"));
    ImageIcon addTaskIcon = new ImageIcon(getClass().getResource("/assets/Icons/Add Task.png"));

    JButtonIcon financesButton = new JButtonIcon(financesIcon, false);
    JButtonIcon addProgramButton = new JButtonIcon(addProgramIcon, false);
    JButtonIcon addResourceButton = new JButtonIcon(addResourceIcon, false);
    JButtonIcon addStaffButton = new JButtonIcon(addStaffIcon, false);
    JButtonIcon addAttendeeButton = new JButtonIcon(addAttendeeIcon, false);
    JButtonIcon addTaskButton = new JButtonIcon(addTaskIcon, false);
    JButtonIcon loadEventButton = new JButtonIcon(addTaskIcon, false);

    
    Finances FINANCES = new Finances();

    People PEOPLE = new People();

    Programs RESOURCES = new Programs();

    public eventPlanner() {
        setTitle("Dashboard");

        setSize(1440, 1024);

        setLayout(null);

        add(FINANCES);
        FINANCES.setLayout(null);
        setContentPane(FINANCES);
        add(PEOPLE);
        PEOPLE.setLayout(null);
        add(RESOURCES);
        RESOURCES.setLayout(null);

        JPanel background = new JPanel();
        JPanel menuPanel1 = new JPanel();
        JPanel menuPanel2 = new JPanel();
        JPanel totalExpensesPanel = new JPanel();
        JPanel totalRevenuePanel = new JPanel();
        JPanel attendeesPanel = new JPanel();
        JPanel budgetPanel = new JPanel();
        JPanel remainingBudgetPanel = new JPanel();
        JPanel checkPanel = new JPanel();
        JPanel revenuePanel = new JPanel();
        JPanel expensesPanel = new JPanel();
        JPanel programPanel = new JPanel();

        // Font boldFont = new Font("Inter-Bold", 24);
        // Font lightFont = new Font("Inter-Light", 13);

        // Panel for revenue pop
        financesButton.setBounds(1343, 230, 80, 80);
        addProgramButton.setBounds(1343, 300, 80, 80);
        addResourceButton.setBounds(1343, 380, 80, 80);
        addStaffButton.setBounds(1343, 460, 80, 80);
        addAttendeeButton.setBounds(1343, 540, 80, 80);
        addTaskButton.setBounds(1343, 850, 80, 80);
        loadEventButton.setBounds(1343, 100, 80, 80);

        financesButton.setBackground(blueColor);
        financesButton.setBorder(border);
        addProgramButton.setBackground(blueColor);
        addProgramButton.setBorder(border);
        addResourceButton.setBackground(blueColor);
        addResourceButton.setBorder(border);
        addStaffButton.setBackground(blueColor);
        addStaffButton.setBorder(border);
        addAttendeeButton.setBackground(blueColor);
        addAttendeeButton.setBorder(border);
        addTaskButton.setBackground(blueColor);
        addTaskButton.setBorder(border);
        loadEventButton.setBackground(blueColor);
        loadEventButton.setBorder(border);
        //
        financesButton.setIcon(financesIcon);
        addProgramButton.setIcon(addProgramIcon);
        addResourceButton.setIcon(addResourceIcon);
        addStaffButton.setIcon(addStaffIcon);
        addAttendeeButton.setIcon(addAttendeeIcon);
        addTaskButton.setIcon(addTaskIcon);
        loadEventButton.setIcon(addTaskIcon);

        // ----------------------------------------------------------------- >

        // - > Menu
        background.setBackground(Color.WHITE);
        background.setBounds(0, 0, 1440, 1024);

        titleLabel.setForeground(Color.WHITE);
        dateLabel.setForeground(Color.WHITE);
        attendeesLabel.setForeground(blueColor);
        attendeesNumberLabel.setForeground(blueColor);
        attendeesPeopleLabel.setForeground(blueColor);
        budgetLabel.setForeground(blueColor);
        budgetNumberLabel.setForeground(blueColor);
        remainingLabel.setForeground(blueColor);
        remainingFirstNumberLabel.setForeground(blueColor);
        remaingingSecondNumberLabel.setForeground(Color.GRAY);
        checklistLabel.setForeground(blueColor);
        dashboardLabel.setForeground(Color.BLACK);
        welcomeLabel.setForeground(Color.BLACK);

        titleLabel.setBounds(67, 72, 252, 44);
        dateLabel.setBounds(67, 84, 100, 100);
        attendeesLabel.setBounds(91, 190, 100, 100);
        attendeesNumberLabel.setBounds(91, 224, 100, 100);
        attendeesPeopleLabel.setBounds(150, 224, 100, 100);
        budgetLabel.setBounds(91, 309, 100, 100);
        budgetNumberLabel.setBounds(91, 343, 100, 100);
        remainingLabel.setBounds(91, 428, 200, 100);
        remainingFirstNumberLabel.setBounds(91, 456, 100, 100);
        remaingingSecondNumberLabel.setBounds(150, 456, 100, 100);
        checklistLabel.setBounds(91, 559, 100, 100);
        dashboardLabel.setBounds(464, 34, 100, 100);
        welcomeLabel.setBounds(464, 65, 500, 100);

        menuPanel1.setBackground(blueColor);
        menuPanel1.setBounds(0, 0, 409, 1024);

        menuPanel2.setBackground(blueColor);
        menuPanel2.setBounds(1341, 0, 365, 1024);

        attendeesPanel.setBackground(Color.WHITE);
        attendeesPanel.setBounds(70, 215, 303, 93);

        budgetPanel.setBackground(Color.WHITE);
        budgetPanel.setBounds(70, 334, 303, 93);

        remainingBudgetPanel.setBackground(Color.WHITE);
        remainingBudgetPanel.setBounds(70, 453, 303, 93);

        checkPanel.setBackground(Color.WHITE);
        checkPanel.setBounds(70, 582, 303, 290);
        // ---------------------------------------------------------------- >

        // - > Finances
        totalExpensesLabel.setForeground(Color.WHITE);
        totalExpensesAmountLabel.setForeground(Color.WHITE);
        totalRevenueLabel.setForeground(Color.WHITE);
        totalRevenueAmountLabel.setForeground(Color.WHITE);
        revenueLabel.setForeground(blueColor);
        revenueNameLabel.setForeground(blueColor);
        revenuePriceLabel.setForeground(blueColor);
        expensesLabel.setForeground(blueColor);
        expensesNameLabel.setForeground(blueColor);
        expensesPriceLabel.setForeground(blueColor);

        totalExpensesLabel.setBounds(486, 156, 100, 100);
        totalExpensesAmountLabel.setBounds(486, 182, 100, 100);
        totalRevenueLabel.setBounds(935, 156, 100, 100);
        totalRevenueAmountLabel.setBounds(935, 182, 100, 100);
        revenueLabel.setBounds(500, 265, 100, 100);
        revenueNameLabel.setBounds(500, 300, 100, 100);
        revenuePriceLabel.setBounds(700, 300, 100, 100);
        expensesLabel.setBounds(950, 265, 100, 100);
        expensesNameLabel.setBounds(950, 300, 100, 100);
        expensesPriceLabel.setBounds(1150, 300, 100, 100);

        totalExpensesPanel.setBackground(blueColor);
        totalExpensesPanel.setBounds(458, 173, 396, 93);

        totalRevenuePanel.setBackground(blueColor);
        totalRevenuePanel.setBounds(907, 173, 396, 93);

        revenuePanel.setBackground(Color.WHITE);
        revenuePanel.setBounds(458, 282, 395, 505);
        revenuePanel.setBorder(border);

        expensesPanel.setBackground(Color.WHITE);
        expensesPanel.setBounds(907, 282, 395, 505);
        expensesPanel.setBorder(border);

        // -------------------------------------------------------------- >

        // - > Button functions
        addButtons();
        financesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                financeInputPopup();
            }
        });
        addProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programInputPopup();
            }
        });
        addResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resourceInputPopup();
            }
        });
        addStaffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffInputPopup();
            }
        });
        addAttendeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attendeeInputPopup();
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskInputPopup();
            }
        });
        loadEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEventPopup();
            }
        });

        // --------------------------------------------------------------- >

        // - > Programs
        programLabel.setForeground(blueColor);
        // programLabel.setBounds(0, 0, 100, 100);
        // -------------------------------------------------------------- >

        // toggles for dashboard
        financesToggle = new JButton("FINANCES");
        financesToggle.setFont(boldFont);
        financesToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(FINANCES);
                financesToggle.setForeground(Color.WHITE);
                financesToggle.setBackground(blueColor);
                programsToggle.setBackground(Color.WHITE);
                programsToggle.setForeground(blueColor);
                peopleToggle.setBackground(Color.WHITE);
                peopleToggle.setForeground(blueColor);
                addToggle();
                addButtons();
            }
        });

        programsToggle = new JButton("RESOURCES");
        programsToggle.setFont(boldFont);
        programsToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programsToggle.setForeground(Color.WHITE);
                programsToggle.setBackground(blueColor);
                financesToggle.setBackground(Color.WHITE);
                financesToggle.setForeground(blueColor);
                peopleToggle.setBackground(Color.WHITE);
                peopleToggle.setForeground(blueColor);
                setContentPane(RESOURCES);
                addToggle();
                addButtons();
            }
        });

        peopleToggle = new JButton("PEOPLE");
        peopleToggle.setFont(boldFont);
        peopleToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                peopleToggle.setForeground(Color.WHITE);
                peopleToggle.setBackground(blueColor);
                programsToggle.setBackground(Color.WHITE);
                programsToggle.setForeground(blueColor);
                financesToggle.setBackground(Color.WHITE);
                financesToggle.setForeground(blueColor);
                setContentPane(PEOPLE);
                addToggle();
                addButtons();
            }
        });

        financesToggle.setBackground(blueColor);
        financesToggle.setForeground(Color.WHITE);
        financesToggle.setBounds(458, 173, 259, 81);
        financesToggle.setBorder(border);

        programsToggle.setBackground(Color.WHITE);
        programsToggle.setForeground(blueColor);
        programsToggle.setBounds(755, 173, 259, 81);
        programsToggle.setBorder(border);

        peopleToggle.setBackground(Color.WHITE);
        peopleToggle.setForeground(blueColor);
        peopleToggle.setBounds(1043, 173, 259, 81);
        peopleToggle.setBorder(border);

        addToggle();

        setVisible(true);
    }

    public void addToggle() {
        getContentPane().add(financesToggle);
        getContentPane().add(peopleToggle);
        getContentPane().add(programsToggle);
        revalidate();
        repaint();
    }

    private void addButtons() {
        getContentPane().add(financesButton);
        getContentPane().add(addProgramButton);
        getContentPane().add(addResourceButton);
        getContentPane().add(addStaffButton);
        getContentPane().add(addAttendeeButton);
        getContentPane().add(addTaskButton);
        getContentPane().add(loadEventButton);

    }

    public void financeInputPopup() { // somewhat done
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Finance Management";
        String[] options = { "Add", "Edit", "Delete" };
        int choice = JOptionPane.showOptionDialog(null,
                "Select an action:",
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Add
                addFinance(title);
                break;
            case 1: // Edit
                editFinance(title);
                break;
            case 2: // Delete
                deleteFinance(title);
                break;
            default:
                // Cancel or close dialog, do nothing
                break;
        }
    }

    private void addFinance(String title) { // somewhat done
        String[] inflowOutflowOptions = { "Inflow", "Outflow" };
        int typeChoice = JOptionPane.showOptionDialog(null,
                "Select type:",
                "Add Entry - " + title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                inflowOutflowOptions,
                inflowOutflowOptions[0]);

        if (typeChoice == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String type = inflowOutflowOptions[typeChoice];
        String name = JOptionPane.showInputDialog(null, "Enter " + type + " name:", "Add Entry - " + title,
                JOptionPane.PLAIN_MESSAGE);
        if (name != null && !name.isEmpty()) {
            try {
                String priceStr = JOptionPane.showInputDialog(null, "Enter " + type + " value:", "Add Entry - " + title,
                        JOptionPane.PLAIN_MESSAGE);
                if (priceStr != null && !priceStr.isEmpty()) {
                    float price = Float.parseFloat(priceStr);
                    JOptionPane.showMessageDialog(null,
                            type + " added successfully!\nName: " + name + "\nValue: " + price, "Add Entry - " + title,
                            JOptionPane.INFORMATION_MESSAGE);

                    // Save to CSV
                    String csvFilePath = currentEventName.toLowerCase() + "_finances.csv";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
                        writer.write(type + "," + name + "," + price);
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                        e.printStackTrace();
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Value cannot be empty.", "Add Entry - " + title,
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format entered.", "Add Entry - " + title,
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        csvToArrayList();
    }

    private void editFinance(String title) { // somewhat done
        String[] financeTypes = { "Inflow", "Outflow" };
        int choice = JOptionPane.showOptionDialog(null, "Choose the type of finance to edit:", "Edit Finance",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, financeTypes, financeTypes[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // User canceled the dialog
        }

        String type = financeTypes[choice];

        try {
            String csvFilePath = currentEventName.toLowerCase() + "_finances.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase(type)) {
                    optionsList.add(line);
                }
            }

            if (optionsList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No " + type.toLowerCase() + " records found.", "Edit " + type,
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] options = optionsList.toArray(new String[0]);
            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit " + type, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedEntry != null && !selectedEntry.isEmpty()) {
                String correctWord = selectedEntry;

                int selectedIndex = -1;
                for (int i = 0; i < lines.size(); i++) {
                    System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                    if (correctWord.equals(lines.get(i))) {
                        selectedIndex = i;
                        System.out.println("Match found at index " + i);
                        break;
                    } else {
                        System.out.println("No match at index " + i);
                    }
                }
                System.out.println("Final selectedIndex: " + selectedIndex);

                if (selectedIndex != -1) {
                    String newName = JOptionPane.showInputDialog(null, "Enter new name:");
                    String newPrice = JOptionPane.showInputDialog(null, "Enter new price:");
                    String newData = "";
                    if (type.equals("Outflow")) {
                        newData = "Outflow," + newName + "," + newPrice;
                    } else if (type.equals("Inflow")) {
                        newData = "Inflow," + newName + "," + newPrice;
                    }
                    lines.set(selectedIndex, newData);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                        for (String updatedLine : lines) {
                            writer.write(updatedLine);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                        e.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Entry edited successfully!", "Edit " + type,
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid selection!", "Edit " + type,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No records found!", "Edit " + type, JOptionPane.WARNING_MESSAGE);
        }

        csvToArrayList();
    }

    private void deleteFinance(String title) { // somewhat done
        String[] financeTypes = { "Inflow", "Outflow" };
        int choice = JOptionPane.showOptionDialog(null, "Choose the type of finance to delete:", "Delete Finance",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, financeTypes, financeTypes[0]);

        if (choice == JOptionPane.CLOSED_OPTION) {
            return; // User canceled the dialog
        }

        String type = financeTypes[choice];
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_finances.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase(type)) {
                    optionsList.add(line);
                }
            }

            if (optionsList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No " + type.toLowerCase() + " records found.", "Delete " + type,
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            String[] options = optionsList.toArray(new String[0]);
            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete " + type, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedEntry != null && !selectedEntry.isEmpty()) {
                String correctWord = selectedEntry;
                int selectedIndex = -1;
                for (int i = 0; i < lines.size(); i++) {
                    if (correctWord.equals(lines.get(i))) {
                        selectedIndex = i;
                        break;
                    }
                }

                if (selectedIndex != -1) {
                    lines.remove(selectedIndex);
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                        for (String updatedLine : lines) {
                            writer.write(updatedLine);
                            writer.newLine();
                        }
                        JOptionPane.showMessageDialog(null, "Entry deleted successfully!", "Delete " + type,
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                        e.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid selection!", "Delete " + type,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No " + type.toLowerCase() + " found!", "Delete " + type,
                    JOptionPane.WARNING_MESSAGE);
        }

        csvToArrayList();
    }

    public void programInputPopup() { // not done
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Program Management";
        String[] options = { "Add Program", "Edit Program", "Delete Program" };
        int choice = JOptionPane.showOptionDialog(null,
                "Select an action:",
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Add Program
                addProgram(title);
                break;
            case 1: // Edit Program
                editProgram();
                break;
            case 2: // Delete Program
                deleteProgram();
                break;
            default:
                break;
        }
    }

    private void addProgram(String title) {
        String prompt = "Enter Program Name:";
        String programName = JOptionPane.showInputDialog(null, prompt, "Add Program - " + title,
                JOptionPane.PLAIN_MESSAGE);

        if (programName != null && !programName.isEmpty()) {
            try {
                String datePrompt = "Enter Date (yyyy-MM-dd):";
                String timePrompt = "Enter Time (hh:mm a):";
                String dateString = JOptionPane.showInputDialog(null, datePrompt, "Add Program - " + title,
                        JOptionPane.PLAIN_MESSAGE);
                String timeString = JOptionPane.showInputDialog(null, timePrompt, "Add Program - " + title,
                        JOptionPane.PLAIN_MESSAGE);

                if (dateString != null && !dateString.isEmpty() && timeString != null && !timeString.isEmpty()) {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                    LocalDate date = LocalDate.parse(dateString, dateFormatter);
                    LocalTime time = LocalTime.parse(timeString, timeFormatter);

                    LocalDateTime dateTime = date.atTime(time);

                    // Save to CSV file
                    String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
                        writer.append("Program," + programName + "," + dateString + "," + timeString);
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                        e.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Program added successfully!", "Add Program - " + title,
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Date and time cannot be empty.", "Add Program - " + title,
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", "Add Program - " + title,
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Program name cannot be empty.", "Add Program - " + title,
                    JOptionPane.ERROR_MESSAGE);
        }

        csvToArrayList();
    }

    private void editProgram() {
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
            List<String> lines = new ArrayList<>();
            String newProgram = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(
                                values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + "," + values[3]);
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            String[] values = lines.get(selectedIndex).split(",");
            newProgram = JOptionPane.showInputDialog(null, "Enter new name:");
            if (newProgram != null && !newProgram.isEmpty()) {
                try {
                    String datePrompt = "Enter new date (yyyy-MM-dd):";
                    String timePrompt = "Enter new time (hh:mm a):";
                    String dateString = JOptionPane.showInputDialog(null, datePrompt, "Add Program - ",
                            JOptionPane.PLAIN_MESSAGE);
                    String timeString = JOptionPane.showInputDialog(null, timePrompt, "Add Program - ",
                            JOptionPane.PLAIN_MESSAGE);

                    if (dateString != null && !dateString.isEmpty() && timeString != null && !timeString.isEmpty()) {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

                        LocalDate date = LocalDate.parse(dateString, dateFormatter);
                        LocalTime time = LocalTime.parse(timeString, timeFormatter);

                        LocalDateTime dateTime = date.atTime(time);

                        String newData = "Program," + newProgram + "," + dateString + "," + timeString;

                        lines.set(selectedIndex, newData);

                        if (selectedIndex != -1) {
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                                for (String updatedLine : lines) {
                                    writer.write(updatedLine);
                                    writer.newLine();
                                }
                                JOptionPane.showMessageDialog(null,
                                        "Entry edited successfully!");
                            } catch (IOException e) {
                                System.err.println("Error writing to CSV file: " + e.getMessage());
                                e.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid selection!");
                        }

                        JOptionPane.showMessageDialog(null, "Program edited successfully!", "Add Program - ",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Date and time cannot be empty.", "Add Program - ",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", "Add Program - ",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Program name cannot be empty.", "Add Program - ",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No program found!");
        }

        csvToArrayList();
    }

    private void deleteProgram() {
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty() && !values[3].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + ","
                                + values[3].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                lines.remove(selectedIndex);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry deleted successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No program found!");
        }

        csvToArrayList();
    }

    public void resourceInputPopup() {
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Resource Management";
        String[] options = { "Add Resource", "Edit Resource", "Delete Resource" };
        int choice = JOptionPane.showOptionDialog(null,
                "Select an action:",
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0: // Add Resource
                addResource(title);
                break;
            case 1: // Edit Resource
                editResource(title);
                break;
            case 2: // Delete Resource
                deleteResource(title);
                break;
            default:
                // Cancel or close dialog, do nothing
                break;
        }
    }

    private void addResource(String title) { // somewhat done
        String resourceName = JOptionPane.showInputDialog(null, "Enter Resource Name:", "Add Resource - " + title,
                JOptionPane.PLAIN_MESSAGE);
        if (resourceName != null && !resourceName.isEmpty()) {
            String resourceSource = JOptionPane.showInputDialog(null, "Enter Resource Source:",
                    "Add Resource - " + title,
                    JOptionPane.PLAIN_MESSAGE);
            if (resourceSource != null && !resourceSource.isEmpty()) {
                while (true) {
                    try {
                        String input = JOptionPane.showInputDialog(null, "Resource Booking Date (YYYY-MM-DD):",
                                "Add Resource - " + title, JOptionPane.PLAIN_MESSAGE);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate bookingDate = LocalDate.parse(input, formatter);
                        String formattedDate = bookingDate.format(formatter);

                        String financesCSVFilePath = currentEventName.toLowerCase() + "_finances.csv";
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(financesCSVFilePath, true))) {
                            writer.write("Resource," + resourceName + "," + resourceSource + "," + formattedDate);
                            writer.newLine();
                        } catch (IOException e) {
                            System.err.println("Error writing to CSV file: " + e.getMessage());
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error writing resource booking information to file!",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        JOptionPane.showMessageDialog(null, "Resource booking added successfully!");
                        break;
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Please input a valid date", "Format error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid resource source entered!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Invalid resource name entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        csvToArrayList();
    }

    private static void editResource(String title) { // somewhat done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_finances.csv";
            List<String> lines = new ArrayList<>();
            String newResourceName = "";
            String newSourceName = "";
            String newDate = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Resource")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + ","
                                + values[3].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedEntry != null) {
                newResourceName = JOptionPane.showInputDialog(null, "Enter new resource name:");
                newSourceName = JOptionPane.showInputDialog(null, "Enter new source name:");

                while (true) {
                    try {
                        String input = JOptionPane.showInputDialog(null, "Enter new date (YYYY-MM-DD): ");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate date = LocalDate.parse(input, formatter);
                        newDate = date.format(formatter);
                        break;
                    } catch (java.time.format.DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Please input a valid date in the format YYYY-MM-DD",
                                "Format error",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }

            String newData = "Resource," + newResourceName + "," + newSourceName + "," + newDate;
            lines.set(selectedIndex, newData);

            if (selectedIndex != -1) {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry edited successfully");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No resources found!");
        }

        csvToArrayList();
    }

    private static void deleteResource(String title) { // somewhat done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_finances.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Resource")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + ","
                                + values[3].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                lines.remove(selectedIndex);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry deleted successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No resource found!");
        }

        csvToArrayList();
    }

    public static void staffInputPopup() {
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Staff Options";
        String message = "Choose an action for Staff:";
        String[] options = { "Add", "Edit", "Delete" };

        int choice = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (choice == 3 || choice == JOptionPane.CLOSED_OPTION) {
            return; // If Cancel or close option is selected, return
        }

        switch (choice) {
            case 0:
                addStaff();
                break;
            case 1:
                editStaff();
                break;
            case 2:
                deleteStaff();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid action.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addStaff() { // somewhat done
        String title = "Add Staff";
        String namePrompt = "Enter Staff Name:";
        String rolePrompt = "Enter Staff Role:";

        // Prompt for staff name
        String staffName = JOptionPane.showInputDialog(null, namePrompt, title, JOptionPane.PLAIN_MESSAGE);
        if (staffName == null || staffName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Staff name cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt for staff role
        String staffRole = JOptionPane.showInputDialog(null, rolePrompt, title, JOptionPane.PLAIN_MESSAGE);
        if (staffRole == null || staffRole.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Staff role cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String peopleCSVFilePath = currentEventName.toLowerCase() + "_people.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(peopleCSVFilePath, true))) {
                writer.append("Staff," + staffName + "," + staffRole);
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Staff information saved successfully!");
            } catch (IOException e) {
                System.err.println("Error writing to CSV file: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error writing staff information to file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        csvToArrayList();
        countAttendee();
    }

    private static void editStaff() { // done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_people.csv";
            List<String> lines = new ArrayList<>();
            String newStaffName = "";
            String newRole = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Staff")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            newStaffName = JOptionPane.showInputDialog(null, "Enter new staff name:");
            newRole = JOptionPane.showInputDialog(null, "Enter new role:");
            String newData = "Staff," + newStaffName + "," + newRole;

            lines.set(selectedIndex, newData);

            if (selectedIndex != -1) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry edited sucessfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No staff found!");
        }

        csvToArrayList();
        countAttendee();
    }

    private static void deleteStaff() { // done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_people.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Staff")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            // Select entry to delete
            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                lines.remove(selectedIndex);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry deleted successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No staff found!");
        }

        csvToArrayList();
        countAttendee();
    }

    public static void attendeeInputPopup() {
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Attendee Options";
        String message = "Choose an action for Attendees:";
        String[] options = { "Add", "Edit", "Delete" };

        int choice = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (choice == 3 || choice == JOptionPane.CLOSED_OPTION) {
            return; // If Cancel or close option is selected, return
        }

        switch (choice) {
            case 0:
                addAttendee();
                break;
            case 1:
                editAttendee();
                break;
            case 2:
                deleteAttendee();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid action.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addAttendee() { // somewhat done
        String title = "Add Attendee";

        String name = JOptionPane.showInputDialog(null, "Enter Attendee Name:", title, JOptionPane.PLAIN_MESSAGE);
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String email = JOptionPane.showInputDialog(null, "Enter Attendee Email:", title, JOptionPane.PLAIN_MESSAGE);
        if (email == null || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Email cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String seatCode = JOptionPane.showInputDialog(null, "Enter Seat Code:", title, JOptionPane.PLAIN_MESSAGE);
        if (seatCode == null || seatCode.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seat Code cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ticketNumber = JOptionPane.showInputDialog(null, "Enter Ticket Number:", title,
                JOptionPane.PLAIN_MESSAGE);
        if (ticketNumber == null || ticketNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ticket Number cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int ticketNum = Integer.parseInt(ticketNumber);
            String peopleCSVFilePath = currentEventName.toLowerCase() + "_people.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(peopleCSVFilePath, true))) {
                writer.write("Attendee," + name + "," + email + "," + seatCode + "," + ticketNum);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("Error writing to CSV file: " + e.getMessage());
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error writing attendee information to file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String message = "Attendee added successfully!\n\n"
                    + "Name: " + name + "\n"
                    + "Email: " + email + "\n"
                    + "Seat Code: " + seatCode + "\n"
                    + "Ticket Number: " + ticketNum;
            JOptionPane.showMessageDialog(null, message);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format for ticket number!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        csvToArrayList();
        countAttendee();
    }

    private static void editAttendee() { // somewhat done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_people.csv";
            List<String> lines = new ArrayList<>();
            String newName = "";
            String newEmail = "";
            String newSeating = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Attendee")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(
                                values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + "," + values[3]
                                        + "," + values[4]);
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            String[] values = lines.get(selectedIndex).split(",");
            newName = JOptionPane.showInputDialog(null, "Enter new name:");
            newEmail = JOptionPane.showInputDialog(null, "Enter new email:");
            newSeating = JOptionPane.showInputDialog(null, "Enter new seating:");
            String newData = "Attendee," + newName + "," + newEmail + "," + newSeating + "," + values[4];

            lines.set(selectedIndex, newData);

            if (selectedIndex != -1) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry edited successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No attendee found!");
        }

        csvToArrayList();
        countAttendee();
    }

    private static void deleteAttendee() { // somewhat done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_people.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }

            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Attendee")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(
                                values[0].trim() + "," + values[1].trim() + "," + values[2].trim() + "," + values[3]
                                        + "," + values[4]);
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                lines.remove(selectedIndex);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry deleted successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No attendee found!");
        }

        csvToArrayList();
        countAttendee();
    }

    public static void taskInputPopup() {
        if (currentEventName == null || currentEventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please load an event first.", "No Event Loaded",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String title = "Task Options";
        String message = "Choose an action for Task:";
        String[] options = { "Add", "Edit", "Delete" };

        int choice = JOptionPane.showOptionDialog(null, message, title,
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        if (choice == 3 || choice == JOptionPane.CLOSED_OPTION) {
            return;
        }

        switch (choice) {
            case 0:
                addTask();
                break;
            case 1:
                editTask();
                break;
            case 2:
                deleteTask();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid action.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void addTask() { // somewhat done
        String title = "Add Task";
        String prompt = "Enter Task Name:";

        String taskName = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
        if (taskName != null && !taskName.isEmpty()) {
            String taskFilePath = currentEventName.toLowerCase() + "_programme.csv";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFilePath, true))) {
                writer.write("Task," + taskName + ",Incomplete");
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Task added successfully!\nStatus: Incomplete", title,
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error writing task information to file!", "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Task name cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
        }

        csvToArrayList();
    }

    private static void editTask() { // done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
            List<String> lines = new ArrayList<>();
            String task = "";
            String status = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV File: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();

            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");

                if (values[0].trim().equalsIgnoreCase("Task")) {

                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit: ", "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            String[] values = lines.get(selectedIndex).split(",");
            task = values[1].trim();
            String[] choose = { "Incomplete", "Complete" };
            int choice = JOptionPane.showOptionDialog(null, "Choose an Option", "Add, Edit, or Delete",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null, choose, choose[0]);

            switch (choice) {
                case 0:
                    status = "Incomplete";
                    break;
                case 1:
                    status = "Complete";
                    break;
                default:
                    return;
            }

            String newData = "Task," + task + "," + status;
            lines.set(selectedIndex, newData);

            if (selectedIndex != -1) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null, "Entry edited successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV File: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No task found!");
            return;
        }

        csvToArrayList();
    }

    private static void deleteTask() { // done
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
            List<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            List<String> optionsList = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[0].trim().equalsIgnoreCase("Task")) {
                    if (values.length >= 3 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        optionsList.add(values[0].trim() + "," + values[1].trim() + "," + values[2].trim());
                    }
                }
            }

            String[] options = optionsList.toArray(new String[0]);

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to delete:",
                    "Delete ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            String correctWord = selectedEntry;

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                System.out.println("Comparing '" + correctWord + "' with '" + lines.get(i) + "'");
                if (correctWord.equals(lines.get(i))) {
                    selectedIndex = i;
                    System.out.println("Match found at index " + i);
                    break;
                } else {
                    System.out.println("No match at index " + i);
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                lines.remove(selectedIndex);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null,
                            "Entry deleted successfully!");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid selection!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No task found!");
        }

        csvToArrayList();
    }

    public void loadEventPopup() {
        String[] options = { "Add New Event", "Load Existing Event" };
        int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Event Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            addNewEvent();
        } else if (choice == 1) {
            loadExistingEvent();
        }
    }

    private void loadExistingEvent() {
        loadEventNames();
        if (eventNames.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No events found!", "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] currentEventNames = eventNames.toArray(new String[0]);
        String selectedEvent = (String) JOptionPane.showInputDialog(null, "Which event?", "Load Event",
                JOptionPane.QUESTION_MESSAGE, null, currentEventNames, currentEventNames[0]);

        if (selectedEvent != null) {
            currentEventName = selectedEvent;
            System.out.println(currentEventName);
            csvToArrayList();
            countAttendee();
            JOptionPane.showMessageDialog(null, "Event loaded: " + currentEventName);
        }
    }

    public void loadEventNames() {
        eventNames.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("events.csv"))) {
            String line;
            reader.readLine(); // Skip the header line
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                eventNames.add(values[0].replace("\"", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading events.csv file", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void addNewEvent() {
        while (true) {
            currentEventName = JOptionPane.showInputDialog(null, "Event Name: "); // Get Event Name
            if (currentEventName != null && !currentEventName.equals("")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Event name cannot be null!", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Event Date (YYYY-MM-DD): "); // Get Event Date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate eventDate = LocalDate.parse(input, formatter);
                currentEventDate = eventDate.format(formatter);
                break;
            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Please input a valid date", "Format error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        while (true) {
            try {
                String budgetInput = JOptionPane.showInputDialog(null, "Budget Allocated: "); // Get Budget
                eventBudgetAllocated = Float.parseFloat(budgetInput);

                if (eventBudgetAllocated <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter an amount greater than zero.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please input a valid amount", "Format error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        int maxAttendees = 0;

        while (true) {
            try {
                String maxAttendeesInput = JOptionPane.showInputDialog(null, "Maximum number of attendees: ");
                maxAttendees = Integer.parseInt(maxAttendeesInput);
                if (maxAttendees <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a number greater than zero.", "Error",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please input a valid number", "Format error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }

        // Create CSV files for the new event and store event details
        createCSVFiles(currentEventName, currentEventDate, eventBudgetAllocated, maxAttendees);

        JOptionPane.showMessageDialog(null,
                "Event added:\nName: " + currentEventName + "\nDate: " + currentEventDate + "\nBudget: "
                        + eventBudgetAllocated + "\nMaximum Attendees: " + maxAttendees);

        currentEventName = "";
    }

    public static void createCSVFiles(String currentEventName, String currentEventDate, float budgetAllocated,
            int maxAttendees) {
        // Create finances CSV file with header
        String financesCSVFilePath = currentEventName.toLowerCase() + "_finances.csv";
        try (BufferedWriter financesWriter = new BufferedWriter(new FileWriter(financesCSVFilePath))) {
            financesWriter.write("Outflows and Inflows");
            financesWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating finances CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create programme CSV file with header
        String programmeCSVFilePath = currentEventName.toLowerCase() + "_programme.csv";
        try (BufferedWriter programmeWriter = new BufferedWriter(new FileWriter(programmeCSVFilePath))) {
            programmeWriter.write("Programs and Tasks");
            programmeWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating programme CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create people CSV file with header
        String staffCSVFilePath = currentEventName.toLowerCase() + "_people.csv";
        try (BufferedWriter staffWriter = new BufferedWriter(new FileWriter(staffCSVFilePath))) {
            staffWriter.write("Attendees and Staffs");
            staffWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating staff CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Append event details to events.csv
        String eventsCSVFilePath = "events.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsCSVFilePath, true))) {
            writer.write(currentEventName + "," + currentEventDate + "," + budgetAllocated + "," + maxAttendees);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to events CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void csvToArrayList() {
        // Clear ArrayList first
        arrayClear();

        // Initialize file paths
        String outflowNameData = currentEventName.toLowerCase() + "_finances.csv";
        String inflowNameData = currentEventName.toLowerCase() + "_finances.csv";
        String outflowPriceData = currentEventName.toLowerCase() + "_finances.csv";
        String inflowPriceData = currentEventName.toLowerCase() + "_finances.csv";
        String eventDateData = "events.csv";
        String programNameData = currentEventName.toLowerCase() + "_programme.csv";
        String programTimeData = currentEventName.toLowerCase() + "_programme.csv";
        String programDateData = currentEventName.toLowerCase() + "_programme.csv";

        // Outflow Names
        try (BufferedReader br = new BufferedReader(new FileReader(outflowNameData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Outflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        outflowNames.add(values[1]);
                    }
                }
            }
            System.out.println("Outflow Names updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Outflow Prices
        try (BufferedReader br = new BufferedReader(new FileReader(outflowPriceData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Outflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        outflowPrices.add(Float.parseFloat(values[2]));
                    }
                }
            }
            System.out.println("Outflow Prices updated.");
            calculateTotalOutflows();
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Inflow Names
        try (BufferedReader br = new BufferedReader(new FileReader(inflowNameData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Inflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        inflowNames.add(values[1]);
                    }
                }
            }
            System.out.println("Inflow Names updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Inflow Prices
        try (BufferedReader br = new BufferedReader(new FileReader(inflowPriceData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Inflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        inflowPrices.add(Float.parseFloat(values[2]));
                    }
                }
            }
            System.out.println("Inflow Prices updated.");
            calculateTotalInflows();
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Event Date
        try (BufferedReader br = new BufferedReader(new FileReader(eventDateData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                        && !values[2].trim().isEmpty() && !values[2].trim().isEmpty()) {
                    eventDates.add(values[1]);
                }
            }
            System.out.println("Event Date updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Program Names
        try (BufferedReader br = new BufferedReader(new FileReader(programNameData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        programNames.add(values[1]);
                    }
                }
            }
            System.out.println("Program Names updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Program Date
        try (BufferedReader br = new BufferedReader(new FileReader(programDateData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        programDate.add(values[2]);
                    }
                }
            }
            System.out.println("Program Dates updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Program Time
        try (BufferedReader br = new BufferedReader(new FileReader(programTimeData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        programTime.add(values[3]);
                    }
                }
            }
            System.out.println("Program Times updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void arrayClear() {
        eventDates.clear();
        programNames.clear();
        programDate.clear();
        programTime.clear();
        outflowNames.clear();
        outflowPrices.clear();
        inflowNames.clear();
        inflowPrices.clear();
    }

    public static void calculateTotalInflows() {
        totalInflows = 0;
        String csvFilePath = "events.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {

                String[] details = line.split(",");
                if (details.length > 0) {
                    String type = details[0].trim();
                    if (currentEventName.equalsIgnoreCase(type)) {
                        if (details.length > 2 && details[2] != null && !details[2].isEmpty()) {
                            totalInflows += Float.parseFloat(details[2]);
                            System.out.println("Budget Alloted Inflow: " + totalInflows);
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading finances from CSV file: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            // Handle cases where the value cannot be parsed as a float
            System.err.println("Error parsing inflow value: " + e.getMessage());
            e.printStackTrace();
        }

        if (inflowPrices.isEmpty()) {
            System.out.println("Inflows Empty");
            return;
        }

        for (float price : inflowPrices) {
            totalInflows += price;
            System.out.println("Current Inflow: " + totalInflows);
        }
    }

    public static void calculateTotalOutflows() {
        if (outflowPrices.isEmpty()) {
            System.out.println("Ouflow Empty");
            totalOutflows = 0;
            return;
        }

        for (float price : outflowPrices) {
            totalOutflows += price;
            System.out.println("Current Outflows: " + totalOutflows);
        }
    }

    // Error fixing ni na function na handy, can get rid after the projectiftaposna
    public static void printArrays() {
        for (String name : outflowNames) {
            int i = 1;
            System.out.println("Ouflow Name " + i + ": " + name);
            i++;
        }

        for (float name : outflowPrices) {
            int i = 1;
            System.out.println("Outflow Price " + i + ": " + name);
            i++;
        }

        for (String name : inflowNames) {
            int i = 1;
            System.out.println("Inflow Name " + i + ": " + name);
            i++;
        }

        for (float name : inflowPrices) {
            int i = 1;
            System.out.println("Inflow Price " + i + ": " + name);
            i++;
        }

        for (String name : programNames) {
            int i = 1;
            System.out.println("Program Name " + i + ": " + name);
            i++;
        }

        for (String name : programDate) {
            int i = 1;
            System.out.println("Program Date " + i + ": " + name);
            i++;
        }

        for (String name : programTime) {
            int i = 1;
            System.out.println("Program Time " + i + ": " + name);
            i++;
        }

        for (String name : eventDates) {
            int i = 1;
            System.out.println("Event Dates " + i + ": " + name);
            i++;
        }

        System.out.println("Size of the Outflow Names: " + outflowNames.size());
        System.out.println("Size of the Outflow Price: " + outflowPrices.size());
        System.out.println("Size of the Inflow Names: " + inflowNames.size());
        System.out.println("Size of the Inflow Price: " + inflowPrices.size());
        System.out.println("Size of the Program Names: " + programNames.size());
        System.out.println("Size of the Program Date: " + programDate.size());
        System.out.println("Size of the Program Time: " + programTime.size());
        System.out.println("Size of the Event Date: " + eventDates.size());
    }

    public static void countAttendee() {
        String attendeeData = currentEventName.toLowerCase() + "_people.csv";

        attendeeCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(attendeeData))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0 && values[0].trim().equalsIgnoreCase("Attendee")) {
                    attendeeCount++;
                }
            }
            System.out.println("Attendee count updated: " + attendeeCount);
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new eventPlanner();
    }
}