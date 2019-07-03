package com.softserve.demo.util;

import com.softserve.demo.model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.time.LocalDateTime;

public final class SignatureGenerator {

    private SignatureGenerator() {
    }

    public static String getSignature(User user) {
        return DigestUtils.md5Hex(user.getUsername() + user.getEmail() + LocalDateTime.now());
    }
}
