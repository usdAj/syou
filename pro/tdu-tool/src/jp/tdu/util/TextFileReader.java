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
 * TextFileReader�N���X�̓t�@�C�������s�̕������ǂݎ��N���X�ł��B
 * �s�̋�؂�́A���A�A���s�A���A�^���s�̑g�ݍ��킹�A�܂���EOF(�t�@�C���̏I���)��Ԃŕ\����܂��B
 * @version 1.0, 20/01/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class TextFileReader{
	private String filename;
	private FileInputStream fileInputStream;
	private BufferedReader  bufferdReader;
	
	/**
	 * �B��̃R���X�g���N�^�ł��B
	 * @param name �ǂݍ��݌��̃t�@�C���̖��O���w�肵�܂��B
	 */
	public TextFileReader(String name){
		this.filename = name;
	}
	
	/**
	 * �t�@�C�����J���܂��B
	 * @return �t�@�C�����J���Ă����true���A����ȊO�ł����false��Ԃ��܂��B
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
	 * �t�@�C������܂��B
	 */
	public void close(){
		try{
			this.fileInputStream.close();         
		}catch(IOException e){
			this.bufferdReader = null;
		}
	}
	
	/**
	 * �t�@�C�������s�̕������ǂݎ��܂��B
	 * @return �ǂݍ��񂾕������Ԃ��܂��B
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
	 * �t�@�C������́A1�s�̕�������R���X�g���N�^�̈����Ƃ���Tokens�C���X�^���X�𐶐����A�ԋp���܂��B
	 * @return Tokens�C���X�^���X��Ԃ��܂��B
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
	 * �t�@�C�����當���񂪓ǂݎ��邩���f���܂��B
	 * @return �t�@�C������̕�����̓ǂݎ�肪�\�ł����true���A����ȊO�ł����false��Ԃ��܂��B
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
