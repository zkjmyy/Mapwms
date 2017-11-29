package com.gxsn.gaodemapdemo.utils;

/**
 * Created by zkj on 2017/02/15
 * GreatWall
 */

public class PinyinUtils {


//
//    static final int GB_SP_DIFF = 160;
//    // 存放国标一级汉字不同读音的起始区位码
//    static final int[] secPosValueList = { 1601, 1637, 1833, 2078, 2274, 2302,
//            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
//            4086, 4390, 4558, 4684, 4925, 5249, 5600 };
//    // 存放国标一级汉字不同读音的起始区位码对应读音
//    static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
//            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
//            'y', 'z' };
//
//
//
//    /**
//     * 汉字转换位汉语拼音首字母，英文字符不变
//     * @param chines 汉字
//     * @return 拼音
//     */
//    public static String converterToFirstSpell(String chines){
//        String pinyinName = "";
//        char[] nameChar = chines.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        if (nameChar == null){
//            return "";
//        }
//        for (int i = 0; i < nameChar.length; i++) {
//            if (nameChar[i] > 128) {
//                try {
//                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                }
//            }else{
//                pinyinName += nameChar[i];
//            }
//        }
//        return pinyinName;
//    }
//
//    /**
//     * 汉字转换位汉语拼音，英文字符不变
//     * @param chines 汉字
//     * @return 拼音
//     */
//    public static String converterToSpell(String chines){
//        String pinyinName = "";
//        char[] nameChar = chines.toCharArray();
//        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        for (int i = 0; i < nameChar.length; i++) {
//            if (nameChar[i] > 128) {
//                try {
//                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
//                } catch (BadHanyuPinyinOutputFormatCombination e) {
//                    e.printStackTrace();
//                }
//            }else{
//                pinyinName += nameChar[i];
//            }
//        }
//        return pinyinName;
//    }
//
//
//
//    public static String getSpells(String characters) {
//        StringBuffer buffer = new StringBuffer();
//        for (int i = 0; i < characters.length(); i++) {
//
//            char ch = characters.charAt(i);
//            if ((ch >> 7) == 0) {
//                // 判断是否为汉字，如果左移7为为0就不是汉字，否则是汉字
//            } else {
//                char spell = getFirstLetter(ch);
//                buffer.append(String.valueOf(spell));
//            }
//        }
//        return buffer.toString();
//    }
//
//    // 获取一个汉字的首字母
//    public static Character getFirstLetter(char ch) {
//
//        byte[] uniCode = null;
//        try {
//            uniCode = String.valueOf(ch).getBytes("GBK");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return null;
//        }
//        if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
//            return null;
//        } else {
//            return convert(uniCode);
//        }
//    }
//
//    /**
//     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
//     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
//     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
//     */
//    static char convert(byte[] bytes) {
//        char result = '-';
//        int secPosValue = 0;
//        int i;
//        for (i = 0; i < bytes.length; i++) {
//            bytes[i] -= GB_SP_DIFF;
//        }
//        secPosValue = bytes[0] * 100 + bytes[1];
//        for (i = 0; i < 23; i++) {
//            if (secPosValue >= secPosValueList[i]
//                    && secPosValue < secPosValueList[i + 1]) {
//                result = firstLetter[i];
//                break;
//            }
//        }
//        return result;
//    }
//

}
