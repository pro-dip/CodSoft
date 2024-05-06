import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculator extends JFrame {
    private JTextField[] subjectFields;
    private JTextArea resultArea;

    private static final String[] SUBJECT_NAMES = {"DSA", "PYTHON", "JAVA", "OS", "CN"};
    private static final int MAX_MARKS = 100;

    public GradeCalculator() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel subjectLabel = new JLabel("Enter marks obtained in each subject (0-100):");
        subjectLabel.setBounds(20, 20, 300, 30);
        add(subjectLabel);

        subjectFields = new JTextField[5];
        for (int i = 0; i < subjectFields.length; i++) {
            JLabel subjectNameLabel = new JLabel(SUBJECT_NAMES[i] + ":");
            subjectNameLabel.setBounds(20, 60 + i * 30, 60, 20);
            add(subjectNameLabel);

            subjectFields[i] = new JTextField();
            subjectFields[i].setBounds(100, 60 + i * 30, 50, 20);
            add(subjectFields[i]);
        }

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 180, 100, 30);
        calculateButton.addActionListener(new CalculateButtonListener());
        add(calculateButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 220, 350, 50);
        add(resultArea);

        setVisible(true);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int totalMarks = 0;
                int numSubjects = subjectFields.length;
                for (JTextField field : subjectFields) {
                    int marks = Integer.parseInt(field.getText());
                    if (marks < 0 || marks > MAX_MARKS) {
                        JOptionPane.showMessageDialog(null, "Marks for each subject must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    totalMarks += marks;
                }
                double averagePercentage = (double) totalMarks / numSubjects;

                String grade;
                if (averagePercentage >= 90) {
                    grade = "A+";
                } else if (averagePercentage >= 80) {
                    grade = "A";
                } else if (averagePercentage >= 70) {
                    grade = "B";
                } else if (averagePercentage >= 60) {
                    grade = "C";
                } else if (averagePercentage >= 50) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                resultArea.setText("Total Marks: " + totalMarks + "\nAverage Percentage: " + String.format("%.2f", averagePercentage) + "%\nGrade: " + grade);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid marks for all subjects.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculator());
    }
}
