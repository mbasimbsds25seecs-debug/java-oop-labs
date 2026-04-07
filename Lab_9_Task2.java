
import java.util.ArrayList;
import java.util.Scanner;

class Patient {

	private String name;
	private String birthDate;
	private String gender;
	private String admissionDate;
	private String report;
	private String treatingDoctor;
	private int daysOfStay;

	Patient(String name, String birthDate, String gender, String admissionDate, String report, String treatingDoctor, int daysOfStay) {
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.admissionDate = admissionDate;
		this.report = report;
		this.treatingDoctor = treatingDoctor;
		this.daysOfStay = daysOfStay;
	}

	public String getName() { return name; }
	public String getBirthDate() { return birthDate; }
	public String getGender() { return gender; }
	public String getAdmissionDate() { return admissionDate; }
	public String getReport() { return report; }
	public String getTreatingDoctor() { return treatingDoctor; }
	public int getDaysOfStay() { return daysOfStay; }

	public void setReport(String report) { this.report = report; }
	public void setTreatingDoctor(String treatingDoctor) { this.treatingDoctor = treatingDoctor; }

	public void showDetails() {
		System.out.println("  Name: " + name);
		System.out.println("  Birth Date: " + birthDate);
		System.out.println("  Gender: " + gender);
		System.out.println("  Admitted: " + admissionDate);
		System.out.println("  Days of Stay: " + daysOfStay);
		System.out.println("  Treating Doctor: " + treatingDoctor);
		System.out.println("  Report: " + report);
	}
}

class TeamMember {

	private String name;
	private String id;
	private String gender;
	private String joiningDate;
	private int maxWorkingHours = 12;

	TeamMember(String name, String id, String gender, String joiningDate) {
		this.name = name;
		this.id = id;
		this.gender = gender;
		this.joiningDate = joiningDate;
	}

	public String getName() { return name; }
	public String getId() { return id; }
	public String getGender() { return gender; }
	public String getJoiningDate() { return joiningDate; }
	public int getMaxWorkingHours() { return maxWorkingHours; }
}

class Nurse extends TeamMember {

	Nurse(String name, String id, String gender, String joiningDate) {
		super(name, id, gender, joiningDate);
	}
}

class Doctor extends TeamMember {

	private String specialty;

	Doctor(String name, String id, String gender, String joiningDate, String specialty) {
		super(name, id, gender, joiningDate);
		this.specialty = specialty;
	}

	public String getSpecialty() { return specialty; }

	public void treatPatient(Patient p) {
		System.out.println("Doctor " + getName() + " is treating " + p.getName());
	}

	public void checkPatientReport(Patient p) {
		System.out.println("\n--- Patient Report ---");
		p.showDetails();
	}
}

class Intern extends Doctor {

	private SeniorDoctor supervisor;

	Intern(String name, String id, String gender, String joiningDate, String specialty, SeniorDoctor supervisor) {
		super(name, id, gender, joiningDate, specialty);
		this.supervisor = supervisor;
	}

	public SeniorDoctor getSupervisor() { return supervisor; }

	@Override
	public void treatPatient(Patient p) {
		System.out.println("Intern " + getName() + " is observing treatment for " + p.getName());
		System.out.println("  Consulting supervisor: " + supervisor.getName());
	}
}

class SeniorDoctor extends Doctor {

	private ArrayList<Patient> patientList;

	SeniorDoctor(String name, String id, String gender, String joiningDate, String specialty) {
		super(name, id, gender, joiningDate, specialty);
		patientList = new ArrayList<Patient>();
	}

	public void addPatient(Patient p) { patientList.add(p); }
	public ArrayList<Patient> getPatients() { return patientList; }

	@Override
	public void treatPatient(Patient p) {
		System.out.println("Senior Doctor " + getName() + " is carefully examining and treating " + p.getName());
		System.out.println("  Specialty: " + getSpecialty());
	}
}

class Surgeon extends Doctor {

	private ArrayList<Patient> patientList;

