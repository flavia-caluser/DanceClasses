package com.example.danceClasses;

import com.example.danceClasses.DTOS.PaymentRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JSONDeserializeTest {
    @Test
    public void PaymentDTOTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String json = "{\n" +
                "    \"date\": \"2024-08-01T20:15:00\",\n" +
                "    \"paymentMethod\": \"CASH\",\n" +
                "    \"lessonPaymentRequestDTOList\": [\n" +
                "        {\n" +
                "            \"studentName\": \"Elena Stoica\",\n" +
                "            \"lessonName\": \"Hip Roll\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"studentName\": \"Elena Stoica\",\n" +
                "            \"lessonName\": \"Coregrafie\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"studentName\": \"Elena Stoica\",\n" +
                "            \"lessonName\": \"Suzzie Q\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";  // Introduce JSON-ul tău aici
        PaymentRequestDTO dto = objectMapper.readValue(json, PaymentRequestDTO.class);
        System.out.println(dto.getLessonPaymentRequestDTOList());

    }
}
