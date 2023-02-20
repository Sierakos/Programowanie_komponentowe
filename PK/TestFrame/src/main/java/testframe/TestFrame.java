package testframe;

import Calc.Calculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class TestFrame extends JFrame {
    Calculator calcbase,calcmod;
    Font font1,font2,font3;

    public TestFrame(){
        setSize(700,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.GRAY);
        JPanel jp = (JPanel) this.getContentPane();
        jp.setLayout(new BorderLayout());
        jp.add(calcPanel(),BorderLayout.CENTER);
        jp.add(calcPanel(), BorderLayout.CENTER);
        this.setVisible(true);
    }

    private JPanel calcPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        calcbase = new Calculator();
        calcmod = new Calculator();
        deserialization();
        calcbase.setBorder(new LineBorder(Color.DARK_GRAY,1,true));
        calcmod.setBorder(new LineBorder(Color.RED,1,true));
        jp.add(calcbase,BorderLayout.EAST);
        jp.add(calcmod,BorderLayout.WEST);
        jp.add(createTestSettings(),BorderLayout.SOUTH);
        return jp;
    }

//    private JPanel testPanel(){
//        JPanel jp = new JPanel();
//        jp.setLayout(new BorderLayout());
//        calcmod = new Calculator();
//        deserialization();
//        calcmod.setBorder(new LineBorder(Color.RED,1,true));
//        jp.add(calcbase,BorderLayout.WEST);
//        jp.add(createTestSettings(),BorderLayout.SOUTH);
//        return jp;
//    }
    private JPanel createTestSettings(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,1));
        jp.add(fontSettingsPanel());
        jp.add(colorChangeTestPanel());
        jp.add(panelVisibilityTest());
        jp.add(setBorderTest());
        jp.setBorder(BorderFactory.createTitledBorder("test"));
        return jp;
    }

    private JPanel fontSettingsPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        font1 = new Font("Times New Roman", Font.PLAIN, 12);
        font2 = new Font("Helvetica", Font.PLAIN, 12);
        font3 = new Font("Tahoma", Font.PLAIN, 12);
        DefaultComboBoxModel<String> fonts = new DefaultComboBoxModel<>();
        fonts.addElement("Times New Roman");
        fonts.addElement("Helvetica");
        fonts.addElement("Tahoma");
        DefaultComboBoxModel<String> fonts2 = new DefaultComboBoxModel<>();
        fonts2.addElement("Times New Roman");
        fonts2.addElement("Helvetica");
        fonts2.addElement("Tahoma");
        DefaultComboBoxModel<String> fonts3 = new DefaultComboBoxModel<>();
        fonts3.addElement("Times New Roman");
        fonts3.addElement("Helvetica");
        fonts3.addElement("Tahoma");
        JComboBox numberpanelfonts = new JComboBox(fonts);
        JComboBox textareafonts = new JComboBox(fonts2);
        JComboBox massconverterfont = new JComboBox(fonts3);
        numberpanelfonts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fonts.getSelectedItem()=="Times New Roman") {
                    calcmod.setNumberPanelButtonFont(font1);
                }else if (fonts.getSelectedItem()=="Helvetica") {
                    calcmod.setNumberPanelButtonFont(font2);
                }else if (fonts.getSelectedItem()=="Tahoma") {
                    calcmod.setNumberPanelButtonFont(font3);
                }
            }
        });
        textareafonts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fonts2.getSelectedItem()=="Times New Roman") {
                    calcmod.setTextAreaFont(font1);
                }else if (fonts2.getSelectedItem()=="Helvetica") {
                    calcmod.setTextAreaFont(font2);
                }else if (fonts2.getSelectedItem()=="Tahoma") {
                    calcmod.setTextAreaFont(font3);
                }
            }
        });
        massconverterfont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fonts3.getSelectedItem()=="Times New Roman") {
                    calcmod.setMassConverterButtonFont(font1);
                }else if (fonts3.getSelectedItem()=="Helvetica") {
                    calcmod.setMassConverterButtonFont(font2);
                }else if (fonts3.getSelectedItem()=="Tahoma") {
                    calcmod.setMassConverterButtonFont(font3);
                }
            }
        });
        jp.add(new JLabel("ButtonFont"));
        jp.add(numberpanelfonts);
        jp.add(new JLabel("ScreenFont"));
        jp.add(textareafonts);
        jp.add(new JLabel("UnitFont"));
        jp.add(massconverterfont);

        return jp;
    }
    private JPanel colorChangeTestPanel(){
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(2,2,2,2));
        JPanel jp2 = new JPanel();
        jp2.setLayout(new GridLayout(3,1));
        JPanel jp3 = new JPanel();
        jp3.setLayout(new FlowLayout());
        JSlider sliderR = new JSlider(0,255,0);
        JSlider sliderG = new JSlider(0,255,0);
        JSlider sliderB = new JSlider(0,255,0);
        JButton TABGColor = new JButton("ScreenColor");
        JButton TAFontColor = new JButton("ScreenFontColor");
        JButton NPButtonBGColor = new JButton("ButtonGColor");
        JButton NPButtonFontColor = new JButton("ButtonFontColor");
        TABGColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = sliderR.getValue();
                int g = sliderG.getValue();
                int b = sliderB.getValue();
                calcmod.setTextAreaBGColor(new Color(r,g,b));
            }
        });
        TAFontColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = sliderR.getValue();
                int g = sliderG.getValue();
                int b = sliderB.getValue();
                calcmod.setTextAreaFontColor((new Color(r,g,b)));
            }
        });
        NPButtonBGColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = sliderR.getValue();
                int g = sliderG.getValue();
                int b = sliderB.getValue();
                calcmod.setNumberPanelButtonBGColor((new Color(r,g,b)));
            }
        });
        NPButtonFontColor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int r = sliderR.getValue();
                int g = sliderG.getValue();
                int b = sliderB.getValue();
                calcmod.setNumberPanelButtonFontColor((new Color(r,g,b)));

            }
        });
        jp.add(TABGColor);
        jp.add(TAFontColor);
        jp.add(NPButtonBGColor);
        jp.add(NPButtonFontColor);
        jp2.add(sliderPanel(sliderR,"R:"));
        jp2.add(sliderPanel(sliderG,"G:"));
        jp2.add(sliderPanel(sliderB,"B:"));
        jp3.add(jp2);
        jp3.add(jp);
        return jp3;
    }
    private JPanel sliderPanel(JSlider colorSlider,String nazwaa){
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        JLabel nazwa = new JLabel(nazwaa);
        JLabel numer = new JLabel("0");
        JSlider slider = colorSlider;
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                numer.setText(String.valueOf(slider.getValue()));
            }
        });
        jp.add(nazwa,BorderLayout.WEST);
        jp.add(slider,BorderLayout.CENTER);
        jp.add(numer,BorderLayout.EAST);
        return jp;
    }
    private JPanel setTextTest(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        JTextArea ta = new JTextArea();
        ta.setPreferredSize(new Dimension(50,22));
        JButton button = new JButton("wstaw");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = ta.getText();
                calcmod.setTextAreaText(text);
            }
        });
        jp.add(ta);
        jp.add(button);
        return jp;
    }
    private JPanel panelVisibilityTest(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        JCheckBox numberPanelVisibility = new JCheckBox("number panel", true);
        JCheckBox massConverterVisibility = new JCheckBox("unit converter", true);
        numberPanelVisibility.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int i = e.getStateChange();
                if (i == 1){
                    calcmod.setNumberPanelVisibility(true);
                }else{
                    calcmod.setNumberPanelVisibility(false);
                }
            }
        });
        massConverterVisibility.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int i = e.getStateChange();
                if (i == 1){
                    calcmod.setMassConverterVisibility(true);
                }else{
                    calcmod.setMassConverterVisibility(false);
                }
            }
        });
        jp.add(numberPanelVisibility);
        jp.add(massConverterVisibility);
        return jp;
    }
    private JPanel setBorderTest(){
        JPanel jp = new JPanel();
        jp.setLayout(new FlowLayout());
        DefaultComboBoxModel<String> border= new DefaultComboBoxModel<>();
        border.addElement("dashed, blue, 10px");
        border.addElement("beveled");
        border.addElement("compound");
        border.addElement("none");
        JComboBox numberpanelborder = new JComboBox(border);
        numberpanelborder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (border.getSelectedItem().equals("dashed, blue, 10px")){
                calcmod.setNumberPanelBorder(BorderFactory.createDashedBorder(Color.blue, 5, 10, 10, true));
            }else if (border.getSelectedItem().equals("beveled")){
                calcmod.setNumberPanelBorder(BorderFactory.createRaisedBevelBorder());
            }else if (border.getSelectedItem().equals("compound")){
                calcmod.setNumberPanelBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLoweredBevelBorder(),
                        BorderFactory.createRaisedBevelBorder()
                ));
            }else if (border.getSelectedItem().equals("none")){
                calcmod.setNumberPanelBorder(null);
            }

            }
        });
        jp.add(numberpanelborder);
        return jp;
    }



    private void deserialization(){
        try {
            FileInputStream fileIn = new FileInputStream("src/main/resources/calcmod.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            calcmod = (Calculator) in.readObject();
            in.close();
            fileIn.close();
        } catch (Exception e) {
            calcmod = new Calculator();
        }
    }
    @Override
    public void removeNotify() {
        super.removeNotify();
        try {
            FileOutputStream fileOut = new FileOutputStream("src/main/resources/calcmod.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(calcmod);
            out.close();
            fileOut.close();
        } catch (IOException i) {
        }
    }
}
