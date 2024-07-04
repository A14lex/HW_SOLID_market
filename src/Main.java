public class Main {
    /*
   Возможности программы:
Вывод доступных для покупки товаров
Фильтрация товаров по ключевым словам, ценам, производителям
Составление продуктовой корзины пользователя
Вывод списка заказов
   */

    public static void main(String[] args) throws Exception {
        // write your code here
        MyWarehouse myWarehouse = new MyWarehouse();//создали склад
        int idWareHouse = myWarehouse.hashCode();
        myWarehouse.products = Product.supply();//наполнили товаром
        MarketManager marketManager = new MarketManager(myWarehouse);//появился "управляющий" этим магазином

        while (marketManager.flag) {
            marketManager.showMenu();
            marketManager.action();

        }
        System.out.println("Конец программы");
    }
}
