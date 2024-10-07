/*
 * Created by JFormDesigner on Sun Oct 06 20:17:15 CST 2024
 */

package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import lib.S_DES;
import lib.S_DES_Enhanced;

/**
 * @author 84151
 */
public class s_des extends JFrame {
    public s_des() {
        initComponents();
    }

    private void click_ok(ActionEvent e) {
        // TODO add your code here
        String key = key_field.getText();
        String plainText = plainText_field.getText();

        /**  通过设置标志位flag来判断输入的明文是字符还是数字
         *   并据此选择合适的类
         * */

        int flag=0;
        for (char c : plainText.toCharArray()) {
            // 如果字符不是'0'也不是'1'，则返回false
            if (c != '0' && c != '1') {
                flag=1;
            }
        }

        if(flag==0){
            S_DES s_des = new S_DES(key, plainText, "en");
            s_des.key_generate();
            cypherText_field.setText(s_des.encrypt());
        }
        if(flag==1){
            S_DES_Enhanced s_des_en = new S_DES_Enhanced(key, plainText, "en");
            cypherText_field.setText(s_des_en.encrypt());
        }
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        key_field = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        plainText_field = new JTextField();
        panel3 = new JPanel();
        label4 = new JLabel();
        cypherText_field = new JTextPane();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(4, 1, 0, 30));

                //---- label1 ----
                label1.setIcon(new ImageIcon(getClass().getResource("/eye2.jpg")));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);

                //======== panel1 ========
                {
                    panel1.setPreferredSize(new Dimension(126, 80));
                    panel1.setMinimumSize(new Dimension(126, 80));
                    panel1.setLayout(new GridLayout(1, 2));

                    //---- label2 ----
                    label2.setText("\u8bf7\u8f93\u5165\u5bc6\u94a5");
                    panel1.add(label2);

                    //---- key_field ----
                    key_field.setMaximumSize(new Dimension(2147483647, 15));
                    panel1.add(key_field);
                }
                contentPanel.add(panel1);

                //======== panel2 ========
                {
                    panel2.setPreferredSize(new Dimension(126, 15));
                    panel2.setLayout(new GridLayout(1, 2));

                    //---- label3 ----
                    label3.setText("\u8bf7\u8f93\u5165\u6570\u636e");
                    panel2.add(label3);
                    panel2.add(plainText_field);
                }
                contentPanel.add(panel2);

                //======== panel3 ========
                {
                    panel3.setPreferredSize(new Dimension(98, 15));
                    panel3.setLayout(new GridLayout(1, 2));

                    //---- label4 ----
                    label4.setText("\u751f\u6210\u5bc6\u6587");
                    panel3.add(label4);
                    panel3.add(cypherText_field);
                }
                contentPanel.add(panel3);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0};
                ((GridBagLayout)buttonBar.getLayout()).rowHeights = new int[] {0, 0, 0};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 1.0E-4};
                ((GridBagLayout)buttonBar.getLayout()).rowWeights = new double[] {1.0, 1.0, 1.0E-4};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> click_ok(e));
                buttonBar.add(okButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
                    new Insets(0, 0, 1, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setTitle("Simple DES Test-----Encrypt");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400,400);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JTextField key_field;
    private JPanel panel2;
    private JLabel label3;
    private JTextField plainText_field;
    private JPanel panel3;
    private JLabel label4;
    private JTextPane cypherText_field;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
