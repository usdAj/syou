/**
 * @(#)KeyboardReader.java 1.1 20/01/2005
 * Copyright 2005 usd.sie.dendai.ac.jp, All rights reserved.
 */
package jp.tdu.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * KeyboardReader�N���X�̓L�[�{�[�h�����s�̕������ǂݎ����String�C���X�^���X�Aint�^�Adouble�^���邢��
 * Tokens�C���X�^���X�֕ϊ����ǂݎ��N���X�ł��B
 * �s�̋�؂�́A���A�A���s�A���A�^���s�̑g�ݍ��킹�A�܂���EOF(�t�@�C���̏I���)��Ԃŕ\����܂��B
 * @version 1.1, 18/05/2005
 * @author miya@sie.dendai.ac.jp
 */
public final class KeyboardReader{
	private KeyboardReader(){
	}
	
	/**
	 * �L�[�{�[�h�����s�̕������ǂݎ��܂��B
	 * @return �L�[�{�[�h����ǂݍ��񂾕�����ł��B
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
	 * �L�[�{�[�h�����s�̕������ǂݎ��A
	 * ���̕�����̋󔒂�^�u�ŋ�؂�ꂽ�ŏ��̕������int�^�ɕϊ����܂��B
	 * �������A������int�^�ɕϊ��ł��Ȃ��ꍇ��Integer.MAX_VALUE�ɂȂ�܂��B
	 * @return int�^�ɕϊ������l��Ԃ��܂��B
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
	 * �L�[�{�[�h�����s�̕������ǂݎ��A
	 * ���̕�����̋󔒂�^�u�ŋ�؂�ꂽ�ŏ��̕������double�^�ɕϊ����܂��B
	 * �������A������double�^�ɕϊ��ł��Ȃ��ꍇ��Double.MAX_VALUE�ɂȂ�܂��B
	 * @return double�^�ɕϊ������l��Ԃ��܂��B
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
	 * �L�[�{�[�h����̕�������R���X�g���N�^�̈����Ƃ���Tokens�C���X�^���X�𐶐����A�ԋp���܂��B
	 * @return Tokens�C���X�^���X��Ԃ��܂��B
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
