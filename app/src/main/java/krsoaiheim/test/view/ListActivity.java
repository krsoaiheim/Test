package krsoaiheim.test.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import krsoaiheim.test.R;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.presenter.ListPresenter;

public class ListActivity extends AppCompatActivity implements IListActivity, IListener {
  private RecyclerView listView;
  private ListPresenter presenter;
  private LinearLayoutManager llm;
  private ItemsAdapter adapter;
  private int page = 1;
  private boolean isLoading = false;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list);
    listView = findViewById(R.id.list);
    String code = getIntent().getStringExtra("code");
    presenter = new ListPresenter(this, getLoaderManager(), code);
    llm = new LinearLayoutManager(this);
    adapter = new ItemsAdapter(this, getLoaderManager());
    adapter.setHasStableIds(true);
    listView.setAdapter(adapter);
    listView.setLayoutManager(llm);
    presenter.loadList(page);
    listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (dy>0) {
          // super.onScrolled(recyclerView, dx, dy);
          int visibleItemCount = llm.getChildCount();
          int totalItemCount = llm.getItemCount();
          int firstVisibleItemPosition = llm.findFirstVisibleItemPosition();
          if ((visibleItemCount+firstVisibleItemPosition) >= totalItemCount &&
                firstVisibleItemPosition >= 0 && !presenter
                .isEndReached() && !isLoading) {
            page++;
            isLoading = true;
            presenter.loadList(page);
          }
        }
      }
    });
  }


  public void decPage() {
    page--;
  }


  public void setLoading(boolean loading) {
    isLoading = loading;
  }


  @Override
  protected void onStop() {
    super.onStop();
    presenter.stopLoading();
  }


  @Override
  public void displayData(List<Item> data) {
    listView.invalidate();
    Log.i("loader", "displayData: "+data);
    if (page == 1) {
      adapter.clear();
    }
    Parcelable state = llm.onSaveInstanceState();
    adapter.add(data);
    llm.onRestoreInstanceState(state);
  }


  @Override
  public void openItem(String textId) {
    Intent intent = new Intent(this, ItemActivity.class);
    intent.putExtra("id", textId);
    startActivity(intent);
  }
}
