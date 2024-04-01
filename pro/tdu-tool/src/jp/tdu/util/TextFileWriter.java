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
 * TextFileWriter�N���X��Sring�C���X�^���X�Aint�^�Adouble�^���邢��Tokens�C���X�^���X�𕶎���ɕϊ����A
 * �t�@�C���ɏ������ރN���X�ł��B
 * @version 1.0, 20/01/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class TextFileWriter{
	private String filename = null;
	private FileOutputStream fout;
	private BufferedWriter bw;
	
	/**
	 * �B��̃R���X�g���N�^�ł��B
	 * @param name �������ݐ�̃t�@�C���̖��O���w�肵�܂��B
	 */
	public TextFileWriter(String name){
		this.filename = name;
	}
	
	/**
	 * �t�@�C�����J���܂��B�������A�t�@�C���͐V�K�쐬�ɂȂ�܂��B
	 * @return �t�@�C�����J���Ă����true���A����ȊO�ł����false��Ԃ��܂��B
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
	 * �t�@�C������܂��B
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
	 * ��������t�@�C���֏������݂܂��B
	 * @param string �������ޕ�����B
	 */
	public void write(String string){
		try{
			this.bw.write(string);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * int�^�̒l�𕶎���ɕϊ����t�@�C���֏������݂܂��B
	 * @param i ��������int�^�̒l�B
	 */
	public void write(int i){
	    this.write(new Integer(i).toString());
	}
	
	/**
	 * double�^�̒l�𕶎���ɕϊ����t�@�C���֏������݂܂��B
	 * @param d ��������double�^�̒l�B
	 */
	public void write(double d){
	    this.write(new Double(d).toString());
	}
	
	/**
	 * ���s�������������݂܂��B
	 */
	public void newLine(){
		try{
			this.bw.newLine();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Tokens�C���X�^���X�𕶎���ɕϊ����t�@�C���֏������݂܂��B
	 * @param tokens ��������Tokens�C���X�^���X�B
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
