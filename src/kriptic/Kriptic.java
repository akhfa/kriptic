/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kriptic;

import com.google.common.base.Splitter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author akhfa
 */
public class Kriptic extends javax.swing.JFrame {

    String result;
    JFileChooser fc;
    /**
     * Creates new form Kriptic
     */
    public Kriptic() {
        initComponents();
        vigenere_basic_radio.setSelected(true);
        vigenere_basic_radio.setActionCommand("vigenere_basic");
        vigenere_ext_radio.setActionCommand("vigenere_ext");
        playfair_radio.setActionCommand("playfair");
        Enkripsi_grup.add(vigenere_basic_radio);
        Enkripsi_grup.add(vigenere_ext_radio);
        Enkripsi_grup.add(playfair_radio);
        ImageIcon img = new ImageIcon("images/lock.png");
        this.setIconImage(img.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Enkripsi_grup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        Asal_text = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Hasil_text = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Encrypt_button = new javax.swing.JButton();
        Decrypt_button = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Kunci_textbox = new javax.swing.JTextField();
        vigenere_basic_radio = new javax.swing.JRadioButton();
        vigenere_ext_radio = new javax.swing.JRadioButton();
        playfair_radio = new javax.swing.JRadioButton();
        show_combobox = new javax.swing.JComboBox<>();
        Open_button = new javax.swing.JButton();
        Save_button = new javax.swing.JButton();
        Exit_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kriptic");
        setResizable(false);

        Asal_text.setColumns(20);
        Asal_text.setLineWrap(true);
        Asal_text.setRows(5);
        jScrollPane1.setViewportView(Asal_text);

        Hasil_text.setEditable(false);
        Hasil_text.setBackground(new java.awt.Color(204, 204, 204));
        Hasil_text.setColumns(20);
        Hasil_text.setLineWrap(true);
        Hasil_text.setRows(5);
        jScrollPane2.setViewportView(Hasil_text);

        jLabel1.setText("Text (Plain / Chiper)");

        jLabel2.setText("Hasil");

        Encrypt_button.setText("Encrypt");
        Encrypt_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Encrypt_buttonActionPerformed(evt);
            }
        });

        Decrypt_button.setText("Decrypt");
        Decrypt_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Decrypt_buttonActionPerformed(evt);
            }
        });

        jLabel3.setText("Kunci");

        vigenere_basic_radio.setText("Vigenere");

        vigenere_ext_radio.setText("Vigenere Ext");

        playfair_radio.setText("Playfair");

        show_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Standard", "Tanpa spasi", "5 huruf" }));
        show_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_comboboxActionPerformed(evt);
            }
        });

        Open_button.setText("Open File");
        Open_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Open_buttonActionPerformed(evt);
            }
        });

        Save_button.setText("Save");
        Save_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Save_buttonActionPerformed(evt);
            }
        });

        Exit_button.setText("Exit");
        Exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Exit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Open_button))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Kunci_textbox))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vigenere_basic_radio)
                                            .addComponent(vigenere_ext_radio, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(playfair_radio))
                                    .addComponent(Encrypt_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Decrypt_button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(Save_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(show_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(Exit_button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Open_button))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vigenere_basic_radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vigenere_ext_radio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(playfair_radio)
                        .addGap(18, 18, 18)
                        .addComponent(Encrypt_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Decrypt_button))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Kunci_textbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(show_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Save_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Exit_button))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Encrypt_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Encrypt_buttonActionPerformed
        // TODO add your handling code here:
        if(Kunci_textbox.getText().length() == 0 || Asal_text.getText().length() == 0 || 
                Kunci_textbox.getText().length() >= 25)
            JOptionPane.showMessageDialog(Kriptic.this, "Kunci terlalu panjang. Maksimal hanya 25 karakter");
        else
        {
            if(Enkripsi_grup.getSelection().getActionCommand().compareTo("vigenere_basic") == 0)
            {
                Vigenere vigenere = new Vigenere(Asal_text.getText(), Kunci_textbox.getText(), true);
                result = vigenere.encrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else if(Enkripsi_grup.getSelection().getActionCommand().compareTo("vigenere_ext") == 0)
            {
                Vigenere vigenere = new Vigenere(Asal_text.getText(), Kunci_textbox.getText(), false);
                result = vigenere.encrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else if(Enkripsi_grup.getSelection().getActionCommand().compareTo("playfair") == 0)
            {
                Playfair playfair = new Playfair(Asal_text.getText(), Kunci_textbox.getText());
                result = playfair.encrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else
                JOptionPane.showMessageDialog(null, "salah");
        }
    }//GEN-LAST:event_Encrypt_buttonActionPerformed

    private void Decrypt_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Decrypt_buttonActionPerformed
        // TODO add your handling code here:
        if(Kunci_textbox.getText().length() == 0 || Asal_text.getText().length() == 0 || 
                Kunci_textbox.getText().length() >= 25)
            JOptionPane.showMessageDialog(Kriptic.this, "Kunci terlalu panjang. Maksimal hanya 25 karakter");
        else
        {
            if(Enkripsi_grup.getSelection().getActionCommand().compareTo("vigenere_basic") == 0)
            {
                Vigenere vigenere = new Vigenere(Asal_text.getText(), Kunci_textbox.getText(), true);
                result = vigenere.decrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else if(Enkripsi_grup.getSelection().getActionCommand().compareTo("vigenere_ext") == 0)
            {
                Vigenere vigenere = new Vigenere(Asal_text.getText(), Kunci_textbox.getText(), false);
                result = vigenere.decrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else if(Enkripsi_grup.getSelection().getActionCommand().compareTo("playfair") == 0)
            {
                Playfair playfair = new Playfair(Asal_text.getText(), Kunci_textbox.getText());
                result = playfair.decrypt();
                String value = show_combobox.getSelectedItem().toString();
                this.setOutput(value);
            }
            else
                JOptionPane.showMessageDialog(null, "salah");
        }
    }//GEN-LAST:event_Decrypt_buttonActionPerformed

    private void show_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_comboboxActionPerformed
        // TODO add your handling code here:
        String value = show_combobox.getSelectedItem().toString();
        this.setOutput(value);
    }//GEN-LAST:event_show_comboboxActionPerformed

    private void Open_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Open_buttonActionPerformed
        // TODO add your handling code here:
        fc = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(workingDirectory);
        int returnval = fc.showOpenDialog(Kriptic.this);
        if(evt.getSource() == Open_button)
        {
            if(returnval == JFileChooser.APPROVE_OPTION)
            {
                File file = fc.getSelectedFile();
                String text = "";
                try (BufferedReader br = new BufferedReader(new FileReader(file))){
                    String temp;
                    while ((temp = br.readLine()) != null)
                    {
                        text += temp + "\n";
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Kriptic.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Kriptic.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    Asal_text.setText(text);
                }
            }
            
        }
    }//GEN-LAST:event_Open_buttonActionPerformed

    private void Save_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Save_buttonActionPerformed
        // TODO add your handling code here:
        if(Hasil_text.getText() == null)
            JOptionPane.showMessageDialog(Kriptic.this, "Tidak ada yang bisa disimpan. Harap lakukan enkripsi dulu.");
        else
        {
            fc = new JFileChooser();
            File workingDirectory = new File(System.getProperty("user.dir"));
            fc.setCurrentDirectory(workingDirectory);
            int returnval = fc.showSaveDialog(Kriptic.this);
            if(returnval == JFileChooser.APPROVE_OPTION)
            {
                try(FileWriter fw = new FileWriter(fc.getSelectedFile())){
                    fw.write(Hasil_text.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Kriptic.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_Save_buttonActionPerformed

    private void Exit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Exit_buttonActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Exit_buttonActionPerformed
    
    private void setOutput(String value)
    {
        if(result!=null)
        {
            if(value.equals("Standard"))
                Hasil_text.setText(this.result);
            else if(value.equals("Tanpa spasi"))
                Hasil_text.setText(this.removeAllSpace());
            else 
            {
                Hasil_text.setText(this.five_words());
            }
        }
    }
    
    private String removeAllSpace()
    {
        return result.replaceAll(" ", "");
    }
    
    private String five_words()
    {
        String show = "";
        Iterable<String> pieces = Splitter.fixedLength(5).split(this.result.replaceAll(" ", ""));
        for (String piece : pieces) {
            show = show + piece + " ";
        }
        show = show.substring(0, show.length() - 1);
        return show;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Kriptic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kriptic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kriptic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kriptic.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kriptic().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Asal_text;
    private javax.swing.JButton Decrypt_button;
    private javax.swing.JButton Encrypt_button;
    private javax.swing.ButtonGroup Enkripsi_grup;
    private javax.swing.JButton Exit_button;
    private javax.swing.JTextArea Hasil_text;
    private javax.swing.JTextField Kunci_textbox;
    private javax.swing.JButton Open_button;
    private javax.swing.JButton Save_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton playfair_radio;
    private javax.swing.JComboBox<String> show_combobox;
    private javax.swing.JRadioButton vigenere_basic_radio;
    private javax.swing.JRadioButton vigenere_ext_radio;
    // End of variables declaration//GEN-END:variables
}
