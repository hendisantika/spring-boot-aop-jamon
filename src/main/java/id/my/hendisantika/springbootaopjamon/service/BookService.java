package id.my.hendisantika.springbootaopjamon.service;

import id.my.hendisantika.springbootaopjamon.model.BookVolumeList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.10
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class BookService {
    private final RestTemplate restTemplate;

    public BookVolumeList books() {
        return restTemplate
                .getForObject(
                        "https://www.googleapis.com/books/v1/volumes?q=sex&max_results=2",
                        BookVolumeList.class
                );
    }
}
