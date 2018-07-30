package krsoaiheim.test.model;

import android.util.Log;

import java.util.List;

import krsoaiheim.test.TestApp;
import krsoaiheim.test.model.db.DBFactory;
import krsoaiheim.test.model.db.DBHelper;
import krsoaiheim.test.model.network.ApiJsonParser;
import krsoaiheim.test.model.network.ApiService;

public class DataManager implements ModelInteractor {
  private ApiService apiService;
  private ApiJsonParser parser;
  private DBHelper helper;


  public DataManager() {
    apiService = new ApiService();
    parser = new ApiJsonParser();
    DBFactory factory = new DBFactory(TestApp.getInstance().getApplicationContext());
    helper = new DBHelper(factory);
  }


  @Override
  public String auth(String login, String password) {
    try {
      String resp = apiService.auth(login, password);
      Log.i("loader", "loadInBackground: "+resp);
      if (resp.equals("-1")) {
        return resp;
      } else {
        String code = parser.parseCode(resp);
        return code;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "-1";
    }
  }


  @Override
  public List<Item> getList(String code, int page) {
    try {
      String resp = apiService.getList(code, page);
      Log.i("loader", "loadInBackground: "+resp);
      if (resp.equals("-1")) {
        return null;
      } else {
        return parser.parseList(resp);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  @Override
  public void saveListToDB(List<Item> list) {
    helper.saveList(list);
  }


  @Override
  public List<Item> getListFromDB() {
    return helper.getList();
  }


  @Override
  public void clearDB() {
    helper.clearDB();
    Log.i("sql", "clearDB: cleared");
  }


  @Override
  public Item getItem(String id) {
    return helper.getItem(id);
  }
}
