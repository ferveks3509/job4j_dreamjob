package ru.job4j.dreamjob.repository.memory;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Vacancy;
import ru.job4j.dreamjob.repository.VacancyRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryVacancyRepository implements VacancyRepository {
    private static final MemoryVacancyRepository INSTANCE = new MemoryVacancyRepository();

    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "desc", LocalDateTime.now()));
        save(new Vacancy(0, "Junior Java Developer", "desc", LocalDateTime.now()));
        save(new Vacancy(0, "Junior+ Java Developer", "desc", LocalDateTime.now()));
        save(new Vacancy(0, "Middle Java Developer", "desc", LocalDateTime.now()));
        save(new Vacancy(0, "Middle+ Java Developer", "desc", LocalDateTime.now()));
        save(new Vacancy(0, "Senior Java Developer", "desc", LocalDateTime.now()));
    }

    public static MemoryVacancyRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(), (id, oldVacancy) -> new Vacancy(
                oldVacancy.getId(),
                vacancy.getTitle(),
                vacancy.getDescription(),
                vacancy.getCreationDate())) != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}