package lib;


/**
 * 实现simple-DES的类
 */
public class S_DES {
    private int[] p10 = {3, 5, 2, 7, 4, 10, 1, 9, 8, 6}; /**由10位到10位的直接置换 */
    private int[] p8 = {6, 3, 7, 4, 8, 5, 10, 9};  /**由10位到8位的置换 */
    private int[] p4 = {2, 4, 3, 1};  //等长置换
    private int[] ip = {2, 6, 3, 1, 4, 8, 5, 7};  /**初始置换盒 IP */
    private int[] ip_1 = {4, 1, 3, 5, 7, 2, 8, 6};  /**初始置换盒的逆 IP的逆 */
    private int[] EP = {4, 1, 2, 3, 2, 3, 4, 1};  /**由4位到8位的扩展置换  */

    //两个S盒
    private int[][] s0 = {
            {1, 0, 3, 2},
            {3, 2, 1, 0},
            {0, 2, 1, 3},
            {3, 1, 3, 2}
    };
    private int[][] s1 = {
            {0, 1, 2, 3},
            {2, 0, 1, 3},
            {3, 0, 1, 0},
            {2, 1, 0, 3}
    };
    public int[] key = new int[10];  /**密钥  */
    private int[] plainText = new int[8];  /**明文  */
    private int[] cypherText = new int[8];  /**密文  */
    public int[] k1 = new int[8];  /**复合函数k1  */
    public int[] k2 = new int[8];  /**复合函数k2  */
    public int[] de = new int[8];  /**一个存储解密结果的数组  */


    /**
    *   构造方法
    *   参数mode为"en", 需要加密; mode为"de", 需要解密
    * */
    public S_DES(String key_input,String t_input,String mode) {
        char[] key_temp = key_input.toCharArray();
        int key_length = key_temp.length;

        for(int i =0; i<key_length;i++){
            key[i] = (int)key_temp[i];
        }
        if (mode.equals("en")){
            char[] pt_temp = t_input.toCharArray();
            int pt_length = pt_temp.length;
            for(int j =0; j<pt_length;j++){
                plainText[j] = (int)pt_temp[j];
            }
        }
        if(mode.equals("de")){
            char[] ct_temp = t_input.toCharArray();
            int ct_length = ct_temp.length;
            for(int j =0; j<ct_length;j++){
                cypherText[j] = (int)ct_temp[j];
            }
        }
    }


