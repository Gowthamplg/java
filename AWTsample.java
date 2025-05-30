import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AWTsample extends Frame {

    Label labelTitle, labelBookTitle, labelAuthor, labelYear, labelSearch;
    TextField textBookTitle, textAuthor, textYear, textSearch;
    Button btnAdd, btnDisplay, btnSearch;
    TextArea textAreaDisplay;
 
    Connection con;
    
    AWTsample() {
        setTitle("Library Management");
        setSize(500, 400);
        setLayout(null);
        setVisible(true);
  
        labelTitle = new Label("Library Search & Add");
        labelTitle.setBounds(150, 40, 200, 30);
        labelTitle.setFont(new Font("Dialog", Font.BOLD, 18));
        add(labelTitle);

        labelBookTitle = new Label("Book Title:");
        labelBookTitle.setBounds(50, 90, 100, 25);
        add(labelBookTitle);

        textBookTitle = new TextField();
        textBookTitle.setBounds(160, 90, 180, 25);
        add(textBookTitle);

        labelAuthor = new Label("Author:");
        labelAuthor.setBounds(50, 130, 100, 25);
        add(labelAuthor);

        textAuthor = new TextField();
        textAuthor.setBounds(160, 130, 180, 25);
        add(textAuthor);

        labelYear = new Label("Year:");
        labelYear.setBounds(50, 170, 100, 25);
        add(labelYear);

        textYear = new TextField();
        textYear.setBounds(160, 170, 180, 25);
        add(textYear);
        
        labelSearch = new Label("Search Book:");
        labelSearch.setBounds(50, 200, 100, 25);
        add(labelSearch);
        
        textSearch = new TextField();
        textSearch.setBounds(160, 200, 180, 25);
        add(textSearch);

        btnAdd = new Button("Add");
        btnAdd.setBounds(50, 240, 80, 30);
        add(btnAdd);
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addBook();
            }
        });

        btnDisplay = new Button("Display All");
        btnDisplay.setBounds(150, 240, 100, 30);
        add(btnDisplay);
        
        btnDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                displayBooks(); 
            }
        });

        btnSearch = new Button("Search");
        btnSearch.setBounds(270, 240, 80, 30);
        add(btnSearch);
        
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                searchBook(); 
            }
        });


        textAreaDisplay = new TextArea();
        textAreaDisplay.setBounds(50, 290, 400, 100);
        textAreaDisplay.setEditable(false); 
        add(textAreaDisplay);

        connectDatabase();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    void connectDatabase() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(
    				"jdbc:mysql://localhost:3306/library",
    				"root",
    				"gowtham"
    				);
    		}catch (Exception e) {
    			textAreaDisplay.setText("Database Connection Failes:"+ e.getMessage());
    		}
    }
    

    
    void addBook() {
    	try {
    	String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
    	PreparedStatement ps = con.prepareStatement(sql);
    	ps.setString(1, textBookTitle.getText());
    	ps.setString(2, textAuthor.getText());
    	ps.setInt(3, Integer.parseInt(textYear.getText()));
    	int rows = ps.executeUpdate();
    	textAreaDisplay.setText(rows + " book(s) added.");
    	textBookTitle.setText(""); textAuthor.setText(""); textYear.setText("");
    	} catch (Exception e) {
    	textAreaDisplay.setText("Error adding book: " + e.getMessage());
    	}
    	}
    
    void displayBooks() {
    	try {
    	Statement st = con.createStatement();
    	ResultSet rs = st.executeQuery("SELECT * FROM books");
    	StringBuilder sb = new StringBuilder();
    	while (rs.next()) {
    	sb.append("ID: ").append(rs.getInt("id"))
    	.append(", Title: ").append(rs.getString("title"))
    	.append(", Author: ").append(rs.getString("author"))
    	.append(", Year: ").append(rs.getInt("year"))
    	.append("\n");

    	}
    	textAreaDisplay.setText(sb.toString());
    	} catch (Exception e) {
    	textAreaDisplay.setText("Error displaying books: " + e.getMessage());
    	}
    	}
    
    void searchBook() {
    	try {
    	String sql = "SELECT * FROM books WHERE title LIKE ?";
    	PreparedStatement ps = con.prepareStatement(sql);
    	ps.setString(1, "%" + textSearch.getText() + "%");
    	ResultSet rs = ps.executeQuery();
    	StringBuilder sb = new StringBuilder();
    	while (rs.next()) {
    	sb.append("ID: ").append(rs.getInt("id"))
    	.append(", Title: ").append(rs.getString("title"))
    	.append(", Author: ").append(rs.getString("author"))
    	.append(", Year: ").append(rs.getInt("year"))
    	.append("\n");
    	}
    	textAreaDisplay.setText(sb.length() > 0 ? sb.toString() : "No books found.");
    	} catch (Exception e) {
    	textAreaDisplay.setText("Search failed: " + e.getMessage());
    	}
    	}
    
    public static void main(String[] args) {
        new AWTsample();
    }
}
