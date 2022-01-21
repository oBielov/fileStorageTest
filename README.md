# File Storage Test task

## Application description

Small REST service to store information about files. Have typical CRUD operations
and one GET endpoint with a bunch of optional parameters. Built around Elasticsearch and Spring Boot.

## Endpoints description

### POST "/file/save-file"
Saves file.\
Requires @RequestBody:\
{\
   "name": "file_name.ext"\
   "size" : 121231   //size in bytes\
   "tags" : ["tag1", "tag2", ...] // default tag generated automatically (MIME type of file). User can add additional tags\
}

### DELETE "/file/delete/{ID}"
Delete file by ID.\
Requires @PathVariable String id\
Returns error 404 if file with such id not found

### POST "file/{id}/tags"
Adds tags to file by file ID\
Requires @RequestBody:\
{\
   "id": "file id"\
   "tags" : ["tag1", "tag2", ...]\
}\
Returns error 404 if file with such id not found

### DELETE "file/{id}/tags"
Delete tags from file by file ID\
Requires @RequestBody:\
{\
   "id": "file id"\
   "tags" : ["tag1", "tag2", ...]\
}\
Returns error 404 if file with such id not found\
Returns error 400 if tags in file does not exists

### GET "file/"
Get page of files from Elasticsearch DB. Has some optional parameters:
##### file/?tags=tag1, tag2, tag3  — filter files by tags (exact coincidence);
##### file/?page=1&size=5 — paginagion parameters. Number of page (page) and quantity of elements displayed (size). Default parameters are page = 0, size = 10;
##### file/?q=ta — When exists apply a search over file name. So all files that have "ta" pattern in file name will be returned.

##Application parameters

Apllication needs Elasticsearch runned. Spring server port and Elasticsearch uri can be configured in resources/application.properties

