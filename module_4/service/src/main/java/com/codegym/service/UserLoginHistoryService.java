package com.codegym.service;

import java.util.Date;
import java.util.Optional;

public interface UserLoginHistoryService {
    Optional<Date> findLastLoginByUserId(Long userId);
}
