package com.cml.test.filestorage.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * Response formed in case of successful request processing
 */
@AllArgsConstructor
@Getter
public class OkResponse implements Response{

    boolean success;

}
