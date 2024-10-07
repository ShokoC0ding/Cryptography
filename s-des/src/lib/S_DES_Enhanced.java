package lib;

/**
 * simple-DES强化版, 可处理由9位ASCII码字符组成的明密文对
 *
 */
public class S_DES_Enhanced {
    private String key; //密钥
    private String t_input; //明文或密文
    private String mode; //确定加密还是解密
    private static S_DES s_des;
    public static int[] ascllArr_plain; //明文字符的ASCII码形式
    public static int[] ascllArr_cypher; //密文字符的ASCII码形式

    /**
     *    构造方法
     */
    public S_DES_Enhanced(String key, String t_input,String mode){
        this.key=key;
        this.t_input=t_input;
        this.mode=mode;

        /**
         *      将明文或密文字符串逐个字符转换成ASCII码，存入int数组
         */
        if(mode.equals("en")){
            this.ascllArr_plain=stringToAsciiArray(t_input);}
        if(mode.equals("de")){
            this.ascllArr_cypher=stringToAsciiArray(t_input);}
    }

    /** 针对明文的每一个字符，使用simple-DES的8-bit加密   */
    public String encrypt(){
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<ascllArr_plain.length;i++){
            s_des = new S_DES(key,intToEightBitBinaryArray(ascllArr_plain[i]),mode);
            s_des.key_generate();
            sb.append((char)(binaryStringToInt(s_des.encrypt())));
        }
        return sb.toString();
    }
    /** 针对密文的每一个字符，使用simple-DES的8-bit解密   */
    public String decrypt(){
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<ascllArr_cypher.length;i++){
            s_des = new S_DES(key,intToEightBitBinaryArray(ascllArr_cypher[i]),mode);
            s_des.key_generate();
            sb.append((char)(binaryStringToInt(s_des.decrypt())));
        }
        return sb.toString();
    }



    /** String 转换成 int[] */
    public static int[] stringToAsciiArray(String input) {
        int[] asciiArray = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 对于ASCII字符，取其低8位；对于非ASCII字符，这也将给出Unicode码点的低8位
            asciiArray[i] = c & 0xFF; // 使用位与操作来保留低8位
        }
        return asciiArray;
    }
    /** int (即ASCII码) 转换为对应的 二进制形式
     *  返回二进制结果进行了反转，保证该结果的最左边代表二进制的最高位
     * */
    public static String intToEightBitBinaryArray(int number) {
        int[] binaryArray = new int[8];

        // 遍历数组的每一位（从最低位到最高位）
        for (int i = 7; i >= 0; i--) {
            // 通过位与操作和右移来获取当前位的值（0或1）
            binaryArray[i] = (number >> i) & 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<binaryArray.length;i++){
            sb.append(binaryArray[i]);
        }
        return sb.reverse().toString();
    }
    /**   String形式的二进制串 转换为 int, 即对应的ASCII码        */
    public static int binaryStringToInt(String str) throws NumberFormatException {
        if (str.length() != 8 || !str.matches("[01]+")) {
            throw new NumberFormatException("Invalid binary string: must be exactly 8 digits and only contain 0s and 1s.");
        }
        return Integer.parseInt(str, 2);
    }

}
