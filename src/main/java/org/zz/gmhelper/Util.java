package org.zz.gmhelper;

import java.math.BigInteger;

/**
 * @Description:
 * @Author: xwc1125
 * @Date: 2022/8/21 20:22
 * @Copyright Copyright@2022
 */
public class Util {

    public static BigInteger toBigInt(String hexValue) {
        String cleanValue = cleanHexPrefix(hexValue);
        return toBigIntNoPrefix(cleanValue);
    }

    public static BigInteger toBigIntNoPrefix(String hexValue) {
        return new BigInteger(hexValue, 16);
    }

    public static String cleanHexPrefix(String input) {
        if (containsHexPrefix(input)) {
            return input.substring(2);
        } else {
            return input;
        }
    }

    public static boolean containsHexPrefix(String input) {
        return !isEmpty(input) && input.length() > 1
                && input.charAt(0) == '0' && input.charAt(1) == 'x';
    }

    /**
     * @param ss
     * @return boolean
     * @Title isEmpty
     * @author xwc1125
     * @date 2016年1月22日 下午2:36:09
     */
    public static boolean isEmpty(String... ss) {
        if (ss == null) {
            return true;
        }
        for (String s : ss) {
            if (s == null || s.length() == 0 || s.trim().length() == 0 || s.equals("null") || s.equals("")) {
                return true;
            }
        }
        return false;
    }
}
