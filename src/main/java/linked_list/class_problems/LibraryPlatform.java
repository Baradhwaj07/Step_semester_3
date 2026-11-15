import java.util.ArrayList;
import java.util.List;

public class LibraryPlatform {

    // Resource interface: common contract for all library items
    interface Resource {
        String getId();
        boolean supportsReserve();
        boolean supportsDownload();
    }

    // Book: can be reserved but NOT downloaded
    static class Book implements Resource {
        private String id;
        private String reservedBy;

        Book(String id) {
            this.id = id;
        }

        @Override public String getId()               { return id; }
        @Override public boolean supportsReserve()    { return true; }
        @Override public boolean supportsDownload()   { return false; }

        public boolean isReserved()                   { return reservedBy != null; }
        public void reserveFor(String memberId)       { this.reservedBy = memberId; }
        public String getReservedBy()                 { return reservedBy; }
    }

    // EBook: can be downloaded but NOT reserved
    static class EBook implements Resource {
        private String id;

        EBook(String id) {
            this.id = id;
        }

        @Override public String getId()               { return id; }
        @Override public boolean supportsReserve()    { return false; }
        @Override public boolean supportsDownload()   { return true; }
    }

    // Ordered list of resources (supports index-based FIND)
    private List<Resource> resources = new ArrayList<>();

    // Linear search by ID — returns index or -1
    private int findIndex(String id) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).getId().equals(id)) return i;
        }
        return -1;
    }

    public void processCommand(String command) {
        String[] parts = command.trim().split("\\s+");
        String cmd = parts[0];

        switch (cmd) {

            case "ADD": {
                // ADD <type> <id>
                String type = parts[1];
                String id   = parts[2];
                if (findIndex(id) != -1) {
                    System.out.println("duplicate rejected");
                } else {
                    Resource r = type.equals("Book") ? new Book(id) : new EBook(id);
                    resources.add(r);
                    System.out.println(id + " added");
                }
                break;
            }

            case "RESERVE": {
                // RESERVE <id> by <memberId>
                String id       = parts[1];
                String memberId = parts[3];
                int idx = findIndex(id);
                if (idx == -1) {
                    System.out.println(id + " not found");
                    break;
                }
                Resource r = resources.get(idx);
                if (!r.supportsReserve()) {
                    System.out.println(id + " rejected reserve unsupported");
                } else {
                    ((Book) r).reserveFor(memberId);
                    System.out.println(id + " reserved for " + memberId);
                }
                break;
            }

            case "DOWNLOAD": {
                // DOWNLOAD <id>
                String id  = parts[1];
                int    idx = findIndex(id);
                if (idx == -1) {
                    System.out.println(id + " not found");
                    break;
                }
                Resource r = resources.get(idx);
                if (!r.supportsDownload()) {
                    System.out.println(id + " rejected download unsupported");
                } else {
                    System.out.println(id + " downloaded");
                }
                break;
            }

            case "FIND": {
                // FIND <id>
                String id  = parts[1];
                int    idx = findIndex(id);
                if (idx == -1) {
                    System.out.println(id + " not found");
                } else {
                    System.out.println(id + " found at index " + idx);
                }
                break;
            }

            default:
                System.out.println("Unknown command: " + cmd);
        }
    }

    public static void main(String[] args) {
        LibraryPlatform platform = new LibraryPlatform();

        platform.processCommand("ADD Book B1");
        platform.processCommand("ADD Book B1");
        platform.processCommand("ADD EBook E1");
        platform.processCommand("RESERVE B1 by M1");
        platform.processCommand("DOWNLOAD B1");
        platform.processCommand("DOWNLOAD E1");
        platform.processCommand("FIND E1");
    }
}
