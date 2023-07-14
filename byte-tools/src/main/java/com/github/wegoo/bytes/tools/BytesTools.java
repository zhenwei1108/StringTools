package com.github.wegoo.bytes.tools;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.Locale;
import sun.security.util.ByteArrays;

/**
 * @author: wegoo
 * @description:
 * @date: 2023/5/16  09:00
 * @since: 1.0.0
 */
public class BytesTools {

  /**
   * @param [data]
   * @return byte[]
   * @author wegoo
   * @description merge 合并byte数组 时间和内存，我选时间
   * @date 2023/5/16  09:10
   * @since: 1.0.0
   */
  public static byte[] merge(byte[]... data) {
    byte[] result = new byte[0];
    if (data != null) {
      for (byte[] datum : data) {
        byte[] mediaValue = new byte[result.length + datum.length];
        System.arraycopy(result, 0, mediaValue, 0, result.length);
        System.arraycopy(datum, 0, mediaValue, result.length, datum.length);
        result = mediaValue;
      }
    }
    return result;
  }


  /**
   * @param [data, length]
   * @return byte[]
   * @author wegoo
   * @description splitPrefix 从前面 截取 指定长度
   * @date 2023/7/9  13:30
   * @since: 1.0.1
   */
  public static byte[] splitPrefix(byte[] data, int length) {
    if (data == null || data.length < length) {
      return new byte[0];
    }
    byte[] result = new byte[length];
    System.arraycopy(data, 0, result, 0, length);
    return result;
  }

  /**
   * @param [data, length]
   * @return byte[]
   * @author wegoo
   * @description splitSuffix 从后面截取指定长度
   * @date 2023/7/9  13:30
   * @since: 1.0.1
   */
  public static byte[] splitSuffix(byte[] data, int length) {
    if (data == null || data.length < length) {
      return new byte[0];
    }
    byte[] result = new byte[length];
    System.arraycopy(data, data.length - length, result, 0, length);
    return result;
  }


  public static byte[] split(byte[] data, int from, int length) {
    if (data == null || from + length > data.length) {
      throw new RuntimeException("input params error");
    }
    byte[] result = new byte[length];
    System.arraycopy(data, from, result, 0, length);
    return result;
  }


  /**
   * @param [data, targetLength, copyLength]
   *       待处理数据， 目标数组长度， 拷贝字节数量
   * @return byte[]
   * @author wegoo
   * @description copyTo 将数字转换为指定长度
   * @date 2023/7/10  17:48
   * @since: 1.0.0
   */
  public static byte[] copyTo(byte[] data, int targetLength, int copyLength) {
    byte[] result = new byte[targetLength];
    if (copyLength > data.length) {
      throw new RuntimeException("data out of length");
    }
    System.arraycopy(data, data.length - copyLength, result, result.length - copyLength,
        copyLength);
    return result;
  }


  /**
   * @param [data]
   * @return int
   * @author wegoo
   * @description bytesToInt 字节数组转 数字
   * @date 2023/7/10  10:07
   * @since: 1.0.0
   */
  public static int bytesToInt(byte[] data) {
    return ByteBuffer.wrap(copyTo(data, 4, data.length)).getInt();
  }

  /**
   * @param [data]
   * @return byte[]
   * @author wegoo
   * @description intToBytes 数字转字节数组
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static byte[] intToBytes(int data) {
    return ByteBuffer.allocate(4).putInt(data).array();
  }

  /**
   * @param [data]
   * @return long
   * @author wegoo
   * @description bytesToLong 字节数组转长数
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static long bytesToLong(byte[] data) {
    return ByteBuffer.wrap(data).getLong();
  }

  /**
   * @param [data]
   * @return byte[]
   * @author wegoo
   * @description longToBytes 长数转字节数组
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static byte[] longToBytes(long data) {
    return ByteBuffer.allocate(8).putLong(data).array();
  }

  /**
   * @param [source, target]
   * @return boolean
   * @author wegoo
   * @description isEquals 比对两个字节数组是否一致
   * @date 2023/7/10  10:22
   * @since: 1.0.0
   */
  public static boolean isEquals(byte[] source, byte[] target) {
    return ByteArrays.isEqual(source, 0, source.length, target, 0, target.length);
  }

  /**
   * @param [data]
   * @return java.lang.String
   * @author wegoo
   * @description bytesToHex 字节数组转hex字符串
   * @date 2023/7/10  11:17
   * @since: 1.0.0
   */
  public static String bytesToHex(byte[] data) {
    StringBuffer sb = new StringBuffer();
    for (byte datum : data) {
      String hex = Integer.toHexString(datum & 0xFF);
      if (hex.length() < 2) {
        sb.append(0);
      }
      sb.append(hex);
    }
    return sb.toString().toUpperCase(Locale.ROOT);
  }

  /**
   * @param [inHex]
   * @return byte[]
   * @author wegoo
   * @description hexToBytes hex字符串转byte数组
   * @date 2023/7/10  11:16
   * @since: 1.0.0
   */
  public static byte[] hexToBytes(String inHex) {
    int hexlen = inHex.length();
    byte[] result;
    if (hexlen % 2 == 1) {
      //奇数
      hexlen++;
      result = new byte[(hexlen / 2)];
      inHex = "0" + inHex;
    } else {
      //偶数
      result = new byte[(hexlen / 2)];
    }
    int j = 0;
    for (int i = 0; i < hexlen; i += 2) {
      result[j] = (byte) Integer.parseInt((inHex.substring(i, i + 2)), 16);
      j++;
    }
    return result;
  }

  /**
   * @param [data]
   * @return java.lang.String
   * @author wegoo
   * @description bytesToBase64String 字节数组转base64
   * @date 2023/7/10  11:23
   * @since: 1.0.0
   */
  public static String bytesToBase64String(byte[] data) {
    return Base64.getEncoder().encodeToString(data);
  }

  /**
   * @param [data]
   * @return byte[]
   * @author wegoo
   * @description base64StringToBytes base64转字节数组
   * @date 2023/7/10  11:23
   * @since: 1.0.0
   */
  public static byte[] base64StringToBytes(String data) {
    return Base64.getDecoder().decode(data);
  }

  /**
   * @param [data]
   * @return boolean
   * @author wegoo
   * @description isBase64 判断是否为base64编码 尝试解码，若解码失败，则认为非base64
   * @date 2023/7/10  13:19
   * @since: 1.0.0
   */
  public static boolean isBase64(String data) {
    boolean result = false;
    try {
      Base64.getDecoder().decode(data);
      result = true;
    } catch (Exception ignore) {
    }
    return result;
  }


}
