package com.cml.test.filestorage.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Response formed in case of unsuccessful request processing
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BadResponse implements Response {

    boolean success;
    String error;

}
