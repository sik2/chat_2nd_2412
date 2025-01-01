package com.ll.chat_pr.global.ut;

import lombok.SneakyThrows;

public class Ut {
    public static class thread {

        @SneakyThrows
        public static void sleep(long millis) {
            Thread.sleep(millis);
        }
    }
}
