package com.example.server;

import com.example.test.GetJobRequest;
import com.example.test.GetJobResponse;
import com.example.test.GetLongestLengthRequest;
import com.example.test.GetLongestLengthResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class TestEndpoint {

    public static final String NAMESPACE = "http://example.com/test";
    public static final String LONGEST_LENGTH_REQUEST_LOCAL_PART = "getLongestLengthRequest";
    public static final String JOB_REQUEST_LOCAL_PART = "getJobRequest";

    @PayloadRoot(namespace = NAMESPACE, localPart = LONGEST_LENGTH_REQUEST_LOCAL_PART)
    @ResponsePayload
    public GetLongestLengthResponse getLongestLength(@RequestPayload GetLongestLengthRequest request) throws TestNotFoundException {
        //int length = getLongestStringLength(concatinateString(request.getInput()));
        List permList = new ArrayList<String>();

        for (int i = 0; i < request.getInput().size(); i++) {
            UtilHelper.generatePerm(i + 1, request.getInput(), "", permList);
        }
        int length = UtilHelper.getLongestStringLength(permList);

        GetLongestLengthResponse response = new GetLongestLengthResponse();
        response.setOutput(length);
        String jobId=String.valueOf(UtilHelper.getRandomNumber());
        response.setJobId(jobId);
        Map mp = new HashMap<GetLongestLengthRequest, GetLongestLengthResponse>();
        mp.put(request, response);
        UtilHelper.jobMap.put(jobId, mp);
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE, localPart = JOB_REQUEST_LOCAL_PART)
    @ResponsePayload
    public GetJobResponse getJob(@RequestPayload GetJobRequest request) {
        //int length = getLongestStringLength(concatinateString(request.getInput()));
        GetJobResponse response = new GetJobResponse();
        response.setJobId(request.getJobId());

        Map<String, Map<GetLongestLengthRequest, GetLongestLengthResponse>> jobMap = UtilHelper.jobMap;
        for (Map.Entry<String, Map<GetLongestLengthRequest, GetLongestLengthResponse>> entrySet : jobMap.entrySet()) {
            String key = entrySet.getKey();
            if (key.equals(request.getJobId())) {
                Map<GetLongestLengthRequest, GetLongestLengthResponse> value = entrySet.getValue();
                for (Map.Entry<GetLongestLengthRequest, GetLongestLengthResponse> ValueEntrySet : value.entrySet()) {
                    GetLongestLengthRequest req = ValueEntrySet.getKey();
                    GetLongestLengthResponse res = ValueEntrySet.getValue();
                    response.setInput(req.getInput());
                    response.setOutput(res.getOutput());
                }

            }

        }


        return response;
    }

}
