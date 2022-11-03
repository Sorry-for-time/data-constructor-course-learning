import me.shalling.CustomString;
import org.junit.jupiter.api.Test;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>CustomStringTest</h1>
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/24 15:38
 */
public class CustomStringTest {
  private final static char[] chars = {'h', 'o', 'w', ' ', 'h', 'o', 'w'};
  private final static CustomString originStr = new CustomString(chars);

  /**
   * 构建自定义字符串和重写 toSting() 方法测试
   */
  @Test
  public void buildAndToStringTest() {
    CustomString definitionString = new CustomString(originStr);
    System.out.println(definitionString);
  }

  /**
   * replace 方法测试
   */
  @Test
  public void replaceTest() {
    CustomString replaceStr = originStr.replace('w', 'W');
    System.out.println(replaceStr);
  }

  /**
   * replaceAll 方法测试
   */
  @Test
  public void replaceAllTest() {
    CustomString replaceStr = originStr.replaceAll('h', 'H');
    System.out.println(replaceStr);
  }

  @Test
  public void insertIntoByLocationTest() {
    CustomString newStr = originStr.insertIntoByLocation(originStr.length() - 2, originStr);
    System.out.println(newStr);
  }

  /**
   * 判断单个字符在自定义字符串中首次出现的位置
   */
  @Test
  public void indexOfCharTest() {
    System.out.println(originStr.indexOf('w'));
  }

  /**
   * 取得子串主字符串中首次出现的位置
   */
  @Test
  public void indexOfDefineStringTest() {
    final CustomString compareStr = new CustomString(new char[]{' ', 'h', 'o'});
    System.out.println(originStr.indexOf(compareStr)); // 3
    final CustomString compareStr1 = new CustomString(new char[]{' ', 'h', 'e'});
    System.out.println(originStr.indexOf(compareStr1)); // -1
    final CustomString compareStr2 = new CustomString(new char[]{'h', 'o'});
    System.out.println(originStr.indexOf(compareStr2)); // 0
  }

  /**
   * 取得自定义字符串的原始数组拷贝
   */
  @Test
  public void toCharArrayTest() {
    System.out.println(originStr.toCharArray());
  }

  /**
   * 截取子串测试
   */
  @Test
  public void subStrTest() {
    System.out.println(originStr.subStr(1, 5));
  }

  /**
   * 将截取的子串以字符数组的方式返回
   */
  @Test
  public void subStrArrayTest() {
    System.out.println(originStr.subStrArray(1, 5));
  }

  /**
   * 字符串拼接测试
   */
  @Test
  public void concatTest() {
    System.out.println(originStr.concat(originStr));
  }

  /**
   * 字符串重复拼接测试
   */
  @Test
  public void repeatTest() {
    System.out.println(originStr.repeat(3)); // how howhow howhow how
  }

  /**
   * 字符串删除指定位置连续个数字符测试
   */
  @Test
  public void deleteTest() {
    System.out.println(originStr); // how how
    System.out.println(originStr.delete(2, 2)); // hohow
  }

  /**
   * 自定义字符串 strip() 测试
   */
  @Test
  public void trimTest() {
    CustomString definitionString = new CustomString(new char[]{'\t', '\n', '\r', 'h', 'o', 'w', '\t', '!', '\t'});
    System.out.println("->" + definitionString + "<-");
    // test built-in trim method
    System.out.println("->" + definitionString.strip() + "<-");
    // 边界测试
    System.out.println("->" + definitionString.subStr(0, 0) + "<-");
    System.out.println("->" + definitionString.subStr(0, 0).strip() + "<-");
  }
}
