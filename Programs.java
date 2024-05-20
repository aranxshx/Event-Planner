import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Programs extends JPanel {
    public Programs() {

        setSize(1440, 1024);

        setLayout(null);

        JPanel background = new JPanel();
        JPanel menuPanel1 = new JPanel();
        JPanel menuPanel2 = new JPanel();
        JPanel attendeesPanel = new JPanel();
        JPanel budgetPanel = new JPanel();
        JPanel remainingBudgetPanel = new JPanel();
        JPanel checkPanel = new JPanel();
        JPanel revenuePanel = new JPanel();
        JPanel expensesPanel = new JPanel();
        JPanel programPanel = new JPanel();
        JPanel resourcesPanel = new JPanel();

        // Font boldFont = new Font("Inter-Bold", 24);
        // Font lightFont = new Font("Inter-Light", 13);
        Color blueColor = new Color(97, 113, 255);
        Border border = BorderFactory.createLineBorder(blueColor, 1);

        // - > Icons
        ImageIcon financesIcon = new ImageIcon(getClass().getResource("Finances.png"));
        ImageIcon addProgramIcon = new ImageIcon(getClass().getResource("Add Program.png"));
        ImageIcon addResourceIcon = new ImageIcon(getClass().getResource("Add Resource.png"));
        ImageIcon addStaffIcon = new ImageIcon(getClass().getResource("Add Staff.png"));
        ImageIcon addAttendeeIcon = new ImageIcon(getClass().getResource("Add Attendee.png"));
        ImageIcon addTaskIcon = new ImageIcon(getClass().getResource("Add Task.png"));

        JLabel icon1 = new JLabel(financesIcon);
        JLabel icon2 = new JLabel(addProgramIcon);
        JLabel icon3 = new JLabel(addResourceIcon);
        JLabel icon4 = new JLabel(addStaffIcon);
        JLabel icon5 = new JLabel(addAttendeeIcon);
        JLabel icon6 = new JLabel(addTaskIcon);

        icon1.setBounds(1343, 230, 80, 80);
        icon2.setBounds(1343, 300, 80, 80);
        icon3.setBounds(1343, 380, 80, 80);
        icon4.setBounds(1343, 460, 80, 80);
        icon5.setBounds(1343, 540, 80, 80);
        icon6.setBounds(1343, 850, 80, 80);

        add(icon1);
        add(icon2);
        add(icon3);
        add(icon4);
        add(icon5);
        add(icon6);
        // ----------------------------------------------- >

        // - > Menu Bar
        background.setBackground(Color.WHITE);
        background.setBounds(0, 0, 1440, 1024);

        JLabel titleLabel = new JLabel("Insert Title");
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

        dashboardLabel.setBounds(464, 34, 100, 100);
        welcomeLabel.setBounds(464, 65, 500, 100);
        // ------------------------------------------------------ >

        // - > Finances
        JLabel resourcesLabel = new JLabel("Resources");
        JLabel resourceLabel = new JLabel("Resource");
        JLabel sourceLabel = new JLabel("Source");
        JLabel priceLabel = new JLabel("Resources");
        JLabel statusLabel = new JLabel("Status");

        resourcesLabel.setForeground(blueColor);
        resourceLabel.setForeground(blueColor);
        sourceLabel.setForeground(blueColor);
        priceLabel.setForeground(blueColor);
        statusLabel.setForeground(blueColor);

        resourcesLabel.setBounds(495, 265, 100, 100);
        resourceLabel.setBounds(495, 305, 100, 100);
        sourceLabel.setBounds(710, 305, 100, 100);
        priceLabel.setBounds(920, 305, 100, 100);
        statusLabel.setBounds(1150, 305, 100, 100);

        resourcesPanel.setBackground(Color.WHITE);
        resourcesPanel.setBounds(458, 279, 845, 290);
        resourcesPanel.setBorder(border);

        programPanel.setBackground(Color.WHITE);
        programPanel.setBounds(458, 590, 845, 290);
        programPanel.setBorder(border);

        expensesPanel.setBackground(Color.WHITE);
        expensesPanel.setBounds(907, 282, 395, 505);
        expensesPanel.setBorder(border);

        revenuePanel.setBackground(Color.WHITE);
        revenuePanel.setBounds(458, 282, 395, 505);
        revenuePanel.setBorder(border);
        // ------------------------------------------------------ >

        // - > Programs
        JLabel programLabel = new JLabel("Program");
        programLabel.setForeground(blueColor);
        programLabel.setBounds(495, 575, 100, 100);

        programPanel.setBackground(Color.WHITE);
        programPanel.setBounds(458, 590, 845, 290);
        programPanel.setBorder(border);

        // programPanel.setBackground(Color.WHITE);
        // programPanel.setBounds(458, 601, 844, 290);
        // programPanel.setBorder(border)
        // ------------------------------------------------------ >;

        // - > Menu Add Label
        add(titleLabel);
        add(dateLabel);
        add(attendeesLabel);
        add(attendeesNumberLabel);
        add(attendeesPeopleLabel);
        add(budgetLabel);
        add(budgetNumberLabel);
        add(remainingLabel);
        add(remainingFirstNumberLabel);
        add(remaingingSecondNumberLabel);
        add(checklistLabel);
        add(dashboardLabel);
        add(welcomeLabel);
        // ----------------------------------------------------- >

        // - > Program Add Label
        add(programLabel);
        // ----------------------------------------------------- >

        // - > Program Add Panel
        add(programPanel);
        // ----------------------------------------------------- >

        // - > Finances Add Label
        add(resourcesLabel);
        add(resourceLabel);
        add(sourceLabel);
        add(priceLabel);
        add(statusLabel);
        // ----------------------------------------------------- >

        // - > Finances Add Panel
        add(resourcesPanel);
        // ----------------------------------------------------- >

        // - > Menu Add Panel
        add(budgetPanel);
        add(remainingBudgetPanel);
        add(checkPanel);
        add(attendeesPanel);
        add(menuPanel1);
        add(menuPanel2);
        add(background);
        // ----------------------------------------------------- >

    }
}