package homework;


import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    private final Deque<Customer> customerList = new ArrayDeque<Customer>();

    public void add(Customer customer) {
        customerList.addLast(customer);
    }

    public Customer take() {
        return customerList.pollLast();
    }
}
