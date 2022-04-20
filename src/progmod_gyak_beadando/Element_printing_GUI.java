package progmod_gyak_beadando;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Element_printing_GUI implements ActionListener, ItemListener{
    
    public static String elérési_út = "ez nem az ám";
    
    JFrame window = new JFrame("Tranzakció keresése az adatbázisban");
    JTextField inputitemTextField = new JTextField();
    JLabel inputitemLabel = new JLabel("Kérem adja meg a tartalmazandó szövegrészt: ", JLabel.CENTER);
    JLabel mitLabel = new JLabel("Kérem válaszon a legördülő menüből: ", JLabel.CENTER);
    JButton signUpButton = new JButton("Keresés");
    JLabel blank = new JLabel();
    FileWriter fileWriter;
    
    JComboBox < String > combo = new JComboBox < > ();
    
    
    Element_printing_GUI() {
        
        GridLayout g1 = new GridLayout();
        //g1.setColumns(2);
        g1.setRows(3);
        
        window.setLayout(g1);
        
        signUpButton.addActionListener(this);
        
        window.add(mitLabel);

        combo.addItem("pénzforgalom típusa");
        combo.addItem("dátum");
        combo.addItem("összeg");
        combo.addItem("leírás");
        
        //combo.addItemListener(this);
        window.add(combo);
            
        window.add(inputitemLabel);
        window.add(inputitemTextField);
        
        window.add(blank);
        
        window.add(signUpButton);
        
        window.setSize(600,300);

        
        window.setVisible(true);
    }   
    
    Properties properties = new Properties();
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals(signUpButton.getActionCommand()))
        {
            //String a = "";
            try {
                if (combo.getSelectedItem().equals("pénzforgalom típusa")){
                    //
                    elérési_út = "//transaction[contains(type,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals("dátum")) {
                    elérési_út = "//transaction[contains(date,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals("összeg")) {
                    elérési_út = "//transaction[contains(price,'"+inputitemTextField.getText()+"')]/azon/text()";
                } 
                if (combo.getSelectedItem().equals("leírás")) {
                    elérési_út = "//transaction[contains(description,'"+inputitemTextField.getText()+"')]/azon/text()";
                }

                Element_printing.Element_printing_function();
 
                //JOptionPane.showMessageDialog(null, "File Written Succesfully!"); 
                
            } catch (HeadlessException e) {
                JOptionPane.showMessageDialog(null, e+"");
            } catch (Exception ex) {
                Logger.getLogger(Element_remove_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}