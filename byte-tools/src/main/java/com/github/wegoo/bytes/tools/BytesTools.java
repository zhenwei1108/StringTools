package com.github.wegoo.bytes.tools;

import java.nio.ByteBuffer;
import sun.security.util.ByteArrays;

/**
 * @author: zhangzhenwei
 * @description:
 * @date: 2023/5/16  09:00
 * @since: 1.0.0
 */
public class BytesTools {

  /**
   * @param [data]
   * @return byte[]
   * @author zhangzhenwei
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
   * @author zhangzhenwei
   * @description splitPrefix 从前面 截取 指定长度
   * @param [data, length]
   * @return byte[]
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
   * @author zhangzhenwei
   * @description splitSuffix 从后面截取指定长度 
   * @param [data, length]
   * @return byte[]
   * @date 2023/7/9  13:30
   * @since: 1.0.1
   */
  public static byte[] splitSuffix(byte[] data, int length) {
    if (data == null || data.length < length) {
      return new byte[0];
    }
    byte[] result = new byte[length];
    System.arraycopy(data, data.length-length, result, 0, length);
    return result;
  }
  

  /**
   * @author zhangzhenwei
   * @description bytesToInt 字节数组转 数字
   * @param [data]
   * @return int
   * @date 2023/7/10  10:07
   * @since: 1.0.0
   */
  public static int bytesToInt(byte[] data){
    return ByteBuffer.wrap(data).getInt();
  }

  /**
   * @author zhangzhenwei
   * @description intToBytes 数字转字节数组
   * @param [data]
   * @return byte[]
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static byte[] intToBytes(int data){
    return ByteBuffer.allocate(4).putInt(data).array();
  }

  /**
   * @author zhangzhenwei
   * @description bytesToLong
   * 字节数组转长数
   * @param [data]
   * @return long
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static long bytesToLong(byte[] data){
    return ByteBuffer.wrap(data).getLong();
  }

  /**
   * @author zhangzhenwei
   * @description longToBytes 长数转字节数组
   * @param [data]
   * @return byte[]
   * @date 2023/7/10  10:08
   * @since: 1.0.0
   */
  public static byte[] longToBytes(long data){
    return ByteBuffer.allocate(8).putLong(data).array();
  }

  /**
   * @author zhangzhenwei
   * @description isEquals 比对两个字节数组是否一致
   * @param [source, target]
   * @return boolean
   * @date 2023/7/10  10:22
   * @since: 1.0.0
   */
  public static boolean isEquals(byte[] source, byte[] target){
    return ByteArrays.isEqual(source,0,source.length, target,0, target.length);
  }

}
