import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

// this is a MyFrame child class of JFrame superclass

public class MyFrame extends JFrame implements ActionListener{
    private static JLabel titleLabel;
    private static JTextField task;
    private static JButton addButton;
    private static JLabel inOrvalidTask;
    private static JTable taskTable;
    private static DefaultTableModel model;
    private static JScrollPane scrollPane;
    
    MyFrame(){

        // frame
        ImageIcon image = new ImageIcon("todo.png");
        this.setIconImage(image.getImage());

        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); 
        setSize(500, 1000); 
        getContentPane().setBackground(Color.WHITE); 
        setLayout(null); 
    
        // title label
        titleLabel = new JLabel("To-Do List");
        
        ImageIcon labelImage = new ImageIcon("todo.png");
        Image originalLabelImage = labelImage.getImage();

        int newWidth = 220;
        int newHeight = 220;

        ImageIcon resizedIconlabelImage = new ImageIcon(originalLabelImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
        titleLabel.setIcon(resizedIconlabelImage);
        titleLabel.setIconTextGap(10);

        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setBounds(100, 50, 300, 300);

        Font font = new Font("Arial", Font.BOLD, 28);
        titleLabel.setFont(font);
        add(titleLabel);

        // task textField
        task = new JTextField();
        Border taskBorder = BorderFactory.createLineBorder(Color.lightGray, 2);
        add(task);
        
        task.setBorder(taskBorder);
        task.setBounds(100, 400, 300, 25);

        // add button
        addButton = new JButton("Add New Task");
        addButton.setBounds(200, 450, 100, 30);
        addButton.setBackground(new Color(174, 214, 241));
        addButton.setForeground(Color.BLACK);

        Border buttonBorder = BorderFactory.createLineBorder(Color.black, 2);
        addButton.setBorder(buttonBorder);
 
        addButton.addActionListener(this);
        add(addButton);

        // inOrvalidTask label
        inOrvalidTask = new JLabel("");
        inOrvalidTask.setForeground(Color.BLACK);
        inOrvalidTask.setBounds(175, 195, 300, 300);
        
        Font inOrvalidTaskFont = new Font("Arial", Font.BOLD, 12);
        inOrvalidTask.setFont(inOrvalidTaskFont);
        add(inOrvalidTask);

        // taskTable and customization
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        taskTable = new JTable();
        model = new DefaultTableModel();

        model.addColumn("Task Name");
        model.addColumn("Status (Done, To Do)");

        taskTable.setModel(model);
        
        scrollPane = new JScrollPane(taskTable);
        scrollPane.setBounds(50, 500, 400, 400);
        add(scrollPane);

        JTableHeader header = taskTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.lightGray);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        taskTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        taskTable.setRowHeight(30);

        taskTable.setFont(new Font("Arial", Font.PLAIN, 12));
        taskTable.setSelectionBackground(new Color(173, 216, 230)); // Light Blue for selection

        taskTable.setShowGrid(true);
        taskTable.setGridColor(Color.lightGray);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String newTask = task.getText();
        if(newTask.equals("")){
            inOrvalidTask.setText("Please enter a valid task");
            task.setText("");
        }else{
            model.addRow(new Object[]{newTask, ""});

            inOrvalidTask.setText("Task successfully added");
            inOrvalidTask.setBounds(180, 195, 300, 300);
            task.setText("");
        }
    }
}
