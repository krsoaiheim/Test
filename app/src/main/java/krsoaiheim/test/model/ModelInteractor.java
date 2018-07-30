package krsoaiheim.test.model;

import java.util.List;

public interface ModelInteractor {
  String auth(String login, String password);
  List<Item> getList(String code, int page);
  void saveListToDB(List<Item> list);
  List<Item> getListFromDB();
  void clearDB();
  Item getItem(String id);
}
