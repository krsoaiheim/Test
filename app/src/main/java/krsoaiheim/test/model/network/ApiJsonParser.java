package krsoaiheim.test.model.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import krsoaiheim.test.model.Item;

public class ApiJsonParser {
  public String parseCode(String resp) throws JSONException {
    JSONObject obj = new JSONObject(resp);
    String status = obj.getString("status");
    if (status.equals("ok")) {
      return obj.getString("code");
    } else {
      return null;
    }
  }


  public List<Item> parseList(String resp) throws JSONException {
    JSONObject obj = new JSONObject(resp);
    JSONArray array = obj.getJSONArray("data");
    List<Item> itemList = new ArrayList<>();
    for (int i = 0; i<array.length(); i++) {
      JSONObject itemObj = array.getJSONObject(i);
      Item item = new Item();
      item.setTextId(itemObj.getString("id"));
      item.setName(itemObj.getString("name"));
      item.setCountry(itemObj.getString("country"));
      item.setLatitude(itemObj.getLong("lat"));
      item.setLongitude(itemObj.getLong("lon"));
      item.setImage("https://pp.userapi.com/c840137/v840137205/dfd6/Qo6IJTC33Fw.jpg");
      itemList.add(item);
    }
    return itemList;
  }
}
