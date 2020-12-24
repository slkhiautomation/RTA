package Test.Automation.Utils.SendEmailSetup;

import Test.Automation.DataProvider.TestData;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CompileHTML {


    public static String resultSet(String jsonFileName) throws IOException {
        LinkedHashMap<String, TestCaseDetails> jsonResults = SeleniumResults.getJsonResults(jsonFileName);

        String resultString =
                "<html>\n" +
                        "<head>\n" +
                        "<style>\n" +
                        "table {\n" +
                        "    border-collapse: separate;\n" +
                        "    width: 100%;\n" +
                        "    border: 1px solid black;\n" +
                        "} \n" +
                        "\n" +
                        "td {\n" +
                        "    border: 1px solid black;\n" +
                        "}\n" +
                        " \n" +
                        "table.ex2 {\n" +
                        "    table-layout: fixed;\n" +
                        "}\n" +
                        "</style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        " \n" +

                        "<p>Execution Time: "+ TestData.SELENIUM_RESULTS_MIN+" min</p>" +

                        getStringCountTestCases(jsonResults) +

                        "<table class=\"ex2\">\n" +
                        "  <tr>\n" +
                        "    <td align='center' width='5%'>Test Case ID</td>\n" +
                        "    <td align='center' width='60%'>Title</td>\n" +
                        "    <td align='center' width='5%'>Status</td>\n" +
                        "    <td align='center' width='25%'>Failure Reason</td>\n" +
                        "    <td align='center' width='5%'>Execution Time</td>\n" +
                        "  </tr>\n";

        for (Map.Entry<String, TestCaseDetails> entry : jsonResults.entrySet()) {

            String status = entry.getValue().getStatus();

            String tdHtml = status.toLowerCase().equals("pass") ? "<td style='color:green'>" : "<td style='color:red'>";
            resultString = resultString.concat(

                    "<tr>"
                            + "<td>" + entry.getKey() + "</td>"
                            + "<td>" + entry.getValue().getTitle() + "</td>"
                            + tdHtml + status + "</td> "
                            + "<td>" + getFailureReason(entry) + "</td>"
                            + "<td>" + entry.getValue().getExecutionTime() + " sec.</td>"
                            + "</tr>");
        }

        resultString = resultString.concat("</table><br> Regards,<br>CRM Automation Team</body></html>");
        return resultString;
    }

	private static String getFailureReason(Map.Entry<String, TestCaseDetails> entry) {
		String failureReason = entry.getValue().getFailureReason();

		if (failureReason.contains("NoSuchElementException") || failureReason.contains("no such element")) {
			String element = StringUtils.substringBefore(StringUtils.substringAfter(failureReason, "\"selector\":\""),
					"\"}");
			failureReason = "Element {" + element + "}" + TestData.InformationMessage.ELEMENT_NOT_FOUND_EXCEPTION
					+ "\n " + TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
					+ entry.getValue().getFailureStepName();
		} else if (failureReason.contains("java.lang.AssertionError") || failureReason.contains("org.junit.Assert.fail")
				|| failureReason.contains("org.junit.ComparisonFailure")) {
			if (failureReason.contains("java.lang.AssertionError")) {
				if (failureReason.contains("Values should be different")) {
					failureReason = "Expected and Actual values should be same. " + "\n "
							+ TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
							+ entry.getValue().getFailureStepName();
				} else {
					if (failureReason.indexOf("!") > 0) {
						failureReason = failureReason.substring(failureReason.indexOf(":") + 2,
								failureReason.indexOf("!") + 1) + "\n "
								+ TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
								+ entry.getValue().getFailureStepName();
					} else {
						failureReason = "Assertion Error, Condition Failed! \n" + "\n "
								+ TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
								+ entry.getValue().getFailureStepName();
					}
				}
			} else {
				failureReason = "Assertion Error, Condition Failed! \n" + "\n "
						+ TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE + entry.getValue().getFailureStepName();
			}
		} else if (failureReason.contains("StaleElementReferenceException")
				|| failureReason.contains("stale element reference")) {
			String element = StringUtils.substringBefore(StringUtils.substringAfter(failureReason, "\"selector\":\""),
					"\"}");
			failureReason = "Element {" + element + "}" + TestData.InformationMessage.ELEMENT_NOT_FOUND_EXCEPTION
					+ "\n " + TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
					+ entry.getValue().getFailureStepName();
		} else if (failureReason.contains("ElementNotVisibleException")
				|| failureReason.contains("element not visible")) {
			String element = StringUtils.substringBefore(StringUtils.substringAfter(failureReason, "\"selector\":\""),
					"\"}");
			failureReason = "Element {" + element + "}" + TestData.InformationMessage.ELEMENT_NOT_FOUND_EXCEPTION
					+ "\n " + TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE
					+ entry.getValue().getFailureStepName();
		} else if (failureReason.contains("TimeoutException") || failureReason.contains("Expected condition failed")) {
			failureReason = TestData.InformationMessage.TIME_OUT_EXCEPTION_MESSAGE + "\n\n "
					+ TestData.InformationMessage.TEST_CASE_FAILED_MESSAGE + entry.getValue().getFailureStepName();
		}
		return failureReason;
	}

    private static String getStringCountTestCases(LinkedHashMap<String, TestCaseDetails> jsonResults) throws IOException {
        return "<p> Total Test Cases: " + getPassFailCount(jsonResults)[2] +
                "<p style='color:green;'>" + "Passed: " + getPassFailCount(jsonResults)[0] + "</p>" +
                "<p style='color:red;'>  " + "Failed: " + getPassFailCount(jsonResults)[1] + "</p>\n\n";
    }

	private static int[] getPassFailCount(LinkedHashMap<String, TestCaseDetails> jsonResults) throws IOException {
		int failCount = 0;
		int passCount = 0;

		for (Map.Entry<String, TestCaseDetails> entry : jsonResults.entrySet()) {

			String status = entry.getValue().getStatus();

			if (status.equals("PASS")) {
				passCount = passCount + 1;
			}
			else if (status.equals("FAIL")) {
				failCount = failCount + 1;
			}
		}
		return new int[] { passCount, failCount, jsonResults.size() };
	}
}
