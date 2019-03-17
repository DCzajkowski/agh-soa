package App.Domain;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CommentsService {
    private List<Comment> comments = new ArrayList<>();

    public void create(String name, String email, String comment) {
        comments.add(new Comment(name, email, comment));
    }

    public List<Comment> getComments() {
        return comments.stream()
            .sorted(Comparator.comparing(Comment::getDate).reversed())
            .collect(Collectors.toUnmodifiableList());
    }
}
