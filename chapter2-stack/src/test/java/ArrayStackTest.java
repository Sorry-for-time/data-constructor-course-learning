import me.shalling.arrayStack.ArrayStack;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>基于数组元素实现栈的测试</h1>
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/16 21:42
 */
public class ArrayStackTest {
  @Test
  public void arrayStackTest() {
    ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
    // 判断当前栈是否为空
    System.out.println("当前栈是否为空: " + (arrayStack.isEmpty() ? "是" : "否"));
    // 填充元素
    Stream.iterate(1, value -> value + 1).limit(10).forEach(arrayStack::push);
    System.out.println("当前栈是否已满: " + (arrayStack.isFull() ? "是" : "否"));
    // 只读遍历
    arrayStack.forEach(e -> System.out.print(e + "\t")); // 10	9	8	7	6	5	4	3	2	1
    System.out.println();
    System.out.println("只读取栈顶元素: " + arrayStack.getStackTopFrame());
    // 弹栈操作
    for (int i = arrayStack.getCurrentFrameLocation(); i > 0; --i) {
      System.out.print(arrayStack.pop() + "\t"); // 10	9	8	7	6	5	4	3	2	1
    }
    System.out.println();
    System.out.println("当前栈是否为空: " + (arrayStack.isEmpty() ? "是" : "否"));
    // 继续填充, 用于测试
    Stream.iterate(1, value -> value + 1).limit(10).forEach(arrayStack::push);
    // 置空栈
    arrayStack.clear();
    System.out.println("调用 clear() 后是否为空: " + (arrayStack.isEmpty() ? "是" : "否")); // 是

    // 下溢出测试
    try {
      System.out.println(arrayStack.pop());
    } catch (RuntimeException exception) {
      System.out.println(exception.getMessage() + "!".repeat(3));
    }
  }
}
