package com.epris.homepage.activity.session.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SessionType {
    PRCASE(1, "PR Case Study"),
    ISSUE(2, "Issue Briefing"),
    TREND(3, "Trend Briefing");

    private final Integer id;
    private final String title;
}
