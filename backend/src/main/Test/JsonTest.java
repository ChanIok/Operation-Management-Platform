import com.alibaba.fastjson.JSONObject;
import com.hrm.pojo.Objects;
import com.hrm.utils.JsonUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonTest {
    @Test
    public void test1(){

        ArrayList<Objects> objects = new ArrayList<>();

        //  objectMap.put("s",new Objects((int)1,(int)2,Integer.parseInt("3")));
        //  objectMap.put("s",new Objects((int)4,(int)5,Integer.parseInt("6")));

        objects.add(new Objects(4,5,6));
        objects.add(new Objects(1,2,3));
        System.out.println(objects.toString());

        HashMap<String, ArrayList> map = new HashMap<>();
        map.put("list",objects);


        System.out.println(map.toString());

        JSONObject jsonObject = JsonUtils.MapToJson(map);
        System.out.println(jsonObject);


         HashMap<String, HashMap<String, ArrayList>> dataList = new HashMap<>();
         //dataList.put("list",)
    }
}
