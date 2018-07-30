package krsoaiheim.test.presenter;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import java.util.List;

import krsoaiheim.test.TestApp;
import krsoaiheim.test.loaders.ListLoader;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.view.IListActivity;

public class ListPresenter implements LoaderManager.LoaderCallbacks<List<Item>> {
  private static final int ID = 1;
  private static final int LIST_PAGE_SIZE = 10;
  private final String code;
  private IListActivity view;
  private LoaderManager loaderManager;
  private ListLoader listLoader;
  private boolean endReached = false;


  public ListPresenter(IListActivity view, LoaderManager loaderManager, String code) {
    this.view = view;
    this.code = code;
    this.loaderManager = loaderManager;
  }


  public boolean isEndReached() {
    return endReached;
  }


  public void loadList(int page) {
    Bundle args = new Bundle();
    args.putInt("page", page);
    loaderManager.initLoader(page, args, this);
  }


  public void stopLoading() {
  }


  @Override
  public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
    return new ListLoader(TestApp.getInstance().getApplicationContext(),
          code,
          args.getInt("page"));
  }


  @Override
  public void onLoadFinished(Loader<List<Item>> loader, List<Item> list) {
    if (list != null && list.size()<LIST_PAGE_SIZE) {
      endReached = true;
    }
    if (list == null) {
      view.decPage();
    }
    loaderManager.destroyLoader(loader.getId());
    view.setLoading(false);
    view.displayData(list);
  }


  @Override
  public void onLoaderReset(Loader<List<Item>> loader) {
  }
}
