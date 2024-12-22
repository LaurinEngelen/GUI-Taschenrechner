import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Hauptfenster extends JFrame implements ActionListener {

    JTextField xTextField;
    JTextField yTextField;
    JTextField resTextField;
    JButton sumButton;
    JButton mulButton;
    JButton subButton;
    JButton difButton;
    JButton sinButton;
    JButton cosButton;
    JButton potyButton;
    JButton log2Button;
    JRadioButton degRadioButton;
    JRadioButton radRadioButton;
    JCheckBox displayModeCheck;
    StringBuffer choices;
    JButton clearButton;

    public void itemStateChange(ItemEvent e){

    }

    public Hauptfenster(){
        this.setTitle("Taschenrechner");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400,400));
        this.setBackground(Color.lightGray);

        //Panel Eingabefeld

        JLabel xLabel = new JLabel(("Operand x"));
        JLabel yLabel = new JLabel(("Operand y"));
        JLabel resLabel = new JLabel(("Resultat"));
        xTextField = new JTextField("0", 10);
        yTextField = new JTextField("0", 10);
        resTextField = new JTextField("0", 10);
        resTextField.setEditable(false);

        JPanel eingabefeld = new JPanel();
        eingabefeld.setLayout(new GridLayout(3,2));
        eingabefeld.setBorder(BorderFactory.createLineBorder(Color.black));
        eingabefeld.add(xLabel);
        eingabefeld.add(xTextField);
        eingabefeld.add(yLabel);
        eingabefeld.add(yTextField);
        eingabefeld.add(resLabel);
        eingabefeld.add(resTextField);


        //Panel Einstellknoepfe

        degRadioButton = new JRadioButton("Deg");
        degRadioButton.setSelected(true);
        radRadioButton = new JRadioButton("Rad");
        radRadioButton.setSelected(false);
        displayModeCheck = new JCheckBox("Helles Display");
        displayModeCheck.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(radRadioButton);
        group.add(degRadioButton);

        radRadioButton.addActionListener(this);
        degRadioButton.addActionListener(this);
        displayModeCheck.addActionListener(this);

        JPanel einstellknoepfe = new JPanel();
        einstellknoepfe.setLayout((new GridLayout(1,3)));
        einstellknoepfe.setBorder(BorderFactory.createEmptyBorder(0,30,0,30));
        einstellknoepfe.add(degRadioButton);
        einstellknoepfe.add(radRadioButton);
        einstellknoepfe.add(displayModeCheck);

        //Panel Operatoren

        sumButton = new JButton("+");
        mulButton = new JButton("*");
        subButton = new JButton("-");
        difButton = new JButton("/");
        sinButton = new JButton("sin");
        cosButton = new JButton("cos");
        potyButton = new JButton("x^y");
        log2Button = new JButton("log2");

        sumButton.addActionListener(this);
        mulButton.addActionListener(this);
        subButton.addActionListener(this);
        difButton.addActionListener(this);
        sinButton.addActionListener(this);
        cosButton.addActionListener(this);
        potyButton.addActionListener(this);
        log2Button.addActionListener(this);

        JPanel operatoren = new JPanel();
        operatoren.setLayout(new GridLayout(2,4));
        operatoren.setBorder(BorderFactory.createLineBorder(Color.black));
        operatoren.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        operatoren.add(sumButton);
        operatoren.add(mulButton);
        operatoren.add(subButton);
        operatoren.add(difButton);
        operatoren.add(sinButton);
        operatoren.add(cosButton);
        operatoren.add(potyButton);
        operatoren.add(log2Button);

        //Panel ClearTaste

        clearButton = new JButton("Clear");

        clearButton.addActionListener(this);

        JPanel clearTaste = new JPanel();
        clearTaste.add(clearButton);

        // Hauptfenster zusammenbauen:
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(eingabefeld);
        panel.add(einstellknoepfe);
        panel.add(operatoren);
        panel.add(clearTaste);
        setContentPane(panel);

        // Hauptfenster ausgeben:
        this.pack();
        this.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String xs = xTextField.getText();
        String ys = yTextField.getText();

        if(source == clearButton) {
            xTextField.setText("0");
            yTextField.setText("0");
            resTextField.setText("0");
            return;
        }

        double xd = 0;
        double yd = 0;

        try {
            xd = Double.parseDouble(xs);
            yd = Double.parseDouble(ys);
        } catch (NumberFormatException NE){
            resTextField.setText("Zahlen angeben!");
            return;
        }




        if (!radRadioButton.isSelected()) {
            if (source == sinButton) {
                resTextField.setText("" + (Math.sin(Math.toRadians(xd))));
                yTextField.setText("0");
            } else if (source == cosButton) {
                resTextField.setText("" + (Math.cos(Math.toRadians(xd))));
                yTextField.setText("0");
            }
        } else {
            if (source == sinButton) {
                resTextField.setText("" + (Math.sin(xd)));
                yTextField.setText("0");
            } else if (source == cosButton) {
                resTextField.setText("" + (Math.cos(xd)));
                yTextField.setText("0");
            }
        }


        if(source == sumButton) {
            resTextField.setText("" + (xd +yd));
        } else if(source == mulButton){
            resTextField.setText("" + (xd *yd));
        } else if(source == subButton){
            resTextField.setText("" + (xd -yd));
        } else if(source == difButton){
            if (yd == 0) {
                resTextField.setText("error");
            } else {
                resTextField.setText("" + (xd / yd));
            }
        } else if(source == potyButton){
            resTextField.setText("" + (Math.pow(xd, yd)));
        } else if(source == log2Button){
            resTextField.setText("" + (Math.log(xd)));
            yTextField.setText("0");
        }

        if(!displayModeCheck.isSelected()){
            xTextField.setBackground(Color.BLACK);
            yTextField.setBackground(Color.BLACK);
            resTextField.setBackground(Color.BLACK);
            xTextField.setForeground(Color.YELLOW);
            yTextField.setForeground(Color.YELLOW);
            resTextField.setForeground(Color.YELLOW);
        } else {
            xTextField.setBackground(Color.WHITE);
            yTextField.setBackground(Color.WHITE);
            resTextField.setBackground(Color.WHITE);
            xTextField.setForeground(Color.BLACK);
            yTextField.setForeground(Color.BLACK);
            resTextField.setForeground(Color.BLACK);
        }
    }
}
