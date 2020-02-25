package com.demo.gateway.resolver.card;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.demo.gateway.model.graph.PageHeader;
import com.demo.gateway.model.graph.card.CardItem;
import com.demo.gateway.model.graph.card.CardPage;
import com.demo.gateway.resolver.account.CreditAccountResolver;
import com.demo.gateway.resolver.account.CurrentAccountResolver;
import com.demo.gateway.service.CardService;
import graphql.kickstart.execution.context.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.demo.gateway.resolver.loader.GraphQLContextBuilder.DL_NAME;

/**
 * @see CreditAccountResolver - parent
 * @see CurrentAccountResolver - parent
 */


@Component
@SuppressWarnings("unused")
@RequiredArgsConstructor
public class CardPageResolver implements GraphQLResolver<CardPage> {
    private final CardService cardService;

    public CompletableFuture<List<CardItem>> list(CardPage cardPage, DataFetchingEnvironment dfe){
        Optional<DataLoaderRegistry> dataLoaderRegistry = ((GraphQLContext) dfe.getContext())
                .getDataLoaderRegistry();

        if (dataLoaderRegistry.isPresent()){
            DataLoader<Integer, List<CardItem>> detailDataLoader =
                    dataLoaderRegistry.get().getDataLoader(DL_NAME);

            return detailDataLoader.load(cardPage.getAccountItem().getId());
        }

        throw new RuntimeException(String.format("Dataloader %s не найден.", DL_NAME));
    }

    public PageHeader header(CardPage cardPage){
        return new PageHeader();
    }


//    public List<CardItem> list(CardPage cardPage){
//        return cardService.getCards(cardPage.getAccountItem().getId());
//    }
}
