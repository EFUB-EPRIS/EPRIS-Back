package com.epris.homepage.activity.network.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum NetworkType {
    NIGHT(1,"EPRIAN의 밤"),
    LECTURE(2, "Career Recure");

    private final Integer id;
    private final String title;
}
