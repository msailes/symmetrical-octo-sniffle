package helloworld;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Handler for requests to Lambda function.
 */
public class App implements RequestHandler<Request, Response> {

    private final FakeModel fakeModel = new FakeModel();

    public Response handleRequest(final Request request, final Context context) {
        context.getLogger().log(request.toString());

        fakeModel.resolve();

        return new Response("settled");
    }
}
