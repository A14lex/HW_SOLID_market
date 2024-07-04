public class ExceptionOrder extends Exception {
    public ExceptionOrder() {
        super("Склад не может выдать товар в таком кол-ве или его нет на складе");
    }
}