	Surgeon(String name, String id, String gender, String joiningDate, String specialty) {
		super(name, id, gender, joiningDate, specialty);
		patientList = new ArrayList<Patient>();
	}

	public void addPatient(Patient p) { patientList.add(p); }
	public ArrayList<Patient> getPatients() { return patientList; }

	@Override
	public void treatPatient(Patient p) {
		System.out.println("Surgeon " + getName() + " is performing surgery on " + p.getName());
		System.out.println("  Specialty: " + getSpecialty());
	}
}

class Department {

	private String name;
	private ArrayList<TeamMember> staff;

	Department(String name) {
		this.name = name;
		staff = new ArrayList<TeamMember>();
	}

	public String getName() { return name; }

	public void addMember(TeamMember m) {
		staff.add(m);
		System.out.println(m.getName() + " added to " + name + " department.");
	}

	public void removeMember(String memberId) {
		for (int i = 0; i < staff.size(); i++) {
			if (staff.get(i).getId().equals(memberId)) {
				System.out.println(staff.get(i).getName() + " removed from " + name + " department.");
				staff.remove(i);
				return;
			}
		}
		System.out.println("No member found with ID: " + memberId);
	}

	public void showStaff() {
		if (staff.isEmpty()) {
			System.out.println("  No staff in this department.");
			return;
		}
		for (TeamMember m : staff) {
			System.out.println("  - " + m.getName() + " (ID: " + m.getId() + ")");
		}
	}

	public ArrayList<TeamMember> getStaff() { return staff; }
}

class Hospital {

	private String name;
	private String address;
	private ArrayList<Patient> patients;
	private ArrayList<Department> departments;

	Hospital(String name, String address) {
		this.name = name;
		this.address = address;
		patients = new ArrayList<Patient>();
		departments = new ArrayList<Department>();
	}

	public String getName() { return name; }
	public String getAddress() { return address; }
	public ArrayList<Patient> getPatients() { return patients; }
	public ArrayList<Department> getDepartments() { return departments; }

	public void addDepartment(Department d) {
		departments.add(d);
		System.out.println("Department '" + d.getName() + "' added.");
	}

	public void addPatient(Patient p) {
		patients.add(p);
		System.out.println("Patient '" + p.getName() + "' admitted.");
	}

	public void removePatient(String patientName) {
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getName().equalsIgnoreCase(patientName)) {
				System.out.println("Patient '" + patients.get(i).getName() + "' removed from hospital.");
				patients.remove(i);
				return;
			}
		}
		System.out.println("No patient found with name: " + patientName);
	}

	public Patient findPatient(String name) {
		for (Patient p : patients) {
			if (p.getName().equalsIgnoreCase(name)) return p;
		}
		return null;
	}

	public void showInfo() {
		System.out.println("\n=============================");
		System.out.println("Hospital : " + name);
		System.out.println("Address  : " + address);
		System.out.println("Patients : " + patients.size());
		System.out.println("Departments: " + departments.size());
		System.out.println("=============================\n");
	}
}

public class Lab_9_Task2 {

	static Scanner sc = new Scanner(System.in);
	static Hospital hospital = null;
	static ArrayList<SeniorDoctor> seniorDoctors = new ArrayList<>();
	static ArrayList<Doctor> allDoctors = new ArrayList<>();

