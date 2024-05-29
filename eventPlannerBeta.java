import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.AlphaComposite.*;

public class eventPlannerBeta extends JFrame {
    private Point initialClick;

    // COLORS
    public final Color BACKGROUND_COLOR = new Color(44, 62, 80);
    public final Color NAVIGATION_PANEL_COLOR = new Color(36, 65, 79);
    public final Color PRIMARY_TEXT_COLOR = new Color(236, 240, 241);
    public final Color SECONDARY_TEXT_COLOR = new Color(149, 165, 166);
    public final Color BORDER_COLOR = new Color(76, 128, 128);
    public final Color HIGHLIGHT_COLOR = new Color(255, 132, 129);
    public final Color ERROR_COLOR = new Color(231, 76, 60);

    // FONTS
    Font eventNameFont = FontLoader.getFont("Inter-ExtraBold", 31);
    Font dateFont = FontLoader.getFont("Inter-Medium", 18);
    Font iconButtonFont = FontLoader.getFont("Inter-Regular", 24);
    Font titleFont = FontLoader.getFont("Inter-ExtraBold", 33);
    Font daysNumFont = FontLoader.getFont("Inter-ExtraBold", 69);
    Font daysTextFont = FontLoader.getFont("Inter-Medium", 34);
    Font bTableFont = FontLoader.getFont("Inter-ExtraBold", 29);
    Font sTableFont = FontLoader.getFont("Inter-ExtraBold", 24);
    Font lTableFont = FontLoader.getFont("Inter-ExtraBold", 27);
    Font headerFont = FontLoader.getFont("Inter-Bold", 19);
    Font tableContentFont = FontLoader.getFont("Inter-Regular", 15);

    Font beforeEventFont = FontLoader.getFont("Inter-ExtraBold", 18);
    Font remainingBudgetFont = FontLoader.getFont("Inter-ExtraBold", 22);

    // CONSOLIDATION DATA
    public static String currentEventName = "";
    public static String currentEventDate = "";
    public static float eventBudgetAllocated = 0;
    public static int totalInflows = 0;
    public static int totalOutflows = 0;
    public static int attendeeCount = 0;
    public static int maxAttendees = 0;
    public static String date = "";
    public int day = 0;
    public int month = 0;
    public int year = 0;
    public long daysLeft;

    // DATA VARIABLES
    public static List<String> eventNames = new ArrayList<>();
    public static List<String> eventDates = new ArrayList<>();
    public static List<String> programNames = new ArrayList<>();
    public static List<String> programDate = new ArrayList<>();
    public static List<String> programTime = new ArrayList<>();
    public static List<String> outflowNames = new ArrayList<>();
    public static List<Float> outflowPrices = new ArrayList<>();
    public static List<String> inflowNames = new ArrayList<>();
    public static List<Float> inflowPrices = new ArrayList<>();

    private Object[][] revenueData = new Object[100][4];
    private Object[][] expensesData = new Object[100][4];
    private Object[][] resourcesData = new Object[100][4];
    private Object[][] staffData = new Object[100][4];
    private Object[][] attendeeData = new Object[100][4];
    private Object[][] taskData = new Object[100][4];
    private Object[][] programData = new Object[100][3];

    // ELEMENTS
    // Background
    JPanel background = new JPanel();
    ImageIcon backgroundImage = new ImageIcon("./assets/background.png");
    JLabel backgroundLabel = new JLabel(backgroundImage);
    JLabel dashboardLabel = new JLabel("Overview");

    // Icons
    ImageIcon dashboardImage = new ImageIcon("./assets/Icons/Dashboard.png");
    ImageIcon financesImage = new ImageIcon("./assets/Icons/Finances.png");
    ImageIcon peopleImage = new ImageIcon("./assets/Icons/People.png");
    ImageIcon programImage = new ImageIcon("./assets/Icons/Program.png");
    ImageIcon resourcesImage = new ImageIcon("./assets/Icons/Resources.png");
    ImageIcon switchLeftImage = new ImageIcon("./assets/Icons/Switch Left.png");
    ImageIcon switchRightImage = new ImageIcon("./assets/Icons/Switch Right.png");

