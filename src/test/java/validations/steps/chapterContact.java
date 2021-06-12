package validations.steps;

import framework.Context;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import validations.objects.pages.Contact;

import java.util.Map;

public class chapterContact {
    private Contact contactPage;

    Contact getMyPage() {
        if (contactPage == null) {
            contactPage = new Contact(Context.defaultDriver);
        }
        return contactPage;
    }

    @When("I enter the following data")
    public void iEnterTheFollowingData(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        /*
            | name     | <name>     |
            | address1 | <address1> |
            | address2 | <address2> |
            | subject  | <subject>  |
            | message  | <message>  |
         */
        getMyPage().setNameField(data.get("name"));
        getMyPage().setAddressField1(data.get("address1"));
        getMyPage().setAddressField2(data.get("address2"));
        getMyPage().setSubjectField(data.get("subject"));
        getMyPage().setMessageField(data.get("message"));
    }

    @And("I enter a message {int} chars long")
    public void iEnterAMessageCharsLong(int numChars) {
        getMyPage().setMessageField(bigMessage.substring(0, numChars));
    }

    @And("I send the message")
    public void iSendTheMessage() {
        getMyPage().sendMessage();
    }

    @Then("the message is not sent")
    public void theMessageIsNotSent() {
        /* The browser itself had been told that the fields are all required
         * Chrome will show little pop-ups, but it is not easy to find these in the DOM
         * Similarly, there are other client-side verifications that should prevent submission.
         * When the page is drawn a timestamp is put on it. If that has not changed, then
         * we have fair confidence that it has not been redrawn.
         */
        Assert.assertEquals("The form appears to have changed", getMyPage().momentOfBirth, getMyPage().getTimestamp());
    }

    @Then("an attempt to send is made")
    public void anAttemptToSendIsMade() {
        // The implementation is: the form passes control to the server-side worker, which passes control back
        Assert.assertNotEquals("The form seems to be unchanged",
                getMyPage().momentOfBirth, getMyPage().getTimestamp());
        // So there should always be some sort of response from the server-side worker
        Assert.assertTrue(
                "The confirmation message should be populated",
                getMyPage().getSendingResultsMessage().length() > 0);
    }

    @And("confirmation of sending is shown")
    public void confirmationOfSendingIsShown() {
        Assert.assertTrue(
                "The confirmation message should be visible",
                getMyPage().sendingResultsMessageIsVisible());
    }

    @And("the sending was successful")
    public void theSendingWasSuccessful() {
        Assert.assertTrue(
                "The confirmation message should be visible",
                getMyPage().sendingResultSignifiesSuccess());
    }

    @Then("the form is not visible")
    public void theFormIsNotVisible() {
        Assert.assertFalse("The form should not be visible",
                getMyPage().theFormIsVisible());
    }

