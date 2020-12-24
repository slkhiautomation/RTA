package Test.Automation.DataProvider;

/**
 * Created by Omer Aqeel
 */
public class TestData {
 
	public static long SELENIUM_RESULTS_MIN;
//    private static String getTestData(String scenario, String feature, String testcase)  {
//		String testDataString = null; 
//    	try {
//    		testDataString= readExcelData("Testdata", feature, scenario).get(testcase).toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return testDataString;
//	}
	
	public interface Email {
		public final static String EMAIL_RESULT_SUBJECT = "Test Automation Results";
		public final static String EMAIL_SEND_FROM_LABEL = "Automation Result";
		public final static String EMAIL_FROM = "emailFrom";
		public final static String EMAIL_TO = "emailTo";
		public final static String EMAIL_CC = "emailCC";
		public final static String EMAIL_BCC = "emailBCC";
		public final static String EMAIL_SMTP_HOST_SERVER = "smtpHostServer";
		public final static String EMAIL_SMTP_MAIL_SERVER = "smtpMailServer";
		public final static String SCENARIO_LABEL = "Scenario";
		public final static String SCENARIO_OUTLINE_LABEL = "Scenario Outline";
		public final static String BACKGROUND_LABEL = "Background";
		public final static String FAILED_STATUS = "failed";
		public final static String PASS_STATUS = "PASS";
		public final static String FAIL_STATUS = "FAIL";
	}
	
	/*
	 * All Information Messages are define here
	 */
	public interface InformationMessage {
		public final static String GIFT_CARD_ZERO_BALANCE = "Your Gift Card amount was $0.00, Please provide a different Gift Card having sufficient amount to process the order!";
		public final static String GIFT_CARD_LESS_BALANCE = "Your Gift Card did not have enough amount, Please provide a different Gift Card!";
		public final static String ELEMENT_NOT_FOUND_EXCEPTION = " was not visible on UI for some reason, so application was not able to interact with it. ";
		public final static String TEST_CASE_FAILED_MESSAGE = "Test Case failed at Step : ";
		public final static String TIME_OUT_EXCEPTION_MESSAGE = "Execution did not complete in given time. ";
		public final static String ASSERT_EQUAL_MESSAGE = " is not equal to the ";
		public final static String ASSERT_NOT_EQUAL_MESSAGE = " is equal to the ";
		public final static String DOUBLE_QUOTES = "\"";
		public final static String DOUBLE_QUOTES_END = "\"!";
		public final static String WISHLIST_NOT_EMPTY_BAG_MESSAGE = "Your wishlist bag is not empty!";
		public final static String SHOPPING_BAG_NOT_EMPTY_BAG_MESSAGE = "Your shopping bag is not empty!";
		public final static String ITEM_BAG_SIZE_NOT_M_MESSAGE = "Selected Bag Item size is not 'M'!";
		public final static String ITEM_BAG_SIZE_NOT_L_MESSAGE = "Selected Bag Item size is not 'L'!";
		public final static String BAG_ORDER_SUMMARY_ITEM_TOTAL_COUNT_MESSAGE = "Bag Order Summary did not showing the Items Total count!";
		public final static String CHECKOUT_BUTTON_NOT_SHOWING_MESSAGE = "Checkout button is not showing on UI!";
		public final static String PAYPAL_BUTTON_NOT_SHOWING_MESSAGE = "PayPal button is not showing on UI!";
		public final static String WISHLIST_ITEM_SIZE_NOT_M_MESSAGE = "Selected Wishlist Item size is not 'M'!";
		public final static String WISHLIST_ITEM_SIZE_NOT_L_MESSAGE = "Selected Wishlist Item size is not 'L'!";
		public final static String SEARCH_ITEM_NOT_MATCHED_FIRST_BAG_ITEM = "Search item did not matched with the First Bag Item!";
		public final static String SEARCH_ITEM_NOT_MATCHED_SECOND_BAG_ITEM = "Search item did not matched with the Second Bag Item!";
		public final static String BAG_SIZE_NOT_EQUAL_2 = "Bag size is not equal to 2!";
		public final static String TOTAL_BAG_SIZE_NOT_SHOWING_TOTAL_ITEM_COUNT_MESSAGE = "Total bag size is not showing the total item count!";
		public final static String PAGE_HEADING_TITLE_NOT_MY_BAG_MESSAGE = "Page heading Title is not 'My Bag'!";
		public final static String WISHLIST_TITLE_NOT_WISHLIST_MESSAGE = "Wishlist Title is not 'Wishlist'!";
		public final static String URL_NOT_ENDS_CARD_MESSAGE = "Current URL did not ends with 'cart'!";
		public final static String FINAL_ACTUAL_TOTAL_NOT_EQUAL_TOTAL_DIFFERENCE = "Final Actual Total is not equal to Total Difference!";
		public final static String FINAL_ACTUAL_TOTAL_EQUAL_TOTAL_DIFFERENCE = "Final Actual Total is equal to Total Difference!";
		public final static String EXPECTED_SUB_TOTAL_NOT_EQUAL_MESSAGE = "Expected Sub Total according to Item Quantity is not equal to Updated Sub Total!";
		public final static String EXPECTED_SUB_TOTAL_EQUAL_MESSAGE = "Expected Sub Total according to Item Quantity is equal to Updated Sub Total!";
		public final static String EXPECTED_SUB_TOTAL_NOT_EQUAL_UPDATED_SUB_MESSAGE = "Expected Sub Total is not equal to Updated Sub Total!";
		public final static String EXPECTED_SUB_TOTAL_EQUAL_UPDATED_SUB_MESSAGE = "Expected Sub Total is equal to Updated Sub Total!";
		public final static String ZERO_BALANCE_MESSAGE = "Your order has no balance, so no payment method is necessary to complete this order.";
	}
     
}
