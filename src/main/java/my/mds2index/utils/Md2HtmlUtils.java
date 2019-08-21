package my.mds2index.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pegdown.PegDownProcessor;

import my.mds2index.config.Mds2indexConfig;

public class Md2HtmlUtils {
    /**
     * MarkDown文本转化成HTML文本
     *
     * @param markdownTxt
     *
     * @return
     */
    public static String parseHtml(String markdownTxt) {
        try {
            PegDownProcessor pdp = new PegDownProcessor(Integer.MAX_VALUE);
            String html = pdp.markdownToHtml(markdownTxt);
            return html;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 清除中内容中0个或只有一个字的标签对  如 <LL><XX>汉</XX><FF>字</FF></LL>
     *
     * @param htmlText
     *
     * @return
     */
    public static String parsePageByParam(String htmlText) {
        try {
            if (isBlank(htmlText)) {
                return "";
            }
            String regExp = "##\\s*(.+)\\s*##";

            Matcher paramMatcher = Pattern.compile(regExp).matcher(htmlText);
            StringBuffer sb = new StringBuffer();
            while (paramMatcher.find()) {
                String paramKey = paramMatcher.group(1);
                String paramValue = Mds2indexConfig.get(paramKey);
                paramValue = escapeExprSpecialWord(paramValue);
                paramMatcher.appendReplacement(sb, toString(paramValue));
            }
            paramMatcher.appendTail(sb);
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return htmlText;
        }
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     *
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (!isBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str != null && (strLen = str.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static String toString(String str, String nullStr) {
        if (null == str) {
            return nullStr;
        } else {
            return str;
        }
    }

    public static String toString(String str) {
        return toString(str, "");
    }

}
