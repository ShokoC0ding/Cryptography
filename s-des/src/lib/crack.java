package lib;

public class crack {
    private static String plainText;  //明文
    private static String cypherText;  //密文
    public String key;  //密钥

    /**    构造方法 接收输入的明文和密文    */
    public crack(String plainText, String cypherText){
        this.plainText=plainText;
        this.cypherText=cypherText;
        this.key = "";
    }

    /**  使用递归方法, 枚举生成 8-bit密钥的所有可能结果*/
    private void generateAllCombinations(int[] array, int index) {
        if (index == array.length) {
            StringBuilder sb = new StringBuilder();
            for(int i =0;i<array.length;i++){
                sb.append(array[i]);
            }
            try_key(sb.toString());  /** 对枚举得到的密钥进行测试*/
            return;
        }
        // Set the current index to 0
        array[index] = 0;
        generateAllCombinations(array, index + 1);

        // Set the current index to 1
        array[index] = 1;
        generateAllCombinations(array, index + 1);
    }


    /**   密钥正确性测试  
          测试方法为 通过S_DES和S_DES_Enhanced中的方法，由密钥和明文直接生成密文，
          检查密文是否与预期一致
    */
    public void try_key(String key){
        int flag=0;  /**    标志位, 确定密文对是数字0、1串还是ASCII字符串*/

        for (char c : plainText.toCharArray()) {
            // 如果字符不是'0'也不是'1'，则返回false
            if (c != '0' && c != '1') {
                flag=1;
            }
        }

        if(flag==0){
            S_DES s_des = new S_DES(key, plainText, "en");
            s_des.key_generate();
            System.out.println(cypherText);
            if(s_des.encrypt().equals(cypherText)){
                this.key+=key+"\n";
            }
        }
        if(flag==1){
            S_DES_Enhanced s_des_en = new S_DES_Enhanced(key, plainText, "en");
            if(s_des_en.encrypt().equals(cypherText)){
                this.key+=key+"\n";
            }
        }
    }

    /** 启动破解 */
    public void begin_crack(){
        int[] temp = new int[10];
        generateAllCombinations(temp,0);
    }


}