	public static void main(String[] args) {

		System.out.println("=== Hospital Management System ===\n");

		// setup hospital first
		System.out.print("Enter hospital name: ");
		String hName = sc.nextLine();
		System.out.print("Enter hospital address: ");
		String hAddress = sc.nextLine();
		hospital = new Hospital(hName, hAddress);

		int choice = -1;

		while (choice != 0) {

			System.out.println("\n--- Main Menu ---");
			System.out.println("1. Add Department");
			System.out.println("2. Add Staff Member");
			System.out.println("3. Add Patient");
			System.out.println("4. Remove Patient");
			System.out.println("5. Remove Staff Member");
			System.out.println("6. Treat a Patient");
			System.out.println("7. Check Patient Report");
			System.out.println("8. Show Hospital Info");
			System.out.println("9. Show Department Staff");
			System.out.println("0. Exit");
			System.out.print("Enter choice: ");

			choice = Integer.parseInt(sc.nextLine());

			switch (choice) {
				case 1: addDepartment(); break;
				case 2: addStaff(); break;
				case 3: addPatient(); break;
				case 4: removePatient(); break;
				case 5: removeStaff(); break;
				case 6: treatPatient(); break;
				case 7: checkReport(); break;
				case 8: hospital.showInfo(); break;
				case 9: showDeptStaff(); break;
				case 0: System.out.println("Exiting..."); break;
				default: System.out.println("Invalid choice.");
			}
		}
	}

	static void addDepartment() {
		System.out.print("Enter department name: ");
		String name = sc.nextLine();
		hospital.addDepartment(new Department(name));
	}

	static void addStaff() {

		if (hospital.getDepartments().isEmpty()) {
			System.out.println("No departments found. Add a department first.");
			return;
		}

		System.out.print("Enter name: ");
		String name = sc.nextLine();
		System.out.print("Enter ID: ");
		String id = sc.nextLine();
		System.out.print("Enter gender: ");
		String gender = sc.nextLine();
		System.out.print("Enter joining date: ");
		String date = sc.nextLine();

		System.out.println("Select type:");
		System.out.println("1. Nurse");
		System.out.println("2. Senior Doctor");
		System.out.println("3. Intern");
		System.out.println("4. Surgeon");
		System.out.print("Choice: ");
		int type = Integer.parseInt(sc.nextLine());

		TeamMember member = null;

		if (type == 1) {
			member = new Nurse(name, id, gender, date);

		} else if (type == 2) {
			System.out.print("Enter specialty: ");
			String spec = sc.nextLine();
			SeniorDoctor sd = new SeniorDoctor(name, id, gender, date, spec);
			seniorDoctors.add(sd);
			allDoctors.add(sd);
			member = sd;

		} else if (type == 3) {
			if (seniorDoctors.isEmpty()) {
				System.out.println("No senior doctors available. Add a senior doctor first.");
				return;
			}
			System.out.print("Enter specialty: ");
			String spec = sc.nextLine();
			System.out.println("Select supervisor:");
			for (int i = 0; i < seniorDoctors.size(); i++) {
				System.out.println((i + 1) + ". " + seniorDoctors.get(i).getName());
			}
			System.out.print("Choice: ");
			int supChoice = Integer.parseInt(sc.nextLine()) - 1;
			Intern intern = new Intern(name, id, gender, date, spec, seniorDoctors.get(supChoice));
			allDoctors.add(intern);
			member = intern;

		} else if (type == 4) {
			System.out.print("Enter specialty: ");
			String spec = sc.nextLine();
			Surgeon surgeon = new Surgeon(name, id, gender, date, spec);
			allDoctors.add(surgeon);
			member = surgeon;
		}

		if (member == null) {
			System.out.println("Invalid type.");
			return;
		}

		System.out.println("Select department:");
		ArrayList<Department> depts = hospital.getDepartments();
		for (int i = 0; i < depts.size(); i++) {
			System.out.println((i + 1) + ". " + depts.get(i).getName());
		}
		System.out.print("Choice: ");
		int deptChoice = Integer.parseInt(sc.nextLine()) - 1;
		depts.get(deptChoice).addMember(member);
	}

