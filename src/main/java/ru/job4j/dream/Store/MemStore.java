package ru.job4j.dream.Store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemStore implements Store {
    private static final MemStore INST = new MemStore();

    private static AtomicInteger POST_ID = new AtomicInteger();
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger();
    private static AtomicInteger USER_ID = new AtomicInteger();

    private  Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private  Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private  Map<Integer, User> users = new ConcurrentHashMap<>();



    @Override
    public Collection<User> findAllUsers() {
        return users.values();
    }

    @Override
    public void save(User user) {
        if (user.getId() == 0) {
            user.setId(USER_ID.incrementAndGet());
        }
        users.put(user.getId(), user);
    }


    @Override
    public User findByIdUser(int id) {
       return users.get(id);
    }

    @Override
    public User findByEmail(String email) {
        User user = null;
        for (User value : users.values()) {
            if (value.getEmail().equals(email)) {
                 user = value;
                 break;
            }
        }
        return user;
    }

    public static MemStore instOf() {
        return INST;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void save(Post post) {
        posts.values();
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void save(Candidate candidate) {
       candidates.values();
    }

    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    public void deleteCandidate(int id) {
        candidates.remove(id);
        String path = "c:\\images\\" + id;
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}