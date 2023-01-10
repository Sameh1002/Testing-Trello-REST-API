import io.restassured.RestAssured;

public class TrelloAPI {
    public static void main(String[] args) {

        String displayName = "FWD";
        String key = "982abc8dcdc1f5c1f521158a8a130b2e";
        String token = "ATTA21ffc751290b3358369e166609b32df8553dfb06ba83ffb998a84cf6e0277df8210993B3";

        RestAssured
                .given()
                .baseUri("https://api.trello.com/1/organizations")
                .queryParam("displayName", displayName)
                .queryParam("key", key)
                .queryParam("token", token)
                .body("")
                .when()
                .post()
                .prettyPrint();
    }
}
