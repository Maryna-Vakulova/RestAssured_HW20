package API;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HW_20_TestTextWithPojo {
    private final static String URL = "https://reqres.in/";
    private final String EXPECTED_TEXT = "To keep ReqRes free, contributions towards server costs are appreciated!";

    @Test
    public void checkTextInResponse() {

        Specification.installSpecification(Specification.requestSpecification(URL),
                Specification.responseSpecification(200));

        SupportData supportData = given()
                .when()
                .get("api/unknown/2")
                .then().log().all()
                .extract().body().jsonPath().getObject("support", SupportData.class);

        Assert.assertNotNull(supportData.getText());
        Assert.assertEquals(supportData.getText(), EXPECTED_TEXT);
    }
}
