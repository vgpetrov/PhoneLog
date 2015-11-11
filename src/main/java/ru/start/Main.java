package ru.start;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by vitaly on 02/03/15.
 *
 * Test
 */
@Deprecated
public class Main {

    public static void main(String[] args) {
        String password = "1qa@WS3ed";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    }
}
