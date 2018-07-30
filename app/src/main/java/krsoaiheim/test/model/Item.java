package krsoaiheim.test.model;

public class Item {
  private int id;


  public String getTextId() {
    return textId;
  }


  public void setTextId(String textId) {
    this.textId = textId;
  }


  private String textId;
  private String name;
  private String country;
  private long latitude;
  private long longitude;
  private String image;


  public Item() {
  }


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getCountry() {
    return country;
  }


  public void setCountry(String country) {
    this.country = country;
  }


  public long getLatitude() {
    return latitude;
  }


  public void setLatitude(long latitude) {
    this.latitude = latitude;
  }


  public long getLongitude() {
    return longitude;
  }


  public void setLongitude(long longitude) {
    this.longitude = longitude;
  }


  public String getImage() {
    return image;
  }


  public void setImage(String image) {
    this.image = image;
  }


  @Override
  public String toString() {
    return "[id="+id+"; textId="+textId+"]";
  }
}
