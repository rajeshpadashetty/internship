import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel; // ✅ small fix for clarity
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ToDoApp extends JFrame implements ActionListener {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JButton addButton, removeButton, clearButton;

    public ToDoApp() {
        // Frame setup
        setTitle("To-Do List App");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("My To-Do List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Task list model and view
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        taskField = new JTextField(20);
        addButton = new JButton("Add");
        removeButton = new JButton("Remove");
        clearButton = new JButton("Clear All");

        inputPanel.add(taskField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(clearButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Add listeners
        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Show frame
        setVisible(true);
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Enter a task first!");
            }
        } else if (e.getSource() == removeButton) {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Select a task to remove!");
            }
        } else if (e.getSource() == clearButton) {
            if (taskListModel.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No tasks to clear!");
            } else {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to clear all tasks?",
                        "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    taskListModel.clear();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoApp::new);
    }
}
