package Test.Automation.Utils.SendEmailSetup;

public class TestCaseDetails {

	String title;
	String status;
	String failureReason;
	String failureStepName;
	String executionTime;

	public TestCaseDetails(String title, String status, String failureReason, String failureStepName,
			String executionTime) {
		this.title = title;
		this.status = status;
		this.failureReason = failureReason;
		this.failureStepName = failureStepName;
		this.executionTime = executionTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public String getFailureStepName() {
		return failureStepName;
	}

	public String getExecutionTime() {
		return executionTime;
	}
}