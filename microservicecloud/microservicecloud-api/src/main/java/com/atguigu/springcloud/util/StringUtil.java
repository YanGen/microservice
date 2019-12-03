package com.atguigu.springcloud.util;

/**
 * @ClassName StringUtil
 * @Description TODO
 * @Author dong <feng.db@uniteddata.com>
 * @Date 2019/11/14
 * @Version 1.0.0
 **/
public class StringUtil {
    /**
     * 将带下划线转成字符串转成驼峰式首字母小写
     * @param key
     * @return
     */
    public static String transferLowerCaseCamel(String key) {
        if (key.trim().length() == 0) return key;
        char[] chars = key.toCharArray();
        for (int i = 0;i<chars.length;++i){
            if (chars[i] == '_' && i+1<chars.length) {
                chars[i+1] -= 32;
            }
        }
        return new String(chars);
    }

    /**
     * 将字符串首字母变成小写
     * @param str
     * @return
     */
    public static String toFirstLowerCase(String str){
        char[] chars = str.toCharArray();
        if (chars[0]>=65 && chars[0] <= 90) chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 将驼峰字符串转换成下划线连接（下划线后每个首字母小写）
     * @return
     */
    public static String toUnderLine(String str){
        char[] chars = StringUtil.toFirstLowerCase(str).toCharArray();
        int i = 0,j = 0;
        StringBuffer buffer = new StringBuffer();
        for (char c : chars){
            if(c>=65 && c<= 90){
                buffer.append('_');
                buffer.append(c += 32);
            }else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }
}
