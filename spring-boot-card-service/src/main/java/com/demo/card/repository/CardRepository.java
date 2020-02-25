package com.demo.card.repository;

import com.demo.card.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByAccountId(Integer limitId);
    Page<Card> findAllByAccountIdIn(List<Integer> limitIdList, Pageable pageable);
    List<Card> findAllByAccountIdIn(List<Integer> limitIdList);
}
