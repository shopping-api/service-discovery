package com.generoso.ft.sd.steps;

import com.generoso.ft.sd.client.Client;
import com.generoso.ft.sd.client.RequestTemplate;
import com.generoso.ft.sd.client.model.Endpoint;
import com.generoso.ft.sd.state.ScenarioState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequestStepDefinitions {

    private static final Map<String, String> bodyMap;

    private final Map<Endpoint, RequestTemplate> requestTemplates;
    private final Client client;
    private final ScenarioState scenarioState;

    @Given("an endpoint {} is prepared")
    public void thePrivateEndpointIsPrepared(Endpoint endpoint) {
        var requestTemplate = getRequestTemplate(endpoint);
        scenarioState.setRequestTemplate(requestTemplate);
    }

    @Given("an endpoint {} is prepared with path parameter {word}")
    public void anEndpointIsPreparedWithPathParameter(Endpoint endpoint, String pathParameter) {
        var requestTemplate = getRequestTemplate(endpoint);
        requestTemplate.pathParameter(pathParameter);
        scenarioState.setRequestTemplate(requestTemplate);
    }

    @And("a request body is prepared for {word}")
    public void aRequestBodyIsPreparedFor(String requestBodyKey) {
        scenarioState.getRequestTemplate().body(bodyMap.get(requestBodyKey));
    }

    @When("the request is sent")
    public void theEndpointReceivesARequest() {
        var response = client.execute(scenarioState.getRequestTemplate());
        scenarioState.setActualResponse(response);
    }

    private RequestTemplate getRequestTemplate(Endpoint endpoint) {
        if (requestTemplates.containsKey(endpoint)) {
            return requestTemplates.get(endpoint);
        }

        throw new RuntimeException("Invalid request template provided.");
    }

    static {
        bodyMap = new HashMap<>();
        bodyMap.put("APP_NAME", """
            {
                "instance": {
                    "instanceId": "5784ed89d3cf:gateway:8080",
                    "app": "APP_NAME",
                    "appGroupName": null,
                    "ipAddr": "172.20.0.3",
                    "sid": "na",
                    "homePageUrl": "http://172.20.0.3:8080/",
                    "statusPageUrl": "http://172.20.0.3:8080/actuator/info",
                    "healthCheckUrl": "http://172.20.0.3:8080/actuator/health",
                    "secureHealthCheckUrl": null,
                    "vipAddress": "app_name",
                    "secureVipAddress": "app_name",
                    "countryId": 1,
                    "dataCenterInfo": {
                      "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
                      "name": "MyOwn"
                    },
                    "hostName": "172.20.0.3",
                    "status": "UP",
                    "overriddenStatus": "UNKNOWN",
                    "leaseInfo": {
                      "renewalIntervalInSecs": 30,
                      "durationInSecs": 90,
                       "registrationTimestamp": 0,
                       "lastRenewalTimestamp": 0,
                       "evictionTimestamp": 0,
                       "serviceUpTimestamp": 0
                     },
                    "isCoordinatingDiscoveryServer": false,
                    "lastUpdatedTimestamp": 1658954363496,
                    "lastDirtyTimestamp": 1658954365828,
                    "actionType": null,
                    "asgName": null,
                    "port": {
                       "$": 8080,
                      "@enabled": "true"
                    },
                    "securePort": {
                      "$": 443,
                      "@enabled": "false"
                    },
                    "metadata": {
                      "management.port": "8080"
                    }
                }
            }
            """);
    }
}
