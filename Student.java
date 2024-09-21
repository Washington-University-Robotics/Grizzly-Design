package assignment7;

public class Student {
	//instance variables (encapsulation: keep internal state consistent)
	private String firstName;
	private String lastName;
	private int studentId;
	private int totalAttemptedCredits;
	private int totalPassingCredits;
	private double totalQualityPoints;
	private double bearBucksBalance;
	
	 /**
	  * Creates a new student based on the following parameters:
	  *
	  * @param firstName      first name of student
	  * @param lastName    	  last name of student
	  * @param studentId      ID of student
	  * @return               student
	  */
	public Student(String firstName, String lastName, int studentId) {
		//differentiate between the instance variables and method parameters (because they have the same name)
		this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
	}
	/**
     * Gets full name of student
     *
     * @return full name of student
     */
	public String getFullName() {
		return firstName + " " + lastName;
	}
	/**
     * Gets ID of student
     *
     * @return ID of student
     */
	public int getId() {
		return studentId;
	}
	 /**
     * Gets total attempted credits of student
     *
     * @return total attempted credits of student
     */
	public int getTotalAttemptedCredits() {
		return totalAttemptedCredits;
	}
	/**
     * Gets total passing credits of student
     *
     * @return total passing credits of student
     */
	public int getTotalPassingCredits() {
		return totalPassingCredits;
	}
	 /**
     * Calculates GPA of student
     *
     * @return GPA of student
     */
	public double calculateGradePointAverage() {
		if(totalAttemptedCredits == 0) {
			return Double.NaN;
		}
		return totalQualityPoints / totalAttemptedCredits;
	}
	 /**
     * Submits grade for student based on the following parameters:
     *
     * @param grade   		grade received
     * @param credits 		number of credits for course
     */
	public void submitGrade(double grade, int credits) {
		totalAttemptedCredits += credits;
		totalQualityPoints += grade * credits;
		if(grade >= 1.7) {
			totalPassingCredits += credits;
		}
	}
	 /**
     * Determines class standing of student
     *
     * @return class standing of student (first year, sophomore, junior, or senior)
     */
	public String getClassStanding() {
		if(totalPassingCredits < 30) {
			return "First Year";
		}
		else if(totalPassingCredits >= 30 && totalPassingCredits < 60) {
			return "Sophomore";
		}
		else if(totalPassingCredits >= 60 && totalPassingCredits < 90) {
			return "Junior";
		}
		else {
			return "Senior";
		}
	}
	 /**
     * Determines if student is eligible for PhiBetaKappa
     *
     * @return if student is eligible
     */
	public boolean isEligibleForPhiBetaKappa() {
		double gpa = calculateGradePointAverage();
		return (totalPassingCredits >= 98 && gpa >= 3.60) || (totalPassingCredits >= 75 && gpa >= 3.80);
	}
	 /**
     * Determines number of bear bucks deposited
     *
     * @return bear bucks balance based on deposit
     */
	public void depositBearBucks(double amount) {
        bearBucksBalance += amount;
    }
	 /**
     * Determines number of bear bucks deducted
     *
     * @return bear bucks balance based on deductions
     */
	 public void deductBearBucks(double amount) {
	        bearBucksBalance -= amount;
	    }
	 /**
	     * Determines bear bucks balance
	     *
	     * @return bear bucks balance
	     */
	 public double getBearBucksBalance() {
	        return bearBucksBalance;
	    }
	 /**
	     * Determines remaining bear bucks balance with administration fee
	     *
	     * @return remaining bear bucks balance
	     */
	 public double cashOutBearBucks() {
	        double remainingBalance = bearBucksBalance - 10.0;
	        if (remainingBalance <= 0) {
	            bearBucksBalance = 0;
	            return 0;
	        } else {
	            bearBucksBalance = 0;
	            return remainingBalance;
	        }
	    }
	 /**
	  * Creates a new legacy student based on the following parameters:
	  *
	  * @param firstName      first name of new legacy student
	  * @param otherParent    other parent
	  * @param isHyphenated   whether the last name should be hyphenated
	  * @param id             ID of legacy student
	  * @return               newly created student
	  */
	 public Student createLegacy(String firstName, Student otherParent, boolean isHyphenated, int id) {
		 double inheritedBearBucks = this.cashOutBearBucks() + otherParent.cashOutBearBucks(); //add student bear bucks to parent
		 
		 String legacyLastName;
		 if (isHyphenated == true) {
			 legacyLastName = this.lastName + "-" + otherParent.lastName;
		 }else {
			 legacyLastName = this.lastName;
		 }
		 Student legacy = new Student(firstName, legacyLastName, id);
		 legacy.depositBearBucks(inheritedBearBucks);
		 return legacy;
	 }
	 public String toString() {
	        return getFullName() + " " + studentId;
	    }
}
