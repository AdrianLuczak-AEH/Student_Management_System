package gui;

import db_manager.StudentManagerImplementation;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentManagerGUI {
    private StudentManagerImplementation studentManager = new StudentManagerImplementation();

    public void createAndShowGUI() {
        JFrame frame = new JFrame("System Zarządzania Studentami");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel wejściowy
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField gradeField = new JTextField();

        inputPanel.add(new JLabel("Student ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        // Panel przycisków
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton updateButton = new JButton("Update Student");
        JButton displayButton = new JButton("Display All Students");
        JButton averageButton = new JButton("Calculate Average");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(averageButton);

        // Panel wyjściowy
        JTextArea outputArea = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Obsługa zdarzeń dla przycisku "Add Student"
        addButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double grade = Double.parseDouble(gradeField.getText().trim());

                if (age <= 0 || grade < 0.0 || grade > 100.0) {
                    throw new IllegalArgumentException("Nieprawidłowe dane wejściowe!");
                }

                studentManager.addStudent(new Student(name, age, grade, id));
                outputArea.setText("Dodano studenta!");
            } catch (Exception ex) {
                outputArea.setText("Błąd: " + ex.getMessage());
            }
        });

        // Obsługa zdarzeń dla przycisku "Remove Student"
        removeButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                if (id.isEmpty()) throw new IllegalArgumentException("Podaj ID studenta!");

                studentManager.removeStudent(id);
                outputArea.setText("Student o ID " + id + " został usunięty.");
            } catch (Exception ex) {
                outputArea.setText("Błąd: " + ex.getMessage());
            }
        });

        // Obsługa zdarzeń dla przycisku "Update Student"
        updateButton.addActionListener(e -> {
            try {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                double grade = Double.parseDouble(gradeField.getText().trim());

                if (id.isEmpty() || age <= 0 || grade < 0.0 || grade > 100.0) {
                    throw new IllegalArgumentException("Nieprawidłowe dane wejściowe!");
                }

                studentManager.updateStudent(new Student(name, age, grade, id));
                outputArea.setText("Zaktualizowano dane studenta o ID " + id + ".");
            } catch (Exception ex) {
                outputArea.setText("Błąd: " + ex.getMessage());
            }
        });

        // Obsługa zdarzeń dla przycisku "Display All Students"
        displayButton.addActionListener(e -> {
            try {
                ArrayList<Student> students = studentManager.displayAllStudents();
                if (students.isEmpty()) {
                    outputArea.setText("Brak studentów w bazie danych.");
                } else {
                    StringBuilder result = new StringBuilder("Lista studentów:\n");
                    for (Student student : students) {
                        result.append(student.getStudentID()).append(" - ")
                                .append(student.getName()).append(", Wiek: ")
                                .append(student.getAge()).append(", Ocena: ")
                                .append(student.getGrade()).append("\n");
                    }
                    outputArea.setText(result.toString());
                }
            } catch (Exception ex) {
                outputArea.setText("Błąd: " + ex.getMessage());
            }
        });

        // Obsługa zdarzeń dla przycisku "Calculate Average"
        averageButton.addActionListener(e -> {
            try {
                double average = studentManager.calculateAverageGrade();
                outputArea.setText("Średnia ocen wszystkich studentów: " + average);
            } catch (Exception ex) {
                outputArea.setText("Błąd: " + ex.getMessage());
            }
        });


        // Układ główny
        frame.setLayout(new BorderLayout());
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
