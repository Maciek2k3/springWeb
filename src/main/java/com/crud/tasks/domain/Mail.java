package com.crud.tasks.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
@AllArgsConstructor
public class Mail {
    @NonNull
    private final String mailTo;

    private String toCc;

    @NonNull
    private final String subject;

    @NonNull
    private final String message;

}

