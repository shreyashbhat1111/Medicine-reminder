import java.util.*;
import java.text.SimpleDateFormat;

class Medicine {
    private String name;
    private Date time;

    public Medicine(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public Date getTime() {
        return time;
    }
}

public class MedicineReminder {
    private static List<Medicine> medicines = new ArrayList<>();
    private static Timer timer = new Timer();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        System.out.println("=== Medicine Reminder App ===");
        System.out.print("How many medicines do you want to add? ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.print("Enter medicine name: ");
            String name = sc.nextLine();

            System.out.print("Enter reminder time (HH:mm): ");
            String timeStr = sc.nextLine();
            Date time = sdf.parse(timeStr);

            Medicine med = new Medicine(name, time);
            medicines.add(med);

            scheduleReminder(med);
        }

        System.out.println("Reminders scheduled. Keep the program running...");
    }

    private static void scheduleReminder(Medicine med) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(" Reminder: Take your medicine - " + med.getName() +
                                   " at " + new SimpleDateFormat("HH:mm").format(med.getTime()));
            }
        }, med.getTime());
    }
}
