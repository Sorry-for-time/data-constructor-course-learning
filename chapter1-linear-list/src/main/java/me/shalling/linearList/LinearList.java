package me.shalling.linearList;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>基于数组的顺序表实现</h1>
 * @package {me.shalling.linearList}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/9/24 18:45
 */
public class LinearList<T> implements Serializable, Iterable<T> {
  /**
   * @description 序列化 ID 标识
   */
  @Serial
  private static final long serialVersionUID = -4364246500592769953L;

  /**
   * @description 线性表的容量, 默认: 20
   */
  private int maxLength = 20;

  /**
   * @description 缓冲区使用到的下标位置
   */
  private int currentIndex;

  /**
   * @description 缓冲区数组
   */
  private final Object[] buffer;

  public LinearList() {
    this.buffer = new Object[this.maxLength];
  }

  public boolean isEmpty() {
    return this.maxLength == 0;
  }

  public boolean isRunOut() {
    return this.maxLength == this.currentIndex;
  }

  public LinearList(int maxLength) {
    this.maxLength = maxLength;
    this.buffer = new Object[this.maxLength];
  }

  /**
   * @param element        插入的新元素
   * @param insertLocation 插入的位置
   * @description 将新元素插入到顺序表当中
   */
  public void insertToList(T element, int insertLocation) {
    // 边界判断
    if (this.isRunOut()) {
      throw new RuntimeException("空间已用完");
    }
    if (insertLocation > currentIndex + 1) {
      throw new RuntimeException("请插入在: " + this.currentIndex + " 之前");
    }
    if (insertLocation < 1) {
      throw new RuntimeException("下标非法");
    }
    // 采用尾插法进行插入
    for (int i = this.currentIndex; i >= insertLocation; --i) {
      this.buffer[i] = this.buffer[i - 1];
    }
    this.buffer[insertLocation - 1] = element; // 新插入的元素放置在数组对应的缓存下标位置
    ++this.currentIndex;
  }

  /**
   * @param newItem  新元素
   * @param location 指定下表
   * @description 根据索引位置更新顺序表中的指定元素
   */
  public void updateItemByIndex(T newItem, int location) {
    if (location > this.currentIndex + 1 || location < 1) {
      throw new RuntimeException("下标位置非法");
    }
    this.buffer[location - 1] = newItem;
  }

  public T deleteItem(int removeLocation) {
    // 边界判断
    if (this.isEmpty()) {
      throw new RuntimeException("当前列表为空");
    }
    if (removeLocation > this.currentIndex || removeLocation < 1) {
      throw new RuntimeException("索引位置非法");
    }
    // 取得对应下标的值进行保存
    T backValue = (T) this.buffer[removeLocation - 1];
    // 循环进行元素向前迁移
    for (int i = removeLocation - 1; i < this.currentIndex; ++i) {
      this.buffer[i - 1] = this.buffer[i];
    }
    --this.currentIndex;
    return backValue;
  }


  /**
   * @param consumer 消费者对象
   * @description 遍历顺序表中缓存区的实际元素
   */
  public void forEach(Consumer<? super T> consumer) {
    for (int i = 0; i < this.currentIndex; i++) {
      consumer.accept((T) this.buffer[i]);
    }
  }

  /**
   * @return 元素列表
   * @description 取得缓存中的列表元素数组
   */
  public T[] getFactItems() {
    Object[] backValue = new Object[this.currentIndex];
    System.arraycopy(this.buffer, 0, backValue, 0, this.currentIndex);
    return (T[]) backValue;
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private int start = 0;

      @Override
      public boolean hasNext() {
        return start < currentIndex;
      }

      @Override
      public T next() {
        return (T) buffer[start++];
      }
    };
  }
}
