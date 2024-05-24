import javax.swing.border.Border;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

// import javafx.scene.text.Font;

public class Finances extends JPanel {
    Font boldFont = new Font("Arial", Font.BOLD, 14);
    Font regularFont = new Font("Arial", Font.PLAIN, 14);
    
    JButton financesToggle, peopleToggle, programsToggle;
    Color blueColor = new Color(97, 113, 255);
    Border border = BorderFactory.createLineBorder(blueColor, 1);

    JLabel titleLabel = new JLabel(eventPlanner.currentEventName);
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

    
    public Finances() {

        setSize(1440, 1024);

        setLayout(null);

        JPanel background = new JPanel();
        JPanel menuPanel1 = new JPanel();
        JPanel menuPanel2 = new JPanel();
        RoundedRectangle attendeesPanel = new RoundedRectangle(50, blueColor);
        RoundedRectangle budgetPanel = new RoundedRectangle(50, blueColor);
        RoundedRectangle remainingBudgetPanel = new RoundedRectangle(50, blueColor);
        RoundedRectangle checkPanel = new RoundedRectangle(50, blueColor);
        RoundedRectangle revenuePanel = new RoundedRectangle(50, Color.WHITE);
        RoundedRectangle expensesPanel = new RoundedRectangle(50, Color.WHITE);
        RoundedRectangle programPanel = new RoundedRectangle(50, Color.WHITE);

        RoundedRectangle totalExpensesPanel = new RoundedRectangle(50, Color.WHITE);
        RoundedRectangle totalRevenuePanel = new RoundedRectangle(50, Color.WHITE);

        // Font boldFont = new Font("Inter-Bold", 24);
        // Font lightFont = new Font("Inter-Light", 13);

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

        JLabel revenueLabel = new JLabel("Revenue");
        JLabel revenueNameLabel = new JLabel("Name");
        JLabel revenuePriceLabel = new JLabel("Price");
        JLabel expensesLabel = new JLabel("Expenses");
        JLabel expensesNameLabel = new JLabel("Name");
        JLabel expensesPriceLabel = new JLabel("Price");

        icon1.setBounds(1343, 230, 60, 60);
        icon2.setBounds(1343, 300, 60, 60);
        icon3.setBounds(1343, 380, 60, 60);
        icon4.setBounds(1343, 460, 60, 60);
        icon5.setBounds(1343, 540, 60, 60);
        icon6.setBounds(1343, 850, 60, 60);

        add(icon1);
        add(icon2);
        add(icon3);
        add(icon4);
        add(icon5);
        add(icon6);
        // ---------------------------------------------------------------- >

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


        titleLabel.setFont(boldFont);
        attendeesLabel.setFont(regularFont);
        attendeesPeopleLabel.setFont(regularFont);
        budgetLabel.setFont(regularFont);
        remainingLabel.setFont(regularFont);
        checklistLabel.setFont(boldFont);
        dashboardLabel.setFont(boldFont);
        welcomeLabel.setFont(boldFont);

        // Finance label setFont
        revenueLabel.setFont(boldFont);
        revenueNameLabel.setFont(boldFont);
        revenuePriceLabel.setFont(boldFont);
        expensesLabel.setFont(boldFont);
        expensesNameLabel.setFont(boldFont);
        expensesPriceLabel.setFont(boldFont);
        totalExpensesLabel.setFont(boldFont);
        totalRevenueLabel.setFont(boldFont);
    
        totalExpensesAmountLabel.setFont(regularFont);
        totalRevenueAmountLabel.setFont(regularFont);

        dateLabel.setFont(regularFont);
        attendeesNumberLabel.setFont(boldFont);
        budgetNumberLabel.setFont(boldFont);
        remainingFirstNumberLabel.setFont(boldFont);
        remaingingSecondNumberLabel.setFont(boldFont);
        attendeesNumberLabel.setFont(boldFont);
        budgetNumberLabel.setFont(boldFont);
        remainingFirstNumberLabel.setFont(boldFont);
        remaingingSecondNumberLabel.setFont(boldFont);

        menuPanel1.setBackground(blueColor);
        menuPanel1.setBounds(0, 0, 409, 1024);

        menuPanel2.setBackground(blueColor);
        menuPanel2.setBounds(1341, 0, 365, 1024);

        attendeesPanel.setColor(Color.WHITE);
        attendeesPanel.setBounds(70, 215, 303, 93);

        budgetPanel.setColor(Color.WHITE);
        budgetPanel.setBounds(70, 334, 303, 93);

        remainingBudgetPanel.setColor(Color.WHITE);
        remainingBudgetPanel.setBounds(70, 453, 303, 93);

        checkPanel.setColor(Color.WHITE);
        checkPanel.setBounds(70, 582, 303, 290);
        // ---------------------------------------------------------------- >

        // - > Finances
        revenueLabel.setForeground(blueColor);
        revenueNameLabel.setForeground(blueColor);
        revenuePriceLabel.setForeground(blueColor);
        expensesLabel.setForeground(blueColor);
        expensesNameLabel.setForeground(blueColor);
        expensesPriceLabel.setForeground(blueColor);

        totalExpensesLabel.setBounds(486, 820, 100, 100);
        totalExpensesAmountLabel.setBounds(486, 846, 100, 100);
        totalExpensesLabel.setForeground(Color.WHITE);
        totalExpensesAmountLabel.setForeground(Color.WHITE);

        totalRevenueLabel.setForeground(Color.WHITE);
        totalRevenueAmountLabel.setForeground(Color.WHITE);

        totalRevenueLabel.setBounds(935, 820, 100, 100);
        totalRevenueAmountLabel.setBounds(935, 846, 100, 100);

        revenueLabel.setBounds(500, 265, 100, 100);
        revenueNameLabel.setBounds(500, 300, 100, 100);
        revenuePriceLabel.setBounds(700, 300, 100, 100);
        expensesLabel.setBounds(950, 265, 100, 100);
        expensesNameLabel.setBounds(950, 300, 100, 100);
        expensesPriceLabel.setBounds(1150, 300, 100, 100);

        totalExpensesPanel.setColor(blueColor);
        totalExpensesPanel.setBounds(458, 838, 396, 93);

        totalRevenuePanel.setColor(blueColor);
        totalRevenuePanel.setBounds(907, 838, 396, 93);

        revenuePanel.setColor(Color.WHITE);
        revenuePanel.setBounds(458, 282, 395, 505);

        expensesPanel.setColor(Color.WHITE);
        expensesPanel.setBounds(907, 282, 395, 505);
        // -------------------------------------------------------------- >

        
        // - > Program
        programLabel.setForeground(blueColor);
        // programLabel.setBounds(0, 0, 100, 100);
        // programPanel.setBackground(Color.WHITE);
        // programPanel.setBounds(458, 601, 844, 290);
        // programPanel.setBorder(border);
        // -------------------------------------------------------------- >

        // - > Finances Add Label
        add(totalExpensesLabel);
        add(totalExpensesAmountLabel);
        add(totalRevenueLabel);
        add(totalRevenueAmountLabel);
        add(revenueLabel);
        add(revenueNameLabel);
        add(revenuePriceLabel);
        add(expensesLabel);
        add(expensesNameLabel);
        add(expensesPriceLabel);
        // ------------------------------------------------------------- >

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
        // add(programPanel);
        // ------------------------------------------------------------- >

        // - > Finances Add Panel
        add(revenuePanel);
        add(expensesPanel);
        add(totalExpensesPanel);
        add(totalRevenuePanel);
        // ------------------------------------------------------------ >

        // - > Menu Add Panel
        add(budgetPanel);
        add(remainingBudgetPanel);
        add(checkPanel);
        add(attendeesPanel);
        add(menuPanel1);
        add(menuPanel2);
        add(background);
        // ------------------------------------------------------------- >

        setVisible(true);

    }

    
}