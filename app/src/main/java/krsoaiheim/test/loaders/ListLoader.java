package krsoaiheim.test.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

import krsoaiheim.test.model.DataManager;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.model.ModelInteractor;

public class ListLoader extends AsyncTaskLoader<List<Item>> {
  private final int page;
  private String code;


  public ListLoader(Context context, String code, int page) {
    super(context);
    this.code = code;
    this.page = page;
  }


  @Override
  protected void onStartLoading() {
    super.onStartLoading();
    forceLoad();
  }


  @Override
  public List<Item> loadInBackground() {
    ModelInteractor interactor = new DataManager();
    if (page == 1) {
      interactor.clearDB();
    }
    List<Item> list = null;
    try {
      list = interactor.getList(code, page);
    } catch (Exception e) {
      return null;
    }
    interactor.saveListToDB(list);
    return list;
  }
}
