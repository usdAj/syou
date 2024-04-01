import java.io.File;
import java.util.Map;

public class CT {
	public static void main(String[] args) {
		String jarFile = "tdu-tool.jar";
		System.out.println("チェックツール　" + jarFile);
		
		String key = "CLASSPATH";
		String value = ".;tdu-tool.jar";
		
		Map<String, String> env = System.getenv();
		String keyValue = env.get(key);
		if(keyValue == null) {
			System.out.println(key + " がありません");
			String k = searchKey(env, "CLASS");
			if( k != null ) {
				System.out.println(k + " が類似しています．スペルミスが考えられます");
			} else {
				k = searchValue(env, "tdu");
				if( k != null ) {
					System.out.println(k + " が類似しています．スペルミスが考えられます");
				}
			}
			System.out.println();
			System.out.println("※環境変数の編集後は，コマンドプロンプトを閉じて，開き直してください");
			System.exit(2);
		}
		
		System.out.println(key + " " + keyValue);
		if(!keyValue.contains(value)) {
			System.out.println(value + " と一致していません　スペルミスが考えられます");
			if( !keyValue.startsWith(".") ) {
				System.out.println("先頭に . がありません");
			}
			if( !keyValue.contains(";") ) {
				System.out.println("; がありません");
			}
			if( !keyValue.contains("tdu") ) {
				System.out.println("tdu がありません");
			}
			if( !keyValue.contains("-") ) {
				System.out.println("- がありません");
			}
			if( !keyValue.contains("tool") ) {
				System.out.println("tool がありません");
			}
			if( !keyValue.contains(".jar") ) {
				System.out.println(".jar がありません");
			}
			
			System.out.println();
			System.out.println("※環境変数の編集後は，コマンドプロンプトを閉じて，開き直してください");
			System.exit(3);
		}
		
		File file = new File(jarFile);
		if( !file.exists() ) {
			System.out.println(jarFile + "　ファイルが見つかりません");
			System.out.println("ファイル名を確認して，Javaのソースコードを保存しているディレクトリ（フォルダ）に置いてください");
			System.exit(4);
		}
		
		System.out.println("チェックを完了しました．");
		System.out.println("（確認）" + jarFile + "　は，Javaのソースコードを保存しているディレクトリ（フォルダ）に置いてください");
	}
	
	public static String searchKey(Map<String,String> map, String s) {
		for(String key : map.keySet()) {
			if( key.contains(s)) {
				String v = map.get(key);
				System.out.println("環境変数：" + key + " " + v);
				return key;
			}
		}
		return null;
	} 
	
	public static String searchValue(Map<String,String> map, String s) {
		for(String key : map.keySet()) {
			String v = map.get(key);
			if( v.contains(s)) {
				System.out.println("環境変数：" + key + " " + v);
				return key;
			}
		}
		return null;
	} 
	
}
