package ru.job4j.dream.Store;

import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {
    private static final Store INST = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "developer",
                LocalDateTime.of(2021, 8, 15, 20, 00)));
        posts.put(2, new Post(2, "Middle Java Job", "developer",
                LocalDateTime.of(2021, 8, 16, 20, 00)));
        posts.put(3, new Post(3, "Senior Java Job", "developer",
                LocalDateTime.of(2021, 8, 16, 20, 00)));
    }

    public static Store instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
