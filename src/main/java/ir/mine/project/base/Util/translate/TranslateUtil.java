package ir.mine.project.base.Util.translate;

public class TranslateUtil {

	
	public static String translateToPersian(String str){
		
		return str.replaceAll((char)65279 + "", "").replaceAll("ي", "ی").replaceAll("ك", "ک");
	}
	
}
