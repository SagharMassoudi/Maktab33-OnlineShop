package dto;

public class Products {
  private int id;
  private String name;
  private String model;
  private String producer;
  private double price;
  private int stock;

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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return("___________________________"+"\n"+"ID:" +
                id + "\n" + "Name:" + name + "\n" + "Model:" + model +
                "\n" + "Producer:" + producer + "\n" + "Price:" + price + "\n" +
                "Stock:" + stock);
    }

}
