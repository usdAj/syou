/**
 * @(#)TextFileReader.java 1.1 20/01/2005
 * Copyright 2005 usd.sie.dendai.ac.jp, All rights reserved.
 */
package jp.tdu.util;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * TextFileReaderクラスはファイルから一行の文字列を読み取るクラスです。
 * 行の区切りは、復帰、改行、復帰／改行の組み合わせ、またはEOF(ファイルの終わり)状態で表されます。
 * @version 1.0, 20/01/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class TextFileReader{
	private String filename;
	private FileInputStream fileInputStream;
	private BufferedReader  bufferdReader;
	
	/**
	 * 唯一のコンストラクタです。
	 * @param name 読み込み元のファイルの名前を指定します。
	 */
	public TextFileReader(String name){
		this.filename = name;
	}
	
	/**
	 * ファイルを開きます。
	 * @return ファイルが開いていればtrueを、それ以外であればfalseを返します。
	 */
	public boolean open(){
		try{
			this.fileInputStream = new FileInputStream(this.filename);
			this.bufferdReader = new BufferedReader(new InputStreamReader(this.fileInputStream));
		}catch(IOException e){
			this.bufferdReader = null;
		}
		return this.ready();
	}
	
	/**
	 * ファイルを閉じます。
	 */
	public void close(){
		try{
			this.fileInputStream.close();         
		}catch(IOException e){
			this.bufferdReader = null;
		}
	}
	
	/**
	 * ファイルから一行の文字列を読み取ります。
	 * @return 読み込んだ文字列を返します。
	 */
	public String readLine(){
		String line = null;
		try{
			if(this.ready()){
				line = this.bufferdReader.readLine();
			}
		}catch(IOException e){
			this.bufferdReader = null;
		}
		return line;
	}
	
	/**
	 * ファイルからの、1行の文字列をコンストラクタの引数としてTokensインスタンスを生成し、返却します。
	 * @return Tokensインスタンスを返します。
	 */
	public Tokens createTokens(){
		String line = null;
		try{
			if(this.ready()){
				line = this.bufferdReader.readLine();
			}
		}catch(IOException e){
			this.bufferdReader = null;
		}
		return new Tokens(line);
	}
	
	/**
	 * ファイルから文字列が読み取れるか判断します。
	 * @return ファイルからの文字列の読み取りが可能であればtrueを、それ以外であればfalseを返します。
	 */
	public boolean ready(){
		if(this.bufferdReader == null){
		  return false;
		}
		try{
			return this.bufferdReader.ready();
		}catch(IOException e){
			return false;
		}
	}
}
