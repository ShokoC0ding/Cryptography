# S_AES小组作业
## 作业报告
###   小组:RNG       
成员：李泽坤、吴科明
#### 第一关：基本测试



用户界面总览：
![image](https://github.com/likiki180/DES/assets/143941355/0b2dc226-e52e-4cd4-ad81-5fedf33ea65a)







我们首先展示用户界面和输入输出不合法的情况。
当输入的信息和密钥内容或者位数不合法时，会重新输入：

![image](https://github.com/likiki180/DES/assets/143941355/8195bee8-20fc-4e86-b4f1-13740b80b866)




![image](https://github.com/likiki180/DES/assets/143941355/0109f098-661a-4c10-8508-ed7270c9df3d)






接下来，我们展示二进制的加解密操作：

输入明文：1010001110110000

输入密钥：0000011010101011



我们得到了下面的加密结果，密文为0011010100111110：

![image](https://github.com/likiki180/DES/assets/143941355/a079a78e-c488-49b1-859d-ecbec9046b0d)





接下来，我们用刚才得到的密文和密钥进行解密：

密文：0011010100111110

密钥：0000011010101011

![image](https://github.com/likiki180/DES/assets/143941355/b7aaf521-bb5f-4632-bb0a-d2fd693ccf37)





解密结果为:1010001110110000

这和我们最开始使用的明文是一致的，至此我们通过了第一关的测试



#### 第二关 交叉测试

本次测试使用内容如下：

输入明文：1010001110110000

输入密钥：0000011010101011





我们程序的二进制明文加密测试：
![image](https://github.com/likiki180/DES/assets/143941355/b41fea8c-e110-49f1-9a9c-175fd0a74b64)




我们程序的ASCII码明文加密测试：

![image](https://github.com/likiki180/DES/assets/143941355/dd5ce16f-fd5a-4bfc-9ec2-7a07c95ad82f)




交叉测试组的两个加密结果：

输入明文：1010001110110000

输入密钥：0000011010101011


该小组的结果如下:

![image](https://github.com/likiki180/DES/assets/143941355/a1f93518-5366-4ada-b4d8-2773fdb71dd9)




可以看出，我们和其他小组的交叉测试结果是一样的， 交叉测试通过！



#### 第三关 拓展功能

我们的作品进行了拓展，加密算法的数据输入可以是ASII编码字符串

对应地输出也可以是ACII字符串(很可能是乱码)。而且我们使用的是web开发，在网页显示中，还纯在部分乱码是不可见的，不可以复制的状态


下图是加密演示：

明文2：RNG

密钥2：0000011010101011

加密结果为: ['0110001011100111', '1110111000011110', '0011111000011001']




![image](https://github.com/likiki180/DES/assets/143941355/33d2c200-f90a-4280-9d77-fa2d732bf000)




面是对应的解密演示：

![image](https://github.com/likiki180/DES/assets/143941355/f670d0ba-e555-4d55-b784-92e3d36a77b3)



解密成功，得到对应的明文，演示结束，第三关通过。

#### 第四关 多重加密

#####4.1 双重加密将S-AES算法通过双重加密进行扩展，分组长度仍然是16 bits，但密钥长度为32 bits

选择对应的双重加密选项：
![image](https://github.com/likiki180/DES/assets/143941355/680e7061-892a-4764-abc2-bb3633e7c725)

按照位数提示要求，输入明文
明文：1001100101100101
密钥：10100011101100000000011010101011
![image](https://github.com/likiki180/DES/assets/143941355/42e6a545-34a9-4c51-b966-1efcd4a8b832)

加密结果，密文1011010000001010：

![image](https://github.com/likiki180/DES/assets/143941355/ce493e9a-4fce-4cb0-b54b-96ac461d48fe)

现在进行解密：
![image](https://github.com/likiki180/DES/assets/143941355/8cb76469-06f2-476c-80e8-0f6faf41d56d)
成功得到刚才的明文

4.1测试通过




#####4.2 中间相遇攻击假设你找到了使用相同密钥的明、密文对(一个或多个)，请尝试使用中间相遇攻击的方法找到正确的密钥Key(K1+K2)
选择对应的中间相遇攻击选项：
![image](https://github.com/likiki180/DES/assets/143941355/935a84cb-c62c-431f-bed7-d3b2eefac80e)

按要求输入我们已知的明文和密文对，假如说我们现在知道两对：

![image](https://github.com/likiki180/DES/assets/143941355/137e0597-a5bb-4c8f-9aa5-f4a3bb20b266)

破解结果:
我们找到了两个密钥
密钥1：01101100101111000110000111101110
密钥2：01100001111011100110110010111100


![image](https://github.com/likiki180/DES/assets/143941355/d23f7c88-452a-44cf-93fa-18e4bfc74bab)

刚才的两对是我提前设计好的，所以能破解。如果随机给出明密文对，也不总是能破解，如下图：

![image](https://github.com/likiki180/DES/assets/143941355/c3645f1a-8b0d-4d7e-9eda-a86f43aa7714)

4.2测试通过





#####4.3 三重加密将S-AES算法通过三重加密进行扩展，我们按照按照32 bits密钥Key(K1+K2)的模式进行三重加密解密

选择对应的三重加密选项：
![image](https://github.com/likiki180/DES/assets/143941355/b17bde3d-3423-42c1-b824-2c9921612682)


按要求输入我们已知的明文和密文对
![image](https://github.com/likiki180/DES/assets/143941355/0eeb7372-665c-499b-9af1-1b81a7c2b873)

明文：0000111100001111
2个密钥：0100101011110101;1101011100101000

加密结果：
![image](https://github.com/likiki180/DES/assets/143941355/b407d6d4-eb37-48fb-a30e-115d65a6ec43)

加密结果为：1010000110001111

对应的解密：
![image](https://github.com/likiki180/DES/assets/143941355/6a4cb077-6137-49d2-ba9c-2b6bc0b5d4be)

成功得到刚才的原文

4.3测试通过
第四关测试通过

#### 第五关：工作模式

在第五关卡，我们基于S-AES算法，使用密码分组链(CBC)模式对较长的明文消息进行加密。
注意初始向量(16 bits) 的生成，并需要加解密双方共享。
在CBC模式下进行加密，并尝试对密文分组进行替换或修改，
然后进行解密，请对比篡改密文前后的解密结果。

选择对应的CBC选项：

![image](https://github.com/likiki180/DES/assets/143941355/6fc4a59c-5eb5-4f3a-ba22-a232d0f53954)


按要求输入初始化向量，明文或者密文，密钥
![image](https://github.com/likiki180/DES/assets/143941355/3f2d16b3-829d-4dcb-8521-a5a23eb135bb)

我们支持同时进行多组明文的加密
明文：1010101011011010, 1010010110011101, 1010110111011010, 1010100110011101
初始化向量：1100110011001100
密钥：0100101011110101
加密结果：
0110100111001001
1110111001110101
0101100010011000
0100101010001001


![image](https://github.com/likiki180/DES/assets/143941355/6bc4d55c-acf1-42cf-a9fb-84f19d2529f8)

对结果进行解密：
![image](https://github.com/likiki180/DES/assets/143941355/0235238e-22fa-4909-bef2-c9825feca0e0)

成功得到原来的密文，加解密成功

我们调转密文块的分组顺序，下面演示篡改密文后的解密结果：
![image](https://github.com/likiki180/DES/assets/143941355/5183adc8-d2ab-408f-aeff-26347ff67d6f)

可以看出，篡改后生成的明文与之前不一致，说明密文被篡改后无法得到正确的明文。

第五关测试通过，
测试通过






### S-AES 加解密系统开发手册

## 1. 系统概述
本系统采用c/s架构来实现S-AES加解密算法。它支持普通的加解密，ASCII码的加解密，以及多重加密，工作模式等功能。

## 2.原理介绍

### S-AES算法原理

Simplified Advanced Encryption Standard (S-AES) 是一种简化的 AES（Advanced Encryption Standard）加密算法。它以相对较小的块大小和密钥大小作为特点，用于教育和学习加密算法的基本概念。下面是 S-AES 算法的详细原理：

1. **S-Box 和逆 S-Box**：

   S-AES 使用一个 16 个字节的 S-Box（Substitution Box），以及一个对应的逆 S-Box。S-Box 是一个用于替换字节的固定置换表，它将一个 4 位二进制数替换为另一个 4 位二进制数。逆 S-Box 则是 S-Box 的逆操作。

2. **初始密钥扩展**：

   S-AES 使用一个 16 位的初始密钥，它将在加密和解密中使用。初始密钥通过密钥扩展算法扩展为多个轮次所需的子密钥。扩展密钥的过程包括以下几个步骤：

   - 将 16 位初始密钥分为 8 位的左半部分和右半部分。
   - 使用 S-Box 替换右半部分的每个字节。
   - 将左半部分和右半部分进行循环左移一位。
   - 对左半部分应用按位异或运算（XOR）。

   以上步骤会重复多次，以生成多个子密钥，供加密和解密轮次使用。

3. **加密过程**：

   S-AES 加密过程包括多轮的替代和置换操作。每一轮包括以下步骤：

   - **初始轮密钥添加**：将当前轮次的子密钥与明文进行按位异或（XOR）操作。
   - **S-Box 替代**：将明文经过 S-Box 替代，将每个字节替换为 S-Box 中的对应字节。
   - **行移位**：通过固定的移位规则，对每一行进行字节移位操作。
   - **列混淆**：将每一列进行特定的混淆操作，涉及到有限域上的乘法。
   - **轮密钥添加**：将当前轮次的子密钥再次与处理后的明文进行按位异或（XOR）操作。

   这些步骤将重复多轮，每一轮都使用不同的子密钥，最后一轮不包括列混淆操作。

4. **解密过程**：

   S-AES 解密过程与加密过程相反，它包括多轮的逆操作，包括逆 S-Box 替代、逆行移位、逆列混淆和逆轮密钥添加。这些逆操作使用与加密过程相同的子密钥，但按相反的顺序应用，最终得到明文。

S-AES 的主要目的是教育和理解加密算法的基本原理，它在实际安全性上并不强大，因此不建议在真实的安全应用中使用。它提供了一种学习 AES 的简化方式，帮助理解 AES 加密的内部工作原理。



### 多重加密

双重加密和三重加密是一种数据加密技术，它们通过多次应用相同或不同的加密算法来增强数据的保密性。在S-AES算法的上下文中，双重加密和三重加密扩展了原始的S-AES加密算法，使其适用于更长的密钥，并提供更高级别的数据保护。

#### 双重加密

在双重加密中，S-AES算法使用32位密钥（K1+K2）。这意味着明文首先使用K1进行加密，然后再使用K2进行加密，从而生成密文。在解密时，需要以相反的顺序解密，首先使用K2解密，然后再使用K1解密，以还原原始的明文。

原理如下：

1. 加密过程：
   - 明文 -> K1加密 -> 中间密文 -> K2加密 -> 最终密文

2. 解密过程：
   - 最终密文 -> K2解密 -> 中间密文 -> K1解密 -> 明文

这种双重加密提供了更高的安全性，因为需要两个不同的密钥来解密数据，而且两个加密算法可能是不同的。

#### 三重加密

在三重加密中，S-AES算法可以采用两种模式之一：

1. **按照32位密钥Key(K1+K2)的模式进行三重加密解密：**

   这种模式使用32位密钥，首先使用K1加密，然后使用K2解密，最后再使用K1进行加密，从而生成密文。在解密时，需要以相反的顺序操作，即使用K1解密，然后使用K2加密，最后再使用K1解密以还原原始的明文。

   原理如下：

   1. 加密过程：
      - 明文 -> K1加密 -> 中间密文1 -> K2解密 -> 中间密文2 -> K1加密 -> 最终密文

   2. 解密过程：
      - 最终密文 -> K1解密 -> 中间密文2 -> K2加密 -> 中间密文1 -> K1解密 -> 明文

2. **使用48位密钥(K1+K2+K3)的模式进行三重加解密：**

   这种模式使用48位密钥，其中K1、K2和K3分别用于三个不同的加密/解密轮次。原理类似于32位密钥模式，但增加了一个额外的密钥。

   原理如下：

   1. 加密过程：
      - 明文 -> K1加密 -> 中间密文1 -> K2解密 -> 中间密文2 -> K3加密 -> 最终密文

   2. 解密过程：
      - 最终密文 -> K3解密 -> 中间密文2 -> K2加密 -> 中间密文1 -> K1解密 -> 明文

这些双重和三重加密的原理可以提高数据的安全性，但也增加了计算复杂性。密钥的选择和管理也变得更加复杂，因为需要维护多个密钥。



### 中间相遇攻击

中间相遇攻击（Meet-in-the-Middle Attack）是一种密码分析攻击，通常用于攻击加密算法，尤其是用于双向加密的算法，例如双密钥加密或多轮加密。攻击的目标是找到加密算法的密钥，通常是通过分析已知的明文和密文对来实现的。中间相遇攻击的原理如下：

1. 收集明文-密文对：攻击者需要收集一组已知的明文-密文对，这些对由加密算法生成。这些明文-密文对是攻击的输入。
2. 构建中间结果表：攻击者执行加密操作两次，一次从明文到密文（正向加密），一次从密文到明文（反向解密）。在正向加密和反向解密的过程中，攻击者记录每个步骤的中间结果，这包括某一轮的密钥和状态（state）。这些中间结果形成一个表格或数据结构。
3. 比对中间结果：攻击者通过比对两个中间结果表，寻找相同的中间结果。如果在正向加密和反向解密中找到相同的中间结果，那么这些中间结果将在中间相遇攻击中起到关键作用。
4. 寻找密钥：一旦攻击者找到具有相同中间结果的正向加密和反向解密步骤，他们可以确定密钥的部分值。因为这些中间结果是通过相同的密钥生成的，所以密钥的部分值将是相同的。攻击者继续将这些部分密钥组合，以还原完整的密钥。
5. 解密数据：一旦攻击者还原了密钥，他们可以使用它来解密其他密文，或者加密其他明文，从而绕过加密保护。

需要注意的是，中间相遇攻击的成功取决于几个因素，包括已知的明文-密文对数量，中间结果的存储和比对效率，以及加密算法的复杂性。攻击者需要足够的已知明文-密文对来成功执行这种攻击，而且攻击不适用于所有类型的加密算法。因此，在设计和使用加密算法时，要采取措施来防止中间相遇攻击。



#### 使用步骤：

1. 导入必要的库和类：

   ```
   pythonCopy codefrom saes import SimplifiedAES  # 导入SimplifiedAES类
   import random
   ```

2. 创建 `DoubleSimplifiedAES` 类：

   ```
   pythonCopy codeclass DoubleSimplifiedAES(object):
       # 类的初始化方法和其他方法
   ```

3. 定义生成随机明文的函数 `generate_random_plaintext`：

   ```
   pythonCopy codedef generate_random_plaintext():
       return random.randint(0, 0xFFFFFFFF)
   ```

4. 收集明文-密文对：

   ```
   pythonCopy codeplaintexts = [generate_random_plaintext() for _ in range(1000)]  # 生成1000个随机明文
   double_aes = DoubleSimplifiedAES(0x12345678)  # 创建DoubleSimplifiedAES实例，使用相同密钥
   ciphertexts1 = [double_aes.encrypt(plaintext) for plaintext in plaintexts]  # 第一次加密
   ciphertexts2 = [double_aes.encrypt(plaintext) for plaintext in plaintexts]  # 第二次加密
   ```

5. 编写 `find_key_from_intermediates` 函数来找到中间相遇的位置：

   ```
   pythonCopy codedef find_key_from_intermediates(intermediates1, intermediates2):
       for i in range(len(intermediates1)):
           for j in range(len(intermediates2)):
               if intermediates1[i] == intermediates2[j]:
                   return i, j
       return None
   ```

6. 执行中间相遇攻击，查找相遇的位置：

   ```
   pythonCopy code
   result = find_key_from_intermediates(ciphertexts1, ciphertexts2)  # 查找中间相遇
   ```

7. 如果找到中间相遇，进一步查找密钥：

   ```
   pythonCopy codeif result:
       i, j = result
       key1 = double_aes.aes1.find_key(plaintexts[i], ciphertexts1[i])  # 查找第一个子密钥
       key2 = double_aes.aes2.find_key(plaintexts[j], ciphertexts2[j])  # 查找第二个子密钥
       full_key = (key1 << 16) | key2  # 组合得到完整密钥
       print("找到密钥:", hex(full_key))
   ```

8. 如果未找到中间相遇，输出未找到的信息：

   ```
   pythonCopy codeelse:
       print("未找到中间相遇。")
   ```

这个代码示例使用随机生成的明文-密文对并尝试执行中间相遇攻击来还原密钥。如果成功找到中间相遇，将输出找到的密钥。如果未找到中间相遇，将输出未找到的信息。注意，成功执行中间相遇攻击需要足够的明文-密文对，并且密钥空间不宜过大，否则攻击的复杂性会增加。



### CBC模式

密码分组链（Cipher Block Chaining，CBC）是一种对称加密模式，用于将较长的明文消息分成块并加密。在CBC模式中，每个明文块在加密之前都要与前一个密文块（或初始向量）进行异或运算，以增加数据的随机性和安全性。下面是使用S-AES算法和CBC模式进行加密和解密的原理：

**CBC模式的加密原理：**

1. **明文分组**：首先，将明文消息分成块，每个块的大小与S-AES的块大小相匹配（16 bits）。

2. **初始向量**：生成一个16位的初始向量（IV），初始向量需要由加密和解密双方共享。初始向量通常是随机生成的。

3. **加密过程**：对于每个明文块（除了第一个块），执行以下步骤：
   - 将前一个密文块（或初始向量）与当前明文块进行异或运算。
   - 对异或结果进行S-AES加密。
   - 将加密后的结果作为当前密文块。

4. **最终输出**：所有加密后的密文块将组成最终的密文。

**CBC模式的解密原理：**

1. **初始向量**：接收方需要知道初始向量（IV），以便进行解密。

2. **解密过程**：对于每个密文块，执行以下步骤：
   - 使用S-AES解密当前密文块。
   - 将解密后的结果与前一个密文块进行异或运算。
   - 异或结果即为明文块。

3. **最终输出**：所有解密后的明文块将组成最终的明文。

**篡改密文后的解密结果对比：**

在CBC模式中，如果密文块被篡改或修改，那么解密后的结果也会受到影响。如果攻击者篡改了一个密文块，那么解密时会导致明文块中对应的位置出现错误。这是因为解密是通过前一个密文块来计算当前明文块，任何一个密文块的修改都会传播到后续的明文块中，使其解密结果变得不可预测。

CBC模式的安全性部分依赖于初始向量的随机性，因此初始向量的生成和管理是非常重要的。此外，应该采用完整性验证机制，以检测篡改或修改，并在必要时重新加密或拒绝数据。

## 3. 前端页面设计

### 主要结构
- **Logo容器**: 显示系统相关的logo。
- **标题**: 显示系统的主标题。
- **选项列表**: 用户可以选择二进制、ASCII或CBC、多重解密等等模式。
- **输入部分**: 允许用户输入信息和密钥。
- **信息容器**: 显示开发单位和开发者信息。

### 样式和交互
- **背景**: 页面背景采用图片，能够自动调整大小以适应不同的屏幕。
- **提示**: 提供清晰的界面和按钮提示用户进行输入。
- **交互**: 选项列表允许用户选择操作类型。选择暴力破解后，系统将跳转到另一页面。
- **界面1的设计**: 下面是本系统的初始界面的html文件。选择暴力破解后，系统将跳转到另一页面。
```html
<!DOCTYPE html>
<html>
<head>
    <style>
                body {
            background-image: url("/static/lan_F.jpg");
            background-size: cover;
            color: white;
            height: 50vh;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            margin: 0;
            font-size: 3em;
            font-family: Arial, sans-serif;

        }

        .logo-container {
            position: absolute;
            top: 10px;
            left: 10px;
            display: flex;
            align-items: center;
        }
        .logo-container img {
            height: 50px;
            margin-right: 10px;
        }
        .title {

            font-size: 1em;
            margin-bottom:50px;
            margin-top:200px;
        }
.select-container {
    margin: 20px 0; /* 上下边距为20px, 左右边距为0 */
    font-size: 40px; /* 字体大小为16px */
    display: flex; /* 使用flex布局以使label和select在同一行 */
    align-items: center; /* 垂直居中对齐内容 */
}

.select-container label {
    margin-right: 10px; /* 在label和select之间增加10px的间距 */
}

.select-container select {
    padding: 5px 10px; /* 选择框内部的填充：上下5px，左右10px */
    border: 1px solid #cccccc; /* 给选择框一个灰色的边框 */
    border-radius: 4px; /* 边框圆角为4px */
}




        .profile-container img {
            width: 100%;
            height: auto;
        }
        /* New CSS for login form */
                /* 应该是灰框*/
        .login-container {
            display: flex;
            flex-direction: column;
            background: rgba(0, 0, 0, 0.5); /* semi-transparent background */
            padding: 10px;  /* reduce padding */
            border-radius: 5px;
            font-size: 0.5em;

              padding: 20px;  /* 增加内边距 */

              width: 600px;  /* 增加宽度 */
    height: auto;  /* 根据内容自适应高度 */


        }
         /* 这个是输入字体控制 */
        .login-container input {
            margin-bottom: 5px;
            padding: 5px;
            font-size: 0.8em;
            background: transparent;
            color: white;
            border: none;
        }
         /* 这个是按钮控制 */
        .login-container button {
            padding: 5px;
            background-color: lightblue;
            color: black;
            border: none;
            cursor: pointer;
            font-size: 0.5em; /* reduce font-size */
             margin-bottom:30px;

        }

                 /* 这个是底部字体 */
        .info-container {
            position: absolute;
            bottom: 0;
            width: 100%;
            text-align: center;
            padding: 10px 0;
            background-color: rgba(0, 0, 0, 0.5);
            font-size: 0.2em;
        }
    </style>
    <script>
        window.onload = function() {
    document.getElementById('login-button').onclick = function() {
        var username = document.getElementById('username-input').value;
        var password = document.getElementById('password-input').value;
        var option = document.getElementById('option-list').value; // 获取当前选择的选项

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username: username, password: password, operation: "encryption", option: option})
        }).then(response => response.json())
          .then(data => alert(data.message));
    }
   // 添加事件监听器检测选择的变化
            document.getElementById('option-list').addEventListener('change', function() {
                if (this.value === "brute_force") {
                    window.location.href = "/brute_force_page"; // 假设新的HTML页面的路由为"/brute_force_page"
                }


                if (this.value === "to_3")
                {
                    window.location.href = "/page3"; // 假设新的HTML页面的路由为"/brute_force_page"
                }


                if (this.value === "to_cbc")
                {
                    window.location.href = "/page_cbc"; // 假设新的HTML页面的路由为"/brute_force_page"
                }


                if (this.value === "to_attack")
                {
                    window.location.href = "/page_attack"; // 假设新的HTML页面的路由为"/brute_force_page"
                }

            });
    document.getElementById('login-button2').onclick = function() {
        var username = document.getElementById('username-input').value;
        var password = document.getElementById('password-input').value;
        var option = document.getElementById('option-list').value; // 获取当前选择的选项

        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username: username, password: password, operation: "decryption", option: option})
        }).then(response => response.json())
          .then(data => alert(data.message));
    }
}



    </script>
</head>
<body>
    <div class="logo-container">
        <img src="/static/logo1.png" alt="Logo 1">

        <img src="/static/logo3.png" alt="Logo 3">
    </div>
    <div class="title">
        S-AES加解密系统
    </div>

       <!-- 选项列表部分 -->
    <div class="select-container">
        <label for="option-list">选项：</label>
        <select id="option-list">
            <option value="binary">二进制</option>
            <option value="asciil">ASCLL</option>
             <option value="brute_force">二重加密</option>
            <option value="to_3">三重加密</option>
            <option value="to_cbc">CBC</option>
            <option value="to_attack">中间相遇攻击</option>
        </select>
    </div>


          <!-- 文本部分 -->
    <div class="login-container">
        <input id="username-input" type="text" placeholder="信息" required/>
<!--        <input id="password-input" type="password" placeholder="密钥" required/>-->
        <input id="password-input" type="text" placeholder="密钥(16bits)" required/>
        <button id="login-button">加密</button>
        <button id="login-button2">解密</button>


    </div>

    <div class="info-container">
        <p>所属单位：重庆大学大数据与软件学院  |   分工小组：RNG</p>
        <p>开发人员：吴科明 李泽坤   |  联系方式：1281673219@qq.com</p>
    </div>
</body>
</html>


```


## 4. 使用指南


1. 打开S-DES加解密系统主页。
2. 选择所需的操作类型：二进制、ASCII或多重加密、CBC模式等。
3. 按照提示输入信息和对应的的密钥。如果输入不合法会报错。
4. 点击“加密”或“解密”按钮以进行相应操作。
5. 如果你选择了ASCII模式，那么你输入的信息应该是一个ASCII编码的字符串，而输出也将是一个ASCII编码的字符串。请注意，由于S-DES算法会对数据进行各种复杂的变换，所以加密后得到的字符串可能包含一些无法打印或无法显示的字符（即所谓的"乱码"）。但这并不影响解密过程。只要知道正确的密钥，就可以使用S-DES算法将这个"乱码"字符串解密回原始字符串。

## 5. 联系信息
若有任何疑问或建议，请联系开发团队：1281673219@qq.com

## 6. 其他
我们的系统支持浏览器**在线使用**，如果你有需求，请你联系开发团队开启服务器。









