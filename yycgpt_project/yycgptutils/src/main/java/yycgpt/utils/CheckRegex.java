package yycgpt.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckRegex {
	//判断是否是数字（包括小数）
	public static boolean isNumeric_xs(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]+.?[0-9]"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	//判断是否是整数（包括小数）
		public static boolean isNumeric_zs(String str){ 
			   Pattern pattern = Pattern.compile("[0-9]*"); 
			   Matcher isNum = pattern.matcher(str);
			   if( !isNum.matches() ){
			       return false; 
			   } 
			   return true; 
			}
}
