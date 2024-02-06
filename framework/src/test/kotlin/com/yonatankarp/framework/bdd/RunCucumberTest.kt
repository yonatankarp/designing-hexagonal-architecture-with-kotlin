package com.yonatankarp.framework.bdd

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    plugin = ["pretty", "html:build/cucumber-result"],
)
class RunCucumberTest
