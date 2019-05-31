package com.erik.contactdemo;

import com.github.promeg.pinyinhelper.Pinyin;

public class PinYinUtils {

    /**
     * 将hanzi转成拼音
     *
     * @param hanzi 汉字或字母
     * @return 拼音
     */
    public static String getPinyin(String hanzi) {
        StringBuilder sb = new StringBuilder();
        //HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //由于不能直接对多个汉子转换，只能对单个汉子转换
        char[] arr = hanzi.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isWhitespace(arr[i])) {
                continue;
            }
            try {
                String ss = Pinyin.toPinyin(arr[i]);
                //String[] pinyinArr = PinyinHelper.toHanyuPinyinStringArray(arr[i], format);
                /*if (pinyinArr != null) {
                    sb.append(pinyinArr[0]);
                } else {
                    sb.append(arr[i]);
                }*/
                sb.append(ss);
            } catch (Exception e) {
                e.printStackTrace();
                //不是正确的汉字
                sb.append(arr[i]);
            }

        }
        return sb.toString();
    }

}
