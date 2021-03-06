package com.example.operations.configurations;

import com.example.operation.AllData;
import com.example.operation.AllDataInfoXML;
import com.example.operation.Envelope;
import com.example.operations.properties.ExternalProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CBRClient extends WebServiceGatewaySupport {

    private final RestTemplate restTemplate;
    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;
    private final ExternalProperties externalProperties;

    private HttpEntity<Map<String, Object>> makeEntity(String writer, HttpHeaders headers) {
        headers.setContentType(MediaType.TEXT_XML);
        headers.setAccept(Collections.singletonList(MediaType.TEXT_XML));
        HttpEntity<Map<String, Object>> entity = new HttpEntity(writer, headers);

        return entity;
    }

    private ResponseEntity<String> makeRequest(String writer, HttpHeaders headers) {
        ResponseEntity<String> response = restTemplate
                .exchange(externalProperties.getCbr(), HttpMethod.POST, makeEntity(writer, headers), String.class);

        return response;
    }

    public AllData.MainIndicatorsVR.Currency getCurrencyData() throws JAXBException {
        Envelope envelope = new Envelope();
        StringWriter writer = new StringWriter();
        HttpHeaders headers = new HttpHeaders();

        envelope.setBody(new Envelope.Body());
        envelope.getBody().setAllDataInfoXML(new AllDataInfoXML());
        marshaller.marshal(envelope, writer);

        String responseXML = makeRequest(writer.toString(), headers).getBody();
        Envelope dataInfoXMLResponse = (Envelope) unmarshaller.unmarshal(new StringReader(responseXML));

        return dataInfoXMLResponse.getBody().getAllDataInfoXMLResponse()
                .getAllDataInfoXMLResult().getAllData()
                .getMainIndicatorsVR().getCurrency();
    }

}
