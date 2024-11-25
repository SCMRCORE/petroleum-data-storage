package com.petroleumserver.controller;

import com.petroleumcommom.result.Result;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/real-time")
public class RealTimeController {



    @PostMapping
    public Result test() {
        String jsonStr = "{\n" +
                "    \"xmlStr\": \"<?xml version=\\\"1.0\\\" encoding=\\\"utf-8\\\"?>\\n<soap:Envelope\n" +
                " xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\"\n" +
                " xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\"\n" +
                " xmlns:soap=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"\n" +
                " xmlns:tns=\\\"http://www.witsml.org/message/120\\\">\\n <soap:Body>\\n\n" +
                " <tns:WMLS_GetFromStore uri=\\\"http://www.witsml.org/message/120\\\">\\n" +
                " <WMLtypeIn>well</WMLtypeIn>\\n <QueryIn>\\n <![CDATA[\\n <wells\n" +
                " xmlns=\\\"http://www.witsml.org/schemas/131\\\">\\n <well uid=\\\"\\\">\\n <name />\\n" +
                " </well>\\n</wells>\\n ]]>\n </QueryIn>\n <OptionsIn/>\\n <CapabilitiesIn/>\n" +
                " </tns:WMLS_GetFromStore>\\n </soap:Body>\\n</soap:Envelope>\"\n" +
                "}";

        
        String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tns=\"http://www.witsml.org/message/120\">\n" +
                " <soap:Body>\n" +
                "  <tns:WMLS_GetFromStore uri=\"http://www.witsml.org/message/120\">\n" +
                "   <WMLtypeIn>well</WMLtypeIn>\n" +
                "   <QueryIn>\n <![CDATA[\n <wells xmlns=\"http://www.witsml.org/schemas/131\">\n" +
                "   <well uid=\"\">\n <name />\n </well>\n</wells>\n ]]>\n </QueryIn>\n" +
                "   <OptionsIn/>\n <CapabilitiesIn/>\n</tns:WMLS_GetFromStore>\n" +
                " </soap:Body>\n</soap:Envelope>";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost("http://10.77.78.233/apigateway/witsml/tj");

            
            httpPost.setHeader("Authorization", "Basic d3JkYnRqX1NjdGlhbmppbmE6MTE=");
            httpPost.setHeader("appCode", "152b5e38657e0b8cd73964bc315f74b6");
            httpPost.setHeader("apiToken", "ALHMNMBLQHHQGMVF");
            httpPost.setHeader("Content-Type", "application/xml");

            
            StringEntity stringEntity = new StringEntity(xmlStr);
            httpPost.setEntity(stringEntity);

            
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
                
                int statusCode = response.getStatusLine().getStatusCode();
                System.out.println("Response Status: " + statusCode);

                
                if (statusCode == 200) {  
                    String responseBody = EntityUtils.toString(response.getEntity());
                    System.out.println("Response Body: " + responseBody);
                } else {
                    System.out.println("Request failed with status: " + statusCode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success();

    }

}
