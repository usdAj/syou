/**
 * @(#)TextFileWriter.java 1.1 20/01/2005
 * Copyright 2005 usd.sie.dendai.ac.jp, All rights reserved.
 */
package jp.tdu.util;

import java.io.FileOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * TextFileWriterクラスはSringインスタンス、int型、double型あるいはTokensインスタンスを文字列に変換し、
 * ファイルに書き込むクラスです。
 * @version 1.0, 20/01/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class TextFileWriter{
	private String filename = null;
	private FileOutputStream fout;
	private BufferedWriter bw;
	
	/**
	 * 唯一のコンストラクタです。
	 * @param name 書き込み先のファイルの名前を指定します。
	 */
	public TextFileWriter(String name){
		this.filename = name;
	}
	
	/**
	 * ファイルを開きます。ただし、ファイルは新規作成になります。
	 * @return ファイルが開いていればtrueを、それ以外であればfalseを返します。
	 */
	public boolean open(){
		try{
			this.fout = new FileOutputStream(this.filename);
			this.bw = new BufferedWriter(new OutputStreamWriter(this.fout));
		}catch(IOException e){
			e.printStackTrace();
		}
		return this.bw != null ? true: false;
	}
	
	/**
	 * ファイルを閉じます。
	 */
	public void close(){
		try{
			this.bw.flush();
			this.fout.close();         
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 文字列をファイルへ書き込みます。
	 * @param string 書き込む文字列。
	 */
	public void write(String string){
		try{
			this.bw.write(string);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * int型の値を文字列に変換しファイルへ書き込みます。
	 * @param i 書き込むint型の値。
	 */
	public void write(int i){
	    this.write(new Integer(i).toString());
	}
	
	/**
	 * double型の値を文字列に変換しファイルへ書き込みます。
	 * @param d 書き込むdouble型の値。
	 */
	public void write(double d){
	    this.write(new Double(d).toString());
	}
	
	/**
	 * 改行文字を書き込みます。
	 */
	public void newLine(){
		try{
			this.bw.newLine();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Tokensインスタンスを文字列に変換しファイルへ書き込みます。
	 * @param tokens 書き込むTokensインスタンス。
	 */
	public void write(Tokens tokens){
		if(tokens.size() != 0){
			for(int i = 0; i < tokens.size(); i++){
				this.write((i != (tokens.size() - 1) ? tokens.getString(i) + " ": tokens.getString(i)));
			}
			this.newLine();
	    }
	}
}
