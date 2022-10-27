package me.shalling;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>自定义字符串</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/24 14:47
 */
public class DefinitionString implements Serializable, Comparable<DefinitionString> {
  @Serial
  private static final long serialVersionUID = 1421462135553039727L;

  /**
   * 内部维护的字符存储数组
   */
  private final char[] strBuffers;

  /**
   * 自定义字符串的长度
   */
  private final int strLength;

  public DefinitionString(char[] chars) {
    this.strBuffers = new char[chars.length];
    // 进行独立拷贝, 防止引用改变带来的结构破坏
    System.arraycopy(chars, 0, this.strBuffers, 0, this.strBuffers.length);
    this.strLength = chars.length;
  }

  public DefinitionString(DefinitionString definitionString) {
    this(definitionString.strBuffers);
  }

  public int length() {
    return this.strLength;
  }

  /**
   * 返回指定位置截取的字符串子串(注: 截取方式为左闭右开, 起始位置从 0 开始)
   *
   * @param indexStart 起始位置
   * @param indexEnd   终点位置
   * @return 截取的子串
   */
  public DefinitionString subStr(int indexStart, int indexEnd) {
    char[] chars = new char[indexEnd - indexStart];
    for (int i = 0, record = indexStart; i < indexEnd; ++i, ++record) {
      chars[i] = this.strBuffers[record];
    }
    return new DefinitionString(chars);
  }

  /**
   * 返回指定位置截取的字符串子串数组(注: 截取方式为左闭右开, 起始位置从 0 开始)
   *
   * @param indexStart 起始位置
   * @param indexEnd   终点位置
   * @return 截取的子串字符数组
   */
  public char[] subStrArray(int indexStart, int indexEnd) {
    char[] chars = new char[indexEnd - indexStart];
    for (int i = 0, record = indexStart; i < indexEnd; ++i, ++record) {
      chars[i] = this.strBuffers[record];
    }
    return chars;
  }

  public DefinitionString replace(char matchStr, char replaceStr) {
    int count = 0;
    char[] newStrBuffer = new char[this.strLength];
    System.arraycopy(this.strBuffers, 0, newStrBuffer, 0, newStrBuffer.length);
    while (count < newStrBuffer.length) {
      if (newStrBuffer[count] == matchStr) {
        newStrBuffer[count] = replaceStr;
        break;
      }
      count++;
    }
    return new DefinitionString(newStrBuffer);
  }

  public DefinitionString replaceAll(char matchStr, char replaceStr) {
    int count = 0;
    char[] newStrBuffer = new char[this.strLength];
    System.arraycopy(this.strBuffers, 0, newStrBuffer, 0, newStrBuffer.length);
    while (count < newStrBuffer.length) {
      if (newStrBuffer[count] == matchStr) {
        newStrBuffer[count] = replaceStr;
      }
      count++;
    }
    return new DefinitionString(newStrBuffer);
  }

  public DefinitionString insertIntoDefineLocation(int insertLocation, DefinitionString newStr) {
    int newStrLength = newStr.strLength + this.strLength;
    char[] strBuffer = new char[newStrLength];
    int start = 0;
    int oldStrReadLength = 0;
    for (; start < insertLocation; ++start, ++oldStrReadLength) {
      strBuffer[start] = this.strBuffers[start];
    }
    for (int j = 0; j < newStr.strBuffers.length; ++j, ++start) {
      strBuffer[start] = newStr.strBuffers[j];
    }
    for (int j = 0; start < newStrLength; ++start, ++j, ++oldStrReadLength) {
      strBuffer[start] = this.strBuffers[oldStrReadLength];
    }
    return new DefinitionString(strBuffer);
  }

  /**
   * 将两个自定义字符串拼接成一个字符数组
   *
   * @param str1 第一个自定义字符串
   * @param str2 第二个自定义字符串
   * @return 拼接完成的字符数组
   */
  private static char[] joinStrArr(DefinitionString str1, DefinitionString str2) {
    int strBufferLength = str1.length() + str2.length();
    char[] chars = new char[strBufferLength];
    int i = 0;
    for (int j = 0; j < str1.length(); j++, i++) {
      chars[i] = str1.strBuffers[j];
    }
    for (int j = 0; j < str2.length(); j++, i++) {
      chars[i] = str1.strBuffers[j];
    }
    return chars;
  }

  /**
   * 返回一个拼接完成后的自定义字符串
   *
   * @param origin  起始字符串
   * @param joinStr 拼接串
   * @return 拼接完的的自定义字符串
   */
  public DefinitionString concat(DefinitionString origin, DefinitionString joinStr) {
    return new DefinitionString(joinStrArr(origin, joinStr));
  }

  /**
   * 返回一个拼接完成后的自定义字符串
   *
   * @param origin  起始字符串
   * @param joinStr 拼接串
   * @return 拼接完的的自定义字符数组
   */
  public char[] concatStrArray(DefinitionString origin, DefinitionString joinStr) {
    return joinStrArr(origin, joinStr);
  }

  @Override
  public String toString() {
    return String.valueOf(this.strBuffers);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof DefinitionString string)) return false;
    if (strLength != string.strLength) return false;
    return Arrays.equals(strBuffers, string.strBuffers);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(strBuffers);
    result = 31 * result + strLength;
    return result;
  }

  @Override
  public int compareTo(DefinitionString o) {
    return 0;
  }
}
