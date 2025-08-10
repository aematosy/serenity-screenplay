package com.screenplay.project.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/registrarUsuario.feature",
        glue = "com.screenplay.project.stepdefinitions",
        tags = "@NuevoUsuario",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"
        }
)
public class TestRunner {
}