package Thread.Thread04;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap�ڲ�ʹ�ö�(Segment)����ʾ��Щ��ͬ�Ĳ��֣�ÿ������ʵ����һ��С��HashTable
 * ���������Լ�������ֻҪ����޸Ĳ��������ڲ�ͬ�Ķ��ϣ����ǾͿ��Բ������С�
 * ��һ������ֳ���16����(Segment)��Ҳ�������֧��16���̵߳Ĳ����޸Ĳ�����
 * ��Ҳ���ڶ��̳߳���ʱ��С�������ȴӶ�������������һ�ַ�����
 * ���Ҵ����д�๲�����ʹ��volatile�ؼ���������Ŀ���ǵ�һʱ���ȡ�޸ĵ����ݣ����ܷǳ��á�
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
