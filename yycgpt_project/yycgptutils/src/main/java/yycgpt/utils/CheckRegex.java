package yycgpt.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckRegex {
	// 判断是否是数字（包括小数）
	public static boolean isNumeric_xs(String str) {
		Pattern pattern = Pattern.compile("[0-9]+.?[0-9]");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// 判断是否是整数（包括小数）
	public static boolean isNumeric_zs(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	//判断邮箱的书写	abczhuqiujie@163.com
	public static boolean isEmail_163(String str) {
		Pattern pattern = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[163]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	//匹配所有邮箱
	public static boolean isEmail_all(String str) {
		Pattern pattern = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	//判断手机号码13[0-9]{9}
	
	public static boolean isEmail_phone(String str) {
		Pattern pattern = Pattern.compile("13[0-9]{9}");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		
		System.out.println(isEmail_phone("18819491842"));
	}

	
}
