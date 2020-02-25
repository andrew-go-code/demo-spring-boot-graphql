package com.demo.gateway.config;

import com.coxautodev.graphql.tools.SchemaParserDictionary;
import com.demo.gateway.exception.CustomGraphQLErrorHandler;
import com.demo.gateway.model.graph.account.CreditAccount;
import com.demo.gateway.model.graph.account.CurrentAccount;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphQLConfig {

    @Bean
    public GraphQLErrorHandler errorHandler() {
        return new CustomGraphQLErrorHandler();
    }

    @Bean
    public SchemaParserDictionary schemaParserDictionary() {
        return new SchemaParserDictionary()
                .add("CreditAccount", CreditAccount.class)
                .add("CurrentAccount", CurrentAccount.class);
    }

}
