package krsoaiheim.test.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import krsoaiheim.test.R;
import krsoaiheim.test.model.Item;
import krsoaiheim.test.presenter.ItemPresenter;

public class ItemActivity extends AppCompatActivity implements IItem, OnMapReadyCallback {
  private TextView textId, textName, textCountry;
  private MapView map;
  private String id;
  private ItemPresenter presenter;
  private Long lon, lat;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item);
    id = getIntent().getStringExtra("id");
    textId = findViewById(R.id.id);
    textName = findViewById(R.id.name);
    textCountry = findViewById(R.id.country);
    map = findViewById(R.id.map);
    presenter = new ItemPresenter(this, getLoaderManager());
    presenter.loadItem(id);
    map.onCreate(new Bundle());
  }


  @Override
  protected void onStart() {
    super.onStart();
    map.onStart();
  }


  @Override
  protected void onResume() {
    super.onResume();
    map.onResume();
  }


  @Override
  protected void onDestroy() {
    super.onDestroy();
    map.onDestroy();
  }


  @Override
  public void displayItem(Item item) {
    textId.setText("ID:  "+item.getTextId());
    textName.setText("Name:  "+item.getName());
    textCountry.setText("Country:  "+item.getCountry());
    lat = item.getLatitude();
    lon = item.getLongitude();
    if (lat != null && lon != null) {
      map.getMapAsync(this);
    }
  }


  @Override
  public void onMapReady(GoogleMap googleMap) {
    LatLng coords = new LatLng(lat, lon);
    googleMap.setMinZoomPreference(15);
    googleMap.setMaxZoomPreference(25);
    googleMap.addMarker(new MarkerOptions().position(coords));
    googleMap.moveCamera(CameraUpdateFactory.newLatLng(coords));
  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
    finish();
  }
}
