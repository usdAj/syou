/**
 * @(#)Tokens.java 1.01 26/07/2006
 * Copyright 2005 usd.sie.dendai.ac.jp, All rights reserved.
 */
package jp.tdu.util;

import java.util.Iterator;
import java.lang.Iterable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ������i�Q�j������A�f���~�b�^�[�i��؂蕶���j�ŋ�؂�ꂽ�������
 * �Ǘ�����N���X�ł��B���̗v�f��int�^�Along�^���邢��double�^�֕ϊ�
 * ���邱�Ƃ��ł��܂��B
 * @version 1.01, 25/01/2011
 * @author miya@sie.dendai.ac.jp
 */
public class Tokens implements Iterable<String>{
	private static final String delim = " \t";
	private static final String quoteChars = "\'\"";
	private static final Class<?>[] types = {String.class};
	private ArrayList<String> list = new ArrayList<String>();
	private String string;
	
	/**
	 * �p�����[�^�Ƃ��ĕ�����i�Q�j���󂯎��R���X�g���N�^�B
	 * �������A�p�����[�^�Ƃ��ăf���~�b�^�[�A���p�����ȗ������ꍇ�ɂ́A
	 * �f���~�b�^�[��" \t"�ŋ󔒕����ƃ^�u�����A���p����"\'\""�ŃV���O���E�_�u��quote����\������܂��B
	 * ����̈��p���ň͂܂ꂽ���������͂����ꍇ�A��؂蕶����g���Ă��Ȃ����p���������Ƃ��ĔF�����܂����A
	 * ��������͂����߂Ɏg�p��������̈��p���͍폜����܂��B�܂��A����̈��p���ň͂܂ꂽ������̒��ɁA
	 * ���̈��p���𕶎��Ƃ��ē��͂���ɂ́A���̈��p����2�d�ɋL�q���܂��B
	 * @param string ������i�S�j�B
	 */
	public Tokens(String string){
		this(string, Tokens.delim, Tokens.quoteChars);
	}
	
	/**
	 * �p�����[�^�Ƃ��ĕ�����i�Q�j�ƃf���~�b�^�[�i��؂蕶���j���󂯎��R���X�g���N�^�B
	 * @param string ������i�S�j�B
	 * @param delim �f���~�b�^�[�i��؂蕶���j�B
	 */
	public Tokens(String string, String delim){
		this(string, delim, Tokens.quoteChars);
	}
	
	/**
	 * �p�����[�^�Ƃ��ĕ�����i�Q�j�A�f���~�b�^�[�i��؂蕶���j�ƈ��p�����󂯎��R���X�g���N�^�B
	 * @param string ������i�S�j�B
	 * @param delim �f���~�b�^�[�i��؂蕶���j�B
	 * @param quoteChars ���p���B
	 */
	public Tokens(String string, String delim, String quoteChars){
		if(delim == null){
			delim = Tokens.delim;
		}
		if(quoteChars == null){
			quoteChars = Tokens.quoteChars;
		}
		if(string != null){
			this.string = string;
			int current = this.skipInit(0, string, delim);
			int max = string.length();
			while(current < max){
				int start = current;
				if(quoteChars.indexOf(string.charAt(start)) < 0){
					while((current < max) && (delim.indexOf(string.charAt(current)) < 0)){
						current++;
					}
					this.list.add(string.substring(start, current));
				}else{
					current++;
					StringBuffer sb = new StringBuffer();
					if(start + 1 < max){
						while((current < max) && ((string.charAt(current) != string.charAt(start)) || (current + 1 < max ? string.charAt(current + 1) == string.charAt(start): false))){
							if(string.charAt(current) != string.charAt(start)){
								sb.append(string.charAt(current));
							}else{
								sb.append(string.charAt(++current));
							}
							current++;
						}
						this.list.add(sb.toString());
						current++;
					}
				}
				current = this.skipDelim(current, string, delim);
			}
		}
	}
	int skipDelim(int current, String string, String delim){
		while((current < string.length()) && (delim.indexOf(string.charAt(current)) >= 0)){
			current++;
		}
		return current;
	}
	int skipInit(int current, String string, String delim){
		return this.skipDelim(current, string, delim);
	}
	
	/**
	 * �w�肳�ꂽ�ʒu�̕������int�^�ɕϊ����܂��B
	 * �������A������int�^�ɕϊ��ł��Ȃ��ꍇ��Integer.MAX_VALUE���ԋp����܂��B
	 * @param number ���ԁB
	 * @return �w�肵�����Ԃ̒l�B
	 */
	public int getInt(int number){
		if(this.list.size() > number){
			try{
				return Integer.parseInt(this.list.get(number));
			}catch(NumberFormatException e){
			}
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * �w�肳�ꂽ�ʒu�̕������long�^�ɕϊ����܂��B
	 * �������A������logn�^�ɕϊ��ł��Ȃ��ꍇ��Long.MAX_VALUE���ԋp����܂��B
	 * @param number ���ԁB
	 * @return �w�肵�����Ԃ̒l�B
	 */
	public long getLong(int number){
		if(this.list.size() > number){
			try{
				return Long.parseLong(this.list.get(number));
			}catch(NumberFormatException e){
			}
		}
		return Long.MAX_VALUE;
	}
	
	/**
	 * �w�肳�ꂽ�ʒu�̕������ԋp���܂��B
	 * @param number ���ԁB
	 * @return �w�肵�����Ԃ̕�����B
	 */
	public String getString(int number){
		if(this.list.size() > number){
			return this.list.get(number);
		}
		return null;
	}
	
	/**
	 * �R���X�g���N�^�ŗ^����ꂽ������i�S�j�B
	 * @return ������i�S�j�B
	 */
	public String toString(){
		return this.string;
	}
	
	/**
	 * �w�肳�ꂽ�ʒu�̕������double�^�ɕϊ����܂��B
	 * �������A������double�^�ɕϊ��ł��Ȃ��ꍇ��Double.MAX_VALUE���ԋp����܂��B
	 * @param number ���ԁB
	 * @return �w�肵�����Ԃ̒l�B
	 */
	public double getDouble(int number){
		if(this.list.size() > number){
			try{
				return new Double(this.list.get(number)).doubleValue();
			}catch(NumberFormatException e){
			}
		}
		return Double.MAX_VALUE;
	}
	
	/**
	 * ������i�S�j�ɂ���v�f�̌���ԋp���܂��B
	 * @return ������i�S�j�ɂ���v�f�̌��B
	 */
	public int size(){
		return this.list.size();
	}
	
	/**
	 * ���̕�����i�S�j�̗v�f��K�؂ȏ����ŌJ��Ԃ��������锽���q��ԋp���܂��B 
	 * @return ������i�S�j�̗v�f��K�؂ȏ����ŌJ��Ԃ��������锽���q�B
	 */
	public Iterator<String> iterator(){
		return Collections.unmodifiableList(this.list).iterator();
	}
	
	/**
	 * ������i�S�j��������Class�N���X�Ŏw�肵���^�ɕϊ����A�����v�f�Ƃ��郊�X�g��ԋp���܂��B
	 * @param clazz �v�f�̕ϊ��^�B
	 * @return ������i�S�j���i�[�����V�������X�g�B
	 */
	public <T extends Number> List<T> createList(Class<T> clazz) throws 
			InstantiationException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		ArrayList<T> arrayList = new ArrayList<T>(this.list.size());
		Constructor<T> constructor = clazz.getConstructor(Tokens.types);
		for(String s: this.list){
			arrayList.add(constructor.newInstance(s));
		}
		return arrayList;
	}
	
	/**
	 * ���̕�����i�S�j���i�[�������X�g��ԋp���܂��B
	 * @return ������i�S�j���i�[�������X�g�B
	 */
	public List<String> getList(){
		return Collections.unmodifiableList(this.list);
	}
	
	/**
	 * CSV�`���i�f�[�^�t�H�[�}�b�g�`���̈�j�Ńt�H�[�}�b�g���ꂽ������i�S�j
	 * ���󂯎��Tokens�C���X�^���X�𐶐����܂��B
	 * @param string ������i�S�j�B
	 * @return CSV�`���p��Tokens�C���X�^���X�B
	 */
	public static Tokens getCSVInstance(String string){
		char d = ',';
		String delim = new String(new char[]{d});
		Tokens tokens = new Tokens(string, delim){
			int skipDelim(int current, String string, String delim){
				return ++current;
			}
			int skipInit(int current, String string, String delim){
				return 0;
			}
		};
		if(tokens.toString().charAt(tokens.toString().length() - 1) == d){
			tokens.list.add("");
		}
		return tokens;
	}
	
	/**
	 * Tokens�C���X�^���X���󂯎��ACSV�`���i�f�[�^�t�H�[�}�b�g�`���̈�j�Ńt�H�[�}�b�g���ꂽ������i�S�j
	 * �ϊ����܂��B
	 * @param CSV�`���p��Tokens�C���X�^���X�B
	 * @return string ������i�S�j�B
	 */
	public static String toCSV(Tokens tokens){
		char quoteChar = '"';
		if(tokens.size() == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Tokens.convert(tokens.getString(0), quoteChar));
		for(int i = 0; i < tokens.size(); i++){
			sb.append(',');
			sb.append(Tokens.convert(tokens.getString(i), quoteChar));
		}
		return sb.toString();
	}
	
	public static String convert(String string, char quoteChar){
		try{
			new Double(string).doubleValue();
			return string;
		}catch(NumberFormatException e){
		}
		int current = 0;
		StringBuffer sb = new StringBuffer();
		if(string.indexOf(quoteChar) < 0){
			return string;
		}
		sb.append(quoteChar);
		while(current < string.length()){
			int start = current;
			current = string.indexOf(quoteChar, start);
			if(current < 0){
				sb.append(string.substring(start, string.length()));
				sb.append(quoteChar);
				return sb.toString();
			} else {
				sb.append(string.substring(start, current));
				sb.append(quoteChar);
				sb.append(quoteChar);
				current++;
			}
		}
		return "";
	}
	
}