    // Navigation Panel
    JPanel navigationPanel = new JPanel();
    JButton titleButton = new JButton();
    JLabel dateLabel = new JLabel("May 31, 2024");
    JLabel attendeesLabel = new JLabel("Attendees: 5 / 10,000");

    JButton dashboardButton = new JButton();
    JButton financesButton = new JButton();
    JButton resourcesButton = new JButton();
    JButton peopleButton = new JButton();
    JButton programButton = new JButton();

    // Finances
    RoundedRectangle financesPanel = new RoundedRectangle(20);
    JLabel financesLabel = new JLabel("Finances");
    String[] revenueColumns = { "Name", "Price" };

    CustomTableModel revenueModel = new CustomTableModel(revenueColumns, revenueData);
    JTable revenueTable = new JTable(revenueModel);
    JScrollPane revenueScroll = new JScrollPane(revenueTable);

    // Days
    RoundedRectangle daysPanel = new RoundedRectangle(20);
    JLabel daysLeftNumLabel = new JLabel("50");
    JLabel daysLeftTextLabel1 = new JLabel("days");
    JLabel daysLeftTextLabel2 = new JLabel("before event");

    // Remaining
    RoundedRectangle remainingPanel = new RoundedRectangle(20);
    JLabel remainingBudgetLabel = new JLabel("Remaining budget");

    // Resources
    RoundedRectangle resourcesPanel = new RoundedRectangle(20);
    JLabel resourcesLabel = new JLabel("Resources");
    JLabel resourceNameLabel = new JLabel("Name");
    JLabel resourceSourceLabel = new JLabel("Source");
    JLabel resourceDateLabel = new JLabel("Date");
    JLabel resourceStatusLabel = new JLabel("Status");

    RoundedRectangle resourceSeparator1 = new RoundedRectangle(10);
    RoundedRectangle resourceSeparator2 = new RoundedRectangle(10);
    RoundedRectangle resourceSeparator3 = new RoundedRectangle(10);
    RoundedRectangle resourceSeparator4 = new RoundedRectangle(10);

    // People
    RoundedRectangle peoplePanel = new RoundedRectangle(20);
    JLabel staffLabel = new JLabel("Staff");
    JLabel attendeesTLabel = new JLabel("Attendees");
    String[] attendeesColumn = { "", "" };
    CustomTableModel attendeesModel = new CustomTableModel(attendeesColumn, attendeeData);
    JTable attendeesTable = new JTable(attendeesModel);
    JScrollPane attendeesScrollPane = new JScrollPane(attendeesTable);

    // Staff
    String[] staffColumn = { "", "" };
    CustomTableModel staffModel = new CustomTableModel(staffColumn, staffData);
    JTable staffTable = new JTable(staffModel);
    JScrollPane staffScrollPane = new JScrollPane(staffTable);

    // Resource
    String[] resourcesColumn = { "", "" };
    CustomTableModel resourcesModel = new CustomTableModel(resourcesColumn, resourcesData);
    JTable resourcesTable = new JTable(resourcesModel);
    JScrollPane resourcesScrollPane = new JScrollPane(resourcesTable);

    // Inflow\

    // Task

    // Program

    JLabel attendeeNameLabel = new JLabel("Name", JLabel.CENTER);
    JLabel attendeeEmailLabel = new JLabel("Role", JLabel.CENTER);

    JButton switchLeftButton = new JButton();
    JButton switchRightButton = new JButton();

    RoundedRectangle peopleSeparator = new RoundedRectangle(10);

    // Tasks
    RoundedRectangle tasksPanel = new RoundedRectangle(50);
    JLabel checklistLabel = new JLabel("Checklist");
    JLabel programLabel = new JLabel("Program");
    RoundedRectangle checklistSeparator = new RoundedRectangle(10);

    // Exit Button
    JButton exitButton = new JButton("Exit");

