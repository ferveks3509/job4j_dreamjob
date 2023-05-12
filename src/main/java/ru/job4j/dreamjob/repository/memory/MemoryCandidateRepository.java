package ru.job4j.dreamjob.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.repository.CandidateRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {
    private static final MemoryCandidateRepository INSTANCE = new MemoryCandidateRepository();

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Intern", "desc", LocalDateTime.now()));
        save(new Candidate(0, "Junior", "desc", LocalDateTime.now()));
        save(new Candidate(0, "Junior+", "desc", LocalDateTime.now()));
        save(new Candidate(0, "Middle", "desc", LocalDateTime.now()));
        save(new Candidate(0, "Middle+", "desc", LocalDateTime.now()));
        save(new Candidate(0, "Senior", "desc", LocalDateTime.now()));
    }

    public static MemoryCandidateRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(), (id, oldCandidate) -> new Candidate(
                oldCandidate.getId(),
                candidate.getName(),
                candidate.getDescription(),
                candidate.getCreationDate())) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
