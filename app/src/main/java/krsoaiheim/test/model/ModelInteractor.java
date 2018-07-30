package krsoaiheim.test.model;

import java.util.List;

public interface ModelInteractor {
  String auth(String login, String password) throws Exception;
  List<Item> getList(String code, int page) throws Exception;
  void saveListToDB(List<Item> list);
  void clearDB();
  Item getItem(String id);
}
