package com.gpholding.service;

import com.gpholding.domain.vo.ResponseResult;

public interface EmailService {
    ResponseResult sendMail(String toMail);
}
