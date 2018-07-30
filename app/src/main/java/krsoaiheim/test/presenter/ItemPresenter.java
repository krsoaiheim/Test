package krsoaiheim.test.presenter;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import krsoaiheim.test.TestApp;
import krsoaiheim.test.loaders.ItemLoader;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.view.IItem;

public class ItemPresenter implements LoaderManager.LoaderCallbacks<Item> {
  private IItem view;
  private LoaderManager loaderManager;


  public ItemPresenter(IItem view, LoaderManager loaderManager) {
    this.view = view;
    this.loaderManager = loaderManager;
  }


  public void loadItem(String id) {
    Bundle args = new Bundle();
    args.putString("id", id);
    loaderManager.restartLoader(-1, args, this);
  }


  @Override
  public Loader<Item> onCreateLoader(int id, Bundle args) {
    return new ItemLoader(TestApp.getInstance().getApplicationContext(),
          args.getString("id"));
  }


  @Override
  public void onLoadFinished(Loader<Item> loader, Item data) {
    view.displayItem(data);
  }


  @Override
  public void onLoaderReset(Loader<Item> loader) {
  }
}
