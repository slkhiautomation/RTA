package Test.Automation.Utils.SendEmailSetup;

import Test.Automation.DataProvider.TestData;
import Test.Automation.Utils.PropertyReader;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class SendEmail {

	public static void main(String[] args) throws IOException, MessagingException {
		sendEmailBody(TestData.Email.EMAIL_RESULT_SUBJECT, "cucumber-json-report-Sanity");
	}

	public static void sendEmailBody(String subject, String jsonFileName) throws MessagingException, IOException {

		PropertyReader propertyReader = new PropertyReader();
		String emailSendFrom = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_FROM);
		String emailSendTo = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_TO);
		String emailSendCC = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_CC);
		String emailSendBCC = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_BCC);
		String smtpHostServer = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_SMTP_HOST_SERVER);
		String smtpMailServer = propertyReader.readPropertyConfigEmail(TestData.Email.EMAIL_SMTP_MAIL_SERVER);
		Properties props = System.getProperties();
		props.put(smtpMailServer, smtpHostServer);
		Session session = Session.getInstance(props, null);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailSendFrom, TestData.Email.EMAIL_SEND_FROM_LABEL));
		message.setReplyTo(InternetAddress.parse(emailSendFrom, false));
		String browser = new PropertyReader().readProperty("browser");
		subject = subject + " | " + browser.toUpperCase() + " | "
				+ new SimpleDateFormat("MMM dd, yyyy hh:mm a").format(new Date());
		message.setSubject(subject, "UTF-8");
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailSendTo, false));
		message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(emailSendCC));
		MimeBodyPart attach = new MimeBodyPart();
		String extrpt = "target\\cucumber-reports\\report.html";
		DataSource source = new FileDataSource(extrpt);
		attach.setDataHandler(new DataHandler(source));
		attach.setFileName(extrpt);
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(attach);
        message.setContent(CompileHTML.resultSet(jsonFileName), "text/html");
        message.setContent(multipart);
		Transport.send(message);
		System.out.println("email has been sent....");
	}
}
