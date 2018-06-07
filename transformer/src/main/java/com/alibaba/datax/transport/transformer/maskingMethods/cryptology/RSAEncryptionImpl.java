package com.alibaba.datax.transport.transformer.maskingMethods.cryptology;

import java.io.*;
import java.security.*;
import java.security.interfaces.*;
import java.math.*;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.datax.transport.transformer.maskingConfigure.RSAKey;


import javax.crypto.Cipher;

/**
 * @author liujiaye
 * RSA
 */

public class RSAEncryptionImpl extends CryptologyMasking{
	// 填充模式
	final public static int RAW = 2;
	final public static int PKCS1 = 1;

	private RSAPublicKey publicKey;
	private RSAPrivateKey privateKey;
	private KeyPair pair;
	private int keyLength = 1024;

	public int pairIsChanged = 0;//多次操作的计数器（预留）

	private static Logger logger = LoggerFactory.getLogger(RSAEncryptionImpl.class);


	/**
	 * constructor
	 * no parameter
	 */
	public RSAEncryptionImpl() {
		// generate RSA Key Pair
		generateRSAKeyPair();
	}

	//生成密钥、公钥加密私钥解密新方法，包括String与byte转换问题解决；明文、密文过长问题
	//String底层是以Char数组记录的，Byte会转化成Char，因为Char和Byte的长度不同，所以在转化的过程中会对Byte进行整合，主要是用0填充。将字节转化为两位BCD码

	private void generateRSAKeyPair() {
		try {
			publicKey = RSAKey.getPublicKey();
			privateKey = RSAKey.getPrivateKey();
		} catch (Exception e) {
			System.out.println("Exception in keypair generation. Reason: " + e);
		}
	}

	public void printRSAKeyPair(){
		try {
			KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance("RSA");
			// setKeyLength 1024,setCertaintyOfPrime
			rsaKeyGen.initialize(keyLength, new SecureRandom());
			KeyPair Pair = rsaKeyGen.genKeyPair();
			System.out.println((RSAPublicKey) Pair.getPublic());
			RSAPrivateKey priKey = (RSAPrivateKey) Pair.getPrivate();
			RSAPublicKey pubKey = (RSAPublicKey) Pair.getPublic();
		} catch (Exception e) {
			System.out.println("Exception in keypair generation. Reason: " + e);
		}
	}


