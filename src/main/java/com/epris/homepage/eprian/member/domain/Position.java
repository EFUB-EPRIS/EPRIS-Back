package com.epris.homepage.eprian.member.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Position {
    PLANNING(1,"기획부"),
    PR(2, "홍보부"),
    OPERATION(3, "운영부");

    private final Integer id;
    private final String title;
}
