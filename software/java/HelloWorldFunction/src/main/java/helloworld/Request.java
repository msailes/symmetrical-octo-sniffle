// Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
// SPDX-License-Identifier: MIT-0

package helloworld;

public class Request {

    private String requestId;
    private String vechicalType;
    private String vechicalModel;
    private int vechicalAge;

    public Request() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVechicalType() {
        return vechicalType;
    }

    public void setVechicalType(String vechicalType) {
        this.vechicalType = vechicalType;
    }

    public String getVechicalModel() {
        return vechicalModel;
    }

    public void setVechicalModel(String vechicalModel) {
        this.vechicalModel = vechicalModel;
    }

    public int getVechicalAge() {
        return vechicalAge;
    }

    public void setVechicalAge(int vechicalAge) {
        this.vechicalAge = vechicalAge;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", vechicalType='" + vechicalType + '\'' +
                ", vechicalModel='" + vechicalModel + '\'' +
                ", vechicalAge=" + vechicalAge +
                '}';
    }
}
