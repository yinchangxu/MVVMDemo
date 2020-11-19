package com.example.myapplication.util;

import android.content.Context;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.Toast;


/**
 * Function: EditText 输入文本过滤器工具类
 * Author: ShiJingFeng
 * Date: 2019/10/6 17:34
 * Description:
 */
public class EditTextInputFilterUtil {

    private EditTextInputFilterUtil() {}

    /**
     * 简单工厂模式创建对象
     * @return 对象
     */
    public static EditTextInputFilterUtil create() {
        return new EditTextInputFilterUtil();
    }

    /**
     * 获取 禁止系统表情输入 过滤器
     * @return 禁止系统表情输入 过滤器
     */
    public InputFilter getProhibitSystemEmojiInputFilter(Context context) {
        return (CharSequence source, int start, int end, Spanned dest, int dstart, int dend) -> {
            StringBuffer buffer = new StringBuffer();
            for (int i = start; i < end; i++) {
                char codePoint = source.charAt(i);
                if (!isSystemEmoji(codePoint)) {
                    buffer.append(codePoint);
                } else {
                    Toast.makeText(context,"暂不支持系统表情",Toast.LENGTH_SHORT).show();
                    i++;
                    continue;
                }
            }
            if (source instanceof Spanned) {
                SpannableString sp = new SpannableString(buffer);
                TextUtils.copySpansFrom((Spanned) source, start, end, null,
                        sp, 0);
                return sp;
            } else {
                return buffer;
            }
        };
    }

    /**
     * 判断是不是系统表情
     * @param codePoint 字符
     * @return true: 是系统表情 false: 不是系统表情
     */
    private boolean isSystemEmoji(char codePoint) {
        if ((codePoint == 0x0)
                || (codePoint == 0x9)
                || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF))) {
            return false;
        }
        return true;
    }

}
