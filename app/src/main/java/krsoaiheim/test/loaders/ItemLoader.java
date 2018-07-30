package krsoaiheim.test.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import krsoaiheim.test.model.DataManager;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.model.ModelInteractor;

public class ItemLoader extends AsyncTaskLoader<Item> {
  private String id;


  public ItemLoader(Context context, String id) {
    super(context);
    this.id = id;
  }


  @Override
  protected void onStartLoading() {
    super.onStartLoading();
    forceLoad();
  }


  @Override
  public Item loadInBackground() {
    ModelInteractor interactor = new DataManager();
    return interactor.getItem(id);
  }
}
