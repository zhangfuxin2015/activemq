package Thread.Thread04;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的HashTable
 * ，它们有自己的锁。只要多个修改操作发生在不同的段上，它们就可以并发进行。
 * 把一个整体分成了16个段(Segment)。也就是最高支持16个线程的并发修改操作。
 * 这也是在多线程场景时减小锁的粒度从而降低锁竞争的一种方案。
 * 并且代码中大多共享变量使用volatile关键字声明，目的是第一时间获取修改的内容，性能非常好。
 */
public class UseConcurrentMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
        chm.put("k1", "v1");
        chm.put("k2", "v2");
        chm.put("k3", "v3");
        chm.putIfAbsent("k4", "vvvv");
        //System.out.println(chm.get("k2"));
        //System.out.println(chm.size());

        for(Map.Entry<String, Object> me : chm.entrySet()){
            System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
        }

    }
}
