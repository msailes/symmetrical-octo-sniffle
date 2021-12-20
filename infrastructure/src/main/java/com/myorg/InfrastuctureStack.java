package com.myorg;

import software.amazon.awscdk.services.apigatewayv2.alpha.AddRoutesOptions;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpApi;
import software.amazon.awscdk.services.apigatewayv2.alpha.HttpMethod;
import software.amazon.awscdk.services.apigatewayv2.alpha.PayloadFormatVersion;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.LambdaProxyIntegration;
import software.amazon.awscdk.services.apigatewayv2.integrations.alpha.LambdaProxyIntegrationProps;
import software.amazon.awscdk.services.iam.PolicyStatement;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Permission;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

public class InfrastuctureStack extends Stack {
    public InfrastuctureStack(final Construct scope, final String id) {
        this(scope, id, null);
    }

    public InfrastuctureStack(final Construct scope, final String id, final StackProps props) {
        super(scope, id, props);

        Function javaFunction = Function.Builder.create(this, "JavaFunction")
                .functionName("JavaFunction")
                .handler("helloworld.App::handleRequest")
                .runtime(Runtime.JAVA_11)
                .memorySize(1024)
                .code(Code.fromAsset("../software/java/HelloWorldFunction/target/HelloWorld-1.0.jar"))
                .build();

        Map<String, String> environmentVariables = new HashMap<>();
        environmentVariables.put("JAVA_FUNCTION_NAME", javaFunction.getFunctionName());

        Function pythonFunction = Function.Builder.create(this, "PythonFunction")
                .functionName("PythonFunction")
                .handler("app.lambda_handler")
                .runtime(Runtime.PYTHON_3_9)
                .memorySize(512)
                .code(Code.fromAsset("../software/python/hello_world"))
                .environment(environmentVariables)
                .build();

        pythonFunction.addToRolePolicy(PolicyStatement.Builder.create()
                        .actions(singletonList("lambda:InvokeFunction"))
                        .resources(singletonList(javaFunction.getFunctionArn()))
                .build());

        HttpApi httpApi = HttpApi.Builder.create(this, "ProductsApi")
                .apiName("ProductsApi")
                .build();

        httpApi.addRoutes(AddRoutesOptions.builder()
                .path("/")
                .methods(singletonList(HttpMethod.POST))
                .integration(new LambdaProxyIntegration(LambdaProxyIntegrationProps.builder()
                        .handler(pythonFunction)
                        .payloadFormatVersion(PayloadFormatVersion.VERSION_2_0)
                        .build()))
                .build());
    }
}
