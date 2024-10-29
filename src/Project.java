import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel LRpanel;
    private JButton left;
    private JButton right;
    private JTextArea nameArea;
    private JTextArea affiliationArea;
    private JTextArea alliesArea;
    private JTextArea enemiesArea;
    private JLabel nameLabel;
    private JButton randomEnter;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    int index = 0;


    public Project() {
        prepareGUI();
    }

    public static void main(String[] args) {
        Project swingControlDemo = new Project();

        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame();
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        LRpanel = new JPanel();
        left = new JButton("<--");
        right = new JButton("-->");
        nameArea = new JTextArea("Name: ");
        affiliationArea = new JTextArea("Affiliation: ");
        alliesArea = new JTextArea("Allies: \n");
        enemiesArea = new JTextArea("Enemies: \n");
        randomEnter = new JButton("RANDOMIZE");

        nameArea.setEditable(false);
        affiliationArea.setEditable(false);
        alliesArea.setEditable(false);
        enemiesArea.setEditable(false);


        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2, 2));
        topPanel.setLayout(new GridLayout(4, 1));
        bottomPanel.setLayout(new GridLayout(1, 2));
        LRpanel.setLayout(new GridLayout(1,2));
        mainFrame.setVisible(true);

        nameArea.setLineWrap(true);
        nameArea.setWrapStyleWord(true);
        affiliationArea.setLineWrap(true);
        affiliationArea.setWrapStyleWord(true);
        alliesArea.setLineWrap(true);
        alliesArea.setWrapStyleWord(true);
        enemiesArea.setLineWrap(true);
        enemiesArea.setWrapStyleWord(true);

        mainFrame.add(topPanel);
        mainFrame.add(bottomPanel);
        topPanel.add(nameArea);
        topPanel.add(LRpanel);
        LRpanel.add(left);
        LRpanel.add(right);
        topPanel.add(randomEnter);
        topPanel.add(affiliationArea);
        bottomPanel.add(alliesArea);
        bottomPanel.add(enemiesArea);

    }

    private void showEventDemo() {

        left.setActionCommand("LEFT");
        right.setActionCommand("RIGHT");
        randomEnter.setActionCommand("RANDOM");
        randomEnter.addActionListener(new ButtonClickListener());
        left.addActionListener(new ButtonClickListener());
        right.addActionListener(new ButtonClickListener());


        mainFrame.setVisible(true);
        setText(index);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("LEFT")) {
                index -=1;
                if (index <0){
                    index = 19;
                }
            }
            else if (command.equals("RIGHT")){
                index +=1;
                if(index>19){
                    index = 0;
                }
            }
            else if(command.equals("RANDOM")) {
                index =(int)(Math.random()*20);

            }
            setText(index);
        }
    }
    public void setText(int index){
        ReadJson reader = new ReadJson();
        try {
            reader.pull();
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        alliesArea.setText("Allies: \n");
        enemiesArea.setText("Enemies: \n");
        nameArea.setText("Name: "+reader.characters[index].name);
        if (reader.characters[index].affiliation != null) {
            affiliationArea.setText("Affiliation: " + reader.characters[index].affiliation);
        }
        else{
            affiliationArea.setText("Affiliation: No affiliation");
        }
        if(reader.characters[index].allies != null) {
            for (int i = 0; i < reader.characters[index].allies.size(); i++) {
                alliesArea.append((String) reader.characters[index].allies.get(i) + "\n");
            }
        }
        if(reader.characters[index].enemies != null) {
            for (int i = 0; i < reader.characters[index].enemies.size(); i++) {
                enemiesArea.append((String) reader.characters[index].enemies.get(i) + "\n");
            }
        }
    }
}
