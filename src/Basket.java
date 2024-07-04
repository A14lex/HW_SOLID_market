import java.util.HashMap;
import java.util.Map;

public class Basket extends MyWarehouse {
    //что у корзины, что у склада будет одинаковый функционал
    public Map<Product, Integer> products = new HashMap<>();
    public Integer id = 0;

    public Basket() {

    }


}
