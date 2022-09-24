package me.shalling.linkLinearList;

import java.io.Serial;
import java.io.Serializable;
import java.util.function.Consumer;

/**
 * @author Shalling
 * @version v0.01
 * @description <h1>基于单链表实现的顺序表</h1>
 * @package {me.shalling.linkLinearList}
 * @see <a href="https://github.com/Sorry-for-time">follow me on github</a>
 * @since 2022/9/24 18:51
 */
public class LinkLinearList<T> implements Serializable {
  /**
   * @description 序列化 UID 标记
   */
  @Serial
  private static final long serialVersionUID = 2755267163778624810L;

  /**
   * @description 顺序表最大长度
   */
  private final int capacity;

  /**
   * @description 顺序表以使用的容量, 默认: 0
   */
  private int currentIndex = 0;

  private LinkNode<T> endNodeRecord;

  /**
   * @description 顺序表头节点
   */
  private LinkNode<T> headNode;

  public LinkLinearList() {
    this.capacity = 20;
  }

  public LinkLinearList(int maxSize) {
    this.capacity = maxSize;
  }

  /**
   * @return true | false
   * @description 当前顺序表是否已满
   */
  public boolean isRunOut() {
    return this.capacity == this.currentIndex;
  }

  /**
   * @return true | false
   * @description 判断当前顺序表是否为空
   */
  public boolean isEmpty() {
    return this.currentIndex == 0;
  }

  /**
   * @param dataDomainValue 插入的数据
   * @description 将新的数据插入到顺序表末尾
   */
  public void insertToList(T dataDomainValue) {
    if (this.isRunOut()) {
      throw new RuntimeException("空间已用完");
    }
    if (this.isEmpty()) {
      this.headNode = new LinkNode<>(dataDomainValue, null);
      this.endNodeRecord = this.headNode;
    } else {
      LinkNode<T> tLinkNode = new LinkNode<>(dataDomainValue, null);
      this.endNodeRecord.setNext(tLinkNode);
      this.endNodeRecord = tLinkNode;
    }
    ++this.currentIndex;
  }

  /**
   * @param dataDomainValue 数据域
   * @param insertLocation  插入的位置
   * @description 往新的节点中插入
   */
  public void insertToList(T dataDomainValue, int insertLocation) {
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
    // 如果是空顺序表的情况下直接插入并设置头部
    if (this.isEmpty()) {
      this.headNode = new LinkNode<>(dataDomainValue, null);
      // 记录链表尾元素的位置
      this.endNodeRecord = this.headNode;
    }
    // 如果插入的位置是在尾部
    else if (this.currentIndex + 1 == insertLocation) {
      LinkNode<T> tLinkNode = new LinkNode<>(dataDomainValue, null);
      this.endNodeRecord.setNext(tLinkNode);
      // 更新尾部元素的位置
      this.endNodeRecord = tLinkNode;
    }
    // 其它的情况
    else {
      LinkNode<T> current = this.headNode;
      for (int i = 1; i < insertLocation; i++) {
        current = current.getNext();
      }
      LinkNode<T> tLinkNode = new LinkNode<>(dataDomainValue, current.getNext());
      current.setNext(tLinkNode);
    }
    // 使用记录 +1
    ++this.currentIndex;
  }


  /**
   * @return 删除节点的数据域值
   * @description 删除顺序表末尾所在的元素
   */
  public T deleteItem() {
    if (isEmpty()) {
      throw new RuntimeException("当前顺序表为空");
    }
    T backValue = this.endNodeRecord.getDataItem();
    if (this.currentIndex == 1) {
      this.headNode = null;
      this.endNodeRecord = null;
    } else {
      // 更新尾部元素的位置
      LinkNode<T> record = this.headNode;
      for (int i = 1; i < this.currentIndex - 1; i++) {
        record = record.getNext();
      }
      this.endNodeRecord = record;
    }
    --this.currentIndex;
    return backValue;
  }

  /**
   * @param removeLocation 删除的指定位置
   * @return 删除节点的数据域值
   * @description 删除顺序表指定下标位置所在的元素
   */
  public T deleteItem(int removeLocation) {
    // 边界判断
    if (this.isEmpty()) {
      throw new RuntimeException("当前列表为空");
    }
    if (removeLocation > this.currentIndex || removeLocation < 1) {
      throw new RuntimeException("索引位置非法");
    }
    T backValue;
    LinkNode<T> current = this.headNode;
    if (removeLocation == 1) {
      backValue = this.headNode.getDataItem();
      if (this.currentIndex == 1) {
        this.headNode = null;
        this.endNodeRecord = null;
      }
    } else {
      for (int i = 1; i < removeLocation - 1; i++) {
        current = current.getNext();
      }
      // 如果是直接删除末尾的元素, 那么需要更新节点位置
      if (this.currentIndex == removeLocation) {
        this.endNodeRecord = current;
      }
      backValue = current.getNext().getDataItem();
      // 更新指向, 指向要移除元素的下一个节点
      current.setNext(current.getNext().getNext());
    }
    // 更新记录
    --this.currentIndex;
    return backValue;
  }

  /**
   * @param consumer 消费者函数
   * @description 对链表实现的顺序表进行遍历操作
   */
  public void forEach(Consumer<T> consumer) {
    LinkNode<T> sendItem = this.headNode;
    for (int i = 0; i < this.currentIndex; i++) {
      consumer.accept(sendItem.getDataItem());
      sendItem = sendItem.getNext();
    }
  }

  /**
   * @param newDomainValue 进行替换的新数据
   * @param location       指定位置
   * @description 根据指定下标位置更新顺序表中指定元素数据域的值
   */
  public void updateItemByIndex(T newDomainValue, int location) {
    // 边界判断
    if (this.isEmpty()) {
      throw new RuntimeException("当前列表为空");
    }
    if (location > this.currentIndex || currentIndex < 1) {
      throw new RuntimeException("索引位置非法");
    }
    LinkNode<T> current = this.headNode;
    for (int i = 1; i < location; ++i) {
      // 更新标记节点的位置
      current = current.getNext();
    }
    // 更新节点值
    current.setDataItem(newDomainValue);
  }

  /**
   * @return 实际元素组成的数组
   * @description 返回顺序表中的实际元素组成的数组
   */
  public T[] getFactItems() {
    T[] backValue = (T[]) new Object[this.currentIndex];
    LinkNode<T> current = this.headNode;
    for (int i = 0; i < this.currentIndex; ++i) {
      // 更新标记节点的位置
      backValue[i] = current.getDataItem();
      current = current.getNext();
    }
    return backValue;
  }
}
