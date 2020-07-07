package com.info.util;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class StringUtil {

    public static Integer toInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Float toPercentFloat(String str) {
        try {
            float coefficient = 1f;
            coefficient = Float.parseFloat(str);
            if (coefficient == 0) {
                coefficient = 100f;
            }
            return coefficient / 100;
        } catch (Exception e) {
            return 1f;
        }
    }

    public static Float toFloat(String str) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
            return 0f;
        }
    }

    public static Float toFloat(BigDecimal str) {
        try {
            return str.floatValue();
        } catch (Exception e) {
            return 0f;
        }
    }

    public static Float toFloat(Double str) {
        try {
            return str.floatValue();
        } catch (Exception e) {
            return 0f;
        }
    }

    public static Double toDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0d;
        }
    }

    private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    //	private final static Pattern phoneNumber = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|17[0135678]|18[0-9])\\\\d{8}$");
    private final static Pattern phoneNumber = Pattern.compile("^1[3|4|5|7|8][0-9]{9}$");

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (email == null || email.trim().length() == 0)
            return false;
        return emailer.matcher(email).matches();
    }

    public static boolean isPhoneNumber(String number) {
        if (number == null || number.trim().length() == 0) {
            return false;
        }
        return phoneNumber.matcher(number).matches();
    }

    public static Integer subStringToInteger(String src, String start, String to) {
        return stringToInteger(subString(src, start, to));
    }

    public static String subString(String src, String start, String to) {
        int indexFrom = start == null ? 0 : src.indexOf(start);
        int indexTo = to == null ? src.length() : src.indexOf(to);
        if (indexFrom >= 0 && indexTo >= 0 && indexFrom <= indexTo) {
            if (null != start) {
                indexFrom += start.length();
            }

            return src.substring(indexFrom, indexTo);
        } else {
            return null;
        }
    }

    public static String subString(String src, String start, String to, boolean toLast) {
        if (!toLast) {
            return subString(src, start, to);
        } else {
            int indexFrom = start == null ? 0 : src.indexOf(start);
            int indexTo = to == null ? src.length() : src.lastIndexOf(to);
            if (indexFrom >= 0 && indexTo >= 0 && indexFrom <= indexTo) {
                if (null != start) {
                    indexFrom += start.length();
                }

                return src.substring(indexFrom, indexTo);
            } else {
                return null;
            }
        }
    }

    public static Integer stringToInteger(String in) {
        if (in == null) {
            return null;
        } else {
            in = in.trim();
            if (in.length() == 0) {
                return null;
            } else {
                try {
                    return Integer.parseInt(in);
                } catch (NumberFormatException var2) {
                    return null;
                }
            }
        }
    }

    public static boolean equals(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equals(b);
        }
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null) {
            return b == null;
        } else {
            return a.equalsIgnoreCase(b);
        }
    }

    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(String value) {
        return isEmpty((CharSequence) value);
    }

    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    /**
     * 判断多个字符串是否其中某一个为空
     *
     * @param value 多个字符串
     * @return true:其中某一个为空 false:全部不为空
     */
    public static boolean isAnyEmpty(String... value) {
        if (value == null || value.length == 0) {
            return true;
        }
        for (String aValue : value) {
            if (isEmpty(aValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断多个字符串是否全都不为空
     *
     * @param value 多个字符串
     * @return true:全不为空, false:有1个或多个为空
     */
    public static boolean isAllNotEmpty(String... value) {
        if (value == null || value.length == 0) {
            return false;
        }
        for (String aValue : value) {
            if (isEmpty(aValue)) {
                return false;
            }
        }
        return true;
    }

    public static int lowerHashCode(String text) {
        if (text == null) {
            return 0;
        } else {
            int h = 0;

            for (int i = 0; i < text.length(); ++i) {
                char ch = text.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    ch = (char) (ch + 32);
                }

                h = 31 * h + ch;
            }

            return h;
        }
    }

    public static boolean isNumber(String str) {
        if (str.length() == 0) {
            return false;
        } else {
            int sz = str.length();
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = str.charAt(0) == '-' ? 1 : 0;
            int i;
            char ch;
            if (sz > start + 1 && str.charAt(start) == '0' && str.charAt(start + 1) == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while (i < str.length()) {
                        ch = str.charAt(i);
                        if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    ch = str.charAt(i);
                    if (ch >= '0' && ch <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (ch == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (ch != '+' && ch != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < str.length()) {
                    ch = str.charAt(i);
                    if (ch >= '0' && ch <= '9') {
                        return true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (!allowSigns && (ch == 'd' || ch == 'D' || ch == 'f' || ch == 'F')) {
                            return foundDigit;
                        } else if (ch != 'l' && ch != 'L') {
                            return false;
                        } else {
                            return foundDigit && !hasExp;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }

    public static boolean isNumber(char[] chars) {
        if (chars.length == 0) {
            return false;
        } else {
            int sz = chars.length;
            boolean hasExp = false;
            boolean hasDecPoint = false;
            boolean allowSigns = false;
            boolean foundDigit = false;
            int start = chars[0] == '-' ? 1 : 0;
            int i;
            char ch;
            if (sz > start + 1 && chars[start] == '0' && chars[start + 1] == 'x') {
                i = start + 2;
                if (i == sz) {
                    return false;
                } else {
                    while (i < chars.length) {
                        ch = chars[i];
                        if ((ch < '0' || ch > '9') && (ch < 'a' || ch > 'f') && (ch < 'A' || ch > 'F')) {
                            return false;
                        }

                        ++i;
                    }

                    return true;
                }
            } else {
                --sz;

                for (i = start; i < sz || i < sz + 1 && allowSigns && !foundDigit; ++i) {
                    ch = chars[i];
                    if (ch >= '0' && ch <= '9') {
                        foundDigit = true;
                        allowSigns = false;
                    } else if (ch == '.') {
                        if (hasDecPoint || hasExp) {
                            return false;
                        }

                        hasDecPoint = true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (ch != '+' && ch != '-') {
                            return false;
                        }

                        if (!allowSigns) {
                            return false;
                        }

                        allowSigns = false;
                        foundDigit = false;
                    } else {
                        if (hasExp) {
                            return false;
                        }

                        if (!foundDigit) {
                            return false;
                        }

                        hasExp = true;
                        allowSigns = true;
                    }
                }

                if (i < chars.length) {
                    ch = chars[i];
                    if (ch >= '0' && ch <= '9') {
                        return true;
                    } else if (ch != 'e' && ch != 'E') {
                        if (!allowSigns && (ch == 'd' || ch == 'D' || ch == 'f' || ch == 'F')) {
                            return foundDigit;
                        } else if (ch != 'l' && ch != 'L') {
                            return false;
                        } else {
                            return foundDigit && !hasExp;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return !allowSigns && foundDigit;
                }
            }
        }
    }

    /**
     * 将num数字左边补0，直至长度为length的字符串。如果num的长度大于length，则不补0。
     *
     * @param num    需要格式化的数字
     * @param length 结果长度
     * @return
     */
    public static String toFixedLengthNum(int num, int length) {
        String str = String.valueOf(num);
        String result = str;
        int strLen = str.length();
        if (strLen < length) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length - strLen; i++) {
                sb.append(0);
            }
            result = sb.append(str).toString();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("num : " + toFixedLengthNum(100, 5));
    }
}
