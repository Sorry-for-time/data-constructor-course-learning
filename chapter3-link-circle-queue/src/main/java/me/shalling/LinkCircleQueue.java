package me.shalling;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>基于链表实现的循环队列</h1>
 * @package {me.shalling}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/10/16 22:10
 */
public class LinkCircleQueue<T> implements Serializable, Iterable<T> {
  @Serial
  private static final long serialVersionUID = -5433111772277942347L;

  /**
   * @description 循环队列元素存储上限
   */
  private final int maxQueueNodeSize;

  /**
   * @description 当前循环队列使用空间
   */
  private int currentUse = 0;

  /**
   * @description 队首定义
   */
  private QueueNode<T> front;

  /**
   * @description 队尾部定义
   */
  private QueueNode<T> rear;

  public LinkCircleQueue() {
    this.maxQueueNodeSize = 20;
  }

  public LinkCircleQueue(int maxQueueNodeSize) {
    this.maxQueueNodeSize = maxQueueNodeSize;
  }

  /**
   * @return 当前队列是否为空
   */
  public boolean isEmpty() {
    return this.currentUse == 0;
  }

  /**
   * @return 当前队列是否为空
   */
  public boolean isFull() {
    return this.currentUse == this.maxQueueNodeSize;
  }

  /**
   * @param data 入队数据
   * @description 入队操作
   */
  public void enterQueue(T data) {
    if (this.isFull()) {
      throw new RuntimeException("当前队列已满");
    }

    QueueNode<T> tQueueNode = new QueueNode<>(data, null);
    if (this.isEmpty()) {
      this.front = tQueueNode;
      this.rear = this.front;
    } else {
      this.rear.setNext(tQueueNode);
      this.rear = this.rear.getNext();
    }
    // 更新使用记录
    ++this.currentUse;
  }

  /**
   * @return 出队元素数据域值
   * @description 出队操作
   */
  public T exitQueue() {
    if (this.isEmpty()) {
      throw new RuntimeException("当前队列为空");
    }
    // 取得队首元素的数据域值
    T frontDataDomain = this.front.getDataDomain();
    // 更新首部元素位置和使用空间记录
    --this.currentUse;
    this.front = this.front.getNext();
    // 如果队列里已经没有了任何元素, 那么进行重置首尾引用
    if (this.currentUse == 0) {
      this.front = this.rear = null;
    }
    return frontDataDomain;
  }

  /**
   * @description 置空循环链队列
   */
  public void clear() {
    this.currentUse = 0;
    this.front = this.rear = null;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<>() {
      QueueNode<T> current = front;

      @Override
      public boolean hasNext() {
        return null != this.current;
      }

      @Override
      public T next() {
        T dataDomain = this.current.getDataDomain();
        this.current = this.current.getNext();
        return dataDomain;
      }
    };
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    QueueNode<T> current = this.front;
    while (null != current) {
      action.accept(current.getDataDomain());
      current = current.getNext();
    }
  }
}
