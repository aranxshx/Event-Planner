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

    // Program: String-time (assuming time is represented as long milliseconds)
    Map<String, Long> programMap = new HashMap<>();

    // Resource: String-dictionary (another Map)
    Map<String, Map<String, Object>> resourceMap = new HashMap<>();

    // Staff: String-String
    Map<String, String> staffMap = new HashMap<>();

    // Attendee: String-String
    Map<String, String> attendeeMap = new HashMap<>();

    // Task: String-boolean
    Map<String, Boolean> taskMap = new HashMap<>();

    JButton financesToggle, peopleToggle, programsToggle;
    Color blueColor = new Color(97, 113, 255);
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
    Dashboard DASHBOARD = new Dashboard();

    // - > Icons
    ImageIcon financesIcon = new ImageIcon(getClass().getResource("Finances.png"));
    ImageIcon addProgramIcon = new ImageIcon(getClass().getResource("Add Program.png"));
    ImageIcon addResourceIcon = new ImageIcon(getClass().getResource("Add Resource.png"));
    ImageIcon addStaffIcon = new ImageIcon(getClass().getResource("Add Staff.png"));
    ImageIcon addAttendeeIcon = new ImageIcon(getClass().getResource("Add Attendee.png"));
    ImageIcon addTaskIcon = new ImageIcon(getClass().getResource("Add Task.png"));

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
        add(DASHBOARD);
        DASHBOARD.setLayout(null);
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
                financeInputPopup(revenueMap); // Assuming you want to add to revenueMap initially
            }
        });
        addProgramButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programInputPopup(programMap);
            }
        });
        addResourceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resourceInputPopup(resourceMap);
            }
        });
        addStaffButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffInputPopup(staffMap);
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

        financesToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(FINANCES);
                addToggle();
                addButtons();
            }
        });

        programsToggle = new JButton("RESOURCES");

        programsToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(RESOURCES);
                addToggle();
                addButtons();
            }
        });

        peopleToggle = new JButton("PEOPLE");
        peopleToggle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    // Instead of a popup, make a proper customized JPanel for the inputs

    public void financeInputPopup(Map<String, Float> targetMap) {
        String title = targetMap == revenueMap ? "Add Revenue" : "Add Income";
        String prompt = targetMap == revenueMap ? "Enter Revenue Details:" : "Enter Income Details:";

        // Create a dialog with input fields and buttons
        Object[] message = { prompt };
        String inputString = JOptionPane.showInputDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
        if (inputString != null && !inputString.isEmpty()) {
            try {
                float inputValue = Float.parseFloat(
                        JOptionPane.showInputDialog(null, "Enter Value:", title, JOptionPane.PLAIN_MESSAGE));
                targetMap.put(inputString, inputValue);
                JOptionPane.showMessageDialog(null, "Data added successfully!", title, JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid number format entered.", title, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void programInputPopup(Map<String, Long> targetMap) {
        String title = "Add Program";
        String prompt = "Enter Program Name:";

        // Create a dialog with input fields and buttons
        String programName = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
        if (programName != null && !programName.isEmpty()) {
            try {
                String dateTimePrompt = "Enter Date and Time (yyyy-MM-dd HH:mm):";
                String dateTimeString = JOptionPane.showInputDialog(null, dateTimePrompt, title,
                        JOptionPane.PLAIN_MESSAGE);
                if (dateTimeString != null && !dateTimeString.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                    long epochMillis = dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
                    targetMap.put(programName, epochMillis);
                    JOptionPane.showMessageDialog(null, "Program added successfully!", title,
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", title,
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void resourceInputPopup(Map<String, Map<String, Object>> targetMap) {
        String title = "Add Resource";
        String prompt = "Enter Resource Name:";

        // Prompt for resource name
        String resourceName = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
        if (resourceName != null && !resourceName.isEmpty()) {
            // Prompt for resource source
            String resourceSource = JOptionPane.showInputDialog(null, "Enter Resource Source:", title,
                    JOptionPane.PLAIN_MESSAGE);
            if (resourceSource != null && !resourceSource.isEmpty()) {
                try {
                    // Prompt for date and time
                    String dateTimePrompt = "Enter Date and Time (yyyy-MM-dd HH:mm):";
                    String dateTimeString = JOptionPane.showInputDialog(null, dateTimePrompt, title,
                            JOptionPane.PLAIN_MESSAGE);
                    if (dateTimeString != null && !dateTimeString.isEmpty()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
                        long epochMillis = dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();

                        // Store the resource details in a nested map
                        Map<String, Object> resourceDetails = new HashMap<>();
                        resourceDetails.put("source", resourceSource);
                        resourceDetails.put("dateTime", epochMillis);

                        targetMap.put(resourceName, resourceDetails);
                        JOptionPane.showMessageDialog(null, "Resource added successfully!", title,
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date-time format entered.", title,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public void staffInputPopup(Map<String, String> targetMap) {
        String title = "Add Staff";
        String namePrompt = "Enter Staff Name:";
        String rolePrompt = "Enter Staff Role:";

        // Prompt for staff name
        String staffName = JOptionPane.showInputDialog(null, namePrompt, title, JOptionPane.PLAIN_MESSAGE);
        if (staffName != null && !staffName.isEmpty()) {
            // Prompt for staff role
            String staffRole = JOptionPane.showInputDialog(null, rolePrompt, title, JOptionPane.PLAIN_MESSAGE);
            if (staffRole != null && !staffRole.isEmpty()) {
                targetMap.put(staffName, staffRole);
                JOptionPane.showMessageDialog(null, "Staff added successfully!", title,
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Staff role cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Staff name cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void attendeeInputPopup(Map<String, String> attendeeMap) {
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

        attendeeMap.put("Name", name);
        attendeeMap.put("Email", email);
        attendeeMap.put("SeatCode", seatCode);

        String summary = "Attendee Details:\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Seat Code: " + seatCode;

        JOptionPane.showMessageDialog(null, summary, "Attendee Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void taskInputPopup(Map<String, Boolean> taskMap) {
        String title = "Add Task";
        String prompt = "Enter Task Name:";

        String taskName = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
        if (taskName != null && !taskName.isEmpty()) {
            taskMap.put(taskName, false);
            JOptionPane.showMessageDialog(null, "Task added successfully!\nStatus: Incomplete", title,
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Task name cannot be empty.", title, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadEventPopup() {
        String[] options = { "Add New Event", "Load Existing Event" };
        int choice = JOptionPane.showOptionDialog(null, "Choose an option:", "Event Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            JOptionPane.showMessageDialog(null, "Add New Event selected");
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
            // update
            revalidate();
            repaint();
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