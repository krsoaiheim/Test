package krsoaiheim.test.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import krsoaiheim.test.R;
import krsoaiheim.test.model.Item;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
  private List<Item> list = new ArrayList<>();
  private IListener listener;


  public ItemsAdapter(IListener listener) {
    this.listener = listener;
    setHasStableIds(false);
  }


  @NonNull
  @Override
  public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
    return new ItemsViewHolder(v);
  }


  @Override
  public void onBindViewHolder(@NonNull ItemsViewHolder holder, int position) {
    final Item item = list.get(position);
    holder.setName(item.getName());
    holder.setImage(item.getImage());
    holder.getItemView().setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        listener.openItem(item.getTextId());
      }
    });
  }


  @Override
  public int getItemCount() {
    return list.size();
  }


  public void add(List<Item> data) {
    final int size = list.size();
    list.addAll(data);
    notifyItemRangeInserted(size+1, data.size());
  }


  @Override
  public long getItemId(int position) {
    return position;
  }


  public void clear() {
    list.clear();
    notifyDataSetChanged();
  }
}
