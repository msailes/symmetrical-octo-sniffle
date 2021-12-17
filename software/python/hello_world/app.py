import json
import os
import boto3

lambda_client = boto3.client("lambda")
JAVA_FUNCTION_NAME = os.environ["JAVA_FUNCTION_NAME"]

PAYLOAD_KEYS = ["requestId", "vechicalType", "vechicalModel", "vechicalAge"]

def lambda_handler(event, context):
    # Load dict from JSON body payload
    try:
        payload = json.loads(event["body"])
    except:
        return {
            "statusCode": 400,
            "body": json.dumps({
                "error": "Invalid request payload"
            })
        }

    # Check if all keys are present
    if not all(key in payload for key in PAYLOAD_KEYS):
        return {
            "statusCode": 400,
            "body": json.dumps({
                "error": "Invalid request payload"
            })
        }

    # Optional: do any transformation on the payload

    # Invoke the other Lambda function
    response = lambda_client.invoke(
        FunctionName=JAVA_FUNCTION_NAME,
        Payload=json.dumps(payload)
    )

    # Something went wrong
    if response["StatusCode"] != 200:
        return {
            "statusCode": 500,
            "body": json.dumps({
                "error": "Internal error: {}".format(response.get("FunctionError", "Unkown internal error"))
            })
        }

    # Read the response payload
    response_payload = response["Payload"].read()

    # Optional: transform from JSON
    response_payload = json.loads(response_payload)

    # Optional: transform the response payload

    # Send the response back to the end user
    return {
        "statusCode": 200,
        "body": json.dumps(response_payload)
    }