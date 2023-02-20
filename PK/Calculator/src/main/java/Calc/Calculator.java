package Calc;

import lombok.Getter;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


public class Calculator extends JPanel implements Serializable,ActionListener,KeyListener{

    private final ArrayList<JButton> buttonList = new ArrayList<>();
    private final String[] str = {"1","2","3","+","4","5","6","-","7","8","9","*",".","0","00","/"};

    private JTextArea calculationArea;
    private JButton buttonkcal, buttonkJ,equals;
    private JPanel numberPanel,massConverterPanel,calcPanel;


    public Calculator(){
       setLayout(new BorderLayout());
       numberPanel = createNumberPanel();
       calcPanel = CreateCalcPanel();
       add(calcPanel,BorderLayout.CENTER);
       add(numberPanel,BorderLayout.SOUTH);
       massConverterPanel = createConventerPanel();
       add(massConverterPanel,BorderLayout.NORTH);
    }


    public double calculateDN(String operation){
        Expression e = new ExpressionBuilder(operation).build();
        return e.evaluate();
    }

    private JPanel CreateCalcPanel(){
        JPanel jp = new JPanel();
        jp.setBorder(new EmptyBorder(2,0,2,0));
        jp.setLayout(new BorderLayout());
        calculationArea = new JTextArea();
        calculationArea.setWrapStyleWord(true);
        calculationArea.setLineWrap(true);
        calculationArea.setText("0");
        calculationArea.addKeyListener(this);
        jp.add(calculationArea,BorderLayout.CENTER);
        equals = new JButton("=");
        equals.addActionListener(this);
        jp.add(equals,BorderLayout.NORTH);


        return jp;
    }
    private JPanel createNumberPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,4,2,2));
        for(int i = 0; i < str.length; i++) {
            JButton button = new JButton(str[i]);
            int finalI = i;
            button.addActionListener(this);
            buttonList.add(button);
            jp.add(buttonList.get(i));
        }
        return jp;
    }
    private JPanel createConventerPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2, 1, 2, 2));
        buttonkcal = new JButton("kcal");
        buttonkcal.setEnabled(false);
        buttonkJ = new JButton("kJ");
        buttonkcal.addActionListener(this);
        buttonkJ.addActionListener(this);
        jp.add(buttonkcal);
        jp.add(buttonkJ);

        return jp;
    }
    @Getter
    private Color numberPanelButtonBGColor,numberPanelButtonFontColor;
    @Getter
    private Color textAreaBGColor,textAreaFontColor;
    @Getter
    private Boolean numberPanelVisibility,massConverterVisibility;
    @Getter
    private Font textAreaFont,numberPanelButtonFont,massConverterButtonFont;
    @Getter
    private String textAreaText;
    @Getter
    private Border NPborder;


    public void setNumberPanelButtonBGColor (Color color){
        numberPanelButtonBGColor = color;
        buttonList.forEach(e->e.setBackground(numberPanelButtonBGColor));
    }
    public void setNumberPanelButtonFontColor (Color color){
        numberPanelButtonFontColor = color;
        buttonList.forEach(e->e.setForeground(numberPanelButtonFontColor));
    }
    public void setTextAreaBGColor (Color color){
        textAreaBGColor = color;
        calculationArea.setBackground(textAreaBGColor);

    }
    public void setTextAreaFontColor (Color color){
        textAreaFontColor = color;
        calculationArea.setForeground(textAreaFontColor);
    }
    public void setNumberPanelVisibility (Boolean visibility){
        numberPanelVisibility = visibility;
        numberPanel.setVisible(numberPanelVisibility);
    }
    public void setMassConverterVisibility (Boolean visibility){
        massConverterVisibility = visibility;
        massConverterPanel.setVisible(massConverterVisibility);
    }
    public void setTextAreaFont (Font font){
        textAreaFont = font;
        calculationArea.setFont(textAreaFont);
    }
    public void setNumberPanelButtonFont (Font font){
        numberPanelButtonFont = font;
        buttonList.forEach(e->e.setFont(numberPanelButtonFont));
    }
    public void setMassConverterButtonFont (Font font){
        massConverterButtonFont = font;
        buttonkcal.setFont(massConverterButtonFont);
        buttonkJ.setFont(massConverterButtonFont);

    }
    public void setTextAreaText (String text){
        textAreaText = text;
        calculationArea.setText(textAreaText);

    }
    public void setNumberPanelBorder(Border border){
        NPborder =border;
        numberPanel.setBorder(NPborder);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(equals)){
            try {
                textAreaText = String.valueOf(calculateDN(calculationArea.getText()));
                calculationArea.setText(textAreaText);
            }catch(Exception ex){
                textAreaText = "0.0";
                calculationArea.setText(textAreaText);
            }
        }else if (e.getSource().equals(buttonkcal)){
            try {
                double number = Double.parseDouble(calculationArea.getText());
                textAreaText = String.valueOf(number / 4.1868);
                calculationArea.setText(textAreaText);
                buttonkcal.setEnabled(false);
                buttonkJ.setEnabled(true);
            } catch (Exception ex) {
                textAreaText="0.0";
                calculationArea.setText(textAreaText);
                buttonkcal.setEnabled(false);
                buttonkJ.setEnabled(true);
            }
        }else if (e.getSource().equals(buttonkJ)){
            try {
                double number = Double.parseDouble(calculationArea.getText());
                textAreaText = String.valueOf(number * 4.1868);
                calculationArea.setText(textAreaText);
                buttonkcal.setEnabled(true);
                buttonkJ.setEnabled(false);
            } catch (Exception ex) {
                textAreaText="0.0";
                calculationArea.setText(textAreaText);
                buttonkcal.setEnabled(true);
                buttonkJ.setEnabled(false);
            }
        }else{
            for(int i = 0; i < str.length; i++) {
                if (e.getSource().equals(buttonList.get(i))){
                    calculationArea.append(str[i]);
                }
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            try {
                textAreaText = String.valueOf(calculateDN(calculationArea.getText()));
                calculationArea.setText(textAreaText);
            }catch(Exception ex){
                textAreaText = "0.0";
                calculationArea.setText(textAreaText);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
