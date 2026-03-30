import java.util.Scanner;

class Patient {
    protected String name;
    protected int age;
    protected String condition;

    public Patient(String name, int age, String condition) {
        this.name = name;
        this.age = age;
        this.condition = condition;
    }

    public void evaluateHealth() {
        System.out.println("Patient: " + name + "  Condition: " + condition);
    }

    public boolean isCritical() {
        return condition.equalsIgnoreCase("critical");
    }

    public String getName() { return name; }
    public String getCondition() { return condition; }
}

class CriticalPatient extends Patient {
    private int heartRate;

    public CriticalPatient(String name, int age, int heartRate) {
        super(name, age, "Critical");
        this.heartRate = heartRate;
    }

    @Override
    public void evaluateHealth() {
        System.out.println("[CRITICAL]  " + name + "  Heart Rate: " + heartRate + " bpm  Needs IMMEDIATE attention!");
    }
}

class StablePatient extends Patient {
    private String monitoringFrequency;

    public StablePatient(String name, int age, String monitoringFrequency) {
        super(name, age, "Stable");
        this.monitoringFrequency = monitoringFrequency;
    }

    @Override
    public void evaluateHealth() {
        System.out.println("[STABLE]    " + name + "  Monitoring: every " + monitoringFrequency);
    }
}

class ModeratePatient extends Patient {
    private String vitalSigns;

    public ModeratePatient(String name, int age, String vitalSigns) {
        super(name, age, "Moderate");
        this.vitalSigns = vitalSigns;
    }

    @Override
    public void evaluateHealth() {
        System.out.println("[MODERATE]  " + name + "  Vitals: " + vitalSigns + "  Under observation.");
    }
}

public class lab8 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("========== TASK 2: HEALTHCARE MONITORING ==========\n");

        System.out.print("How many patients do you want to enter? ");
        int n = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        Patient[] patients = new Patient[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Patient " + (i + 1) + " ---");
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter type (1 = Critical, 2 = Stable, 3 = Moderate): ");
            int type = sc.nextInt();
            sc.nextLine();

            if (type == 1) {
                System.out.print("Enter heart rate (bpm): ");
                int heartRate = sc.nextInt();
                sc.nextLine();
                patients[i] = new CriticalPatient(name, age, heartRate);

            } else if (type == 2) {
                System.out.print("Enter monitoring frequency (e.g. 6 hours): ");
                String freq = sc.nextLine();
                patients[i] = new StablePatient(name, age, freq);

            } else {
                System.out.print("Enter vital signs (e.g. BP 140/90): ");
                String vitals = sc.nextLine();
                patients[i] = new ModeratePatient(name, age, vitals);
            }
        }

        // Process all patients
        System.out.println("\n--- Health Status Summary ---");
        int criticalCount = 0;
        for (Patient p : patients) {
            p.evaluateHealth();
            if (p.isCritical()) criticalCount++;
        }

        System.out.println("\nTotal Critical Cases: " + criticalCount);

        // Single patient evaluation
        System.out.println("\n--- Single Patient Evaluation ---");
        System.out.println("\nEnter details for one more patient:");
        System.out.print("Enter name: ");
        String sName = sc.nextLine();

        System.out.print("Enter age: ");
        int sAge = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter vital signs: ");
        String sVitals = sc.nextLine();

        Patient single = new ModeratePatient(sName, sAge, sVitals);
        single.evaluateHealth();

        sc.close();
    }
}