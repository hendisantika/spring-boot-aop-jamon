package id.my.hendisantika.springbootaopjamon.controller;

import id.my.hendisantika.springbootaopjamon.annotation.MethodMonitor;
import id.my.hendisantika.springbootaopjamon.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    @MethodMonitor(name = "account.list", uri = "/account")
    public ResponseEntity<List<Account>> account() {
        List<Account> account = jdbcTemplate.query("SELECT * FROM account", new BeanPropertyRowMapper(Account.class));
        return ResponseEntity.ok(account);
    }
}
