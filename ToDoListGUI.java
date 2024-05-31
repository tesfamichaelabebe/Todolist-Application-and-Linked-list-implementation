package assignment1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame {
    private ToDoList toDoList;
    private JTextField titleField;
    private JTextField descriptionField;
    private JTextArea displayArea;
    private JTextField completeTitleField;

    public ToDoListGUI() {
        toDoList = new ToDoList();

        // Setting up the JFrame
        setTitle("To-Do List Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating Panels
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        JPanel completePanel = new JPanel(new GridLayout(1, 2));

        // Creating Input Fields
        titleField = new JTextField();
        descriptionField = new JTextField();
        completeTitleField = new JTextField();

        // Creating Text Area for displaying tasks
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        // Creating Buttons
        JButton addButton = new JButton("Add Task");
        JButton completeButton = new JButton("Mark Completed");

        // Adding Action Listeners to Buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                if (!title.isEmpty() && !description.isEmpty()) {
                    toDoList.addToDo(new Task(title, description));
                    titleField.setText("");
                    descriptionField.setText("");
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both title and description.");
                }
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = completeTitleField.getText();
                if (!title.isEmpty()) {
                    toDoList.markToDoAsCompleted(title);
                    completeTitleField.setText("");
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter the title of the task to mark as completed.");
                }
            }
        });

        // Adding Components to Panels
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel(""));

        completePanel.add(new JLabel("Complete Title:"));
        completePanel.add(completeTitleField);

        buttonPanel.add(addButton);
        buttonPanel.add(completeButton);

        // Adding Panels to Frame
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(completePanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);

        setVisible(true);
    }

    private void updateDisplay() {
        displayArea.setText("");
        displayArea.append("To-Do List:\n");
        displayArea.append("-----------------\n");
        Node current = toDoList.head;
        while (current != null) {
            Task task = current.task;
            displayArea.append("Title: " + task.getTitle() + "\n");
            displayArea.append("Description: " + task.getDescription() + "\n");
            displayArea.append("Completed: " + (task.isCompleted() ? "Yes" : "No") + "\n");
            displayArea.append("-----------------\n");
            current = current.next;
        }
    }

    public static void main(String[] args) {
        new ToDoListGUI();
    }
}

class Task {
    private String title;
    private String description;
    private boolean completed;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class ToDoList {
    Node head;

    public ToDoList() {
        this.head = null;
    }

    public void addToDo(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void markToDoAsCompleted(String title) {
        Node current = head;
        while (current != null) {
            if (current.task.getTitle().equals(title)) {
                current.task.markCompleted();
                return;
            }
            current = current.next;
        }
        JOptionPane.showMessageDialog(null, "Task with title \"" + title + "\" not found.");
    }

    public void viewToDoList(JTextArea displayArea) {
        Node current = head;
        if (current == null) {
            displayArea.append("The to-do list is empty.\n");
            return;
        }
        while (current != null) {
            Task task = current.task;
            displayArea.append("Title: " + task.getTitle() + "\n");
            displayArea.append("Description: " + task.getDescription() + "\n");
            displayArea.append("Completed: " + (task.isCompleted() ? "Yes" : "No") + "\n");
            displayArea.append("-----------------\n");
            current = current.next;
        }
    }
}