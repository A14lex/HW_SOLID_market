public interface WareHouse<C1, T1> {
    //Задача склада - выдавать и принимать товар
    void refund(C1 c1, T1 t1);//прием товара

    T1 shipment(C1 c1, T1 t1) throws Exception;//отгрузка товара


}
