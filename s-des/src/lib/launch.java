package lib;
import window.*;

import java.io.IOException;

/**
*
*  程序启动类
*  启动三个窗口，分别为 Simple DES Test-----Encrypt   负责加密
*                   Simple DES Test-----Decrypt   负责解密
*                   Key-Cracking   负责破解密钥
* */

public class launch {
    public static void main(String args[]) throws IOException {
        s_des wd1 =new s_des();
        s_des_de wd2=new s_des_de();
        wd1.setVisible(true);
        wd2.setVisible(true);
        breaker wd3 = new breaker();
        wd3.setVisible(true);
    }
}
