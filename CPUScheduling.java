import java.util.*;
public class CPUScheduling {
        public static void FCFS(int n, int[] bt) {
        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] ft = new int[n];

        wt[0] = 0;                 // First process waiting time
        ft[0] = bt[0];             // First completes at its burst time
        tat[0] = ft[0];

        for (int i = 1; i < n; i++) {
            wt[i] = ft[i - 1];
            ft[i] = wt[i] + bt[i];
            tat[i] = ft[i];
        }

        System.out.println("\n--- First Come First Serve (FCFS) ---");
        System.out.println("P\tBT\tWT\tTAT\tFT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t" + bt[i] + "\t" + wt[i] + "\t" + tat[i] + "\t" + ft[i]);
        }
    }

    // ---------------- SJF Non-Preemptive -----------------
    public static void SJF(int n, int[] bt) {
        int[] p = new int[n];      // process ids
        int[] bt2 = new int[n];    // copy of burst times
        
        for (int i = 0; i < n; i++) {
            p[i] = i + 1;
            bt2[i] = bt[i];
        }

        // Sort based on burst time (Selection Sort)
        for (int i = 0; i < n; i++) {
            int pos = i;
            for (int j = i + 1; j < n; j++) {
                if (bt2[j] < bt2[pos]) {
                    pos = j;
                }
            }

            // Swap burst times
            int temp = bt2[i];
            bt2[i] = bt2[pos];
            bt2[pos] = temp;

            // Swap process ids
            temp = p[i];
            p[i] = p[pos];
            p[pos] = temp;
        }

        int[] wt = new int[n];
        int[] tat = new int[n];
        int[] ft = new int[n];

        wt[0] = 0;
        ft[0] = bt2[0];
        tat[0] = ft[0];

        for (int i = 1; i < n; i++) {
            wt[i] = ft[i - 1];
            ft[i] = wt[i] + bt2[i];
            tat[i] = ft[i];
        }

        System.out.println("\n--- Shortest Job First (SJF Non-Preemptive) ---");
        System.out.println("P\tBT\tWT\tTAT\tFT");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + p[i] + "\t" + bt2[i] + "\t" + wt[i] + "\t" + tat[i] + "\t" + ft[i]);}
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes : ");
        int n = sc.nextInt();

        int[] bt = new int[n];
        System.out.println("Enter brust time: ");
        for(int i = 0;i<n;i++){
            System.out.print("P" + (i + 1) + ": ");
            bt[i] = sc.nextInt();
        }

        FCFS(n, bt);
        SJF(n, bt);

        sc.close();
    }
}
