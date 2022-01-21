package com.cml.test.filestorage.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * File document class. Stores file name and size.
 * Field name can not be blank or null
 * Field size can not be null or be less than zero
 * Id is generated with java.util.UUID
 */
@Document(indexName = "file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {
    @Id
    private String id;
    @NotBlank(message = "File name can not be blank")
    private String name;
    @Min(value = 0, message = "File size can not be negative")
    private int size;
    private ArrayList<String> tags;
}
