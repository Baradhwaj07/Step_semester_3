import java.util.ArrayDeque;
import java.util.Deque;

// Problem 3: Round Robin CPU Scheduler
// Simulate the Round Robin (RR) scheduling algorithm.
// Each process is given a fixed time quantum to execute. If a process cannot
// finish within its quantum, it is re-enqueued at the back of the ready queue
// with its remaining burst time reduced. When a process finishes, record its
// completion time, then compute turnaround time and waiting time.
//
// Turnaround Time = Completion Time − Arrival Time
// Waiting Time    = Turnaround Time − Burst Time
//
// Approach: Keep a current-time counter.
//   - Dequeue the front process.
//   - Run it for min(quantum, remainingTime).
//   - Advance the clock by that amount.
//   - If remaining > 0, re-enqueue; else record completion time.
//
// Sample (quantum = 3):
//   Process | BurstTime
//     P1    |    5
//     P2    |    3
//     P3    |    8
//   P1 runs 3 → remaining=2; P2 runs 3 → done at t=6; P3 runs 3 → remaining=5;
//   P1 runs 2 → done at t=11; P3 runs 3 → remaining=2; P3 runs 2 → done at t=16

public class RoundRobinScheduler {

    static class Process {
        String name;
        int arrivalTime;
        int burstTime;
        int remainingTime;
        int completionTime;
        int turnaroundTime;
        int waitingTime;

        Process(String name, int arrivalTime, int burstTime) {
            this.name = name;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.remainingTime = burstTime;
        }
    }

    // Run Round Robin scheduling and populate completion/turnaround/waiting times
    public static void runRoundRobin(Process[] processes, int quantum) {
        Deque<Process> readyQueue = new ArrayDeque<>();
        int currentTime = 0;
        int completed = 0;
        int n = processes.length;
        boolean[] enqueued = new boolean[n];

        // Enqueue all processes that have arrived at time 0
        for (int i = 0; i < n; i++) {
            if (processes[i].arrivalTime <= currentTime) {
                readyQueue.addLast(processes[i]);
                enqueued[i] = true;
            }
        }

        System.out.println("--- Execution Timeline ---");

        while (completed < n) {
            if (readyQueue.isEmpty()) {
                // CPU idle — advance to next arrival
                currentTime++;
                for (int i = 0; i < n; i++) {
                    if (!enqueued[i] && processes[i].arrivalTime <= currentTime) {
                        readyQueue.addLast(processes[i]);
                        enqueued[i] = true;
                    }
                }
                continue;
            }

            Process current = readyQueue.pollFirst();
            int runTime = Math.min(quantum, current.remainingTime);

            System.out.printf("  t=%-3d  %s runs for %d units", currentTime, current.name, runTime);
            currentTime += runTime;
            current.remainingTime -= runTime;

            // Enqueue any new arrivals that came in during this slice
            for (int i = 0; i < n; i++) {
                if (!enqueued[i] && processes[i].arrivalTime <= currentTime) {
                    readyQueue.addLast(processes[i]);
                    enqueued[i] = true;
                }
            }

            if (current.remainingTime == 0) {
                current.completionTime = currentTime;
                current.turnaroundTime = current.completionTime - current.arrivalTime;
                current.waitingTime = current.turnaroundTime - current.burstTime;
                completed++;
                System.out.printf("  → DONE at t=%d%n", currentTime);
            } else {
                readyQueue.addLast(current); // re-enqueue with remaining time
                System.out.printf("  → remaining=%d, re-enqueued%n", current.remainingTime);
            }
        }
    }

    // Print results table
    public static void printResults(Process[] processes) {
        System.out.println("\n--- Scheduling Results (quantum = ?) ---");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-12s%n",
                "Process", "Arrival", "Burst", "Completion", "Turnaround", "Waiting");
        System.out.println("-".repeat(72));

        double totalTurnaround = 0;
        double totalWaiting = 0;

        for (Process p : processes) {
            System.out.printf("%-10s %-10d %-10d %-15d %-15d %-12d%n",
                    p.name, p.arrivalTime, p.burstTime,
                    p.completionTime, p.turnaroundTime, p.waitingTime);
            totalTurnaround += p.turnaroundTime;
            totalWaiting += p.waitingTime;
        }

        System.out.println("-".repeat(72));
        System.out.printf("Average Turnaround Time : %.2f%n", totalTurnaround / processes.length);
        System.out.printf("Average Waiting Time    : %.2f%n", totalWaiting / processes.length);
    }

    public static void main(String[] args) {
        // Sample 1: quantum = 3, all arrive at t=0
        System.out.println("===== Sample 1: quantum=3 =====");
        Process[] procs1 = {
            new Process("P1", 0, 5),
            new Process("P2", 0, 3),
            new Process("P3", 0, 8)
        };
        runRoundRobin(procs1, 3);
        printResults(procs1);

        System.out.println();

        // Sample 2: quantum = 2, processes with different arrivals
        System.out.println("===== Sample 2: quantum=2 =====");
        Process[] procs2 = {
            new Process("P1", 0, 4),
            new Process("P2", 1, 3),
            new Process("P3", 2, 5),
            new Process("P4", 3, 2)
        };
        runRoundRobin(procs2, 2);
        printResults(procs2);
    }
}
