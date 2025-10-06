package id.my.hendisantika.springbootaopjamon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-aop-jamon
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 07/10/25
 * Time: 06.09
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookVolumeList(List<BookVolume> items) {
}
