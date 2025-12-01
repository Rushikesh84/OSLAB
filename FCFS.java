import java.util.Scanner;

public class FCFS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of processes: ");
		int n;
		try {
			n = Integer.parseInt(sc.nextLine().trim());
		} catch (Exception e) {
			System.out.println("Invalid number. Exiting.");
			sc.close();
			return;
		}

		if (n <= 0) {
			System.out.println("Number of processes must be positive. Exiting.");
			sc.close();
			return;
		}

		int[] burst = new int[n];

		for (int i = 0; i < n; i++) {
			while (true) {
				System.out.print("Enter burst time for process P" + (i + 1) + ": ");
				String line = sc.nextLine().trim();
				try {
					int b = Integer.parseInt(line);
					if (b < 0) {
						System.out.println("Burst time cannot be negative. Try again.");
						continue;
					}
					burst[i] = b;
					break;
				} catch (NumberFormatException ex) {
					System.out.println("Invalid integer. Please enter again.");
				}
			}
		}

		sc.close();

		int[] waiting = new int[n];
		int[] turnaround = new int[n];

		waiting[0] = 0;
		for (int i = 1; i < n; i++) {
			waiting[i] = waiting[i - 1] + burst[i - 1];
		}

		int totalWaiting = 0;
		int totalTurnaround = 0;

		for (int i = 0; i < n; i++) {
			turnaround[i] = waiting[i] + burst[i];
			totalWaiting += waiting[i];
			totalTurnaround += turnaround[i];
		}

		double avgWaiting = (double) totalWaiting / n;
		double avgTurnaround = (double) totalTurnaround / n;

		System.out.println();
		System.out.printf("%6s %12s %14s %16s%n", "Process", "Burst Time", "Waiting Time", "Turnaround");
		for (int i = 0; i < n; i++) {
			System.out.printf("%6s %12d %14d %16d%n", "P" + (i + 1), burst[i], waiting[i], turnaround[i]);
		}

		System.out.println();
		System.out.printf("Average waiting time    : %.2f%n", avgWaiting);
		System.out.printf("Average turnaround time : %.2f%n", avgTurnaround);
	}
}