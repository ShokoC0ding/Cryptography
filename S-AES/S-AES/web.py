from flask import Flask, render_template, request, jsonify

from double_saes import DoubleSimplifiedAES
from triple_saes import TripleSimplifiedAES_mode1, TripleSimplifiedAES_mode2
import ast
from double_saes import DoubleSimplifiedAES
from saes import SimplifiedAES
from utils import decimal_to_16bit_binary
from double_saes import DoubleSimplifiedAES


app = Flask(__name__)


from double_saes import DoubleSimplifiedAES

def find_key_from_intermediates(plaintexts, ciphertexts):
    ciphertexts = [int(binary_str, 2) for binary_str in ciphertexts]
    key_candidates = []

    for i in range(len(plaintexts)):
        for j in range(len(plaintexts)):
            if i != j:
                plaintext1 = plaintexts[i]
                ciphertext1 = ciphertexts[i]
                plaintext2 = plaintexts[j]
                ciphertext2 = ciphertexts[j]

                double_aes = DoubleSimplifiedAES(0)  # 初始化一个双AES实例

                key1 = double_aes.aes1.find_key(int(plaintext1, 2), ciphertext1)
                key2 = double_aes.aes2.find_key(int(plaintext2, 2), ciphertext2)

                full_key = (key1 << 16) | key2
                key_candidates.append(full_key)
    if key_candidates:

        key_candidates = [format(item, '032b') for item in key_candidates]
        return key_candidates
    else:
        return "未找到中间相遇。"

def binary_string_to_array(binary_string):
    # 使用','来分割字符串
    binaries = binary_string.split(',')

    # 使用int函数将每个二进制字符串转换为整数，基数设置为2
    return [int(binary, 2) for binary in binaries]

def cbc_encrypt(plaintext, key, iv):
    ciphertext = []
    previous_cipher = iv
    saes = SimplifiedAES(key)

    for block in plaintext:
        # 在加密前，每个明文分组与前一个密文分组(或IV)进行异或操作
        xor_result = block ^ previous_cipher
        encrypted_block = saes.encrypt(xor_result)
        ciphertext.append(encrypted_block)
        previous_cipher = encrypted_block

    return ciphertext


def cbc_decrypt(ciphertext, key, iv):
    plaintext = []
    saes = SimplifiedAES(key)
    previous_cipher = iv

    for block in ciphertext:
        decrypted_block = saes.decrypt(block)
        # 在解密后，需要将解密结果与前一个密文分组(或IV)进行异或操作
        plaintext_block = decrypted_block ^ previous_cipher
        plaintext.append(plaintext_block)
        previous_cipher = block

    return plaintext

# 普通加密函数
def f11(info, key):
    # 检查 info 是否为16位的二进制数字
    if not (len(info) == 16 ):
        return "输入不合法: 信息必须为16位"

    # 检查 key 是否为16的二进制数字
    if not (len(key) == 16 ):
        return "输入不合法: 密钥必须为16位"

    key =int(key, 2)
    info = int(info,2)
    ciphertext = SimplifiedAES(key).encrypt(info)  # 0b0010010011101100

    return decimal_to_16bit_binary(ciphertext)

# 普通解密函数
def f12(info, key):
    # 检查 info 是否为16位的二进制数字
    if not (len(info) == 16):
        return "输入不合法: 信息必须为16位"

    # 检查 key 是否为16的二进制数字
    if not (len(key) == 16):
        return "输入不合法: 密钥必须为16位"
    key = int(key, 2)
    info = int(info, 2)

    ciphertext = SimplifiedAES(key).decrypt(info)  # 0b0010010011101100

    return decimal_to_16bit_binary(ciphertext)

#ascll加密
def f21(info, key):

    # 检查 key 是否为16位的二进制数字
    if not (len(key) == 16 ):
        return "输入不合法: 密钥必须为16位二进制数字"
    key  = int(key, 2)
    saes = SimplifiedAES(key)

    # 要加密的ASCII字符串，每个字符表示为一个16位整数
    plaintext_ascii = info
    plaintext_integers = [ord(char) for char in plaintext_ascii]

    # 加密
    encrypted_integers = [saes.encrypt(plaintext_int) for plaintext_int in plaintext_integers]
    binary_encrypted_integers = [decimal_to_16bit_binary(single_element) for single_element in encrypted_integers]

    return binary_encrypted_integers


#ascll解密
def f22(info, key):
    # 检查 key 是否为16位的二进制数字
    if not (len(key) == 16):
        return "输入不合法: 密钥必须为16位二进制数字"

    key = int(key, 2)
    saes = SimplifiedAES(key)
    info = ast.literal_eval(info)


    # 解密
    encrypted_integers = [int(binary_string, 2) for binary_string in info]

    decrypted_integers = [saes.decrypt(encrypted_int) for encrypted_int in encrypted_integers]

    # 将解密后的整数转换回ASCII字符串
    decrypted_ascii = "".join([chr(int_value) for int_value in decrypted_integers])


    return decrypted_ascii

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/page3')
def get_page3():
    return render_template('3.html')

