/*
 * Created by JFormDesigner on Mon Oct 07 06:56:21 CST 2024
 */

package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import lib.crack;

/**
 * @author 84151
 */
public class breaker extends JFrame {
    public breaker() {
        initComponents();
    }

    private void ok_bt(ActionEvent e) {
        // TODO add your code here
        /**
         * long startTime = System.currentTimeMillis();
         * long endTime = System.currentTimeMillis();
         * long executionTime = endTime - startTime;
         *  用以计算程序运行时间
         * */
        String plainText = plain.getText();
        String cyhperText = cypher.getText();

        crack cr = new crack(plainText,cyhperText);

        long startTime = System.currentTimeMillis();
        cr.begin_crack();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        key.setText(cr.key);
        time.setText(executionTime+"毫秒");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        panel2 = new JPanel();
        label2 = new JLabel();
        plain = new JTextField();
        panel3 = new JPanel();
        label3 = new JLabel();
        cypher = new JTextField();
        panel4 = new JPanel();
        label4 = new JLabel();
        scrollPane1 = new JScrollPane();
        key = new JTextArea();
        panel5 = new JPanel();
        label5 = new JLabel();
        time = new JTextArea();
        ok_bt = new JButton();
        label1 = new JLabel();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(1, 2));

        //======== panel1 ========
        {
            panel1.setLayout(new GridLayout(5, 1, 0, 30));

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout(1, 2));

                //---- label2 ----
                label2.setText("\u8f93\u5165\u660e\u6587");
                label2.setHorizontalAlignment(SwingConstants.CENTER);
                panel2.add(label2);
                panel2.add(plain);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout(1, 2));

                //---- label3 ----
                label3.setText("\u8f93\u5165\u5bc6\u6587");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                panel3.add(label3);
                panel3.add(cypher);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout(1, 2));

                //---- label4 ----
                label4.setText("\u5bc6\u94a5\u7834\u8bd1\u7ed3\u679c");
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                panel4.add(label4);

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(key);
                }
                panel4.add(scrollPane1);
            }
            panel1.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout(1, 2));

                //---- label5 ----
                label5.setText("\u8017\u65f6");
                label5.setHorizontalAlignment(SwingConstants.CENTER);
                panel5.add(label5);
                panel5.add(time);
            }
            panel1.add(panel5);

            //---- ok_bt ----
            ok_bt.setText("OK");
            ok_bt.addActionListener(e -> ok_bt(e));
            panel1.add(ok_bt);
        }
        contentPane.add(panel1);

        //---- label1 ----
        label1.setIcon(new ImageIcon(getClass().getResource("/right1.jpg")));
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(100, 338));
        contentPane.add(label1);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setTitle("Key-Cracking");
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label2;
    private JTextField plain;
    private JPanel panel3;
    private JLabel label3;
    private JTextField cypher;
    private JPanel panel4;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JTextArea key;
    private JPanel panel5;
    private JLabel label5;
    private JTextArea time;
    private JButton ok_bt;
    private JLabel label1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

}
