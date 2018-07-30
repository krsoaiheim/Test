package krsoaiheim.test.model;

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
  public String auth(String login, String password) throws Exception {
    String resp = apiService.auth(login, password);
    return parser.parseCode(resp);
  }


  @Override
  public List<Item> getList(String code, int page) throws Exception {
    String resp = apiService.getList(code, page);
    return parser.parseList(resp);
  }


  @Override
  public void saveListToDB(List<Item> list) {
    helper.saveList(list);
  }


  @Override
  public void clearDB() {
    helper.clearDB();
  }


  @Override
  public Item getItem(String id) {
    return helper.getItem(id);
  }
}
