import me.shalling.LinkCircleQueue;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>自定义循环链队列测试</h1>
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/16 22:45
 */
public class LinkCircleQueueTest {
  @Test
  public void linkCircleQueueTest() {
    LinkCircleQueue<Integer> linkCircleQueue = new LinkCircleQueue<>(10);
    System.out.println("当前队列是否为空: " + (linkCircleQueue.isEmpty() ? "是" : "否"));
    // 填充数据
    Stream.iterate(1, prev -> prev + 1).limit(10).forEach(linkCircleQueue::enterQueue);
    System.out.println("当前队列是否已满: " + (linkCircleQueue.isFull() ? "是" : "否"));
    // 出队
    System.out.println("出队数据: " + linkCircleQueue.exitQueue());
    System.out.println("出队数据: " + linkCircleQueue.exitQueue());
    System.out.println("出队数据: " + linkCircleQueue.exitQueue());
    // 只读遍历
    System.out.print("只读遍历: ");
    linkCircleQueue.forEach(e -> System.out.print(e + "\t"));
    // 插入数据
    Stream.iterate(10, prev -> prev + 1).limit(3).forEach(linkCircleQueue::enterQueue);
    System.out.println();
    // 只读遍历
    System.out.print("只读遍历: ");
    linkCircleQueue.forEach(e -> System.out.print(e + "\t"));
    System.out.println();
    // 出队
    System.out.print("出队元素: ");
    while (!linkCircleQueue.isEmpty()) {
      System.out.print(linkCircleQueue.exitQueue() + "\t");
    }
    System.out.println();
    System.out.println("当前队列是否为空: " + (linkCircleQueue.isEmpty() ? "是" : "否"));

    Stream.iterate(10, prev -> prev + 1).limit(10).forEach(linkCircleQueue::enterQueue);
    System.out.println("当前队列是否已满: " + (linkCircleQueue.isFull() ? "是" : "否")); // 是
    // 置空操作
    linkCircleQueue.clear();
    System.out.println("当前队列是否为空: " + (linkCircleQueue.isEmpty() ? "是" : "否")); // 是
    // 边界处理
    try {
      System.out.println(linkCircleQueue.exitQueue());
    } catch (Exception exception) {
      System.out.println("异常信息: " + exception.getMessage() + "!".repeat(3));
    }
  }
}
