package krsoaiheim.test.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import krsoaiheim.test.R;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
  private ImageView imageView;
  private TextView textView;
  private View itemView;


  public ItemsViewHolder(View itemView) {
    super(itemView);
    this.itemView = itemView;
    textView = itemView.findViewById(R.id.name);
    imageView = itemView.findViewById(R.id.img);
  }


  public void setImage(String img) {
    Picasso.get().load(img).into(imageView);
  }


  public void setName(String name) {
    textView.setText(name);
  }


  public View getItemView() {
    return itemView;
  }
}