    public eventPlannerBeta() {
        setTitle("Eve");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1440, 1024);
        setLayout(null);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, getWidth(), getHeight());
        add(panel);

        // Exit Button
        exitButton.setBounds(49, 906, 153, 23);
        exitButton.setForeground(Color.RED);
        exitButton.setBackground(NAVIGATION_PANEL_COLOR);
        exitButton.setBorder(null);
        exitButton.setFont(tableContentFont);
        exitButton.setOpaque(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.add(exitButton);

        // Remaining Budget
        remainingBudgetLabel.setBounds(877, 336, 176, 25);
        remainingBudgetLabel.setForeground(PRIMARY_TEXT_COLOR);
        remainingBudgetLabel.setFont(beforeEventFont);
        panel.add(remainingBudgetLabel);

        remainingPanel.setBounds(855, 305, 250, 175);
        remainingPanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(remainingPanel);

        // Finances
        financesLabel.setBounds(441, 112, 232, 142);
        financesLabel.setForeground(PRIMARY_TEXT_COLOR);
        financesLabel.setFont(bTableFont);
        panel.add(financesLabel);

        financesPanel.setBounds(412, 120, 415, 360);
        financesPanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(financesPanel);

        // Days Before
        daysLeftTextLabel1.setBounds(988, 146, 100, 100);
        daysLeftTextLabel1.setForeground(PRIMARY_TEXT_COLOR);
        daysLeftTextLabel1.setFont(daysTextFont);
        panel.add(daysLeftTextLabel1);

        daysLeftTextLabel2.setBounds(878, 229, 159, 40);
        daysLeftTextLabel2.setForeground(PRIMARY_TEXT_COLOR);
        daysLeftTextLabel2.setFont(beforeEventFont);
        panel.add(daysLeftTextLabel2);

        daysLeftNumLabel.setBounds(878, 146, 205, 72);
        daysLeftNumLabel.setForeground(PRIMARY_TEXT_COLOR);
        daysLeftNumLabel.setFont(daysNumFont);
        panel.add(daysLeftNumLabel);

        daysPanel.setBounds(856, 122, 249, 156);
        daysPanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(daysPanel);

        // Tasks Panel
        checklistLabel.setBounds(1178, 77, 151, 50);
        checklistLabel.setForeground(PRIMARY_TEXT_COLOR);
        checklistLabel.setFont(lTableFont);
        panel.add(checklistLabel);

        programLabel.setBounds(1178, 357, 151, 50);
        programLabel.setForeground(PRIMARY_TEXT_COLOR);
        programLabel.setFont(lTableFont);
        panel.add(programLabel);

        checklistSeparator.setBounds(1157, 334, 241, 3);
        checklistSeparator.setColor(BORDER_COLOR);
        panel.add(checklistSeparator);

        tasksPanel.setBounds(1141, 39, 271, 940);
        tasksPanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(tasksPanel);

        // Resources Panel
        resourceNameLabel.setBounds(436, 544, 106, 50);
        resourceNameLabel.setForeground(PRIMARY_TEXT_COLOR);
        resourceNameLabel.setFont(headerFont);
        panel.add(resourceNameLabel);

        resourceSourceLabel.setBounds(718, 544, 106, 50);
        resourceSourceLabel.setForeground(PRIMARY_TEXT_COLOR);
        resourceSourceLabel.setFont(headerFont);
        panel.add(resourceSourceLabel);

        resourceDateLabel.setBounds(885, 544, 106, 50);
        resourceDateLabel.setForeground(PRIMARY_TEXT_COLOR);
        resourceDateLabel.setFont(headerFont);
        panel.add(resourceDateLabel);

        resourceStatusLabel.setBounds(1011, 544, 106, 50);
        resourceStatusLabel.setForeground(PRIMARY_TEXT_COLOR);
        resourceStatusLabel.setFont(headerFont);
        panel.add(resourceStatusLabel);

        resourcesLabel.setBounds(436, 514, 170, 50);
        resourcesLabel.setForeground(PRIMARY_TEXT_COLOR);
        resourcesLabel.setFont(sTableFont);
        panel.add(resourcesLabel);

        resourceSeparator1.setBounds(646, 546, 3, 154);
        resourceSeparator1.setColor(BORDER_COLOR);
        panel.add(resourceSeparator1);

        resourceSeparator1.setBounds(646, 546, 3, 154);
        resourceSeparator1.setColor(BORDER_COLOR);
        panel.add(resourceSeparator1);

        resourceSeparator2.setBounds(845, 546, 3, 154);
        resourceSeparator2.setColor(BORDER_COLOR);
        panel.add(resourceSeparator2);

        resourceSeparator3.setBounds(982, 546, 3, 154);
        resourceSeparator3.setColor(BORDER_COLOR);
        panel.add(resourceSeparator3);

        resourcesPanel.setBounds(410, 499, 695, 218);
        resourcesPanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(resourcesPanel);

        // People Panel
        attendeesTable.getColumnModel().getColumn(0).setPreferredWidth(203);
        attendeesTable.getColumnModel().getColumn(1).setPreferredWidth(441);
        attendeesTable.setOpaque(false);
        attendeesTable.setForeground(PRIMARY_TEXT_COLOR);
        attendeesTable.setFont(tableContentFont);
        attendeesTable.setShowGrid(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setOpaque(false);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        attendeesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        attendeesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        attendeesScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        attendeesScrollPane.setBorder(null);
        attendeesScrollPane.setBounds(434, 830, 643, 131);
        attendeesScrollPane.setOpaque(false);
        attendeesScrollPane.getViewport().setOpaque(false);
        attendeesScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        attendeesScrollPane.setBorder(null);
        panel.add(attendeesScrollPane);

        // Staff
        staffTable.getColumnModel().getColumn(0).setPreferredWidth(203);
        staffTable.getColumnModel().getColumn(1).setPreferredWidth(441);
        staffTable.setOpaque(false);
        staffTable.setForeground(PRIMARY_TEXT_COLOR);
        staffTable.setFont(tableContentFont);
        staffTable.setShowGrid(false);

        staffTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        staffTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        staffScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        staffScrollPane.setBorder(null);
        staffScrollPane.setBounds(100, 830, 643, 131);
        staffScrollPane.setOpaque(false);
        staffScrollPane.getViewport().setOpaque(false);
        staffScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        staffScrollPane.setBorder(null);
        panel.add(staffScrollPane);

        // Resources
        resourcesTable.getColumnModel().getColumn(0).setPreferredWidth(203);
        resourcesTable.getColumnModel().getColumn(1).setPreferredWidth(441);
        resourcesTable.setOpaque(false);
        resourcesTable.setForeground(PRIMARY_TEXT_COLOR);
        resourcesTable.setFont(tableContentFont);
        resourcesTable.setShowGrid(false);

        resourcesTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        resourcesTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        resourcesScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        resourcesScrollPane.setBorder(null);
        resourcesScrollPane.setBounds(100, 830, 643, 131);
        resourcesScrollPane.setOpaque(false);
        resourcesScrollPane.getViewport().setOpaque(false);
        resourcesScrollPane.setBackground(NAVIGATION_PANEL_COLOR);
        resourcesScrollPane.setBorder(null);
        panel.add(resourcesScrollPane);

        // Inflow

        switchRightButton.setBounds(490, 750, 60, 60);
        switchRightButton.setBorder(null);
        switchRightButton.setBackground(NAVIGATION_PANEL_COLOR);
        switchRightButton.setOpaque(false);
        switchRightButton.setIcon(switchRightImage);
        switchRightButton.setFont(iconButtonFont);
        switchRightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffLabel.setText("Attendee");
                attendeeEmailLabel.setText("Email Address");
                switchRightButton.setEnabled(false);
                switchRightButton.setVisible(false);
                switchLeftButton.setVisible(true);
                switchLeftButton.setEnabled(true);
            }
        });
        panel.add(switchRightButton);

        switchLeftButton.setBounds(545, 752, 60, 60);
        switchLeftButton.setBorder(null);
        switchLeftButton.setBackground(NAVIGATION_PANEL_COLOR);
        switchLeftButton.setOpaque(false);
        switchLeftButton.setIcon(switchLeftImage);
        switchLeftButton.setFont(iconButtonFont);
        switchLeftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                staffLabel.setText("Staff");
                attendeeEmailLabel.setText("Role");
                switchLeftButton.setEnabled(false);
                switchLeftButton.setVisible(false);
                switchRightButton.setVisible(true);
                switchRightButton.setEnabled(true);
            }
        });
        panel.add(switchLeftButton);
        switchLeftButton.setVisible(false);
        switchLeftButton.setEnabled(false);

        attendeeNameLabel.setBounds(436, 788, 170, 50);
        attendeeNameLabel.setForeground(PRIMARY_TEXT_COLOR);
        attendeeNameLabel.setFont(headerFont);
        panel.add(attendeeNameLabel);

        attendeeEmailLabel.setBounds(794, 788, 170, 50);
        attendeeEmailLabel.setForeground(PRIMARY_TEXT_COLOR);
        attendeeEmailLabel.setFont(headerFont);
        panel.add(attendeeEmailLabel);

        staffLabel.setBounds(436, 755, 170, 50);
        staffLabel.setForeground(PRIMARY_TEXT_COLOR);
        staffLabel.setFont(sTableFont);
        panel.add(staffLabel);

        peopleSeparator.setBounds(636, 792, 3, 170);
        peopleSeparator.setColor(BORDER_COLOR);
        panel.add(peopleSeparator);

        peoplePanel.setBounds(410, 738, 695, 240);
        peoplePanel.setColor(NAVIGATION_PANEL_COLOR);
        panel.add(peoplePanel);

        // Navigation Panel
        navigationPanel.setBounds(0, 0, 364, getHeight());
        navigationPanel.setBackground(NAVIGATION_PANEL_COLOR);
        navigationPanel.setLayout(null);
        panel.add(navigationPanel);

        // Title and date
        titleButton.setBounds(32, 39, 286, 71);
        titleButton.setFont(titleFont);
        titleButton.setForeground(PRIMARY_TEXT_COLOR);
        titleButton.setBackground(null);
        titleButton.setBorder(null);
        titleButton.setOpaque(false);
        titleButton.setText("Event Planner");
        titleButton.setHorizontalAlignment(SwingConstants.LEFT);
        titleButton.setHorizontalTextPosition(SwingConstants.LEFT);
        titleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadEventPopup();
            }
        });
        navigationPanel.add(titleButton);

        dateLabel.setBounds(32, 95, 230, 32);
        dateLabel.setFont(dateFont);
        dateLabel.setForeground(PRIMARY_TEXT_COLOR);
        navigationPanel.add(dateLabel);

        attendeesLabel.setBounds(32, 145, 230, 32);
        attendeesLabel.setFont(dateFont);
        attendeesLabel.setForeground(PRIMARY_TEXT_COLOR);
        navigationPanel.add(attendeesLabel);

        // Buttons
        dashboardButton.setBounds(0, 210, 364, 90);
        dashboardButton.setBorder(null);
        dashboardButton.setBackground(null);
        dashboardButton.setOpaque(false);
        dashboardButton.setIcon(dashboardImage);
        dashboardButton.setText("Dashboard");
        dashboardButton.setFont(iconButtonFont);
        dashboardButton.setForeground(PRIMARY_TEXT_COLOR);
        dashboardButton.setHorizontalAlignment(SwingConstants.LEFT);
        dashboardButton.setVerticalTextPosition(SwingConstants.CENTER);
        dashboardButton.setBorder(new EmptyBorder(10, 35, 10, 10));
        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        navigationPanel.add(dashboardButton);

        financesButton.setBounds(0, 300, 364, 90);
        financesButton.setBorder(null);
        financesButton.setBackground(null);
        financesButton.setOpaque(false);
        financesButton.setIcon(financesImage);
        financesButton.setText("Finances");
        financesButton.setFont(iconButtonFont);
        financesButton.setForeground(PRIMARY_TEXT_COLOR);
        financesButton.setHorizontalAlignment(SwingConstants.LEFT);
        financesButton.setVerticalTextPosition(SwingConstants.CENTER);
        financesButton.setBorder(new EmptyBorder(10, 35, 10, 10));
        financesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                financeInputPopup();
            }
        });
        navigationPanel.add(financesButton);

        resourcesButton.setBounds(0, 390, 364, 90);
        resourcesButton.setBorder(null);
        resourcesButton.setBackground(null);
        resourcesButton.setOpaque(false);
        resourcesButton.setIcon(resourcesImage);
        resourcesButton.setText("Resources");
        resourcesButton.setFont(iconButtonFont);
        resourcesButton.setForeground(PRIMARY_TEXT_COLOR);
        resourcesButton.setHorizontalAlignment(SwingConstants.LEFT);
        resourcesButton.setVerticalTextPosition(SwingConstants.CENTER);
        resourcesButton.setBorder(new EmptyBorder(10, 35, 10, 10));
        resourcesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resourceInputPopup();
            }
        });
        navigationPanel.add(resourcesButton);

        peopleButton.setBounds(0, 480, 364, 90);
        peopleButton.setBorder(null);
        peopleButton.setBackground(null);
        peopleButton.setOpaque(false);
        peopleButton.setIcon(peopleImage);
        peopleButton.setText("People");
        peopleButton.setFont(iconButtonFont);
        peopleButton.setForeground(PRIMARY_TEXT_COLOR);
        peopleButton.setHorizontalAlignment(SwingConstants.LEFT);
        peopleButton.setVerticalTextPosition(SwingConstants.CENTER);
        peopleButton.setBorder(new EmptyBorder(10, 35, 10, 10));
        peopleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (staffLabel.getText().equals("Staff")) {
                    staffInputPopup();
                } else if (staffLabel.getText().equals("Attendee")) {
                    attendeeInputPopup();
                }
            }
        });
        navigationPanel.add(peopleButton);

        programButton.setBounds(0, 570, 364, 90);
        programButton.setBorder(null);
        programButton.setBackground(null);
        programButton.setOpaque(false);
        programButton.setIcon(programImage);
        programButton.setText("Program");
        programButton.setFont(iconButtonFont);
        programButton.setForeground(PRIMARY_TEXT_COLOR);
        programButton.setHorizontalAlignment(SwingConstants.LEFT);
        programButton.setVerticalTextPosition(SwingConstants.CENTER);
        programButton.setBorder(new EmptyBorder(10, 35, 10, 10));
        programButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                programInputPopup();
            }
        });
        navigationPanel.add(programButton);

        // Background
        dashboardLabel.setBounds(416, 47, 485, 50);
        dashboardLabel.setFont(titleFont);
        dashboardLabel.setForeground(PRIMARY_TEXT_COLOR);
        panel.add(dashboardLabel);

        background.add(backgroundLabel);
        background.setBounds(0, -5, getWidth(), getHeight() + 5);
        panel.add(background);

        setContentPane(panel);
        setVisible(true);

        // Allow draggable frame
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint();
                getComponentAt(initialClick);
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Get the current location of the frame
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;

                // Move frame to this position
                int x = thisX + xMoved;
                int y = thisY + yMoved;
                setLocation(x, y);
            }
        });

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
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
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
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
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
                    String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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

                        String financesCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
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

    private void editResource(String title) { // somewhat done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
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

    private void deleteResource(String title) { // somewhat done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
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

    public void staffInputPopup() {
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

    private void addStaff() { // somewhat done
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
            String peopleCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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
        countAttendee();
        csvToArrayList();
    }

    private void editStaff() { // done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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

        countAttendee();
        csvToArrayList();
    }

    private void deleteStaff() { // done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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

        countAttendee();
        csvToArrayList();
    }

    public void attendeeInputPopup() {
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

    private void addAttendee() { // somewhat done
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
            String peopleCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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

        countAttendee();
        csvToArrayList();
    }

    private void editAttendee() { // somewhat done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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

        countAttendee();
        csvToArrayList();
    }

    private void deleteAttendee() { // somewhat done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
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

        countAttendee();
        csvToArrayList();
    }

    public void taskInputPopup() {
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

    private void addTask() { // somewhat done
        String title = "Add Task";
        String prompt = "Enter Task Name:";

        String taskName = JOptionPane.showInputDialog(null, prompt, title, JOptionPane.PLAIN_MESSAGE);
        if (taskName != null && !taskName.isEmpty()) {
            String taskFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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

    private void editTask() { // done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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

    private void deleteTask() { // done
        try {
            String csvFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
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

            countAttendee();
            csvToArrayList();
            JOptionPane.showMessageDialog(null, "Event loaded: " + currentEventName);
            titleButton.setText(currentEventName);
        }
    }

    public void loadEventNames() {
        eventNames.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("./events/events.csv"))) {
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

    private void addNewEvent() {
        while (true) {
            currentEventName = JOptionPane.showInputDialog(null, "Event Name: "); // Get Event Name
            titleButton.setText(currentEventName);
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
        String financesCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        try (BufferedWriter financesWriter = new BufferedWriter(new FileWriter(financesCSVFilePath))) {
            financesWriter.write("Outflows and Inflows");
            financesWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating finances CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create programme CSV file with header
        String programmeCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
        try (BufferedWriter programmeWriter = new BufferedWriter(new FileWriter(programmeCSVFilePath))) {
            programmeWriter.write("Programs and Tasks");
            programmeWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating programme CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Create people CSV file with header
        String staffCSVFilePath = "./events/" + currentEventName.toLowerCase() + "_people.csv";
        try (BufferedWriter staffWriter = new BufferedWriter(new FileWriter(staffCSVFilePath))) {
            staffWriter.write("Attendees and Staffs");
            staffWriter.newLine();
        } catch (IOException e) {
            System.err.println("Error creating staff CSV file for the new event: " + e.getMessage());
            e.printStackTrace();
        }

        // Append event details to events.csv
        String eventsCSVFilePath = "./events/events.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsCSVFilePath, true))) {
            writer.write(currentEventName + "," + currentEventDate + "," + budgetAllocated + "," + maxAttendees);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to events CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void csvToArrayList() {
        // Clear ArrayList first
        arrayClear();
        countAttendee();
        refresh();

        // Initialize file paths
        String outflowNameData = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        String inflowNameData = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        String outflowPriceData = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        String inflowPriceData = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        String eventDateData = "./events/" + "events.csv";
        String programNameData = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
        String programTimeData = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
        String programDateData = "./events/" + currentEventName.toLowerCase() + "_programme.csv";
        String finances = "./events/" + currentEventName.toLowerCase() + "_finances.csv";
        String people = "./events/" + currentEventName.toLowerCase() + "_people.csv";
        String task = "./events/" + currentEventName.toLowerCase() + "_programme.csv";

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

        // Attendee Data
        try (BufferedReader br = new BufferedReader(new FileReader(people))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Attendee")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 4; i++) {
                            attendeeData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Attendee data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Staff Data
        staffData = new Object[100][2];
        try (BufferedReader br = new BufferedReader(new FileReader(people))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Staff")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 2; i++) {
                            staffData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Staff data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Resources Data
        resourcesData = new Object[100][3];
        try (BufferedReader br = new BufferedReader(new FileReader(finances))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Resource")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 3; i++) {
                            resourcesData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Resources data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Revenue Data
        revenueData = new Object[100][2];
        try (BufferedReader br = new BufferedReader(new FileReader(finances))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Inflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 2; i++) {
                            revenueData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Revenue data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Expenses Data
        expensesData = new Object[100][2];
        try (BufferedReader br = new BufferedReader(new FileReader(finances))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Outflow")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 2; i++) {
                            expensesData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Expenses data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Task Data
        taskData = new Object[100][2];
        try (BufferedReader br = new BufferedReader(new FileReader(task))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Task")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 2; i++) {
                            taskData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Task data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        // Program Data
        programData = new Object[100][3];
        try (BufferedReader br = new BufferedReader(new FileReader(programNameData))) {
            int x = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].trim().equalsIgnoreCase("Program")) {
                    if (values.length >= 2 && !values[0].trim().isEmpty() && !values[1].trim().isEmpty()
                            && !values[2].trim().isEmpty()) {
                        for (int i = 0; i < 2; i++) {
                            taskData[x][i] = values[i + 1].trim();
                        }
                    }
                    x++;
                }
            }
            System.out.println("Program data updated.");
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }

        refresh();
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
        String csvFilePath = "./events/events.csv";

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
        String attendeeData = "./events/" + currentEventName.toLowerCase() + "_people.csv";

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

        String maxAttend = "./events/events.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(maxAttend))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0 && values[0].trim().equalsIgnoreCase(currentEventName)) {
                    maxAttendees = Integer.parseInt(values[3]);
                    System.out.println("Max Attendees: " + maxAttendees);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refresh() {
        getDate();

        dateLabel.setText(date);
        attendeesLabel.setText("Attendees: " + attendeeCount + " / " + maxAttendees);
        daysLeftNumLabel.setText("" + daysLeft);

        revalidate();
        repaint();

        /*
         * JLabel backgroundLabel = new JLabel(backgroundImage);
         * JLabel dashboardLabel = new JLabel("Overview");
         * JLabel dateLabel = new JLabel("May 31, 2024");
         * JLabel attendeesLabel = new JLabel("Attendees: 5 / 10,000");
         * JLabel financesLabel = new JLabel("Finances");
         * JLabel daysLeftNumLabel = new JLabel("50");
         * JLabel daysLeftTextLabel1 = new JLabel("days");
         * JLabel daysLeftTextLabel2 = new JLabel("before event");
         * JLabel remainingBudgetLabel = new JLabel("Remaining budget");
         * JLabel resourcesLabel = new JLabel("Resources");
         * JLabel resourceNameLabel = new JLabel("Name");
         * JLabel resourceSourceLabel = new JLabel("Source");
         * JLabel resourceDateLabel = new JLabel("Date");
         * JLabel resourceStatusLabel = new JLabel("Status");
         * JLabel staffLabel = new JLabel("Staff");
         * JLabel attendeesTLabel = new JLabel("Attendees");
         * JLabel attendeeNameLabel = new JLabel("Name", JLabel.CENTER);
         * JLabel attendeeEmailLabel = new JLabel("Role", JLabel.CENTER);
         * JLabel checklistLabel = new JLabel("Checklist");
         * JLabel programLabel = new JLabel("Program");
         */
    }

    public void getDate() {
        String daysRemaining = "./events/events.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(daysRemaining))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0 && values[0].trim().equalsIgnoreCase(currentEventName)) {
                    String yearString = "";
                    String monthString = "";
                    String dayString = "";
                    System.out.println("DATE STRING: " + values[1]);
                    int counter = 0;

                    for (int i = 0; i < values[1].length(); i++) {
                        char numbers = values[1].charAt(i);
                        if (numbers == '/') {
                            counter++;
                        } else if (numbers != '/' && counter == 0) {
                            dayString += numbers;
                        } else if (numbers != '/' && counter == 1) {
                            monthString += numbers;
                        } else if (numbers != '/' && counter == 2) {
                            yearString += numbers;
                        }
                    }

                    day = Integer.parseInt(dayString);
                    month = Integer.parseInt(monthString);
                    year = Integer.parseInt(yearString);

                    LocalDate nowDate = LocalDate.now();
                    LocalDate selectedDate = LocalDate.of(year, month, day);
                    daysLeft = ChronoUnit.DAYS.between(nowDate, selectedDate);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, uuuu");
                    date = formatter.format(selectedDate);

                    System.out.println("DATE BRO: " + day + "/" + month + "/" + year);
                    System.out.println("DAYS LEFT" + daysLeft);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from the CSV file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            eventPlannerBeta frame = new eventPlannerBeta();
            frame.setVisible(true);
        });
    }
}