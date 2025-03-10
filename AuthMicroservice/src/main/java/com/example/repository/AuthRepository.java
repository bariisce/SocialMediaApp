package com.example.repository;

import com.example.entity.Auth;

public interface AuthRepository extends MyGenericRepo<Auth,Long> {

    Boolean existsByUsernameAndPassword(String username, String password);
}
