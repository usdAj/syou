/**
 * @(#)KeyboardReader.java 1.1 20/01/2005
 * Copyright 2005 usd.sie.dendai.ac.jp, All rights reserved.
 */
package jp.tdu.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * KeyboardReaderクラスはキーボードから一行の文字列を読み取ってStringインスタンス、int型、double型あるいは
 * Tokensインスタンスへ変換し読み取るクラスです。
 * 行の区切りは、復帰、改行、復帰／改行の組み合わせ、またはEOF(ファイルの終わり)状態で表されます。
 * @version 1.1, 18/05/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class KeyboardReader{
	private KeyboardReader(){
	}
	
	/**
	 * キーボードから一行の文字列を読み取ります。
	 * @return キーボードから読み込んだ文字列です。
	 */
	public static String readString(){
		String line = null;
		try{
			line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return line;
	}
	
	/**
	 * キーボードから一行の文字列を読み取り、
	 * その文字列の空白やタブで区切られた最初の文字列をint型に変換します。
	 * ただし、文字列がint型に変換できない場合はInteger.MAX_VALUEになります。
	 * @return int型に変換した値を返します。
	 */
	public static int readInt(){
		String line = null;
		try{
			line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return new Tokens(line).getInt(0);
	}
	
	/**
	 * キーボードから一行の文字列を読み取り、
	 * その文字列の空白やタブで区切られた最初の文字列をdouble型に変換します。
	 * ただし、文字列がdouble型に変換できない場合はDouble.MAX_VALUEになります。
	 * @return double型に変換した値を返します。
	 */
	public static double readDouble(){
		String line = null;
		try{
			line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return new Tokens(line).getDouble(0);
	}
	
	/**
	 * キーボードからの文字列をコンストラクタの引数としてTokensインスタンスを生成し、返却します。
	 * @return Tokensインスタンスを返します。
	 */
	public static Tokens createTokens(){
		String line = null;
		try{
			line = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}catch(IOException e){
			e.printStackTrace();
		}
		return new Tokens(line);
	}
}
