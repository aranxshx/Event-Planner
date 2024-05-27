import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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

    public static List<String> eventNames = new ArrayList<>();
    public static List<String> eventDates = new ArrayList<>();
    public static List<String> programNames = new ArrayList<>();
    public static List<String> programTimes = new ArrayList<>();
    public static List<String> outflowNames = new ArrayList<>();
    public static List<Float> outflowPrices = new ArrayList<>();
    public static List<String> inflowNames = new ArrayList<>();
    public static List<Float> inflowPrices = new ArrayList<>();

    // Revenue: String-float
    Map<String, Float> revenueMap = new HashMap<>();

    // Income: String-float
    Map<String, Float> incomeMap = new HashMap<>();

    // Resource: String-dictionary (another Map)
    Map<String, Map<String, Object>> resourceMap = new HashMap<>();

    // Staff: String-String
    Map<String, String> staffMap = new HashMap<>();

    // Attendee: String-String
    Map<String, String> attendeeMap = new HashMap<>();

    // Task: String-boolean
    Map<String, Boolean> taskMap = new HashMap<>();

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
                resourceInputPopup(resourceMap);
            }
        });
        addStaffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffInputPopup();
            }
        });
        addAttendeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                attendeeInputPopup(attendeeMap);
            }
        });
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskInputPopup(taskMap);
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

                break;
            case 2: // Delete Program
                break;
            default:
                // Cancel or close dialog, do nothing
                break;
        }
    }

    private void addProgram(String title) {
        String prompt = "Enter Program Name:";
        String programName = JOptionPane.showInputDialog(null, prompt, "Add Program - " + title,
                JOptionPane.PLAIN_MESSAGE);
        if (programName != null && !programName.isEmpty()) {
            try {
                String dateTimePrompt = "Enter Date and Time (yyyy-MM-dd HH:mm):";
                String dateTimeString = JOptionPane.showInputDialog(null, dateTimePrompt, "Add Program - " + title,
                        JOptionPane.PLAIN_MESSAGE);
                if (dateTimeString != null && !dateTimeString.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

                    // Save to CSV file
                    String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
                        writer.append("Program," + programName + "," + dateTimeString);
                        writer.newLine();
                    } catch (IOException e) {
                        System.err.println("Error writing to CSV file: " + e.getMessage());
                        e.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(null, "Program added successfully!", "Add Program - " + title,
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", "Add Program - " + title,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editProgram(Map<String, Long> targetMap, String title) {
        String programName = JOptionPane.showInputDialog(null, "Enter the Program Name to edit:",
                "Edit Program - " + title, JOptionPane.PLAIN_MESSAGE);
        if (programName != null && !programName.isEmpty() && targetMap.containsKey(programName)) {
            try {
                String dateTimePrompt = "Enter new Date and Time (yyyy-MM-dd HH:mm):";
                String dateTimeString = JOptionPane.showInputDialog(null, dateTimePrompt, "Edit Program - " + title,
                        JOptionPane.PLAIN_MESSAGE);
                if (dateTimeString != null && !dateTimeString.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                    long epochMillis = dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
                    targetMap.put(programName, epochMillis);
                    JOptionPane.showMessageDialog(null, "Program edited successfully!", "Edit Program - " + title,
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", "Edit Program - " + title,
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Program not found.", "Edit Program - " + title,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteProgram(Map<String, Long> targetMap, String title) {
        String programName = JOptionPane.showInputDialog(null, "Enter the Program Name to delete:",
                "Delete Program - " + title, JOptionPane.PLAIN_MESSAGE);
        if (programName != null && !programName.isEmpty() && targetMap.containsKey(programName)) {
            targetMap.remove(programName);
            JOptionPane.showMessageDialog(null, "Program deleted successfully!", "Delete Program - " + title,
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Program not found.", "Delete Program - " + title,
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void resourceInputPopup(Map<String, Map<String, Object>> targetMap) {
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
    }

    public static void attendeeInputPopup(Map<String, String> attendeeMap) {
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
    }

    public static void taskInputPopup(Map<String, Boolean> taskMap) {
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
            return; // If Cancel or close option is selected, return
        }

        switch (choice) {
            case 0:
                addTask();
                break;
            case 1:
                editTask();
                break;
            case 2:
                deleteTask(taskMap);
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
    }

    private static void editTask() {
        try {
            String csvFilePath = currentEventName.toLowerCase() + "_programme.csv";
            List<String> lines = new ArrayList<>();
            String taskName = "";
            String status = "";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                System.err.println("Error reading from CSV file: " + e.getMessage());
                e.printStackTrace();
            }

            System.out.println("Contents of lines:");
            for (String line : lines) {
                System.out.println(line);
            }

            String[] options = new String[lines.size()];
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                options[i] = values[1].trim(); // Assuming the task name is at index 1
            }

            System.out.println("Options:");
            for (String option : options) {
                System.out.println(option);
            }

            String selectedEntry = (String) JOptionPane.showInputDialog(null, "Select the entry to Edit:",
                    "Edit ",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            System.out.println("Selected entry: " + selectedEntry);

            int selectedIndex = -1;
            for (int i = 0; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values[1].trim().equalsIgnoreCase(selectedEntry)) {
                    selectedIndex = i;
                    break;
                }
            }
            System.out.println("Final selectedIndex: " + selectedIndex);

            if (selectedIndex != -1) {
                String[] values = lines.get(selectedIndex).split(",");
                taskName = values[1]; // Assuming the task name is at index 1
                status = JOptionPane.showInputDialog(null, "Enter status (Complete/Incomplete):");
                String newData = "Task," + taskName + "," + status;

                lines.set(selectedIndex, newData);

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                    JOptionPane.showMessageDialog(null, "Entry edited successfully!");
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
    }

    private static void deleteTask(Map<String, Boolean> taskMap) {
        String title = "Delete Task";
        String taskName = JOptionPane.showInputDialog(null, "Enter Task Name to Delete:", title,
                JOptionPane.PLAIN_MESSAGE);

        if (taskName == null || taskName.isEmpty() || !taskMap.containsKey(taskName)) {
            JOptionPane.showMessageDialog(null, "Task not found or name cannot be empty.", title,
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        taskMap.remove(taskName);
        JOptionPane.showMessageDialog(null, "Task deleted successfully!", title, JOptionPane.INFORMATION_MESSAGE);
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
            titleLabel.setText(currentEventName);
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
            int maxAttendees) { // done
        String financesCSVFilePath = currentEventName.toLowerCase() + "_finances.csv";
        try {
            BufferedWriter financesWriter = new BufferedWriter(new FileWriter(financesCSVFilePath));
            financesWriter.close();
        } catch (IOException e) {
            System.err.println("Error creating finances CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create programme CSV file
        String programmeCSVFilePath = currentEventName.toLowerCase() + "_programme.csv";
        try {
            BufferedWriter programmeWriter = new BufferedWriter(new FileWriter(programmeCSVFilePath));
            programmeWriter.close();
        } catch (IOException e) {
            System.err.println("Error creating programme CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create staff CSV file
        String staffCSVFilePath = currentEventName.toLowerCase() + "_people.csv";
        try {
            BufferedWriter staffWriter = new BufferedWriter(new FileWriter(staffCSVFilePath));
            staffWriter.close();
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

    public static void main(String[] args) {
        new eventPlanner();
    }
}