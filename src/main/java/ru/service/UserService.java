package ru.service;

import org.codehaus.jackson.annotate.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.db.entities.User;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by vitaly on 13/04/15.
 */
@Service("userService")
public class UserService extends BaseService {

    private static class RecaptchaResponse {
        @JsonProperty("success")
        private boolean success;
        @JsonProperty("error-codes")
        private Collection<String> errorCodes;
    }

    public static final String GRECAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    public Optional<User> save(@NotNull String name, @NotNull String password, @NotNull String grecaptcha) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("secret", "6LdRxRATAAAAACWMMoJEhcY_FjITADVZlgCS33-g");
        map.add("response", grecaptcha);

        RecaptchaResponse recaptchaResponse = restTemplate.postForObject(GRECAPTCHA_URL, map, RecaptchaResponse.class);
        if (recaptchaResponse.success) {
            User user = new User(name, password);
            User save = getUserDao().save(user);
            return Optional.ofNullable(save);
        } else {
            return Optional.empty();
        }

    }
}
