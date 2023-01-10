import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TrelloAPI {
    public static void main(String[] args) {

        String URL = "https://api.trello.com";

        String key = "982abc8dcdc1f5c1f521158a8a130b2e";
        String token = "ATTA21ffc751290b3358369e166609b32df8553dfb06ba83ffb998a84cf6e0277df8210993B3";

        String organizationName = "FWD";
        String organizationID;

        String boardName = "Board 1";
        String boardID ;

        String memberID;

        String listName = "List 1";
        String listID;


        Response createOrg =
        RestAssured
                .given()
                .baseUri(URL)
                .basePath("/1/organizations")
                .queryParam("displayName", organizationName)
                .queryParam("key", key)
                .queryParam("token", token)
                .body("")
                .when()
                .post();

        System.out.println("Creating Organization");
        createOrg.prettyPrint();
        System.out.println("---------------------------");

        JsonPath OrgPath = createOrg.jsonPath();
        organizationID = OrgPath.getString("id");

        Response ownID =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/members/me")
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .get();

        System.out.println("Getting Member ID");
        ownID.prettyPrint();
        System.out.println("---------------------------");

        JsonPath memberPath = ownID.jsonPath();
        memberID = memberPath.getString("id");

        Response membersOrganizations =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/members/"+memberID+"/organizations")
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .get();

        System.out.println("Member's Organizations ID");
        membersOrganizations.prettyPrint();
        System.out.println("---------------------------");

        Response createBoard =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/boards/")
                        .queryParam("name", boardName)
                        .queryParam("idOrganization", organizationID)
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .body("")
                        .when()
                        .post();

        System.out.println("Creating a Board");
        createBoard.prettyPrint();
        System.out.println("---------------------------");

        JsonPath boardPath = createBoard.jsonPath();
        boardID = boardPath.getString("id");



        Response getBoards =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/organizations/"+organizationID+"/boards")
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .get();

        System.out.println("Getting the Boards");
        getBoards.prettyPrint();
        System.out.println("---------------------------");




        Response createList =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/lists")
                        .queryParam("name", listName)
                        .queryParam("idBoard", boardID)
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .body("")
                        .when()
                        .post();

        System.out.println("Creating a List");
        createList.prettyPrint();
        System.out.println("---------------------------");


        JsonPath listPath = createList.jsonPath();
        listID = listPath.getString("id");


        Response getLists =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/boards/"+boardID+"/lists")
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .get();

        System.out.println("Getting the Lists");
        getLists.prettyPrint();
        System.out.println("---------------------------");


        Response archiveList =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/lists/"+listID+"/closed")
                        .queryParam("value", "true")
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .put();

        System.out.println("Archiving the List");
        archiveList.prettyPrint();
        System.out.println("---------------------------");


        Response deleteBoard =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/boards/"+boardID)
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .delete();

        System.out.println("Deleting the Board");
        deleteBoard.prettyPrint();
        System.out.println("---------------------------");


        Response deleteOrg =
                RestAssured
                        .given()
                        .baseUri(URL)
                        .basePath("/1/organizations/"+organizationID)
                        .queryParam("key", key)
                        .queryParam("token", token)
                        .when()
                        .delete();

        System.out.println("Deleting the Organization");
        deleteOrg.prettyPrint();
        System.out.println("---------------------------");


    }
}
