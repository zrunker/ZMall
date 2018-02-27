package cc.ibooker.zmalllib.utils;

import java.util.regex.Pattern;

/**
 * 常用正则表达是总结
 * Created by 邹峰立 on 2017/7/13.
 */
public class RegularExpressionUtil {
    // 邮箱验证
    public static boolean isEmail(String email) {
        String regex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        // String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.matches(regex, email);
    }

    // 域名验证
    public static boolean isDomainName(String domainName) {
        // 完整的域名至少包括两个名字（比如google.com，由google和com构成），最后可以有一个表示根域的点
        String regex = "[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?";
        return Pattern.matches(regex, domainName);
    }

    // InternetURL验证
    public static boolean isInternetURL(String intentUrl) {
        // http://github.com/
        String regex = "^http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
        return Pattern.matches(regex, intentUrl);
    }

    // 手机号码验证
    public static boolean isPhone(String phone) {
        /*
         * 要更加准确的匹配手机号码只匹配11位数字是不够的，比如说就没有以144开始的号码段， 　　
		 * 故先要整清楚现在已经开放了多少个号码段，国家号码段分配如下：
		 * 移动：134、135、136、137、138、139、147、150、151、157(TD)、158、159、178、182、183、184、187、188
		 * 联通：130、131、132、152、155、156、185、186
		 * 电信：133、153、（1349卫通）、173、177、180、181、189
		 * 阿里通信：170、171、172
		 * 腾讯通信：
		 */
        String regex = "^((13[0-9])|(147)|(15[^4,\\D])|(17[6-8])|(17[0-3])|(18[0-9]))\\d{8}$";
        return Pattern.matches(regex, phone);
    }

    // 固定电话验证
    public static boolean isMobile(String mobile) {
        String regex = "((^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-?\\d{7,8}-(\\d{1,4})$))";
        return Pattern.matches(regex, mobile);
    }

    // 姓名验证
    public static boolean isName(String name) {
        // 目前最长中文名15个字，目前最长英文名116个
        // 2~15个中文或者3~116个英文
        String regex = "(([\u4e00-\u9fa5]{2,15})|([a-zA-Z]{3,116}))";
        return Pattern.matches(regex, name);
    }

    // 帐号是否合法(字母开头，允许5-16字节，允许字母数字下划线)
    public static boolean isAccount(String account) {
        String regex = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
        return Pattern.matches(regex, account);
    }

    // 密码(以字母开头，长度在4~20之间，只能包含字母、数字和下划线)
    public static boolean isPassword(String password) {
        String regex = "^[a-zA-Z]\\w{3,19}$";
        return Pattern.matches(regex, password);
    }

    // 强密码(必须包含大小写字母和数字的组合，不能使用特殊字符，长度在8-20之间)
    public static boolean isStrongPassword(String password) {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        return Pattern.matches(regex, password);
    }

    // 日期格式：yyyy-MM-dd
    public static boolean isDate(String date) {
        String regex = "^\\d{4}-\\d{1,2}-\\d{1,2}";
        return Pattern.matches(regex, date);
    }

    // 中国邮政编码(中国邮政编码为6位数字)
    public static boolean isZipCode(String zipCode) {
        String regex = "[1-9]\\d{5}(?!\\d)";
        return Pattern.matches(regex, zipCode);
    }

    // 腾讯QQ号(腾讯QQ号从10000开始)
    public static boolean isQq(String qq) {
        String regex = "[1-9][0-9]{4,}";
        return Pattern.matches(regex, qq);
    }

    // IP地址(提取IP地址时有用)
    public static boolean isIp(String ip) {
        String regex = "\\d+\\.\\d+\\.\\d+\\.\\d+";
        return Pattern.matches(regex, ip);
    }

    // 中文字符
    public static boolean isChinese(String chinese) {
        String regex = "[\\u4e00-\\u9fa5]";
        return Pattern.matches(regex, chinese);
    }

    // 字符串是否为数字
    public static boolean isNumeric(String numer) {
        String regex = "[0-9]*";
        return Pattern.matches(regex, numer);
    }
}
