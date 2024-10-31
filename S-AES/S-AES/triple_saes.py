from saes import SimplifiedAES


class TripleSimplifiedAES_mode1(object):
    def __init__(self, key1, key2):
        self.aes1 = SimplifiedAES(key1)
        self.aes2 = SimplifiedAES(key2)

    def encrypt(self, plaintext):
        ciphertext1 = self.aes1.encrypt(plaintext)
        ciphertext2 = self.aes2.encrypt(ciphertext1)
        return ciphertext2

    def decrypt(self, ciphertext):
        decrypted1 = self.aes2.decrypt(ciphertext)
        decrypted2 = self.aes1.decrypt(decrypted1)
        return decrypted2


class TripleSimplifiedAES_mode2(object):
    def __init__(self, key1, key2, key3):
        self.aes1 = SimplifiedAES(key1)
        self.aes2 = SimplifiedAES(key2)
        self.aes3 = SimplifiedAES(key3)

    def encrypt(self, plaintext):
        ciphertext1 = self.aes1.encrypt(plaintext)
        ciphertext2 = self.aes2.encrypt(ciphertext1)
        ciphertext3 = self.aes3.encrypt(ciphertext2)
        return ciphertext3

    def decrypt(self, ciphertext):
        decrypted2 = self.aes3.decrypt(ciphertext)
        decrypted1 = self.aes2.decrypt(decrypted2)
        decrypted0 = self.aes1.decrypt(decrypted1)
        return decrypted0