    // this is slightly too long
    @SuppressWarnings("FieldCanBeLocal")
    private final String bigMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam ut lectus " +
            "eget libero tristique auctor sit amet nec velit. Nam maximus nisi vitae felis bibendum euismod. Maecenas efficitur aliquet ante eget scelerisque. Sed vel justo dui. Ut id quam id risus ultricies faucibus id in dolor. Duis efficitur a nisl ut malesuada. Aenean in augue eget ligula tincidunt convallis. Donec est lacus, euismod id malesuada ut, posuere eu nulla. Proin commodo mauris eget blandit auctor. Curabitur a enim vel urna malesuada ultricies. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Quisque sollicitudin vulputate egestas. Phasellus at diam sit amet metus laoreet efficitur. Fusce pellentesque efficitur malesuada. Sed vulputate auctor faucibus. Ut at finibus lacus, et interdum lorem.\n" +
            "\n" +
            "Aenean mattis ornare elit quis fringilla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nullam bibendum, libero id laoreet mattis, metus leo lacinia mauris, non efficitur arcu nibh at sem. Donec vitae magna lobortis, semper justo pellentesque, vehicula purus. Integer vel urna sollicitudin, dictum magna eu, tincidunt est. Morbi iaculis turpis eu suscipit elementum. Pellentesque ornare, diam vel vehicula porttitor, lectus ante porttitor libero, sit amet cursus dui justo a sapien.\n" +
            "\n" +
            "Nam nunc ligula, elementum a lectus interdum, mattis viverra dui. Phasellus turpis magna, lobortis at auctor eget, fermentum in urna. Cras gravida lectus ipsum, nec mollis nisi blandit quis. Curabitur lectus est, facilisis sit amet tellus sed, aliquet mollis enim. Integer sit amet tincidunt diam. Aliquam ipsum ipsum, egestas non quam vel, vestibulum maximus nibh. Praesent ultrices, arcu et maximus consectetur, ex tellus iaculis arcu, eu maximus lacus leo quis leo. Nulla blandit enim a lorem dictum, eu bibendum turpis ultrices. Aliquam augue tellus, vestibulum sit amet dapibus non, porta ut ligula. Integer varius ac est eu tempor. Aliquam efficitur sem erat, sit amet vulputate ante venenatis eu. Mauris consectetur lorem et neque sollicitudin, ut dictum arcu posuere. Maecenas consectetur eget erat ac interdum.\n" +
            "\n" +
            "Quisque cursus cursus neque, vel pellentesque lectus. Cras efficitur eget massa iaculis sollicitudin. Maecenas id sapien in nulla molestie scelerisque. Donec a lorem nisi. Donec dignissim efficitur diam, at congue nisl egestas et. Curabitur mollis, tortor luctus facilisis ultrices, nunc nisl fermentum tellus, quis sodales tellus tellus vitae neque. Morbi vestibulum massa a blandit tempus. Pellentesque lacus tellus, commodo accumsan ante at, placerat molestie risus. Maecenas vitae urna eget felis tempus luctus sed non est. Quisque vitae tempor ligula. Pellentesque sodales dui ac eros fermentum elementum. Ut urna libero, efficitur in leo vel, laoreet fermentum mauris. Pellentesque posuere aliquet sem ac convallis. Donec luctus sit amet felis at consequat.\n" +
            "\n" +
            "Aliquam venenatis enim auctor gravida volutpat. In sed sollicitudin ligula. Cras interdum suscipit auctor. Praesent egestas metus sapien, in ornare nibh vehicula at. Aliquam nisi magna, rhoncus eu vehicula eu, pharetra sit amet sapien. Aliquam dapibus, orci at facilisis tempus, risus est mattis urna, vitae cursus nunc ligula vel magna. Vestibulum mollis iaculis dui, ac viverra eros pulvinar id. Aliquam eleifend dolor at rutrum auctor. Praesent vel fermentum velit. Sed consequat erat sapien, quis fringilla dolor mattis at. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. In lobortis et est in viverra. Mauris pellentesque sodales erat eget auctor.\n" +
            "\n" +
            "Nunc imperdiet nulla semper ipsum rhoncus eleifend. Duis egestas ullamcorper dui, eget suscipit mi venenatis sed. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus gravida enim at sollicitudin commodo. Mauris sem mauris, elementum et fringilla ac, ultrices ut odio. Curabitur quis massa non lorem commodo vulputate eget tincidunt nibh. Aenean condimentum quis turpis vel mollis.\n" +
            "\n" +
            "Proin sapien sem, convallis ac luctus ut, pellentesque vel nisl. Sed auctor molestie justo sit amet feugiat. In commodo nec elit vel laoreet. Aliquam iaculis at magna at volutpat. Curabitur nec rhoncus elit. Ut lacinia blandit enim, pharetra dignissim tortor fermentum et. Praesent consectetur nunc non metus gravida dictum. Vivamus quis gravida augue, et dictum urna. Vestibulum sed nibh magna. Donec scelerisque tristique erat at molestie. Sed id sem a lacus dictum efficitur sit amet quis quam. Cras vehicula vitae turpis at tristique. Integer tempus mauris erat, at dictum nibh rhoncus vitae. Vivamus vitae arcu libero. Integer faucibus enim quis mi imperdiet mattis.\n" +
            "\n" +
            "Phasellus interdum ligula a lectus dapibus, a varius dui posuere. Donec rhoncus finibus elit eu bibendum" +
            ". Aenean rhoncus bibendum lacus. Proin fermentum lorem non neque molestie, a commodo elit ultricies. Nunc tincidunt et odio sit amet pretium. Sed eget massa sapien. Nulla nulla felis, euismod vel lacinia nec, posuere eu tellus. Phasellus posuere auctor augue. Sed sagittis molestie.";

    @Then("the subject field contains {int} characters")
    public void theSubjectFieldContainsCharacters(int numChars) {
        Assert.assertEquals("Unexpected number of chars in the subject field",
                numChars, getMyPage().getSubjectField().length());
    }

    @Then("the subject field contains {string}")
    public void theSubjectFieldContains(String expectedContents) {
        Assert.assertEquals(
                "Unexpected contents of the subject field",
                expectedContents,
                getMyPage().getSubjectField()
        );
    }

    @Then("the email1 field contains {int} characters")
    public void theEmail1FieldContainsCharacters(int numChars) {
        Assert.assertEquals("Unexpected number of characters in the first email field",
                numChars, getMyPage().getAddressField1().length());
    }

    @Then("the email2 field contains {int} characters")
    public void theEmail2FieldContainsCharacters(int numChars) {
        Assert.assertEquals("Unexpected number of characters in the first email field",
                numChars, getMyPage().getAddressField2().length());
    }

    @Then("the name field contains {int} characters")
    public void theNameFieldContainsCharacters(int numChars) {
        Assert.assertEquals("Unexpected number of characters in the first email field",
                numChars, getMyPage().getNameField().length());
    }

    @Then("the message field contains {int} characters")
    public void theMessageFieldContainsCharacters(int numChars) {
        Assert.assertEquals("Unexpected number of characters in the first email field",
                numChars, getMyPage().getMessageField().length());
    }

    @Then("the message counter field contains {int}")
    public void theMessageCounterFieldContains(int num) {
        Assert.assertEquals("Unexpected char counter value",
                num, getMyPage().getMessageCharCount());
    }


}
