import java.util.HashMap;

public class LRUCache {

    // Doubly linked list node
    static class Node {
        int key;
        String value;
        Node prev, next;
        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Node> map;
    private final Node head; // dummy head (most recently used side)
    private final Node tail; // dummy tail (least recently used side)

    LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, "");
        tail = new Node(-1, "");
        head.next = tail;
        tail.prev = head;
    }

    // Insert node right after dummy head (most recently used position)
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    // Remove a node from its current position
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Move an existing node to the front
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }

    // Get value by key; returns null if not present. Marks as recently used.
    public String get(int key) {
        if (!map.containsKey(key)) return null;
        Node node = map.get(key);
        moveToFront(node);
        return node.value;
    }

    // Put key-value pair. If capacity exceeded, evict LRU (node just before tail).
    public void put(int key, String value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToFront(node);
        } else {
            if (map.size() == capacity) {
                // Evict LRU: node just before dummy tail
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
                System.out.println("Cache evicts: " + lru.key + " - " + lru.value);
            }
            Node newNode = new Node(key, value);
            addToFront(newNode);
            map.put(key, newNode);
        }
    }

    public static void main(String[] args) {
        // Capacity = 2
        LRUCache cache = new LRUCache(2);

        cache.put(1, "Numa");
        cache.put(2, "Dona");

        String v1 = cache.get(1);
        System.out.println("get(1) -> " + (v1 != null ? v1 : "null"));  // Numa; makes 1 MRU, 2 LRU

        cache.put(3, "Qhez");  // evicts key 2 (LRU)

        String v2 = cache.get(2);
        System.out.println("get(2) -> " + (v2 != null ? v2 : "null"));  // null (evicted)

        cache.put(7, "Meec");  // evicts key 1 (LRU now, since get(2) returned null, 3 was just added, 1 is LRU)

        String v3 = cache.get(3);
        System.out.println("get(3) -> " + (v3 != null ? v3 : "null"));  // Qhez

        String v4 = cache.get(1);
        System.out.println("get(1) -> " + (v4 != null ? v4 : "null"));  // null (evicted)
    }
}
