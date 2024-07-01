package com.luck.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Clob;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String工具类
 *
 * @author luck
 * @date 2023-10-26
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /**
     * 驼峰与下划线字符串互转Pattern
     */
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(StringUtils.trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 缩略字符串（不区分中英文字符）
     *
     * @param str    目标字符串
     * @param length 截取长度
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            log.error("截取字符异常", e);
        }
        return "";
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (StringUtils.isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }

    /**
     * 缩略字符串（替换html）
     *
     * @param str    目标字符串
     * @param length 截取长度
     */
    public static String rabbr(String str, int length) {
        return abbr(replaceHtml(str), length);
    }

    /**
     * 全角字符转半角字符
     *
     * @param originalStr 原始字符
     */
    public static final String full2HalfChange(String originalStr) {
        StringBuffer outStrBuf = new StringBuffer(StringUtils.EMPTY);
        String tempStr;
        byte[] b = null;
        for (int i = 0; i < originalStr.length(); i++) {
            tempStr = originalStr.substring(i, i + 1);
            // 全角空格转换成半角空格
            if (tempStr.equals("　")) {
                outStrBuf.append(" ");
                continue;
            }
            try {
                b = tempStr.getBytes("unicode");
                // 得到 unicode 字节数据
                if (b[2] == -1) {
                    // 表示全角
                    b[3] = (byte) (b[3] + 32);
                    b[2] = 0;
                    outStrBuf.append(new String(b, "unicode"));
                } else {
                    outStrBuf.append(tempStr);
                }
            } catch (UnsupportedEncodingException e) {
                log.error("全角转半角异常", e);
            }

        }
        return outStrBuf.toString();
    }

    /**
     * 字符串转Html格式
     *
     * @param content
     */
    public static String htmlUnescape(String content) {
        if (content == null || content.equals("")) {
            return null;
        } else {
            return StringEscapeUtils.unescapeHtml4(content);
        }
    }

    /**
     * textarea 存储与显示 保持格式
     *
     * @param value
     */
    public static String textAreaConvert(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replaceAll(" ", "&nbsp;");
            value = value.replaceAll("\r\n", "<br>");
            value = value.replaceAll("\n", "<br>");
        }
        return value;
    }

    /**
     * 随机生成定长字符串
     *
     * @param length
     * @param number 为true时仅生成数字
     */
    public static String createRandomCharData(int length, boolean number) {
        int chooseNum = 3;
        if (number) {
            chooseNum = 1;
        }
        StringBuilder sb = new StringBuilder();
        SecureRandom rand = new SecureRandom();//随机用以下三个随机生成器
        SecureRandom randdata = new SecureRandom();
        int data = 0;
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(chooseNum);
            //目的是随机选择生成数字，大小写字母
            switch (index) {
                case 0:
                    data = randdata.nextInt(10);//仅仅会生成0~9
                    sb.append(data);
                    break;
                case 1:
                    data = randdata.nextInt(26) + 65;//保证只会产生65~90之间的整数
                    sb.append((char) data);
                    break;
                case 2:
                    data = randdata.nextInt(26) + 97;//保证只会产生97~122之间的整数
                    sb.append((char) data);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 浮点类型数据相加
     *
     * @param dataList
     * @param newScale 保留小数位
     */
    public static String addDecimal(List<String> dataList, int newScale) {
        BigDecimal result = BigDecimal.ZERO;
        if (!CollectionUtils.isEmpty(dataList)) {
            for (String data : dataList) {
                if (StringUtils.isNotBlank(data)) {
                    result = result.add(BigDecimal.valueOf(Double.valueOf(data)));
                }
            }
        }
        return String.valueOf(result.setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    /**
     * java.sql.Clob类型转换成String类型
     *
     * @param clob
     */
    public static String valueOf(Clob clob) {
        String result = StringUtils.EMPTY;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            reader = clob.getCharacterStream();
            bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            String tempStr;
            while ((tempStr = bufferedReader.readLine()) != null) {
                sb.append("\n");
                sb.append(tempStr);
            }
            // 去掉第一个换行符
            result = sb.toString().replaceFirst("\n", StringUtils.EMPTY);
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            log.error("java.sql.Clob数据转换异常：", e);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    log.error("流关闭异常：", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    log.error("流关闭异常：", e);
                }
            }
        }
        return result;
    }

    /**
     * 驼峰转下划线
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
