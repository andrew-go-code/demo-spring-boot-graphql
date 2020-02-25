package com.demo.gateway.resolver.account;

import com.demo.gateway.dto.ItemResponse;
import com.demo.gateway.feign.AccountClient;
import com.demo.gateway.model.graph.PageHeader;
import com.demo.gateway.model.graph.account.AccountItem;
import com.demo.gateway.model.graph.account.CreditAccount;
import com.demo.gateway.model.graph.account.Currency;
import com.google.common.io.Resources;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@GraphQLTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AccountResolverTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @MockBean
    private AccountClient accountClient;

    @Test
    void when_accounts_requested_get_status_200_and_json_body() throws IOException, JSONException {
        //given
        ItemResponse<AccountItem> mockResponse = new ItemResponse<AccountItem>()
                .setItems(List.of(
                        new CreditAccount()
                                .setId(1)
                                .setAmount(1000)
                        .setCurrency(Currency.RUB))
                )
                .setHeader(new PageHeader().setOnPage(3).setPage(0).setTotal(1));
        Mockito
                .when(accountClient.getAccounts(Mockito.any(), Mockito.any()))
                .thenReturn(mockResponse);

        //when
        GraphQLResponse graphQLResponse = graphQLTestTemplate.postForResource("get-accounts-request.graphql");

        //then

        assertTrue(graphQLResponse.isOk());
        assertEquals(1, graphQLResponse.get("$.data.accounts.header.total", Integer.class));

        String actual = graphQLResponse.getRawResponse().getBody();
        String expected = Resources.toString(Resources.getResource("get-accounts-response.json"), Charset.defaultCharset());
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.STRICT);
    }
}