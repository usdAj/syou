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
 * 文字列（群）を受取り、デリミッター（区切り文字）で区切られた文字列を
 * 管理するクラスです。その要素はint型、long型あるいはdouble型へ変換
 * することもできます。
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
	 * パラメータとして文字列（群）を受け取るコンストラクタ。
	 * ただし、パラメータとしてデリミッター、引用符を省略した場合には、
	 * デリミッターは" \t"で空白文字とタブ文字、引用符は"\'\""でシングル・ダブルquoteから構成されます。
	 * 同一の引用符で囲まれた文字列を入力した場合、区切り文字や使われていない引用符も文字として認識しますが、
	 * 文字列を囲うために使用した同一の引用符は削除されます。また、同一の引用符で囲まれた文字列の中に、
	 * その引用符を文字として入力するには、その引用符を2重に記述します。
	 * @param string 文字列（郡）。
	 */
	public Tokens(String string){
		this(string, Tokens.delim, Tokens.quoteChars);
	}
	
	/**
	 * パラメータとして文字列（群）とデリミッター（区切り文字）を受け取るコンストラクタ。
	 * @param string 文字列（郡）。
	 * @param delim デリミッター（区切り文字）。
	 */
	public Tokens(String string, String delim){
		this(string, delim, Tokens.quoteChars);
	}
	
	/**
	 * パラメータとして文字列（群）、デリミッター（区切り文字）と引用符を受け取るコンストラクタ。
	 * @param string 文字列（郡）。
	 * @param delim デリミッター（区切り文字）。
	 * @param quoteChars 引用符。
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
	 * 指定された位置の文字列をint型に変換します。
	 * ただし、文字列がint型に変換できない場合はInteger.MAX_VALUEが返却されます。
	 * @param number 順番。
	 * @return 指定した順番の値。
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
	 * 指定された位置の文字列をlong型に変換します。
	 * ただし、文字列がlogn型に変換できない場合はLong.MAX_VALUEが返却されます。
	 * @param number 順番。
	 * @return 指定した順番の値。
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
	 * 指定された位置の文字列を返却します。
	 * @param number 順番。
	 * @return 指定した順番の文字列。
	 */
	public String getString(int number){
		if(this.list.size() > number){
			return this.list.get(number);
		}
		return null;
	}
	
	/**
	 * コンストラクタで与えられた文字列（郡）。
	 * @return 文字列（郡）。
	 */
	public String toString(){
		return this.string;
	}
	
	/**
	 * 指定された位置の文字列をdouble型に変換します。
	 * ただし、文字列がdouble型に変換できない場合はDouble.MAX_VALUEが返却されます。
	 * @param number 順番。
	 * @return 指定した順番の値。
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
	 * 文字列（郡）にある要素の個数を返却します。
	 * @return 文字列（郡）にある要素の個数。
	 */
	public int size(){
		return this.list.size();
	}
	
	/**
	 * この文字列（郡）の要素を適切な順序で繰り返し処理する反復子を返却します。 
	 * @return 文字列（郡）の要素を適切な順序で繰り返し処理する反復子。
	 */
	public Iterator<String> iterator(){
		return Collections.unmodifiableList(this.list).iterator();
	}
	
	/**
	 * 文字列（郡）を引数のClassクラスで指定した型に変換し、それを要素とするリストを返却します。
	 * @param clazz 要素の変換型。
	 * @return 文字列（郡）を格納した新しいリスト。
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
	 * この文字列（郡）を格納したリストを返却します。
	 * @return 文字列（郡）を格納したリスト。
	 */
	public List<String> getList(){
		return Collections.unmodifiableList(this.list);
	}
	
	/**
	 * CSV形式（データフォーマット形式の一つ）でフォーマットされた文字列（郡）
	 * を受け取るTokensインスタンスを生成します。
	 * @param string 文字列（郡）。
	 * @return CSV形式用のTokensインスタンス。
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
	 * Tokensインスタンスを受け取り、CSV形式（データフォーマット形式の一つ）でフォーマットされた文字列（郡）
	 * 変換します。
	 * @param CSV形式用のTokensインスタンス。
	 * @return string 文字列（郡）。
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
