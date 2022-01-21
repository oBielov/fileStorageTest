package com.cml.test.filestorage.service.domain.response;

import com.cml.test.filestorage.documents.File;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileListResponse {

    long total;
    List<File> page;

}
