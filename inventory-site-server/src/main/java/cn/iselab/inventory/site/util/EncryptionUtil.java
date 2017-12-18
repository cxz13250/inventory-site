package cn.iselab.inventory.site.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class EncryptionUtil {

    private static final Logger LOG = LoggerFactory.getLogger(EncryptionUtil.class);

    // 定义 加密算法,可用 DES,DESede,Blowfish
    private static String Algorithm = "DES";
    private static String defaultKey = "witest.net";


    public static String encryptMD5Hex(String str) {
        String value = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes());

            // MD5 的计算结果是一个 128 位的长整数，用字节表示就是 16 个字节
            byte temp[] = md5.digest();
            // 每个字节用 16 进制表示的话，使用两个字符
            char hex[] = new char[16 * 2];

            // 表示转换结果中对应的字符位置
            int k = 0;
            // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
            for (int i = 0; i < 16; i++) {
                byte b = temp[i];
                // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
                hex[k++] = hexDigits[b >>> 4 & 0xf];
                // 取字节中低 4 位的数字转换
                hex[k++] = hexDigits[b & 0xf];
            }

            // 转换后的结果转换为字符串
            value = new String(hex);

        } catch (Exception e) {
            value = "";
            LOG.error("", e);
        }
        return value;
    }

    public static String encryptMD5(String str) {
        String s = str;
        if (s == null) {
            return "";
        } else {
            String value = null;
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                LOG.error("", ex);
            }
            sun.misc.BASE64Encoder baseEncoder = new sun.misc.BASE64Encoder();
            try {
                value = baseEncoder.encode(md5.digest(s.getBytes("utf-8")));
            } catch (Exception ex) {
            }
            return value;
        }
    }

    public static String transferMD5ToMD5Hex(String str) {

        String value = null;
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            byte[] temp = base64Decoder.decodeBuffer(str);
            // 每个字节用 16 进制表示的话，使用两个字符
            char hex[] = new char[16 * 2];

            // 表示转换结果中对应的字符位置
            int k = 0;
            // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
            for (int i = 0; i < 16; i++) {
                byte b = temp[i];
                // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
                hex[k++] = hexDigits[b >>> 4 & 0xf];
                // 取字节中低 4 位的数字转换
                hex[k++] = hexDigits[b & 0xf];
            }

            // 转换后的结果转换为字符串
            value = new String(hex);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;

    }

    public static String encryptDES(String content) throws Exception {
        return encryptDES(content, defaultKey);
    }

    public static String decryptDES(String content) throws Exception {
        return decryptDES(content, defaultKey);
    }


    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encryptDES(String data, String key) throws Exception {
        byte[] bt = encryptDES(data.getBytes(), key.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decryptDES(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decryptDES(buf, key.getBytes());
        return new String(bt);
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encryptDES(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(Algorithm);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decryptDES(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Algorithm);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(Algorithm);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(data);
    }

}
