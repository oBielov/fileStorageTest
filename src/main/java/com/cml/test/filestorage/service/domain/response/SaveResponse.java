package com.cml.test.filestorage.service.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Response formed in case of successful file save
 */
@AllArgsConstructor
@Getter
public class SaveResponse implements Response{

    String fileId;

}
