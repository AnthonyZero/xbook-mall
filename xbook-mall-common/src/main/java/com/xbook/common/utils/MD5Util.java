package com.xbook.common.utils;


import java.security.MessageDigest;

public class MD5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     *
     * @param origin
     * @param charsetname
     * @return
     */
    private static String md5Encode(String origin, String charsetname) {
        String result = null;
        try {
            result = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                result = byteArrayToHexString(md.digest(result.getBytes()));
            else
                result = byteArrayToHexString(md.digest(result.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return result.toUpperCase();
    }

    /**
     * md5 大写32位字符串
     * @param origin
     * @return
     */
    public static String encrypt(String origin) {
        return md5Encode(origin, "utf-8");
    }


    public static void main(String[] args) {
        System.out.println(encrypt("123456"));
    }

}
