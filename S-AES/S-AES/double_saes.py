from saes import SimplifiedAES


class DoubleSimplifiedAES(object):
    def __init__(self, key):
        if not isinstance(key, int):
            raise ValueError("密钥必须是整数。")
        if key < 0 or key > 0xFFFFFFFF:
            raise ValueError("密钥必须是32位整数。")
        self.key = key
        self.key1 = (key & 0xFFFF0000) >> 16  # 密钥的高16位
        self.key2 = key & 0x0000FFFF  # 密钥的低16位
        self.aes1 = SimplifiedAES(self.key1)  # 使用key1创建第一个AES实例
        self.aes2 = SimplifiedAES(self.key2)  # 使用key2创建第二个AES实例

    def encrypt(self, plaintext):
        intermediate_result = self.aes1.encrypt(plaintext)  # 第一次加密
        ciphertext = self.aes2.encrypt(intermediate_result)  # 第二次加密
        return ciphertext

    def decrypt(self, ciphertext):
        intermediate_result = self.aes2.decrypt(ciphertext)  # 第二次解密
        plaintext = self.aes1.decrypt(intermediate_result)  # 第一次解密
        return plaintext
