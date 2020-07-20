package testesQA;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;

public class teste {
	
	@Test
	public void testebuscacep() {
		given()
		.when().get("https://cep-v2-americanas-npf.b2w.io/cep/04661000").then().log().all();
	}

	@Test
	public void cadastroApiCampoObrigatorio() {
		given()
			.contentType("application/json")
			.body("{\r\n\"appDescription\" :\"teste\"\r\n}")
			.when().post("https://api.thecatapi.com/v1/user/passwordlesssignup")
		.then()
			.log().all()
			.body("message", containsString("\"email\" is required"))
			.statusCode(400);
		}
	
	@Test
	public void cadastroApi() {
			given()
				.contentType("application/json")
				.body("{\r\n\"email\": \"lznunes@gmail.com\",\r\n\"appDescription\" :\"teste\"\r\n}")
				.when().post("https://api.thecatapi.com/v1/user/passwordlesssignup")
			.then()
				.log().all()
				.statusCode(200);	
		
	}

	
	// 5a554fc7-4de8-40a6-8f7d-c1d95a709f15

	@Test
	public void EfetivaVotacao() {
		given()
			.contentType("application/json")
			.body("{\"image_id\" : \"3o3\", \"value\": true, \"sub_id\" : \"demo-fe3f55\"}")
			.header("x-api-key" , "5a554fc7-4de8-40a6-8f7d-c1d95a709f15")
			.when().post("https://api.thecatapi.com/v1/votes")
		.then()
			.log().all()
			.body("message", is("SUCCESS"))
			.statusCode(200);	
	
	}
	
	
	@Test
	public void ConsultaVotacao() {
		given()
			.header("x-api-key" , "5a554fc7-4de8-40a6-8f7d-c1d95a709f15")
			.when().get("https://api.thecatapi.com/v1/votes")
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	public void PegarVotacao() {
		Response response =
		given()
			.header("x-api-key" , "5a554fc7-4de8-40a6-8f7d-c1d95a709f15")
			.when().get("https://api.thecatapi.com/v1/votes");
		response.then()
			.log().all()
			.statusCode(200);
		
		//String id = response.jsonPath().getString("id");
		String id = response.jsonPath().getString("id[0]");
		
		System.out.println("ID retornado: " + id);
		
		
	}
	
}
