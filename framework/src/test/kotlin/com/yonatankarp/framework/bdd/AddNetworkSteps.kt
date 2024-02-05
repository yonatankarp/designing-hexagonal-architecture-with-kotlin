package com.yonatankarp.framework.bdd

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.RouterId

class AddNetworkSteps {

    private var routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
    private var router: Router? = null
    private val routerNetworkFileAdapter = RouterNetworkFileAdapter.getInstance()
    private val network = Network(IP("20.0.0.0"), "Marketing", 8)

    @When("I found the router")
    fun lookupRouter() {
        router = routerNetworkFileAdapter.fetchRouterById(routerId)
    }

    @And("The network address is valid and doesn't already exist")
    fun checkAddressValidityAndExistence() {
        val availabilitySpec = NetworkAvailabilitySpecification(network.address, network.name, network.cidr)
        if (router == null || !availabilitySpec.isSatisfiedBy(router!!)) {
            throw IllegalArgumentException("Address already exists")
        }
    }

    @Given("The CIDR is valid")
    fun checkCidr() {
        val cidrSpec = CIDRSpecification()
        if (cidrSpec.isSatisfiedBy(network.cidr)) {
            throw IllegalArgumentException("CIDR is below ${CIDRSpecification.MINIMUM_ALLOWED_CIDR}")
        }
    }

    @Then("Add the network to the router")
    fun addNetwork() {
        router?.let {
            router = it.addNetworkToSwitch(network)
        }
    }
}
