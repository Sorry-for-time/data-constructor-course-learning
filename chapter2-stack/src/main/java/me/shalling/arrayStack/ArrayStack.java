package me.shalling.arrayStack;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>基于数组实现的一个简单栈结构</h1>
 * @package {me.shalling.arrayStack}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/16 21:12
 */
public class ArrayStack<T> implements Serializable, Iterable<T> {
  @Serial
  private static final long serialVersionUID = 3852945347676192668L;

  /**
   * @description 栈元素上限
   */
  private final int stackMaxSize;

  /**
   * @description 当前栈顶所在的位置
   */
  private int currentFrameLocation;

  /**
   * @description 用于存储栈元素的数组
   */
  private final Object[] stackBuffer;

  public ArrayStack() {
    this.stackMaxSize = 100;
    this.stackBuffer = new Object[this.stackMaxSize];
  }

  public ArrayStack(int stackMaxSize) {
    this.stackMaxSize = stackMaxSize;
    this.stackBuffer = new Object[this.stackMaxSize];
  }

  /**
   * @return 栈是否为空
   * @description 判断当前栈是否为空
   */
  public boolean isEmpty() {
    return this.currentFrameLocation == 0;
  }

  /**
   * @return 栈是否已满
   * @description 判断当前栈是否已满
   */
  public boolean isFull() {
    return this.currentFrameLocation == this.stackMaxSize;
  }

  /**
   * @description 置空栈
   */
  public void clear() {
    this.currentFrameLocation = 0;
  }

  /**
   * @param stackFrame 新元素
   * @description 往栈中插入新元素
   */
  public void push(T stackFrame) {
    if (this.isFull()) {
      throw new RuntimeException("当前栈已满");
    }
    this.stackBuffer[this.currentFrameLocation++] = stackFrame;
  }

  /**
   * @return 被弹出的元素
   * @description 弹出当前栈顶的元素
   */
  @SuppressWarnings("unchecked")
  public T pop() {
    if (this.isEmpty()) {
      throw new RuntimeException("当前栈为空");
    }
    return (T) this.stackBuffer[(this.currentFrameLocation--) - 1];
  }

  /**
   * @return 栈顶元素
   * @description 取栈顶元素, 这个操作不会导致栈顶元素被弹出
   */
  @SuppressWarnings("unchecked")
  public T getStackTopFrame() {
    if (this.isEmpty()) {
      throw new RuntimeException("当前栈为空");
    }
    return (T) this.stackBuffer[this.currentFrameLocation - 1];
  }

  /**
   * @return 跌代元素
   * @description 提供迭代器用于 for-each 循环
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      private int end = currentFrameLocation - 1;

      @Override
      public boolean hasNext() {
        return end >= 0;
      }

      @SuppressWarnings("unchecked cast")
      @Override
      public T next() {
        return (T) stackBuffer[this.end--];
      }
    };
  }

  /**
   * @param action 用于结束执处理行栈元素的函数式接口实例
   * @description 对栈进行遍历, 这个操作不会弹出栈元素
   */
  @Override
  @SuppressWarnings("unchecked cast")
  public void forEach(Consumer<? super T> action) {
    for (int i = this.currentFrameLocation - 1; i >= 0; --i) {
      action.accept((T) this.stackBuffer[i]);
    }
  }

  /**
   * @return 栈顶元素位置
   * @description 获取当前栈顶元素的位置
   */
  public int getCurrentFrameLocation() {
    return currentFrameLocation;
  }
}
