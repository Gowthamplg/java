import java.awt.*;
import java.awt.event.*;

public class Employee extends Frame implements ActionListener {
    Label title, nameLbl, idLbl, deptLbl, salaryLbl;
    TextField nameTxt, idTxt, deptTxt, salaryTxt;
    Button addBtn, updateBtn, deleteBtn, clearBtn;
    TextArea displayArea;
    Panel inputPanel, buttonPanel, displayPanel;

    public Employee() {
        setTitle("Employee Management System");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        title = new Label("Employee Management System", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        inputPanel = new Panel(new GridLayout(4, 2, 10, 10));
        nameLbl = new Label("Name:");
        nameTxt = new TextField();
        inputPanel.add(nameLbl);
        inputPanel.add(nameTxt);

        idLbl = new Label("ID:");
        idTxt = new TextField();
        inputPanel.add(idLbl);
        inputPanel.add(idTxt);

        deptLbl = new Label("Department:");
        deptTxt = new TextField();
        inputPanel.add(deptLbl);
        inputPanel.add(deptTxt);

        salaryLbl = new Label("Salary:");
        salaryTxt = new TextField();
        inputPanel.add(salaryLbl);
        inputPanel.add(salaryTxt);

        add(inputPanel, BorderLayout.CENTER);

        buttonPanel = new Panel(new FlowLayout());
        addBtn = new Button("Add");
        updateBtn = new Button("Update");
        deleteBtn = new Button("Delete");
        clearBtn = new Button("Clear");

        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(clearBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        deleteBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        displayPanel = new Panel();
        displayPanel.setLayout(new BorderLayout());
        displayArea = new TextArea(10, 40);
        displayPanel.add(new Label("Employee Records:"), BorderLayout.NORTH);
        displayPanel.add(displayArea, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.EAST);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String name = nameTxt.getText();
        String id = idTxt.getText();
        String dept = deptTxt.getText();
        String salary = salaryTxt.getText();

        if (command.equals("Add")) {
            displayArea.append("Added: " + name + " | " + id + " | " + dept + " | " + salary + "\n");
        } else if (command.equals("Update")) {
            displayArea.append("Updated: " + name + " | " + id + " | " + dept + " | " + salary + "\n");
        } else if (command.equals("Delete")) {
            displayArea.append("Deleted: " + id + "\n");
        } else if (command.equals("Clear")) {
            nameTxt.setText("");
            idTxt.setText("");
            deptTxt.setText("");
            salaryTxt.setText("");
        }
    }

    public static void main(String[] args) {
        new Employee().setVisible(true);
    }
}
