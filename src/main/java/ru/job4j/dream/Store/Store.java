package ru.job4j.dream.Store;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

import java.util.Collection;

public interface Store<T> {
    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    Collection<User> findAllUsers();

    void save(Post post);

    void save(Candidate candidate);

    void save(User user);

    Post findById(int id);

    Candidate findByIdCandidate(int id);

    User findByIdUser(int id);

    User findByEmail(String email);

    void deleteCandidate(int id);
}