@app.route('/page_cbc')
def get_page_cbc():
    return render_template('cbc.html')

@app.route('/page_attack')
def get_page_attack():
    return render_template('attack.html')

@app.route('/brute_force_page')
def brute_force_page():
    return render_template('index2.html')

#二重加密
@app.route('/brute_force', methods=['POST'])
def brute_force():

    data = request.json
    plaintext = data.get('username')
    key = data.get('password')
    plaintext=int(plaintext,2)
    key = int(key, 2)

    # plaintext = 0b0000111100001111
    # # key = 0b0010110101010101
    #
    # key = 0b00101101010101010010110101010101
    # 创建DoubleSimplifiedAES实例
    double_aes = DoubleSimplifiedAES(key)

    # 加密
    ciphertext = double_aes.encrypt(plaintext)


    # 解密
    decrypted_plaintext = double_aes.decrypt(ciphertext)




    return jsonify({"message": decimal_to_16bit_binary(ciphertext)})


#二重解密
@app.route('/process_interface', methods=['POST'])
def process_interface():
    data = request.json
    ciphertext= data.get('username')
    key = data.get('password')
    ciphertext = int(ciphertext, 2)
    key = int(key, 2)

    double_aes = DoubleSimplifiedAES(key)
    decrypted_plaintext = double_aes.decrypt(ciphertext)



    return jsonify({"message": decimal_to_16bit_binary(decrypted_plaintext)})



#3重加密
@app.route('/3_1', methods=['POST'])
def brute_force1():

    data = request.json
    plaintext = data.get('username')
    keys= data.get('password')
    plaintext = int(plaintext, 2)
    key1, key2 =keys.split(';')
    key1 = int(key1, 2)
    key2 = int(key2, 2)



    triple_aes = TripleSimplifiedAES_mode1(key1, key2)
    ciphertext = triple_aes.encrypt(plaintext)


    decrypted = triple_aes.decrypt(ciphertext)





    return jsonify({"message": decimal_to_16bit_binary(ciphertext)})

#3重解密
@app.route('/3_2', methods=['POST'])
def process_interface2():
    data = request.json
    ciphertext= data.get('username')
    keys = data.get('password')
    ciphertext = int(ciphertext, 2)

    key1, key2 =keys.split(';')
    key1 = int(key1, 2)
    key2 = int(key2, 2)



    triple_aes = TripleSimplifiedAES_mode1(key1, key2)

    decrypted = triple_aes.decrypt(ciphertext)

    return jsonify({"message": decimal_to_16bit_binary(decrypted)})

#cbc 加密
@app.route('/cbc_1', methods=['POST'])
def compute_cbc1():

    data = request.json
    iv = data.get('username')
    info = data.get('password')
    key=data.get("text")

    plaintext = binary_string_to_array(info)
    iv= int(iv, 2)
    key=int(key,2)

    # 加密
    ciphertext = cbc_encrypt(plaintext, key, iv)


    out1=[]
    for block in ciphertext:
        out1.append(decimal_to_16bit_binary(block))


    return jsonify({"message": out1})




#cbc 解码
@app.route('/cbc_2', methods=['POST'])
def compute_cbc2():

    data = request.json
    iv = data.get('username')
    info = data.get('password')
    key=data.get("text")

    ciphertext  = binary_string_to_array(info)
    iv= int(iv, 2)
    key=int(key,2)


    # 修改第一个密文块
    ciphertext[0] = 0b0000000000000000  # 替换第一个密文块

    # 解密
    decrypted_plaintext = cbc_decrypt(ciphertext, key, iv)

    out1 = []
    for block in decrypted_plaintext:
        out1.append(decimal_to_16bit_binary(block))


    return jsonify({"message":   out1   })

# 二进制和ascll的加解密
@app.route('/login', methods=['POST'])
def login():
    data = request.json
    info = data.get('username')
    key = data.get('password')
    option = data.get('option')

    operation = data.get('operation')
    if option == "binary":
        if operation == "encryption":
            result = f11(info, key)
            message = f"加密结果为: {result}"
        elif operation == "decryption":
            result = f12(info, key)
            message = f"解密结果为: {result}"
        else:
            return jsonify({"message": "无效的请求"}), 400
    elif option == "asciil":
        if operation == "encryption":
            result = f21(info, key)
            message = f"加密结果为: {result}"
        elif operation == "decryption":
            result = f22(info, key)
            message = f"解密结果为: {result}"
        else:
            return jsonify({"message": "无效的请求"}), 400
    else:
        return jsonify({"message": "无效的选项"}), 400

    return jsonify({"message": message})


#
@app.route('/attck_compute', methods=['POST'])
def attck_compute1():

    data = request.json
    plaintexts = data.get('username')
    ciphertexts= data.get('password')


    plaintexts= plaintexts.split(',')
    ciphertexts = ciphertexts.split(',')

    message = "破解结果是:" + ",".join(map(str, find_key_from_intermediates(plaintexts, ciphertexts)))

    return jsonify({"message": message  })

if __name__ == '__main__':
    # app.run(host='10.230.6.109', port=6600, debug=True)
    app.run(port=6600, debug=True)