	/**
	 * 使用公钥加密
	 * @return
	 */
	public String publicEncrypt(RSAPublicKey pk,String data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			//模长
			int key_len = pk.getModulus().bitLength() / 8;
			//加密数据长度<=模长-11
			String[] datas = splitString(data, key_len - 11);
			String result = "";
			//如果明文长度大于模长-11则要分组加密
			for (String s : datas) {
				result += bcd2Str(cipher.doFinal(s.getBytes()));
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 公钥解密
	 * @param pk
	 * @param data
	 * @return 解密后的明文
	 */
	public String publicDecrypt(RSAPublicKey pk,String data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, pk);
			//模长
			int key_len = pk.getModulus().bitLength() / 8;
			//加密数据长度<=模长-11
			String[] datas = splitString(data, key_len - 11);
			String result = "";
			//如果明文长度大于模长-11则要分组加密
			for (String s : datas) {
				result += bcd2Str(cipher.doFinal(s.getBytes()));
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥解密
	 * @param pk
	 * @param data
	 * @return
	 */
	/**
	 * 私钥解密
	 * @param pk
	 * @param data
	 * @return
	 */
	public String privateDecrypt(RSAPrivateKey pk,String data){
		try{
			Cipher cipher=Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, pk);
			//模长
			int key_len=pk.getModulus().bitLength()/8;
			byte[] bytes=data.getBytes();
			byte[] bcd=ASCII_To_BCD(bytes,bytes.length);
			//如果密文长度大于模长则要分组解密
			String result="";
			byte[][] arrays = splitArray(bcd, key_len);
			for(byte[] arr:arrays){
				result+=new String(cipher.doFinal(arr));
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 私钥加密
	 * @param pk
	 * @param data
	 * @return
	 */
	public String privateEncrypt(RSAPrivateKey pk,String data) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pk);
			//模长
			int key_len = pk.getModulus().bitLength() / 8;
			//加密数据长度<=模长-11
			String[] datas = splitString(data, key_len - 11);
			String result = "";
			//如果明文长度大于模长-11则要分组加密
			for (String s : datas) {
				result += bcd2Str(cipher.doFinal(s.getBytes()));
			}
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * BCD转字符串
	 * @param bytes
	 * @return
	 */
	public static String bcd2Str(byte[] bytes){
		char temp[]=new char[bytes.length*2];
		char val;
		for(int i=0;i<bytes.length;i++){
			//0xf0:1111 1000  ;0x0f:1111 0000
			val=(char)(((bytes[i]&0xf0)>>4)&0x0f);
			temp[i*2]=(char)(val>9?val+'A'-10:val+'0');
			val=(char)(bytes[i]&0x0f);
			temp[i*2+1]=(char)(val>9?val+'A'-10:val+'0');

		}
		return new String(temp);
	}

	/**
	 * 拆分字符
	 * @param content
	 * @param len
	 * @return
	 */
	private static String[] splitString(String content,int len){
		int x=content.length()/len;
		int y=content.length()%len;
		int z=0;
		if(y!=0)
			z=1;
		String[] strs=new String[x+z];
		String str="";
		for(int i=0;i<x+z;i++){
			if(i==x+z-1&&y!=0)
				str=content.substring(i*len,i*len+y);
			else
				str=content.substring(i*len,(i+1)*len);
			strs[i]=str;
		}
		return strs;
	}

	private static byte[] ASCII_To_BCD(byte[] ascii,int asc_len){
		byte[]bcd=new byte[asc_len/2];
		int j=0;
		for(int i=0;i<(asc_len+1)/2;i++){
			bcd[i]=asc_to_bcd(ascii[j++]);
			bcd[i]=(byte)(((j>=asc_len)?0x00:asc_to_bcd(ascii[j++]))+(bcd[i]<<4));
		}
		return bcd;
	}
	private static byte asc_to_bcd(byte asc){
		byte bcd;
		if ((asc >= '0') && (asc <= '9'))
			bcd = (byte) (asc - '0');
		else if ((asc >= 'A') && (asc <= 'F'))
			bcd = (byte) (asc - 'A' + 10);
		else if ((asc >= 'a') && (asc <= 'f'))
			bcd = (byte) (asc - 'a' + 10);
		else
			bcd = (byte) (asc - 48);
		return bcd;
	}

	/**
	 * 拆分数组
	 * @param data
	 * @param len
	 * @return
	 */
	public static byte[][] splitArray(byte[] data, int len) {
		int x = data.length / len;
		int y = data.length % len;
		int z = 0;
		if (y != 0) {
			z = 1;
		}
		byte[][] arrays = new byte[x + z][];
		byte[] arr;
		for (int i = 0; i < x + z; i++) {
			arr = new byte[len];
			if (i == x + z - 1 && y != 0) {
				System.arraycopy(data, i * len, arr, 0, y);
			} else {
				System.arraycopy(data, i * len, arr, 0, len);
			}
			arrays[i] = arr;
		}
		return arrays;
	}
	//旧版本
	/**
	 * 使用公钥加密
	 * @param clearBytes
	 * @param type
	 * @return
	 */
	public byte[] publicEncrypt(byte[] clearBytes, int type) {
		BigInteger mod = publicKey.getModulus();
		// 指数
		BigInteger publicExponent = publicKey.getPublicExponent();
		RSAKeyParameters para = new RSAKeyParameters(false, mod, publicExponent);

		AsymmetricBlockCipher engine = new RSAEngine();
		if (type == PKCS1)
			engine = new PKCS1Encoding(engine);
		engine.init(true, para);
		try {
			byte[] data = engine.processBlock(clearBytes, 0, clearBytes.length);
			return data;
		} catch (InvalidCipherTextException e) {
			e.printStackTrace();
			logger.error("publicEncrypt engine.processBlock error");
		}
		return null;
	}

	/**
	 * 使用公钥解密
	 * @param clearBytes
	 * @param type
	 * @return
	 */
	public byte[] publicDecrypt(byte[] clearBytes, int type) {
		BigInteger mod = publicKey.getModulus();
		BigInteger pubExp = publicKey.getPublicExponent();

		RSAKeyParameters para = new RSAKeyParameters(false, mod, pubExp);
		AsymmetricBlockCipher engine = new RSAEngine();
		if (type == PKCS1)
			engine = new PKCS1Encoding(engine);
		engine.init(false, para);
		try {
			byte[] data = engine.processBlock(clearBytes, 0, clearBytes.length);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("publicDecrypt engine.processBlock exception");
		}
		return null;
	}

	/**
	 * 使用私钥解密
	 * @param encodedBytes
	 * @param type
	 * @return
	 */
	public byte[] privateDecrypt(byte[] encodedBytes, int type) {
		RSAPrivateCrtKey prvCrtKey = (RSAPrivateCrtKey) privateKey;
		BigInteger mod = prvCrtKey.getModulus();
		BigInteger pubExp = prvCrtKey.getPublicExponent();
		BigInteger privExp = prvCrtKey.getPrivateExponent();
		BigInteger pExp = prvCrtKey.getPrimeExponentP();
		BigInteger qExp = prvCrtKey.getPrimeExponentQ();
		BigInteger p = prvCrtKey.getPrimeP();
		BigInteger q = prvCrtKey.getPrimeQ();
		BigInteger crtCoef = prvCrtKey.getCrtCoefficient();

		RSAKeyParameters para = new RSAPrivateCrtKeyParameters(mod, pubExp, privExp, p, q, pExp, qExp, crtCoef);

		AsymmetricBlockCipher engine = new RSAEngine();
		if (type == PKCS1)
			engine = new PKCS1Encoding(engine);

		engine.init(false, para);
		try {
			byte[] data = engine.processBlock(encodedBytes, 0, encodedBytes.length);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("privateDecrypt engine.processBlock error");
		}
		return null;
	}

	/**
	 * 使用私钥加密
	 * @param encodedBytes
	 * @param type
	 * @return
	 */
	public byte[] privateEncrypt(byte[] encodedBytes, int type) {
		RSAPrivateCrtKey prvCrtKey = (RSAPrivateCrtKey) privateKey;
		BigInteger mod = prvCrtKey.getModulus();
		BigInteger pubExp = prvCrtKey.getPublicExponent();
		BigInteger privExp = prvCrtKey.getPrivateExponent();
		BigInteger pExp = prvCrtKey.getPrimeExponentP();
		BigInteger qExp = prvCrtKey.getPrimeExponentQ();
		BigInteger p = prvCrtKey.getPrimeP();
		BigInteger q = prvCrtKey.getPrimeQ();
		BigInteger crtCoef = prvCrtKey.getCrtCoefficient();
		RSAKeyParameters para = new RSAPrivateCrtKeyParameters(mod, pubExp, privExp, p, q, pExp, qExp, crtCoef);
		AsymmetricBlockCipher engine = new RSAEngine();
		if (type == PKCS1)
			engine = new PKCS1Encoding(engine);
		engine.init(true, para);
		try {
			byte[] data = engine.processBlock(encodedBytes, 0, encodedBytes.length);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("privateEncrypt engine.processBlock error");
		}
		return null;
	}


	/**
	 * 使用私钥解密，从用户获取私钥
	 * @param encodedBytes
	 * @param type
	 * @return
	 */
	public byte[] privateEncrypt(RSAPrivateKey pk,byte[] encodedBytes, int type) {
		RSAPrivateCrtKey prvCrtKey = (RSAPrivateCrtKey)pk;
		BigInteger mod = prvCrtKey.getModulus();
		BigInteger pubExp = prvCrtKey.getPublicExponent();
		BigInteger privExp = prvCrtKey.getPrivateExponent();
		BigInteger pExp = prvCrtKey.getPrimeExponentP();
		BigInteger qExp = prvCrtKey.getPrimeExponentQ();
		BigInteger p = prvCrtKey.getPrimeP();
		BigInteger q = prvCrtKey.getPrimeQ();
		BigInteger crtCoef = prvCrtKey.getCrtCoefficient();
		RSAKeyParameters para = new RSAPrivateCrtKeyParameters(mod, pubExp, privExp, p, q, pExp, qExp, crtCoef);
		AsymmetricBlockCipher engine = new RSAEngine();
		if (type == PKCS1)
			engine = new PKCS1Encoding(engine);
		engine.init(true, para);
		try {
			byte[] data = engine.processBlock(encodedBytes, 0, encodedBytes.length);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("privateEncrypt engine.processBlock error");
		}
		return null;
	}

	public RSAPublicKey getPublicKey() {
		return publicKey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public String changeBytesToString(byte[] data) {
		return new String(Hex.encode(data));
	}

	//override from AbstractMasking
	public double execute(double epsilon) throws Exception {
		return -1;
	}

	public String execute(String originData) throws Exception {

		byte[] cipher=publicEncrypt(originData.getBytes(), 1);
		return changeBytesToString(cipher);
	}

	public List<String> execute(List<String> originData) throws Exception {
		List<String> cipherData = new ArrayList<String>();
		for (String str : originData) {
			String cipherStr = changeBytesToString(publicEncrypt(str.getBytes(),1));
			cipherData.add(cipherStr);
		}
		return cipherData;
	}

	//override from Masking
	public void mask() throws Exception{}

	//从文件读写，加密
	/*public String encryptToText(String filepath){
		try {
			BufferedReader inputFile=new BufferedReader(new FileReader(filepath));
			String plainText=new String();
			String temp=inputFile.readLine();
			while(null!=temp)
			{
				plainText+=temp;
				temp=inputFile.readLine();
			}
			inputFile.close();
			//获取时间作为输出文件的唯一名字
			Date day=new Date();
			SimpleDateFormat df = new SimpleDateFormat("MM-dd-HH-mm");
//            System.out.println(filepath.substring(0,filepath.length()-5)+"-AES-"+df.format(day));

			BufferedWriter outputFile=new BufferedWriter(new FileWriter(filepath.substring(0,filepath.length()-5)+"-RSA-"+df.format(day)+".txt"));

			outputFile.write("PublicKey:"+publicKey.toString()+"\n");
			outputFile.write("PrivateKey:"+privateKey.toString()+"\n");
			outputFile.write(changeBytesToString(publicEncrypt(plainText.getBytes(),1)));
//			System.out.println(changeBytesToString(publicEncrypt(plainText.getBytes(),1)));
			outputFile.close();
			return filepath.substring(0,filepath.length()-5)+"-RSA-"+df.format(day)+".txt";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}*/

	public static void main(String[] args){
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader("E:\\LiuKun\\Project2018\\语料库\\中文语料库\\停用词表\\stopword.dic.txt"));
			String plainText = new String();
			String temp = inputFile.readLine();
			while (null != temp) {
				temp = inputFile.readLine();
				String content = new String("你好小姐姐"+temp);
				RSAEncryptionImpl rsatest = new RSAEncryptionImpl();
				System.out.println("String加密前：" + content);
				rsatest.generateRSAKeyPair();
				String result = rsatest.publicEncrypt(rsatest.publicKey, content);
				System.out.println("加密后:\n" + result);
				System.out.println("解密后: "+rsatest.privateDecrypt(rsatest.privateKey, result));
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
}