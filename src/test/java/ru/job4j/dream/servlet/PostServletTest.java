package ru.job4j.dream.servlet;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.Store.MemStore;
import ru.job4j.dream.Store.PsqlStore;
import ru.job4j.dream.Store.Store;
import ru.job4j.dream.model.Post;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {


    @Test
    public void whenCreatePost() throws IOException, ServletException {
        Store<Post> store = MemStore.instOf();

        PowerMockito.mockStatic(PsqlStore.class);
        PowerMockito.when(PsqlStore.instOf()).thenReturn(store);

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        PowerMockito.when(req.getParameter("id")).thenReturn("0");
        PowerMockito.when(req.getParameter("name")).thenReturn("n");
        PowerMockito.when(req.getParameter("description")).thenReturn("d");

        new PostServlet().doPost(req, resp);

        Post result = store.findAllPosts().iterator().next();
        assertThat(result.getName(), Is.is("n"));
        assertThat(result.getDescription(), Is.is("d"));
    }
}