package org.example;

import org.example.stubs.TestInputStream;
import org.example.stubs.TestOutPutStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class EchoServiceTest {

    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponse() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request =  "Hello world!";

        //when:
        TestOutPutStream outputStream = new TestOutPutStream();
        InputStream inputStream = new TestInputStream(request);

        boolean response =  echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:
        String messageSent= outputStream.getMessageSent();
        assertEquals(request,messageSent);
        assertTrue(response);
    }

    @Test
    public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponse() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request =  "Hello world!";

        //when:
        TestOutPutStream outputStream = new TestOutPutStream();
        InputStream inputStream = new TestInputStream("otra cosa");

        boolean response =  echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:
        String messageSent= outputStream.getMessageSent();
        assertEquals(request,messageSent);
        assertFalse(response);
    }

}