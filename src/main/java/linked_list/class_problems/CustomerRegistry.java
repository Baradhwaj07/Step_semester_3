import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerRegistry {

    static class Customer {
        int id;
        String name;

        Customer(int id, String name) {
            this.id   = id;
            this.name = name;
        }

        // Two customers are equal if they share the same ID and name
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Customer)) return false;
            Customer other = (Customer) o;
            return id == other.id && name.equals(other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "(" + id + " " + name + ")";
        }
    }

    private Set<Customer> registry = new HashSet<>();

    // Returns true if the customer was added (not a duplicate)
    public boolean add(Customer customer) {
        return registry.add(customer);
    }

    public boolean contains(Customer customer) {
        return registry.contains(customer);
    }

    public int uniqueCount() {
        return registry.size();
    }

    public static void main(String[] args) {
        CustomerRegistry reg = new CustomerRegistry();

        Customer asha = new Customer(101, "Asha");
        Customer ravi = new Customer(102, "Ravi");

        // Add Asha from Branch A
        System.out.println(reg.add(asha));                       // true

        // Add Asha again from Branch B — duplicate rejected
        System.out.println(reg.add(asha));                       // false (duplicate rejected)

        // Add Ravi
        System.out.println(reg.add(ravi));                       // true

        // Unique customer count
        System.out.println("unique count " + reg.uniqueCount()); // unique count 2

        // Check if Asha exists
        System.out.println("contains " + reg.contains(asha));   // contains true
    }
}