	static void addPatient() {

		System.out.print("Enter patient name: ");
		String name = sc.nextLine();
		System.out.print("Enter birth date: ");
		String birth = sc.nextLine();
		System.out.print("Enter gender: ");
		String gender = sc.nextLine();
		System.out.print("Enter admission date: ");
		String admDate = sc.nextLine();
		System.out.print("Enter diagnosis report: ");
		String report = sc.nextLine();
		System.out.print("Enter days of stay: ");
		int days = Integer.parseInt(sc.nextLine());

		if (allDoctors.isEmpty()) {
			System.out.println("No doctors available yet. Adding patient without doctor.");
			hospital.addPatient(new Patient(name, birth, gender, admDate, report, "Not Assigned", days));
			return;
		}

		System.out.println("Assign a treating doctor:");
		for (int i = 0; i < allDoctors.size(); i++) {
			System.out.println((i + 1) + ". " + allDoctors.get(i).getName());
		}
		System.out.print("Choice: ");
		int docChoice = Integer.parseInt(sc.nextLine()) - 1;
		Doctor assignedDoc = allDoctors.get(docChoice);

		Patient p = new Patient(name, birth, gender, admDate, report, assignedDoc.getName(), days);
		hospital.addPatient(p);

		// assign to doctor's list if senior or surgeon
		if (assignedDoc instanceof SeniorDoctor) {
			((SeniorDoctor) assignedDoc).addPatient(p);
		} else if (assignedDoc instanceof Surgeon) {
			((Surgeon) assignedDoc).addPatient(p);
		}
	}

	static void removePatient() {
		System.out.print("Enter patient name to remove: ");
		String name = sc.nextLine();
		hospital.removePatient(name);
	}

	static void removeStaff() {

		if (hospital.getDepartments().isEmpty()) {
			System.out.println("No departments found.");
			return;
		}

		System.out.println("Select department:");
		ArrayList<Department> depts = hospital.getDepartments();
		for (int i = 0; i < depts.size(); i++) {
			System.out.println((i + 1) + ". " + depts.get(i).getName());
		}
		System.out.print("Choice: ");
		int deptChoice = Integer.parseInt(sc.nextLine()) - 1;

		System.out.print("Enter staff member ID to remove: ");
		String id = sc.nextLine();
		depts.get(deptChoice).removeMember(id);
	}

	static void treatPatient() {

		if (allDoctors.isEmpty()) {
			System.out.println("No doctors available.");
			return;
		}
		if (hospital.getPatients().isEmpty()) {
			System.out.println("No patients available.");
			return;
		}

		System.out.println("Select doctor:");
		for (int i = 0; i < allDoctors.size(); i++) {
			System.out.println((i + 1) + ". " + allDoctors.get(i).getName() + " (" + allDoctors.get(i).getClass().getSimpleName() + ")");
		}
		System.out.print("Choice: ");
		int docChoice = Integer.parseInt(sc.nextLine()) - 1;

		System.out.println("Select patient:");
		ArrayList<Patient> pts = hospital.getPatients();
		for (int i = 0; i < pts.size(); i++) {
			System.out.println((i + 1) + ". " + pts.get(i).getName());
		}
		System.out.print("Choice: ");
		int patChoice = Integer.parseInt(sc.nextLine()) - 1;

		allDoctors.get(docChoice).treatPatient(pts.get(patChoice));
	}

	static void checkReport() {

		if (hospital.getPatients().isEmpty()) {
			System.out.println("No patients available.");
			return;
		}

		System.out.print("Enter patient name: ");
		String name = sc.nextLine();
		Patient p = hospital.findPatient(name);

		if (p == null) {
			System.out.println("Patient not found.");
		} else {
			System.out.println("\n--- Report for " + p.getName() + " ---");
			p.showDetails();
		}
	}

	static void showDeptStaff() {

		if (hospital.getDepartments().isEmpty()) {
			System.out.println("No departments found.");
			return;
		}

		System.out.println("Select department:");
		ArrayList<Department> depts = hospital.getDepartments();
		for (int i = 0; i < depts.size(); i++) {
			System.out.println((i + 1) + ". " + depts.get(i).getName());
		}
		System.out.print("Choice: ");
		int deptChoice = Integer.parseInt(sc.nextLine()) - 1;
		System.out.println("\nStaff in " + depts.get(deptChoice).getName() + ":");
		depts.get(deptChoice).showStaff();
	}
}