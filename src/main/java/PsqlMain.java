import ru.job4j.dream.Store.PsqlStore;
import ru.job4j.dream.Store.Store;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;

public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.save(new Post(0, "Java Job"));
        for (Post post : store.findAllPosts()) {
            System.out.println(post.getId() + " " + post.getName());
        }
        System.out.println("Find by id post: " + store.findById(42).getId() + " " + store.findById(42).getName());
        store.save(new Candidate(0, "candidate"));
        for(Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate.getId() + " " + candidate.getName());
        }
        System.out.println("find candidate by id: " + store.findByIdCandidate(47).getId() + " " + store.findByIdCandidate(47).getName());
        store.save(new User(0,"user", "email", "password"));
        for (User user : store.findAllUsers()) {
            System.out.println(user.getId() + " " + user.getName());
        }
        System.out.println("find user by id: " + store.findByIdUser(1).getId() + " " + store.findByIdUser(1).getName());
    }
}
