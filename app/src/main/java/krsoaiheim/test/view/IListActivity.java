package krsoaiheim.test.view;

import java.util.List;

import krsoaiheim.test.model.Item;

public interface IListActivity {
  void displayData(List<Item> data);
  void setLoading(boolean b);
  void decPage();
  void showMsg();
}
