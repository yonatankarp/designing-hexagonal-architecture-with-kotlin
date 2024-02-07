package com.yonatankarp.application.bdd

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.CIDRSpecification
import com.yonatankarp.domain.specification.NetworkAvailabilitySpecification
import com.yonatankarp.domain.valueobject.NetworkFixture
import com.yonatankarp.domain.valueobject.RouterId
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When

@Suppress("ktlint:standard:function-naming")
class AddNetworkSteps {
    private lateinit var router: Router

    private var network = NetworkFixture.validNetworkIPv4
    private var routerId = RouterId.withoutId()

    @Given("I provide a router ID and the network details")
    fun `obtain routerId`() {
        routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
    }

    @When("I found the router")
    fun `lookup router`() {
        router = SampleRouterView.fetchRouterById(routerId)
            ?: throw RuntimeException("Router not found by ID $routerId")
    }

    @And("The network address is valid and doesn't already exists")
    fun `check address validity and existence`() {
        val availabilitySpec =
            NetworkAvailabilitySpecification(
                network.address,
                network.name,
                network.cidr,
            )
        require(availabilitySpec.isSatisfiedBy(router)) { "Address already exist" }
    }

    @Given("The CIDR is valid")
    fun `check cidr`() {
        val cidrSpec = CIDRSpecification()
        require(cidrSpec.isSatisfiedBy(network.cidr)) { "CIDR is below " + CIDRSpecification.MINIMUM_ALLOWED_CIDR }
    }

    @Then("Add the network to the router")
    fun `add network`() {
        router.addNetworkToSwitch(network)
    }
}
