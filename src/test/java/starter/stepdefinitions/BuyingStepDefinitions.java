package starter.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.actions.Actions;

public class BuyingStepDefinitions {


    @Given("{} navigates to Amazon Website")
    public void navigates_to_amazon_website(String customer) {
        System.out.println(String.format("%s: ", customer)+ Actions.theSearchHomePage());
    }

    @When("{} adds items to the cart")
    public void adds_items_to_the_cart(String customer) {
        System.out.println(String.format("%s: ", customer)+Actions.addingItemsToCart());

    }

    @Then("{} buys the items of the cart")
    public void buys_the_items_of_the_cart(String customer) {
        System.out.println(String.format("%s: ", customer)+Actions.buysItemsCart());
    }

    @Then("{} downloads the receipt")
    public void downloads_the_receipt(String customer) {
        System.out.println(String.format("%s: ", customer)+Actions.downloadReceipt());
    }
}
