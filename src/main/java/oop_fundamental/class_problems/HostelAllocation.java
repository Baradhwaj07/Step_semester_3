class HostelRoom {
    private String roomNo;
    private int beds;
    private int occupied;

    HostelRoom(String roomNo, int beds, int occupied) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = occupied;
    }

    void allot(String name) {
        if (occupied < beds) {
            occupied++;
            System.out.println(name + " allotted to room " + roomNo);
        }
    }

    boolean hasAvailableBed() {
        return occupied < beds;
    }
}

public class HostelAllocation {
    static HostelRoom findAvailableRoom(HostelRoom[] rooms) {
        if (rooms == null) {
            return null;
        }
        for (HostelRoom room : rooms) {
            if (room != null && room.hasAvailableBed()) {
                return room;
            }
        }
        return null;
    }

    static void safeAllot(HostelRoom[] rooms, String studentName) {
        HostelRoom room = findAvailableRoom(rooms);
        if (room == null) {
            System.out.println("No rooms available for " + studentName);
            return;
        }
        room.allot(studentName);
    }

    public static void main(String[] args) {
        HostelRoom[] roomsWithSpace = {
            new HostelRoom("C-214", 3, 2),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(roomsWithSpace, "Divya");

        HostelRoom[] fullRooms = {
            new HostelRoom("C-214", 3, 3),
            new HostelRoom("C-507", 2, 2)
        };
        safeAllot(fullRooms, "Divya");
    }

    // An object array stores references, so passing it does not copy the HostelRoom objects.
}
