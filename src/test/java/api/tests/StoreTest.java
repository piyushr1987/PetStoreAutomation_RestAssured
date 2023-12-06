package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StoreEndPoints;
import api.payloads.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StoreTest {
	// public Logger logger;

	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostStoreOrder(String userId, String petId, String quantity, String shipDate, String status,
			String complete) {
		// logger.info("**********creating store order***********");
		Store userPayload = new Store();

		userPayload.setId((Integer.parseInt(userId)));
		userPayload.setPetId((Integer.parseInt(petId)));
		userPayload.setQuantity(Integer.parseInt(quantity));
		userPayload.setShipDate(shipDate);
		userPayload.setStatus(status);
		userPayload.setComplete(Boolean.parseBoolean(complete));

		Response res = StoreEndPoints.createStore(userPayload);
		res.then().log().all();
		Assert.assertEquals(res.getStatusCode(), 200);

	}

	@Test(priority = 2, dataProvider = "UserId", dataProviderClass = DataProviders.class)
	public void testGetStoreOrderById(int usId) {
		// logger.info("**********read store order by id***********");
		Response res = StoreEndPoints.readUser(usId);

		Assert.assertEquals(res.getStatusCode(), 200);
	}

	@Test(priority = 3, dataProvider = "UserId", dataProviderClass = DataProviders.class)
	public void testDeleteStoreOrderById(int usId) {

		// logger.info("**********delete store order by id***********");
		Response res = StoreEndPoints.deleteUser(usId);

		Assert.assertEquals(res.getStatusCode(), 200);
	}
}
