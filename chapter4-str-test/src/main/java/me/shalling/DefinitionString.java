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

  /**
   * 替换自定义字符中首个出现的指定字符
   *
   * @param matchStr   匹配的字符
   * @param replaceStr 进行替换的字符
   * @return 新建的自定义字符串
   */
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

  /**
   * 在指定位置插入另一个自定义字符串
   *
   * @param insertLocation 插入新字符串的下标
   * @param newStr         进行拼接的字符串
   * @return 插入指定位置后的新字符串实例
   */
  public DefinitionString insertIntoByLocation(int insertLocation, DefinitionString newStr) {
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

  /**
   * 返回字符在自定义字符串中的位置
   *
   * @param matchChar 匹配的字符
   * @return 如果存在, 返回真实的下标位置, 否则返回 -1
   */
  public int indexOf(char matchChar) {
    for (int i = 0; i < this.strBuffers.length; ++i) {
      if (matchChar == this.strBuffers[i]) {
        return i;
      }
    }
    return -1;
  }

  @Override
  public int compareTo(DefinitionString o) {
    // 首先调用 equals 判断, 如果就是相同对象, 直接返回 0
    if (this.equals(o)) {
      return 0;
    }
    // 如果两个自定义字符串的长度一样, 那么逐一进行字符比较, 直到遇到差异
    if (this.strBuffers.length == o.strBuffers.length) {
      for (int i = 0; i < o.strBuffers.length; ++i) {
        if (this.strBuffers[i] > o.strBuffers[i]) {
          return 1;
        } else if (this.strBuffers[i] < o.strBuffers[i]) {
          return -1;
        }
      }
    }
    // 如果字符串长度不同, 直接返回长度差值
    return this.strBuffers.length - o.strBuffers.length;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    // java16 对 instanceof 语法进行了加强, 允许直接使用模式变量, 如下的 definitionStr
    // 可以简单理解为在判断类型成功后提供一个进行类型强转的变量, 方便后续操作
    if (!(o instanceof DefinitionString definitionStr)) return false;
    if (strLength != definitionStr.strLength) return false;
    return Arrays.equals(strBuffers, definitionStr.strBuffers);
  }

  @Override
  public int hashCode() {
    int result = Arrays.hashCode(strBuffers);
    result = 31 * result + strLength;
    return result;
  }

  @Override
  public String toString() {
    return String.valueOf(this.strBuffers);
  }
}
