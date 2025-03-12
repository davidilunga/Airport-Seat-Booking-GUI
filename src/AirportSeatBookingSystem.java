/*import java.awt.*;


import java.io.*;
import java.util.*;
import javax.swing.*;

public class AirportSeatBookingSystem {
    private JFrame frame;
    private JTextArea textArea;
    private Map<Integer, String> seats = new HashMap<>();
    private static final int TOTAL_SEATS = 10;
    private final String FILE_NAME = "seats_data.txt";

    public AirportSeatBookingSystem() {
        frame = new JFrame("Airport Seat Booking System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.SOUTH);
        
        String[] options = {"Add Passenger", "Display Empty Seats", "Delete Passenger", "Find Seat",
                            "Save Data", "Load Data", "Sort Seats", "Reset Seats", "View Seats", "Quit"};
        JComboBox<String> menu = new JComboBox<>(options);
        JButton selectButton = new JButton("Select");
        
        panel.add(menu);
        panel.add(selectButton);
        
        selectButton.addActionListener(e -> handleMenuSelection((String) menu.getSelectedItem()));

        initializeSeats();
        frame.setVisible(true);
    }

    private void initializeSeats() {
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            seats.put(i, "Empty");
        }
    }

    private void handleMenuSelection(String choice) {
        switch (choice) {
            case "Add Passenger":
                addPassenger();
                break;
            case "Display Empty Seats":
                displayEmptySeats();
                break;
            case "Delete Passenger":
                deletePassenger();
                break;
            case "Find Seat":
                findSeat();
                break;
            case "Save Data":
                saveData();
                break;
            case "Load Data":
                loadData();
                break;
            case "Sort Seats":
                sortSeats();
                break;
            case "Reset Seats":
                resetSeats();
                break;
            case "View Seats":
                viewSeats();
                break;
            case "Quit":
                System.exit(0);
                break;
        }
    }

    private void addPassenger() {
        String name = JOptionPane.showInputDialog("Enter Passenger Name:");
        if (name != null && !name.trim().isEmpty()) {
            for (int i = 1; i <= TOTAL_SEATS; i++) {
                if (seats.get(i).equals("Empty")) {
                    seats.put(i, name);
                    textArea.append("Passenger " + name + " added to seat " + i + "\n");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "No empty seats available!");
        }
    }

    private void displayEmptySeats() {
        textArea.append("Empty seats: ");
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            if (seats.get(i).equals("Empty")) {
                textArea.append(i + " ");
            }
        }
        textArea.append("\n");
    }

    private void deletePassenger() {
        String seatNumStr = JOptionPane.showInputDialog("Enter Seat Number to Remove Passenger:");
        try {
            int seatNum = Integer.parseInt(seatNumStr);
            if (seats.containsKey(seatNum) && !seats.get(seatNum).equals("Empty")) {
                textArea.append("Passenger " + seats.get(seatNum) + " removed from seat " + seatNum + "\n");
                seats.put(seatNum, "Empty");
            } else {
                JOptionPane.showMessageDialog(frame, "Seat is already empty or invalid!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
        }
    }

    private void findSeat() {
        String name = JOptionPane.showInputDialog("Enter Passenger Name to Find:");
        for (Map.Entry<Integer, String> entry : seats.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(frame, name + " is in seat " + entry.getKey());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Passenger not found!");
    }

    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<Integer, String> entry : seats.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving data!");
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                seats.put(Integer.parseInt(parts[0]), parts[1]);
            }
            JOptionPane.showMessageDialog(frame, "Data loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data!");
        }
    }

    private void sortSeats() {
        List<String> sortedNames = new ArrayList<>(seats.values());
        sortedNames.remove("Empty");
        Collections.sort(sortedNames);
        textArea.append("Seats sorted by name: " + sortedNames + "\n");
    }

    private void resetSeats() {
        initializeSeats();
        textArea.append("All seats reset to empty.\n");
    }

    private void viewSeats() {
        textArea.append("Current Seat Status:\n");
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            textArea.append("Seat " + i + ": " + seats.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirportSeatBookingSystem::new);
    }
}
*/

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class AirportSeatBookingSystem {
    private JFrame frame;
    private JTextArea textArea;
    private Map<Integer, String> seats = new HashMap<>();
    private static final int TOTAL_SEATS = 10;
    private final String FILE_NAME = "seats_data.txt";
    static String regex = "^[a-zA-Z\\s]+$";

    public AirportSeatBookingSystem() {
        frame = new JFrame("Airport Booking System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.decode("#11151C"));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.decode("#364156"));
        textArea.setForeground(Color.decode("#FFFFFF"));
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10, 1, 5, 5));
        sidePanel.setBackground(Color.decode("#212D40"));
        frame.add(sidePanel, BorderLayout.WEST);

        addNavButton(sidePanel, "Load Data", e -> loadData());
        addNavButton(sidePanel, "Add Passenger", e -> addPassenger());
        addNavButton(sidePanel, "Delete Passenger", e -> deletePassenger());
        addNavButton(sidePanel, "Find Seat", e -> findSeat());
        addNavButton(sidePanel, "View Seats", e -> viewSeats());
        addNavButton(sidePanel, "Display Empty Seats", e -> displayEmptySeats());
        addNavButton(sidePanel, "Save Data/Export", e -> saveData());

        initializeSeats();
        frame.setVisible(true);
    }

    private void addNavButton(JPanel panel, String label, ActionListener action) {
        JButton button = new JButton(label);
        button.setBackground(Color.decode("#3E8989"));
        button.setForeground(Color.decode("#000000"));
        button.setFocusPainted(false);
        button.addActionListener(action);
        panel.add(button);
    }

    private void initializeSeats() {
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            seats.put(i, "Empty");
        }
    }

    private void addPassenger() {
        String firstName = JOptionPane.showInputDialog("Enter Passenger First Name:");
        String surname = JOptionPane.showInputDialog("Enter Passenger Surname:");
        String boardingNumber = JOptionPane.showInputDialog("Enter Boarding Number:");

        if (firstName != null && surname != null && boardingNumber != null && 
            !firstName.trim().isEmpty() && !surname.trim().isEmpty() && !boardingNumber.trim().isEmpty()) {
            String fullName = firstName + " " + surname + " (Boarding No: " + boardingNumber + ")";
            textArea.append("Passenger Added:\n--------------\n\n");
            for (int i = 1; i <= TOTAL_SEATS; i++) {
                if (seats.get(i).equals("Empty")) {
                    seats.put(i, fullName);
                    textArea.append("Passenger " + i +  " : " + fullName + "\n\n");
                    //textArea.append("Passenger " + name + " added to seat " + i + "\n");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "No empty seats available!");
        }
    }

    private void displayEmptySeats() {
        textArea.append("Empty seats: \n-----------\n\n");
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            if (seats.get(i).equals("Empty")) {
                textArea.append("Seat Number: " + i + "\n\n");
            }
        }
        textArea.append("\n");
    }

    private void deletePassenger() {
        String seatNumStr = JOptionPane.showInputDialog("Enter Seat Number to Remove Passenger:");
        try {
            int seatNum = Integer.parseInt(seatNumStr);
            if (seats.containsKey(seatNum) && !seats.get(seatNum).equals("Empty")) {
                textArea.append("Passenger " + seats.get(seatNum) + " removed from seat " + seatNum + "\n");
                seats.put(seatNum, "Empty");
            } else {
                JOptionPane.showMessageDialog(frame, "Seat is already empty or invalid!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Invalid input!");
        }
    }

    private void findSeat() {
        String name = JOptionPane.showInputDialog("Enter Passenger Name to Find:");
        for (Map.Entry<Integer, String> entry : seats.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(frame, name + " is in seat " + entry.getKey());
                return;
            }
        }
        JOptionPane.showMessageDialog(frame, "Passenger not found!");
    }

    private void saveData() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<Integer, String> entry : seats.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            JOptionPane.showMessageDialog(frame, "Data saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving data!");
        }
    }

    private void loadData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                seats.put(Integer.parseInt(parts[0]), parts[1]);
            }
            JOptionPane.showMessageDialog(frame, "Data loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data!");
        }
    }

    private void viewSeats() {
        textArea.append("Current Seat Status:\n----------------\n\n");
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            textArea.append("Seat " + i + ": " + seats.get(i) + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AirportSeatBookingSystem::new);
    }
}
