
package Test.Automation.Utils.SendEmailSetup;

import Test.Automation.DataProvider.TestData;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

public class SeleniumResults {

	private static String testID;
	private static String failStep;

	public static LinkedHashMap<String, TestCaseDetails> getJsonResults(final String jsonFileName) throws IOException {

		SeleniumResultsJsonMapper jsonCucumberReportReader[] = new Gson()
				.fromJson(new FileReader("target/" + jsonFileName + ".json"), SeleniumResultsJsonMapper[].class);

		LinkedHashMap<String, TestCaseDetails> hmap = new LinkedHashMap<>();

		String status, error;
		long duration = 0, totalDuration = 0;
		SeleniumResultsJsonMapper obj;
		String testCaseTitle = null;
		for (int i = 0; i < jsonCucumberReportReader.length; i++) {
			obj = jsonCucumberReportReader[i];
			for (int j = 0; j < obj.elements.length; j++) {
				String testType = obj.elements[j].keyword;
				if (TestData.Email.SCENARIO_LABEL.equals(testType)
						|| TestData.Email.SCENARIO_OUTLINE_LABEL.equals(testType)
						|| TestData.Email.BACKGROUND_LABEL.equals(testType)) {
					testCaseTitle = obj.elements[j].name;

					duration = 0;
					for (int k = 0; k < obj.elements[j].steps.length; k++) {
						status = obj.elements[j].steps[k].result.status;
						duration = duration + obj.elements[j].steps[k].result.duration;
						if (obj.elements[j] != null && obj.elements[j].tags != null
								&& StringUtils.isNotEmpty(obj.elements[j].tags[0].name)) {
							for (int k2 = 0; k2 < obj.elements[j].tags.length; k2++) {
								String tagId = obj.elements[j].tags[k2].name.replace("@", "").trim();
								if (tagId.contains("TCID")) {
									testID = tagId;
									break;
								}
							}
						}

						if (TestData.Email.FAILED_STATUS.equals(status)) {
							error = obj.elements[j].steps[k].result.error_message;
							failStep = obj.elements[j].steps[k].name;
							String[] errorLines = error.split("\n");
							if ("Background".equals(obj.elements[j].keyword)) {
								testCaseTitle = "Background: " + failStep;
							}
							hmap.put(testID,
									new TestCaseDetails(testCaseTitle, TestData.Email.FAIL_STATUS,
											errorLines[0] + '\n' + errorLines[1] + '\n' + errorLines[2], failStep,
											Long.toString(TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS))));
						}
					}
					totalDuration = totalDuration + duration;
				}
				if (!hmap.containsKey(testID) && testID != null) {
					hmap.put(testID, new TestCaseDetails(testCaseTitle, TestData.Email.PASS_STATUS, "", "",
							Long.toString(TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS))));
				}
			}
		}

		long minutes = TimeUnit.MINUTES.convert(totalDuration, TimeUnit.NANOSECONDS);
		//System.out.println("Total Automation Duration = " + minutes + " minutes.");
		TestData.SELENIUM_RESULTS_MIN = minutes;
		return hmap;
	}
}
