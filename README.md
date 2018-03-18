# ENoteEpamLab
Info: project for the "Spring" course in the Epam laboratory.
# Endpoints Table
| URL Path                                 | Request methods | Result                                                           |
|------------------------------------------|-----------------|------------------------------------------------------------------|
| /user                                    | PUT             | save user                                                        |
| /user/all                                | GET             | get all users                                                    |        
| /user/{id}                               | GET,POST,DELETE | get/update/delete user                                           |
|                                          |                 |                                                                  |
| /tags/{userId}                           | GET/PUT         | get all tags from user/create tag for user                       |
| /tags/{userId}/{tagId}                   | POST/DELETE     | update uset tag/delete user tag                                  |
| /tags/{userId}/{notebookId}              | GET             | get all tags from user notebook                                  | 
| /tags/{userId}/{notebookId}/{noteId}     | GET             | get all tags from note                                           |
|                                          |                 |                                                                  |
| /notes/{userId}                          | GET             | get all notes from user                                          |
| /notes/{userId}/tag/{tagId}              | GET             | get all notes from user with tag                                 |
| /notes/{userId}/{notebookId}             | GET/PUT         | get all notes from user notebook/create note in user notebook    |
| /notes/{userId}/{notebookId}/{noteId}    | GET/POST/DELETE | get note/update note/delete note                                 |
| /notes/{userId}/{notebookId}/tag/{tagId} | GET             | get all notes from user notebook with tag                        |
|                                          |                 |                                                                  |
| /notebooks/{userId}                      | GET/PUT         | get all notebooks from user/create notebook to user              |
| /notebooks/{userId}/{notebookId}         | GET/POST/DELETE | get notebook from user/update user notebook/delete user notebook |
| /notebooks/{userId}/tag/{tagId}          | GET             | get all notebooks from user with tag                             |



# Database Scheme
![Database](image.png?raw=true "Database")
