package lib;

public class TaxFunction {

	private static final int ANNUAL_NON_TAXABLE_INCOME = 54000000;
    private static final int ADDITIONAL_NON_TAXABLE_INCOME_PER_CHILD = 1500000;
    private static final int ADDITIONAL_NON_TAXABLE_INCOME_FOR_MARRIED = 4500000;
    private static final double TAX_RATE = 0.05;
	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	 public static int calculateAnnualIncomeTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthsWorked > 12) {
            throw new IllegalArgumentException("Number of months worked per year cannot exceed 12");
        }

        int nonTaxableIncome = ANNUAL_NON_TAXABLE_INCOME;
        if (isMarried) {
            nonTaxableIncome += ADDITIONAL_NON_TAXABLE_INCOME_FOR_MARRIED;
        }
        nonTaxableIncome += Math.min(numberOfChildren, 3) * ADDITIONAL_NON_TAXABLE_INCOME_PER_CHILD;

        int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked - deductible - nonTaxableIncome;
        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(tax, 0);
    }
	
}
