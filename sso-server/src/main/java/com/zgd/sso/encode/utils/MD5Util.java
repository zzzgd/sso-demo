package com.zgd.sso.encode.utils;

import com.google.common.math.IntMath;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * MD5工具类，加盐
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-11 下午7:57:36
 */
public class MD5Util {

  static Logger log = LoggerFactory.getLogger(MD5Util.class);

  public static void main(String[] args) {
    System.out.println("md5WithRandomSalt(\"123\") = " + md5WithRandomSalt("123"));
    System.out.println("verify(\"123\",\"b4d74f25a854d95058336b60877485b66797b7a34048ed90\") = " + verify("123", "b4d74f25a854d95058336b60877485b66797b7a34048ed90"));
  }


  private static String byteToHex(byte[] md5Bytes) {
    StringBuilder hexValue = new StringBuilder();
    for (byte md5Byte : md5Bytes) {
      int val = ((int) md5Byte) & 0xff;
      if (val < 16) {
        hexValue.append("0");
      }
      hexValue.append(Integer.toHexString(val));
    }
    return hexValue.toString();
  }


  /**
   * 加随机盐MD5
   *
   * @param password
   * @return
   * @author daniel
   * @time 2016-6-11 下午8:45:04
   */
  public static String md5WithRandomSalt(String password) {
    String salt = String.format("%016d",
            Long.parseLong(String.valueOf(RandomUtils.nextInt(1, IntMath.pow(10, 9)))
                    + RandomUtils.nextInt(1, IntMath.pow(10, 9))
            ));
    log.info("生成的盐:{}",salt);
    String decodedPwd = DigestUtils.md5Hex(password + salt);
    log.info("password:{}\tmd5加盐编码并转十六进制后,decodedPwd:{}",password,decodedPwd);

    char[] cs = new char[48];
    for (int i = 0; i < 16; i++) {
      cs[i * 3] = Objects.requireNonNull(decodedPwd).charAt(i * 2);
      cs[i * 3 + 1] = salt.charAt(i);
      cs[i * 3 + 2] = decodedPwd.charAt(i * 2 + 1);
    }
    String mergeStr = new String(cs);
    log.info("拼接盐和编码后的decodedPwd得到合并的编码字符串mergeStr:{}",mergeStr);
    return mergeStr;
  }

  /**
   * 校验加盐后是否和原文一致
   *
   * @param password
   * @param encodeStr
   * @return
   * @author daniel
   * @time 2016-6-11 下午8:45:39
   */
  public static boolean verify(String password, String encodeStr) {
    if (StringUtils.isEmpty(encodeStr) || encodeStr.length() != 48){
      return false;
    }
    char[] cs1 = new char[32];
    char[] cs2 = new char[16];
    for (int i = 0; i < 16; i++) {
      cs1[i * 2] = encodeStr.charAt(i * 3);
      cs2[i] = encodeStr.charAt(i * 3 + 1);
      cs1[i * 2 + 1] = encodeStr.charAt(i * 3 + 2);
    }
    String originMd5Str = new String(cs1);
    String salt = new String(cs2);
    log.info("通过解析编码字符串encodeStr:{},得到md5Hex的编码originMd5Str:{}\t和盐salt:{}",encodeStr,originMd5Str,salt);
    String md5Str = DigestUtils.md5Hex(password + salt);
    log.info("通过salt对password:{}\t进行md5编码后得到md5Str:{}",password,md5Str);

    return Objects.equals(originMd5Str,md5Str);
  }


}