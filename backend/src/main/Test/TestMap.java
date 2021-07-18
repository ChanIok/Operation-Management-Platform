import com.hrm.pojo.Cart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zjw
 * @package backend
 * @Date 2021/7/12
 * @Time 19:10
 */
public class TestMap {
    @Test
    public void Map() {


        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);


        ArrayList<Map<String, Integer>> list = new ArrayList<Map<String, Integer>>();
        list.add(map);

        for (Map<String, Integer> map1 : list) {
            for (String s : map1.keySet()
            ) {
                System.out.println("key:" + s + "\t");
                System.out.println("value" + map1.get(s));
            }
        }
    }
}
