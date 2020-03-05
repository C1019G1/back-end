package com.codegym.service;

import com.codegym.dao.entity.UserRank;

import java.awt.*;
import java.lang.reflect.Array;

public interface UserRankService {
    Iterable<UserRank> getAllRank();
}
