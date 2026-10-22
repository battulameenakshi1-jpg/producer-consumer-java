import java.util.LinkedList;

public class ProducerConsumer {
  private static final LinkedList<integer> list = new LinkedList<>();
  private static final int CAPACITY = 2;

public static void main(String[] args) {
  Thread producer = new Thread(() -> {
    int val = 1;
    while (val <= 5) {
      synchronized (List) {
        while (list.size() == CAPACITY) {
          try { list.wait(); } catch (InterruptedException e) {}
        }
        list.add(val);
        System.out.println("Produced: " + val++);
        list.notify();
      }
    }
  });
  Thread consumer = new Thread(() -> {
    int count = 0;
    while (count < 5) {
      synchronized (list) {
        while (list.isEmpty()) {
          try { list.wait(); } catch (InterruptedException e) {}
        }
        int val = list.removeFirst();
        System.out.println("Consumed: " + val);
        count++;
        list.notify();
      }
    }
  });
  producer.start();
  consumer.start();
}
