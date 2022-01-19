package com.ms.blogserver.utils;

import com.ms.blogserver.core.constant.contexts.DigitalContexts;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * @description:
 * @author: zhh
 * @time: 2022/1/17
 */
public class StrComparatorUtils<T> implements Comparator<T> {
    private String str1, str2;
    private int pos1, pos2, len1, len2;
    private final String propertyName;
    private boolean isDesc = false;

    /**
     * 传入对象的属性名，根据该属性值进行排序
     * @param propertyName
     */
    public StrComparatorUtils(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * 传入对象的属性名，根据该属性值进行排序
     * @param propertyName
     * @param isDesc 是否倒序，默认为false
     */
    public StrComparatorUtils(String propertyName, boolean isDesc) {
        this.propertyName = propertyName;
        this.isDesc = isDesc;
    }

    @Override
    public int compare(T o1, T o2) {
        if (isDesc) {
            str2 = getPropertyValue(o1);
            str1 = getPropertyValue(o2);
        } else {
            str1 = getPropertyValue(o1);
            str2 = getPropertyValue(o2);
        }
        len1 = str1.length();
        len2 = str2.length();
        pos1 = pos2 = 0;

        int result = 0;
        while (result == 0 && pos1 < len1 && pos2 < len2) {
            char ch1 = str1.charAt(pos1);
            char ch2 = str2.charAt(pos2);

            if (Character.isDigit(ch1)) {
                result = Character.isDigit(ch2) ? compareNumbers() : -1;
            } else if (Character.isLetter(ch1)) {
                result = Character.isLetter(ch2) ? compareOther(true) : 1;
            } else {
                result = Character.isDigit(ch2) ? 1
                        : Character.isLetter(ch2) ? -1
                        : compareOther(false);
            }
            pos1++;
            pos2++;
        }

        return result == 0 ? len1 - len2 : result;
    }

    private String getPropertyValue(T o1) {
        String str = "";
        try {
            Field field = o1.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            Object obj = field.get(o1);
            if (obj != null){
                return obj.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private int compareNumbers() {
        int end1 = pos1 + 1;
        while (end1 < len1 && Character.isDigit(str1.charAt(end1))) {
            end1++;
        }
        int fullLen1 = end1 - pos1;
        while (pos1 < end1 && str1.charAt(pos1) == DigitalContexts.ZERO) {
            pos1++;
        }

        int end2 = pos2 + 1;
        while (end2 < len2 && Character.isDigit(str2.charAt(end2))) {
            end2++;
        }
        int fullLen2 = end2 - pos2;
        while (pos2 < end2 && str2.charAt(pos2) == DigitalContexts.ZERO) {
            pos2++;
        }

        int delta = (end1 - pos1) - (end2 - pos2);
        if (delta != 0) {
            return delta;
        }

        while (pos1 < end1 && pos2 < end2) {
            delta = str1.charAt(pos1++) - str2.charAt(pos2++);
            if (delta != 0) {
                return delta;
            }
        }

        pos1--;
        pos2--;

        return fullLen2 - fullLen1;
    }

    private int compareOther(boolean isLetters) {
        char ch1 = str1.charAt(pos1);
        char ch2 = str2.charAt(pos2);

        if (ch1 == ch2) {
            return 0;
        }

        if (isLetters) {
            ch1 = Character.toUpperCase(ch1);
            ch2 = Character.toUpperCase(ch2);
            if (ch1 != ch2) {
                ch1 = Character.toLowerCase(ch1);
                ch2 = Character.toLowerCase(ch2);
            }
        }
        return ch1 - ch2;
    }
}

