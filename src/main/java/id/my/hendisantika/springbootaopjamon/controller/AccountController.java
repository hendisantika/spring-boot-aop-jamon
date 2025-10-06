package id.my.hendisantika.springbootaopjamon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.14
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController {

    public final JdbcTemplate jdbcTemplate;

}
