package com.example.ServerAPI.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class IntegrationConfig {

    @Bean
    public MessageChannel inputChanel(){
        return new DirectChannel();
    }

    @Bean
    public MessageChannel fileWriterChanel(){
        return new DirectChannel();
    }

    @Bean
    @Transformer(inputChannel = "inputChanel", outputChannel = "fileWriterChanel")
    public GenericTransformer<String, String> mainTransformer(){
        return text -> {
            // some logic
            return text;
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChanel")
    public FileWritingMessageHandler messageHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(
                new File("D:/roma/SpringProjects/CardTransferApp"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }


}
