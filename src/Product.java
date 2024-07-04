import java.util.HashMap;
import java.util.Map;

public class Product {
    private static Map<Product, Integer> productOfOfSupply = new HashMap<>();

    public static Map<Product, Integer> supply() {
        //поставка прямо на склад от поставщика. Это для демонстрации программы
        productOfOfSupply.put(new Product("Конфеты", "Радуга", 120239, "Шоколадный батончик, Мишка на севере"), 10);
        productOfOfSupply.put(new Product("Молоко", "Простоквашино", 120001, "Цельное, Молоко из Простоквашино"), 12);
        productOfOfSupply.put(new Product("Молоко", "Радуга", 120233, "Безлактозное, 1л"), 33);
        productOfOfSupply.put(new Product("Чайник", "Радуга", 340239, "Электрический, 1кВт"), 2);
        productOfOfSupply.put(new Product("Чай", "Акбар", 140239, "Цейлонский развесной"), 87);
        productOfOfSupply.put(new Product("Вода", "Мосводоканал", 777239, "Вода простая, 50л"), 100);
        productOfOfSupply.put(new Product("Вода", "Радуга", 120555, "Вода газированная, 1л"), 3);
        return productOfOfSupply;
    }

    String name;//наименование (конфеты, игрушки, телевизоры)
    String factory;//изготовитель
    int article;//артикул
    String characteristic;//описание товара

    public Product(String name, String factory, int article, String characteristic) {

        this.name = name;
        this.factory = factory;
        this.article = article;
        this.characteristic = characteristic;
    }

    public Product(Product product) {
        this.name = product.name;
        this.factory = product.factory;
        this.article = product.article;
        this.characteristic = product.characteristic;
    }


    @Override
    public String toString() {
        return "[Наименование: " + name + "; Производитель: " + factory + "; " + " Артикул: " + article + "]";
    }
}
