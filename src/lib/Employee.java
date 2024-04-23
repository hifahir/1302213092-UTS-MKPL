package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate joinedDate;
    private boolean isForeigner;

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinedDate, boolean isForeigner) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinedDate = joinedDate;
        this.isForeigner = isForeigner;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	 public enum Grade {
        GRADE_1,
        GRADE_2,
        GRADE_3
    }
	 
	public void setMonthlySalary(Grade grade) {
		// Array untuk menyimpan nilai gaji berdasarkan grade
		int[] salaries = {3000000, 5000000, 7000000};
		
		// Mendapatkan indeks array berdasarkan grade
		int index = grade.ordinal();
		
		// Mengatur gaji bulanan sesuai dengan grade
		monthlySalary = isForeigner ? (int) (salaries[index] * 1.5) : salaries[index];
	}
	
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
        int monthsWorked = Math.min(12, LocalDate.now().getMonthValue() - joinedDate.getMonthValue() + 1);
        return TaxFunction.calculateAnnualIncomeTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
