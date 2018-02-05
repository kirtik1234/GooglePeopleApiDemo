package com.alacriti.hrm.biz.delegate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.alacriti.hrm.log.impl.AppLogger;
import com.alacriti.hrm.model.vo.SampleVO;
import com.alacriti.hrm.util.LogUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.people.v1.PeopleService;
import com.google.api.services.people.v1.model.ContactGroup;
import com.google.api.services.people.v1.model.ListConnectionsResponse;
import com.google.api.services.people.v1.model.ListContactGroupsResponse;
import com.google.api.services.people.v1.model.Person;

public class SampleDelegate extends BaseDelegate {
	private static final AppLogger log = LogUtil.getLogger(SampleDelegate.class);

	public void getMessage(SampleVO sampleVO) throws FileNotFoundException, IOException {
		log.debugPrintCurrentMethodName();
		log.logInfo("In delegate");
		HttpTransport httpTransport = new NetHttpTransport();
		JacksonFactory jsonFactory = new JacksonFactory();
		String clientId = "642705992555-kgjvu0ei93cd1jgb2pk1h33br56d3j0q.apps.googleusercontent.com";
		String clientSecret = "SGGn8YZ9DUBKjpC_9YG0X_z0";

		// Step 2: Exchange -->
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(httpTransport, jsonFactory,
				clientId, clientSecret, sampleVO.getCode(), "http://localhost:8080").execute();
		// End of Step 2 <--

		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				.setJsonFactory(jsonFactory).setClientSecrets(clientId, clientSecret).build()
				.setFromTokenResponse(tokenResponse);

		PeopleService peopleService = new PeopleService.Builder(httpTransport, jsonFactory, credential).build();
		
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ListContactGroupsResponse START----->>>>>>>>>>>>>>>>>>>>>>>>");
		ListContactGroupsResponse response = peopleService.contactGroups().list().execute();
		List<ContactGroup> connections = response.getContactGroups();
		for (ContactGroup person : connections) {
			System.out.println("ContactGroup:::" + person);
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ListContactGroupsResponse END----->>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println("");
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ContactGroup All START----->>>>>>>>>>>>>>>>>>>>>>>>");
		ContactGroup contactGroup = peopleService.contactGroups().get("contactGroups/all").execute();
		System.out.println("All contactGroups MemberCount:::: " + contactGroup.getMemberCount());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ContactGroup All END----->>>>>>>>>>>>>>>>>>>>>>>>");
		
		
		System.out.println("");

		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ContactGroup Friends START----->>>>>>>>>>>>>>>>>>>>>>>>");
		ContactGroup contactGroup1 = peopleService.contactGroups().get("contactGroups/friends").execute();
		System.out.println(" Friends contactGroups MemberCount:::: " + contactGroup1.getMemberCount());
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ContactGroup Friends END----->>>>>>>>>>>>>>>>>>>>>>>>");
		
		System.out.println("");
		
		
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ListConnections START----->>>>>>>>>>>>>>>>>>>>>>>>");
		ListConnectionsResponse connectionsResponse = peopleService.people().connections().list("people/me")
				.setPersonFields("names,emailAddresses").execute();
		List<Person> persons = connectionsResponse.getConnections();
		for (Person person : persons) {
			System.out.println("Name:::" + person.getNames());
		}
		System.out.println("<<<<<<<<<<<<<<<<<<<<<------ListConnections END----->>>>>>>>>>>>>>>>>>>>>>>>");

	}

}
