import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MarketManager {
    MyWarehouse myWareHouse;
    Map<Integer, Basket> basketList = new HashMap<>();//для хранения списка корзин, что были куплены
    Basket basket;

    public MarketManager(MyWarehouse myWareHouse) {
        this.myWareHouse = myWareHouse;
        basket = new Basket();
    }

    boolean flag = true;//разрешение на выполнение обслуживания

    //Должен выводить список возможных действий для покупателя
    //Затем принять в консоль пункт меню
    /*
    1. Показать спиок товаров.
    2. Отфильтровать список товаров по ключевым словам (цена, производитель, еще что-то)
    3. Принять в корзину товар по артикулу в каком-то кол-ве (артикул, кол-во)
    4. Завершить формирование корзины
    5. Показать корзину
    6. Вывести цену
    7. Сформировать заказ произвести оплату
    /*
    Т.к. этот менеджер работает прямо с консолью, то будем считать, что условно он уже реализовал какой-то интерфейс в котором
    есть методы, которые он в себе реализовал
     */
    public void showMenu() {
        System.out.println("Здравствуйте! Мы начинаем собирать Вам корзину! Ознакомьтесь и выберите услугу:\n" +
                "1. Показать спиcок товаров.\n" +
                "2. Отфильтровать список товаров по ключевым словам (цена, производитель, еще что-то)\n" +
                "3. Принять в корзину товар по артикулу в каком-то кол-ве (артикул, кол-во)\n" +
                "4. Место для рекламы\n" +
                "5. Показать корзину\n" +
                "6. Сформировать заказ произвести оплату\n" +
                "7. Показать список заказов\n" +
//                "8. Очистить корзину ()\n" +
//                "9. Произвести возврат товара (для этого иметь где-то в списке заказов этот заказ)\n" +
//                "10. Повторить заказ (Загрузить из списка заказов, по № с консоли)\n" +
                "11. Выйти из программы");
    }

    public void action() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String i = scanner.nextLine();
        switch (i) {
            case "1":// Показать спиcок товаров
                System.out.println("Сегодня в продаже:");
                myWareHouse.showPrice();
                break;

            case "2"://Отфильтровать по ключевым словам
                filterParametr();
                break;
            case "3"://начать собирать корзину
                createBasket();
                break;
//
            case "5"://показать корзину
                this.basket.showPrice();
                //корзина и склад - идентичные объекты. Методы можно исользовать те же.

                break;
            case "6":
//                Сформировать корзину и завершить покупку
//                при этом в список корзин заносится эта корзина с идентификационным номером, сама корзина очищается.
                System.out.println("Покупка совершена!");
//                basket.createId();
                int idBasket = basket.hashCode();
                System.out.println("Id Вашего заказа (корзины): " + idBasket);
                basketList.put(idBasket, this.basket);
                this.basket = new Basket();//иначе не во что будет собирать следующие продукты
                break;
            case "7":
//                Показатть список заказов
                System.out.println(basketList.toString());


                break;

            case "11":
                System.out.println("Выход из программы");
                flag = false;

                break;


        }
    }

    private void createBasket() throws Exception, ExceptionOrder {
        System.out.println("Собираем корзину");
        System.out.println("Введите артикул и кол-во товара через enter");

        Scanner scanner = new Scanner(System.in);
        Integer article = scanner.nextInt();
        Integer count = scanner.nextInt();
        if (article == null) {
            System.out.println("Вы не ввели артикул!");
            return;
        }
        //Надо выбрать товар c таким артикулом
        Product product = null;
        for (Map.Entry<Product, Integer> element :
                myWareHouse.getProducts().entrySet()) {
            if (element.getKey().article == article) {
                product = element.getKey();

            }
        }
        if (product == null) {
            System.out.println("Нет такого артикула");
            return;
        }
        //Переместить нужное кол-во со склада в корзину
        try {
            myWareHouse.shipment(count, product);
        } catch (ExceptionOrder e) {
            System.out.println(e.getMessage());
            return;
        }
        this.basket.refund(count, product);
        System.out.println(this.basket.toString());

    }

    public void filterParametr() {
        //Запрашиваем ключевые слова, по ним сортируем Map
        System.out.println("Пожалуйста введите через enter: Наименование товара, производитель, артикул");
        System.out.println("пропуск означает, что ключевой параметр может быть любым");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        String fabricName = scanner.nextLine();
        String stringArticle = scanner.nextLine();

        Integer intArticle;
        if (!stringArticle.isEmpty()) {
            intArticle = Integer.parseInt(stringArticle);
        } else {
            intArticle = 0;
        }

        for (Map.Entry<Product, Integer> element :
                myWareHouse.getProducts().entrySet()) {
            if (element.getKey().name.equals(name) || name.equals("")) {
                if (element.getKey().factory.equals(fabricName) || fabricName.equals("")) {
                    if (element.getKey().article == intArticle || intArticle == 0) {
                        System.out.println(element.toString());
                    }
                }
            }

        }


    }
}
