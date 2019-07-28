package com.ilinli.neighbourhoodhelpwebsite.service;

import com.ilinli.neighbourhoodhelpwebsite.domain.vo.ResponseResult;

public interface RequirementsService {

    ResponseResult getPublishRequirements(Integer id,Integer isPerson);
}
