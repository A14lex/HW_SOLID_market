import java.util.HashMap;
import java.util.Map;

public class MyWarehouse implements WareHouse<Integer, Product> {
    //Кладовщик на складе просто отгружает и принимает товар
    public Map<Product, Integer> products = new HashMap<>();

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void showPrice() {
        System.out.println(this.products.toString());
    }

    @Override
    public void refund(Integer integer, Product product) {
        if (this.products.containsKey(product)) {
            products.replace(product, products.get(product) + integer);//есть такой-же товар, просто прибавляем кол-во
        } else {
            this.products.put(product, integer);
        }

    }

    @Override
    public Product shipment(Integer integer, Product product) throws Exception {
        if (this.products.containsKey(product)) {
            if (this.products.get(product) >= integer) {
                System.out.println("Есть товар на складе в достаточном кол-ве");
                Product basketProduct = new Product(product);
                this.products.replace(product, products.get(product) - integer);//уменьшили товар на складе на кол-во, перемещенное в корзину
                return basketProduct;
            } else {
                System.out.println("Нет товара в таком кол-ве, выбрасываем ошибку");
                throw new ExceptionOrder();

            }
        }

        return null;
    }

}
