package com.github.wegoo.string.tools;

/**
 * @author: zhangzhenwei
 * @description:
 * @date: 2023/5/8  10:16
 * @since: 1.0.0
 */
public class StringTools {

  /**
   * @author zhangzhenwei
   * @description isEmpty
   * @param [data]
   * @return boolean
   * @date 2023/5/8  10:27
   * @since: 1.0.0
   */
  public static boolean isEmpty(String data) {
    return data == null || data.length() == 0;
  }


  public static boolean isNotEmpty(String data) {
    return !isEmpty(data);
  }

  /**
   * @author zhangzhenwei
   * @description isBlank
   * 补充判断空串
   * @param [data]
   * @return boolean
   * @date 2023/5/8  10:27
   * @since: 1.0.0
   */
  public static boolean isBlank(String data) {
    return data == null || data.trim().length() == 0;
  }

  public static boolean isNotBlank(String data) {
    return !isBlank(data);
  }


  public static String append(String source, String data) {
    return source + data;
  }

  /**
   * @author zhangzhenwei
   * @description append  填充数据
   * @param [source, data]
   *         源数据， 待填充数据
   * @return java.lang.String
   * @date 2023/5/8  10:27
   * @since: 1.0.0
   */
  public static String append(String source, String... data) {
    StringBuilder sourceBuilder = new StringBuilder(source);
    for (String datum : data) {
      sourceBuilder.append(datum);
    }
    return sourceBuilder.toString();
  }


}
