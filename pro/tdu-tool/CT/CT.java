import java.io.File;
import java.util.Map;

public class CT {
	public static void main(String[] args) {
		String jarFile = "tdu-tool.jar";
		System.out.println("�`�F�b�N�c�[���@" + jarFile);
		
		String key = "CLASSPATH";
		String value = ".;tdu-tool.jar";
		
		Map<String, String> env = System.getenv();
		String keyValue = env.get(key);
		if(keyValue == null) {
			System.out.println(key + " ������܂���");
			String k = searchKey(env, "CLASS");
			if( k != null ) {
				System.out.println(k + " ���ގ����Ă��܂��D�X�y���~�X���l�����܂�");
			} else {
				k = searchValue(env, "tdu");
				if( k != null ) {
					System.out.println(k + " ���ގ����Ă��܂��D�X�y���~�X���l�����܂�");
				}
			}
			System.out.println();
			System.out.println("�����ϐ��̕ҏW��́C�R�}���h�v�����v�g����āC�J�������Ă�������");
			System.exit(2);
		}
		
		System.out.println(key + " " + keyValue);
		if(!keyValue.contains(value)) {
			System.out.println(value + " �ƈ�v���Ă��܂���@�X�y���~�X���l�����܂�");
			if( !keyValue.startsWith(".") ) {
				System.out.println("�擪�� . ������܂���");
			}
			if( !keyValue.contains(";") ) {
				System.out.println("; ������܂���");
			}
			if( !keyValue.contains("tdu") ) {
				System.out.println("tdu ������܂���");
			}
			if( !keyValue.contains("-") ) {
				System.out.println("- ������܂���");
			}
			if( !keyValue.contains("tool") ) {
				System.out.println("tool ������܂���");
			}
			if( !keyValue.contains(".jar") ) {
				System.out.println(".jar ������܂���");
			}
			
			System.out.println();
			System.out.println("�����ϐ��̕ҏW��́C�R�}���h�v�����v�g����āC�J�������Ă�������");
			System.exit(3);
		}
		
		File file = new File(jarFile);
		if( !file.exists() ) {
			System.out.println(jarFile + "�@�t�@�C����������܂���");
			System.out.println("�t�@�C�������m�F���āCJava�̃\�[�X�R�[�h��ۑ����Ă���f�B���N�g���i�t�H���_�j�ɒu���Ă�������");
			System.exit(4);
		}
		
		System.out.println("�`�F�b�N���������܂����D");
		System.out.println("�i�m�F�j" + jarFile + "�@�́CJava�̃\�[�X�R�[�h��ۑ����Ă���f�B���N�g���i�t�H���_�j�ɒu���Ă�������");
	}
	
	public static String searchKey(Map<String,String> map, String s) {
		for(String key : map.keySet()) {
			if( key.contains(s)) {
				String v = map.get(key);
				System.out.println("���ϐ��F" + key + " " + v);
				return key;
			}
		}
		return null;
	} 
	
	public static String searchValue(Map<String,String> map, String s) {
		for(String key : map.keySet()) {
			String v = map.get(key);
			if( v.contains(s)) {
				System.out.println("���ϐ��F" + key + " " + v);
				return key;
			}
		}
		return null;
	} 
	
}
