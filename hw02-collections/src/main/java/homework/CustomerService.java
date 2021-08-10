package homework;

import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private final NavigableMap<Customer, String> collection = new TreeMap<Customer, String>(new CustomerComparator());

    public Map.Entry<Customer, String> getSmallest() {
        return getSimpleEntry(collection.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return getSimpleEntry(collection.higherEntry(customer));
    }

    public Map.Entry<Customer, String> getSimpleEntry(Map.Entry<Customer, String> item) {
        if (item == null) {
            return null;
        }
        return new AbstractMap.SimpleEntry<Customer, String>(new Customer(item.getKey()), item.getValue());
    }

    public void add(Customer customer, String data) {
        collection.put(customer, data);
    }
}
