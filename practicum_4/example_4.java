package practicum_4;

import java.util.Objects;

    // 1. БАЗОВЫЙ КЛАСС (РОДИТЕЛЬ)
    abstract class Payment {
        protected double amount;
        protected String date;
        protected String purpose;

        public Payment(double amount, String date, String purpose) {
            this.amount = amount;
            this.date = date;
            this.purpose = purpose;
        }

        public abstract double calculateCommission();

        public String getDescription() {
            return "Платеж на сумму " + amount + " руб. Дата: " + date + ". Назначение: " + purpose;
        }

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
        private String cashierName;

        public CashPayment(double amount, String date, String purpose, String cashierName) {
            super(amount, date, purpose);
            this.cashierName = cashierName;
        }

        @Override
        public double calculateCommission() {
            return 0.0;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ". Оплата наличными. Принял кассир: " + cashierName;
        }

        @Override
        public boolean equals(Object o) {
            if (!super.equals(o)) return false;
            CashPayment that = (CashPayment) o;
            return Objects.equals(cashierName, that.cashierName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), cashierName);
        }
    }

    // 3. НАСЛЕДНИК 2: КАРТА
    class CardPayment extends Payment {
        private String cardNumber;

        public CardPayment(double amount, String date, String purpose, String cardNumber) {
            super(amount, date, purpose);
            this.cardNumber = cardNumber;
        }

        @Override
        public double calculateCommission() {
            return amount * 0.02;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ". Оплата картой: " + cardNumber;
        }

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

    // 4. НАСЛЕДНИК 3: БАНКОВСКИЙ ПЕРЕВОД
    class BankTransferPayment extends Payment {
        private String accountNumber;

        public BankTransferPayment(double amount, String date, String purpose, String accountNumber) {
            super(amount, date, purpose);
            this.accountNumber = accountNumber;
        }

        @Override
        public double calculateCommission() {
            return (amount * 0.01) + 50.0;
        }

        @Override
        public String getDescription() {
            return super.getDescription() + ". Банковский перевод на счет: " + accountNumber;
        }

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

    // 5. ГЛАВНЫЙ КЛАСС ДЛЯ ЗАПУСКА
    public class example_2 {
        public static void main(String[] args) {

            // Пункт 1 и 2: Создаем массив родительского типа и размещаем
            // минимум по два объекта каждого производного класса
            Payment[] payments = new Payment[]{
                    new CashPayment(1000, "01.11.2026", "Покупка хлеба", "Иванова"),
                    new CashPayment(500, "01.11.2026", "Оплата такси", "Петров"),

                    new CardPayment(5000, "02.11.2026", "Подписка на сервис", "**** **** **** 1234"),
                    new CardPayment(1500, "02.11.2026", "Покупка книги", "**** **** **** 5678"),

                    new BankTransferPayment(20000, "03.11.2026", "Оплата аренды", "408178100..."),
                    new BankTransferPayment(35000, "03.11.2026", "Зарплата сотруднику", "408178100...")
            };

            System.out.println("=== ДЕМОНСТРАЦИЯ ПОЛИМОРФИЗМА ===");

            // Переменные для подсчета общего результата (Пункт 6)
            double totalAmount = 0;      // Общая сумма всех платежей
            double totalCommission = 0;  // Общая сумма всех комиссий

            // Пункт 3: Обрабатываем весь массив одним циклом
            for (Payment p : payments) {
                // Пункт 4 и 5: Вызываем переопределенный метод (calculateCommission)
                // и выводим объект через toString()

                System.out.println("--------------------");
                System.out.println(p.toString()); // Явный вызов toString()

                // Пункт 6: Считаем общий результат
                double commission = p.calculateCommission(); // Полиморфный вызов
                totalCommission += commission;
                totalAmount += p.amount;
            }

            System.out.println("\n=== ОБЩИЙ РЕЗУЛЬТАТ ===");
            // Пункт 6: Способ расчета указан в описании программы (выводе)
            System.out.println("Способ расчета: Суммирование полей amount и calculateCommission() для всех объектов в массиве.");
            System.out.printf("Общая сумма всех платежей: %.2f руб.\n", totalAmount);
            System.out.printf("Общая сумма всех комиссий: %.2f руб.\n", totalCommission);
            System.out.printf("ИТОГО к списанию со счета: %.2f руб.\n", (totalAmount + totalCommission));

            // Демонстрация equals (Пункт 4 и 5 из предыдущего задания)
            System.out.println("\n=== ПРОВЕРКА EQUALS ===");
            CardPayment card2 = new CardPayment(5000, "02.11.2026", "Подписка на сервис", "**** **** **** 1234");
            System.out.println("card1 равен card2? " + payments[2].equals(card2)); // true
            System.out.println("cash1 равен card1? " + payments[0].equals(payments[2]));   // false
        }
    }
}
