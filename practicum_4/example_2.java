package practicum_4;


import java.util.Objects;


// 1. БАЗОВЫЙ КЛАСС (РОДИТЕЛЬ)

// Делаем его abstract (абстрактным).
abstract class Payment {
    // Пункт 1: Сумма, дата, назначение
    protected double amount;
    protected String date;
    protected String purpose;

    public Payment(double amount, String date, String purpose) {
        this.amount = amount;
        this.date = date;
        this.purpose = purpose;
    }

    // Пункт 3: Реализуем методы.
    // calculateCommission(у всех разная комиссия) делаем абстрактным, чтобы каждый наследник ОБЯЗАТЕЛЬНО его реализовал
    public abstract double calculateCommission();

    // Описание платежа
    public String getDescription() {
        return "Платеж на сумму " + amount + " руб. Дата: " + date + ". Назначение: " + purpose;
    }

    // Пункт 4: toString, equals, hashCode в базовом классе
    @Override
    public String toString() {
        return getDescription() + " | Комиссия: " + calculateCommission() + " руб.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(payment.amount, amount) == 0 &&
                Objects.equals(date, payment.date) &&
                Objects.equals(purpose, payment.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date, purpose);
    }
}


// 2. НАСЛЕДНИК 1: НАЛИЧНЫЕ

class CashPayment extends Payment {
    // Пункт 2: Собственный параметр (кто принял наличку)
    private String cashierName;

    public CashPayment(double amount, String date, String purpose, String cashierName) {
        super(amount, date, purpose); // Вызываем конструктор родителя
        this.cashierName = cashierName;
    }

    // Пункт 3: Своя комиссия (0%)
    @Override
    public double calculateCommission() {
        return 0.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ". Оплата наличными. Принял кассир: " + cashierName;
    }

    // Пункт 5: Переопределяем equals, так как добавилось новое поле cashierName
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false; // Сначала проверяем базовые поля
        CashPayment that = (CashPayment) o;
        return Objects.equals(cashierName, that.cashierName); // Потом свое
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashierName);
    }
}

//
// 3. НАСЛЕДНИК 2: КАРТА
//
class CardPayment extends Payment {
    // Пункт 2: Собственный параметр (маскированный номер карты)
    private String cardNumber;

    public CardPayment(double amount, String date, String purpose, String cardNumber) {
        super(amount, date, purpose);
        this.cardNumber = cardNumber;
    }

    // Пункт 3: Своя комиссия (2%)
    @Override
    public double calculateCommission() {
        return amount * 0.02;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ". Оплата картой: " + cardNumber;
    }

    // Пункт 5: Переопределяем equals
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        CardPayment that = (CardPayment) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardNumber);
    }
}

//
// 4. НАСЛЕДНИК 3: БАНКОВСКИЙ ПЕРЕВОД
//
class BankTransferPayment extends Payment {
    // Пункт 2: Собственный параметр (номер счета)
    private String accountNumber;

    public BankTransferPayment(double amount, String date, String purpose, String accountNumber) {
        super(amount, date, purpose);
        this.accountNumber = accountNumber;
    }

    // Пункт 3: Своя комиссия (1% + 50 руб)
    @Override
    public double calculateCommission() {
        return (amount * 0.01) + 50.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ". Банковский перевод на счет: " + accountNumber;
    }

    // Пункт 5: Переопределяем equals
    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        BankTransferPayment that = (BankTransferPayment) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), accountNumber);
    }
}


// 5. ГЛАВНЫЙ КЛАСС ДЛЯ ЗАПУСКА (Демонстрация)

public class example_2 {
    public static void main(String[] args) {
        // Пункт 6: Создаем экземпляры всех производных классов
        CashPayment cash = new CashPayment(1000, "01.11.2026", "Покупка хлеба", "Иванова");
        CardPayment card = new CardPayment(5000, "02.11.2026", "Подписка на сервис", "**** **** **** 1234");
        BankTransferPayment bank = new BankTransferPayment(20000, "03.11.2026", "Оплата аренды", "408178100...");

        // Пункт 6: ПОЛИМОРФНЫЙ ВЫЗОВ
        // Мы создаем массив типа родителя (Payment), но кладем туда детей.
        Payment[] allPayments = {cash, card, bank};

        System.out.println("=== ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА ===");
        for (Payment p : allPayments) {
            // Программа сама понимает, кто из детей сейчас в переменной "p",
            // и вызывает НУЖНЫЙ метод calculateCommission и getDescription
            System.out.println("--------------------");
            System.out.println(p.getDescription());
            System.out.println("Комиссия: " + p.calculateCommission() + " руб.");
            System.out.println("Итог к списанию: " + (p.amount + p.calculateCommission()) + " руб.");
        }

        // Демонстрация equals (Пункт 4 и 5)
        System.out.println("\n=== ПРОВЕРКА EQUALS ===");
        CardPayment card2 = new CardPayment(5000, "02.11.2026", "Подписка на сервис", "**** **** **** 1234");
        System.out.println("card равен card2? " + card.equals(card2)); // true
        System.out.println("cash равен card? " + cash.equals(card));   // false
    }
}


