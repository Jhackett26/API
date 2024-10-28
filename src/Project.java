import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Project implements ActionListener {
    private JFrame mainFrame;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JTextArea nameArea;
    private JTextArea affiliationArea;
    private JTextArea alliesArea;
    private JTextArea enemiesArea;
    private JLabel nameLabel;
    private JButton randomEnter;
    private JButton enter;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    String name = "";


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
        nameArea = new JTextArea(name);
        affiliationArea = new JTextArea("Affiliation: ");
        alliesArea = new JTextArea("Allies: \n");
        enemiesArea = new JTextArea("Enemies: \n");
        nameLabel = new JLabel("NAME:", JLabel.CENTER);
        randomEnter = new JButton("RANDOMIZE");
        enter = new JButton("ENTER");

        affiliationArea.setEditable(false);
        alliesArea.setEditable(false);
        enemiesArea.setEditable(false);


        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2, 2));
        topPanel.setLayout(new GridLayout(5, 1));
        bottomPanel.setLayout(new GridLayout(1, 2));
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
        topPanel.add(nameLabel);
        topPanel.add(nameArea);
        topPanel.add(enter);
        topPanel.add(randomEnter);
        topPanel.add(affiliationArea);
        bottomPanel.add(alliesArea);
        bottomPanel.add(enemiesArea);

    }

    private void showEventDemo() {
        enter.setActionCommand("ENTER");
        enter.addActionListener(new ButtonClickListener());

        randomEnter.setActionCommand("RANDOM");
        randomEnter.addActionListener(new ButtonClickListener());

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            ReadJson reader = new ReadJson();
            try {
                reader.pull();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
            if (command.equals("ENTER")) {
                name = nameArea.getText();


            }
            if(command.equals("RANDOM")) {
                int randIndex = (int)(Math.random()*20);
                alliesArea.setText("Allies: \n");
                enemiesArea.setText("Enemies: \n");

                nameArea.setText(reader.characters[randIndex].name);
                if (reader.characters[randIndex].affiliation != null) {
                    affiliationArea.setText("Affiliation: " + reader.characters[randIndex].affiliation);
                }
                else{
                    affiliationArea.setText("No affiliation");
                }
                for (int i =0; i < reader.characters[randIndex].allies.size();i++){
                    alliesArea.append((String) reader.characters[randIndex].allies.get(i) +"\n");
                }
                if(reader.characters[randIndex].allies != null) {
                    for (int i = 0; i < reader.characters[randIndex].allies.size(); i++) {
                        alliesArea.append((String) reader.characters[randIndex].allies.get(i) + "\n");
                    }
                }
                if(reader.characters[randIndex].enemies != null) {
                    for (int i = 0; i < reader.characters[randIndex].enemies.size(); i++) {
                        enemiesArea.append((String) reader.characters[randIndex].enemies.get(i) + "\n");
                    }
                }
            }
        }
    }
}
