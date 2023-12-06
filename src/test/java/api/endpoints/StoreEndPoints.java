package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	// method created for getting urls from properties file
	public static ResourceBundle getURL() {
		ResourceBundle routes = ResourceBundle.getBundle("routes");// load properties file
		return routes;
	}

	public static Response createStore(Store payload) {
		String post_url = getURL().getString("postStore_url");
		Response res = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(payload)

				.when().post(post_url);

		return res;

	}

	public static Response readUser(int id) {
		String get_url = getURL().getString("getStore_url");

		Response res = given().pathParam("Id", id)

				.when().get(get_url);

		return res;

	}

	public static Response deleteUser(int id) {
		String delete_url = getURL().getString("deleteStore_url");
		Response res = given().pathParam("Id", id)

				.when().delete(delete_url);

		return res;

	}

}
