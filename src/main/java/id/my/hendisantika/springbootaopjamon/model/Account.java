package id.my.hendisantika.springbootaopjamon.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.08
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class Account {
    Integer id;
    String accountNumber;
    String accountHolder;
    Double balance;
    LocalDate openingDate;
    String email;
}
