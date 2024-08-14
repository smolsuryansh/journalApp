package com.hammy.journalApp.service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import com.hammy.journalApp.entity.User;

import java.util.stream.Stream;

public class UsersArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("shyam").password("shyam")),
                Arguments.of(User.builder().userName("suraj").password("suraj"))
        );
    }
}
