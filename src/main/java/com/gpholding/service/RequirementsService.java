package com.gpholding.service;

import com.gpholding.domain.vo.ResponseResult;

public interface RequirementsService {

    ResponseResult getPublishRequirements(Integer id,Integer isPerson);
}
