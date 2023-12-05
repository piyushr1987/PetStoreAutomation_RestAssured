package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTests {

	Faker faker;
	User userPayload;

	public Logger logger;

	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		// logs
		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void testPostUser() {
		logger.info("**********creating user***********");
		Response res = UserEndPoints.createUser(userPayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void testGetUserByName() {
		logger.info("**********reading user info***********");
		Response res = UserEndPoints.readUser(this.userPayload.getUsername());
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("**********user info is displayed***********");
	}

	@Test(priority = 3)
	public void testUpdateUserByName() {

		logger.info("**********updating user info***********");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());

		Response res = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		res.then().log().all();

		Assert.assertEquals(res.getStatusCode(), 200);

		// check data after update
		Response resAfterUpdate = UserEndPoints.updateUser(this.userPayload.getUsername(), userPayload);
		Assert.assertEquals(resAfterUpdate.getStatusCode(), 200);
		
		logger.info("**********user updated***********");

	}

	@Test(priority = 4)
	public void testGetDeleteUserByName() {
		logger.info("**********deleting user***********");
		Response res = UserEndPoints.deleteUser(this.userPayload.getUsername());

		Assert.assertEquals(res.getStatusCode(), 200);
		
		logger.info("**********user deleted***********");
	}

}
