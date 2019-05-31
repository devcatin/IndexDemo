package com.erik.contactdemo.utils;

import com.github.promeg.pinyinhelper.Pinyin;
import com.github.promeg.pinyinhelper.PinyinMapDict;

import java.util.HashMap;
import java.util.Map;

public class PinYinUtils {

    /**
     * 将hanzi转成拼音
     *
     * @param hanzi 汉字或字母
     * @return 拼音
     */
    public static String getPinyin(String hanzi) {
        configWithData();
        StringBuilder sb = new StringBuilder();
        //由于不能直接对多个汉子转换，只能对单个汉子转换
        char[] arr = hanzi.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.isWhitespace(arr[i])) {
                continue;
            }
            try {
                String ss = Pinyin.toPinyin(arr[i]);
                sb.append(ss);
            } catch (Exception e) {
                e.printStackTrace();
                //不是正确的汉字
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    private static void configWithData() {
        // 添加自定义词典
        Pinyin.init(Pinyin.newConfig()
                .with(new PinyinMapDict() {
                    @Override
                    public Map<String, String[]> mapping() {
                        HashMap<String, String[]> map = new HashMap<String, String[]>();
                        map.put("单丹",  new String[]{"SHAN","DAN"});
                        map.put("重耳", new String[]{"CHONG","ER"});
                        map.put("重庆",  new String[]{"CHONG", "QING"});
                        return map;
                    }
                }));
    }

}
