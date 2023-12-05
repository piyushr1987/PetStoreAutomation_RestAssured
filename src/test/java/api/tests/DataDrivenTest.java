package api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId, String userName, String fname, String lname, String email, String pass,
			String ph) {
		User userPayload = new User();

		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(pass);
		userPayload.setPhone(ph);

		Response res = UserEndPoints.createUser(userPayload);
		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void testGetDeleteUserByName(String username) {
		Response res = UserEndPoints.deleteUser(username);

		Assert.assertEquals(res.getStatusCode(), 200);
	}

}
