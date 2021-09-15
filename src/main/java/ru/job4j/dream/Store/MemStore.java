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

    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private Map<Integer, User> users = new ConcurrentHashMap<>();



    public static MemStore instOf() {
        return INST;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            candidate.setId(CANDIDATE_ID.incrementAndGet());
        }
        candidates.put(candidate.getId(), candidate);
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
    public Post findById(int id) {
        return posts.get(id);
    }

    @Override
    public Candidate findByIdCandidate(int id) {
        return candidates.get(id);
    }

    @Override
    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    @Override
    public Collection<User> findAllUsers() {
        return users.values();
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

    @Override
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