    /** 生成两个子密钥 填充 k1,k2 */
    public void key_generate() {
        // p10置换
        int[] temp = new int[10];
        for (int i = 0; i < 10; i++) {
            temp[i] = key[p10[i] - 1];
        }
        // LS-1: 循环左移1位
        int[] lk = new int[5];
        int[] rk = new int[5];
        for (int i = 0; i < 5; i++) {
            lk[i] = temp[i];
            rk[i] = temp[i + 5];
        }
        int temp1 = lk[0];
        for (int i = 0; i < 4; i++) {
            lk[i] = lk[i + 1];
        }
        lk[4] = temp1;
        temp1 = rk[0];
        for (int i = 0; i < 4; i++) {
            rk[i] = rk[i + 1];
        }
        rk[4] = temp1;

        int[] temp2 = new int[10];
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                temp2[i] = lk[i];
            } else {
                temp2[i] = rk[i - 5];
            }
        }

        // p8置换得子密钥K1
        for (int i = 0; i < 8; i++) {
            k1[i] = temp2[p8[i] - 1];
        }

        for (int j = 0; j < 2; j++) { // 使用j代替内层循环的i以避免冲突
            temp1 = lk[0];
            for (int k = 0; k < 4; k++) { // 使用k代替内层循环的i以避免冲突
                lk[k] = lk[k + 1];
            }
            lk[4] = temp1; 
            // 对rk进行相同的操作
            temp1 = rk[0];
            for (int k = 0; k < 4; k++) {
                rk[k] = rk[k + 1];
            }
            rk[4] = temp1; // 同样注意数组边界
        }

        // 将lk和rk合并到temp2中
        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                temp2[i] = lk[i];
            } else {
                temp2[i] = rk[i - 5];
            }
        }

        // p8置换得子密钥K2
        for (int i = 0; i < 8; i++) {
            k2[i] = temp2[p8[i] - 1];
        }
    }

    /**加密  */
    public String encrypt() {
        // 初始置换（IP置换）
        int[] plainText1 = new int[8];
        for (int i = 0; i < 8; i++) {
            plainText1[i] = plainText[ip[i] - 1];
        }

        // 标准的Feistel密码结构，共有两次循环
        // 第一次循环
        int[] lm = new int[4];
        int[] rm = new int[4];
        for (int i = 0; i < 4; i++) {
            lm[i] = plainText1[i];
            rm[i] = plainText1[i + 4];
        }

        // EP扩展置换
        int[] rm1 = new int[8];
        for (int i = 0; i < 8; i++) {
            rm1[i] = rm[EP[i] - 1];
        }
        // 按位异或
        for (int i = 0; i < 8; i++) {
            //rm1[i] ^= k1[i];
            rm1[i] = rm1[i] ^ k1[i];
        }

        // S盒替换
        int s0_row = 2 * rm1[0] + rm1[3];
        int s0_col = 2 * rm1[1] + rm1[2];
        int s0_value = s0[s0_row][s0_col];

        int s1_row = 2 * rm1[4] + rm1[7];
        int s1_col = 2 * rm1[5] + rm1[6];
        int s1_value = s1[s1_row][s1_col];

        // S盒替换结果合并
        int[] s_value = new int[4];
        s_value[0] = s0_value / 2;
        s_value[1] = s0_value % 2;
        s_value[2] = s1_value / 2;
        s_value[3] = s1_value % 2;


        // P4置换
        int[] rm2 = new int[4];
        for (int i = 0; i < 4; i++) {
            rm2[i] = s_value[p4[i] - 1];
        }


        // 按位异或
        for (int i = 0; i < 4; i++) {
            rm2[i] = rm2[i] ^ lm[i];
        }

        // Lm'与Rm组合
        for (int i = 0; i < 4; i++) {
            plainText1[i] = rm2[i];
            plainText1[i + 4] = rm[i];
        }

        // 交换高低位（即左右半部分）
        int[] ln = new int[4];
        int[] rn = new int[4];
        for (int i = 0; i < 4; i++) {
            ln[i] = plainText1[i + 4];
            rn[i] = plainText1[i];
        }

        // 第二次循环
        // Rn做EP扩展置换
        int[] rn1 = new int[8];
        for (int i = 0; i < 8; i++) {
            rn1[i] = rn[EP[i] - 1];
        }

        // 与k2按位异或
        for (int i = 0; i < 8; i++) {
            rn1[i] = rn1[i] ^ k2[i];
        }


        // S盒替代
        s0_row = 2 * rn1[0] + rn1[3];
        s0_col = 2 * rn1[1] + rn1[2];
        s0_value = s0[s0_row][s0_col];

        s1_row = 2 * rn1[4] + rn1[7];
        s1_col = 2 * rn1[5] + rn1[6];
        s1_value = s1[s1_row][s1_col];

        // S盒替换结果合并
        s_value[0] = s0_value / 2;
        s_value[1] = s0_value % 2;
        s_value[2] = s1_value / 2;
        s_value[3] = s1_value % 2;

        // P4置换
        int[] rn2 = new int[4];
        for (int i = 0; i < 4; i++) {
            rn2[i] = s_value[p4[i] - 1];
        }
        //show(s_value);

        // 与Ln按位异或
        for (int i = 0; i < 4; i++) {
            rn2[i] = rn2[i] ^ ln[i];
        }


        // Ln'与Rn组合
        for (int i = 0; i < 4; i++) {
            plainText1[i] = rn2[i];
            plainText1[i + 4] = rn[i];
        }


        // IP^-1置换
        for (int i = 0; i < 8; i++) {
            cypherText[i] = plainText1[ip_1[i] - 1];
        }

        return show(cypherText);
    }

    /**解密  */
    public String decrypt() {
        // 初始置换（IP置换）
        int[] cypherText1 = new int[8];
        for (int i = 0; i < 8; i++) {
            cypherText1[i] = cypherText[ip[i] - 1];
        }

        // 标准的Feistel密码结构，共有两次循环
        // 第一次循环
        int[] lm = new int[4];
        int[] rm = new int[4];
        for (int i = 0; i < 4; i++) {
            lm[i] = cypherText1[i];
            rm[i] = cypherText1[i + 4];
        }

        // EP扩展置换
        int[] rm1 = new int[8];
        for (int i = 0; i < 8; i++) {
            rm1[i] = rm[EP[i] - 1];
        }

        // 按位异或
        for (int i = 0; i < 8; i++) {
            //rm1[i] ^= k1[i];
            rm1[i] = rm1[i] ^ k2[i];
        }

        // S盒替换
        int s0_row = 2 * rm1[0] + rm1[3];
        int s0_col = 2 * rm1[1] + rm1[2];
        int s0_value = s0[s0_row][s0_col];

        int s1_row = 2 * rm1[4] + rm1[7];
        int s1_col = 2 * rm1[5] + rm1[6];
        int s1_value = s1[s1_row][s1_col];

        // S盒替换结果合并
        int[] s_value = new int[4];
        s_value[0] = s0_value / 2;
        s_value[1] = s0_value % 2;
        s_value[2] = s1_value / 2;
        s_value[3] = s1_value % 2;


        // P4置换
        int[] rm2 = new int[4];
        for (int i = 0; i < 4; i++) {
            rm2[i] = s_value[p4[i] - 1];
        }


        // 按位异或
        for (int i = 0; i < 4; i++) {
            rm2[i] = rm2[i] ^ lm[i];
        }

        // Lm'与Rm组合
        for (int i = 0; i < 4; i++) {
            cypherText1[i] = rm2[i];
            cypherText1[i + 4] = rm[i];
        }

        // 交换高低位（即左右半部分）
        int[] ln = new int[4];
        int[] rn = new int[4];
        for (int i = 0; i < 4; i++) {
            ln[i] = cypherText1[i + 4];
            rn[i] = cypherText1[i];
        }

        // 第二次循环
        // Rn做EP扩展置换
        int[] rn1 = new int[8];
        for (int i = 0; i < 8; i++) {
            rn1[i] = rn[EP[i] - 1];
        }

        // 与k1按位异或
        for (int i = 0; i < 8; i++) {
            rn1[i] = rn1[i] ^ k1[i];
        }


        // S盒替代
        s0_row = 2 * rn1[0] + rn1[3];
        s0_col = 2 * rn1[1] + rn1[2];
        s0_value = s0[s0_row][s0_col];

        s1_row = 2 * rn1[4] + rn1[7];
        s1_col = 2 * rn1[5] + rn1[6];
        s1_value = s1[s1_row][s1_col];

        // S盒替换结果合并
        s_value[0] = s0_value / 2;
        s_value[1] = s0_value % 2;
        s_value[2] = s1_value / 2;
        s_value[3] = s1_value % 2;

        // P4置换
        int[] rn2 = new int[4];
        for (int i = 0; i < 4; i++) {
            rn2[i] = s_value[p4[i] - 1];
        }
        //show(s_value);

        // 与Ln按位异或
        for (int i = 0; i < 4; i++) {
            rn2[i] = rn2[i] ^ ln[i];
        }


        // Ln'与Rn组合
        for (int i = 0; i < 4; i++) {
            cypherText1[i] = rn2[i];
            cypherText1[i + 4] = rn[i];
        }


        // IP^-1置换
        for (int i = 0; i < 8; i++) {
            de[i] = cypherText1[ip_1[i] - 1];
        }

        return show(de);
    }

    /**
    *   加密 与 解密 的区别在于两个复合函数的顺序相反
    * */

    /** 辅助方法：打印  */
    public String show(int a[]){
        StringBuilder stringBuilder = new StringBuilder();
        for( int i : a){
            stringBuilder.append((char)(i));
        }
        String result = stringBuilder.toString();
        return result;
    }

}
