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

  /**
   * 根据传入的字符数组创建一个字符串(注: 新建的字符串与传入的字符数组并不会有堆内存上的关联, 它们是完全独立的实体)
   *
   * @param chars 用以简历字符串的字符数组参考值
   */
  public DefinitionString(char[] chars) {
    this.strBuffers = new char[chars.length];
    // 进行独立拷贝, 防止引用改变带来的结构破坏
    System.arraycopy(chars, 0, this.strBuffers, 0, this.strBuffers.length);
    this.strLength = chars.length;
  }

  /**
   * 根据一个已存在的字符串创建一个独立拷贝
   *
   * @param definitionString 已有的字符串
   */
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
  public DefinitionString subStr(final int indexStart, final int indexEnd) {
    if (indexStart < 0 || indexEnd > strLength) {
      throw new RuntimeException("index out of bound");
    }
    char[] chars = new char[indexEnd - indexStart];
    for (int i = 0, record = indexStart; i < indexEnd - indexStart; ++i, ++record) {
      chars[i] = strBuffers[record];
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
    for (int i = 0, record = indexStart; i < indexEnd - indexStart; ++i, ++record) {
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
   * @param joinStr 拼接串
   * @return 拼接完的的自定义字符串
   */
  public DefinitionString concat(DefinitionString joinStr) {
    return new DefinitionString(joinStrArr(this, joinStr));
  }

  /**
   * 返回自定义字符串的字符数组(这是一份独立的拷贝)
   *
   * @return 字符串字符数组独立拷贝
   */
  public char[] toCharArray() {
    char[] chars = new char[strBuffers.length];
    System.arraycopy(this.strBuffers, 0, chars, 0, strLength);
    return chars;
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

  /**
   * 取得子串在原始字符串中首次出现的下标位置, 如果未取得, 返回 -1
   *
   * @param matchStr 进行擦匹配的子串
   * @return 子串首次出现的位置
   * @brief 实现上: 暴力 for 循环逐一比较
   */
  public int indexOf(final DefinitionString matchStr) {
    char[] matchChars = matchStr.toCharArray();
    // 如果参与比较的字符串长度为 0, 直接返回;
    if (matchChars.length == 0) {
      return 0;
    }
    // 如果原始字符串的长度小于参与匹配的字符串, 那么直接返回
    if (this.strBuffers.length < matchChars.length) {
      return -1;
    }
    int count = 0;
    for (int i = 0; i < strBuffers.length; ++i) {
      // 暴力 for 循环进行每个字符的比较
      for (int j = 0; j < matchChars.length; ++j) {
        // 如果父串参与比较的剩余字符数小于进行匹配的子串, 那么直接终止比较
        if (strBuffers.length - i < matchChars.length) return -1;
        if (matchChars[j] == strBuffers[i + j]) {
          count++;
        }
        // 如果不匹配, 直接中断本次循环比对
        else if (matchChars[j] != strBuffers[i]) {
          break;
        }
        if (count == matchChars.length) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * 对字符串进行就地复制拼接操作
   *
   * @param count 重复次数
   * @return 重复拼接完成后的字符串
   */
  public DefinitionString repeat(final int count) {
    // 创建进行重复拼接所需的字符数组
    char[] chars = new char[strBuffers.length * count];
    int eachIndex = 0;
    for (int i = 0; i < count; ++i) {
      for (int j = 0; j < strBuffers.length; j++, eachIndex++) {
        chars[eachIndex] = strBuffers[j];
      }
    }
    return new DefinitionString(chars);
  }

  /**
   * 根据指定的位置删除连续诺干个字符
   *
   * @param deleteStart 删除的起始位置
   * @param continuous  删除字符的连续个数
   * @return 重新拼接完的字符
   */
  public DefinitionString delete(final int deleteStart, final int continuous) {
    if (deleteStart < 0 || continuous < 0) { /* 非法下标那么抛出异常 */
      throw new RuntimeException("ERROR DELETE_START OR CONTINUOUS PARAM");
    }
    if (continuous == 0) { /*  如果实际上并不进行删除操作, 那么返回新的拷贝即可 */
      return new DefinitionString(this);
    }
    int mallocSize;
    // 如果连续删除的字符数量已经到达字串末尾的情况
    if (deleteStart + continuous >= strBuffers.length) {
      mallocSize = strBuffers.length - deleteStart;
    }
    // 如果删除的只是中间的某一部分, 删除后仍然有部分剩余
    else {
      mallocSize = strBuffers.length - continuous;
    }
    char[] chars = new char[mallocSize];
    // 记录填充下标的位置
    int eachIndex = 0;
    for (; eachIndex < deleteStart; eachIndex++) {
      chars[eachIndex] = strBuffers[eachIndex];
    }
    // 如果删除连续字符后仍然有诺干个字符的情况, 那么填充回去
    if (deleteStart + continuous < strBuffers.length) {
      int cutSlicePoint = deleteStart + continuous;
      for (; cutSlicePoint < strBuffers.length; cutSlicePoint++, eachIndex++) {
        chars[eachIndex] = strBuffers[cutSlicePoint];
      }
    }
    return new DefinitionString(chars);
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
