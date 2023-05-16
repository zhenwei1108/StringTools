package com.github.wegoo.bytes.tools;

import java.lang.reflect.Array;

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


}
