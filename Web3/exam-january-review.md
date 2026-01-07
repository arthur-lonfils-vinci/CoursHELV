## Backend
### [augmentGraphqlSchema.ts](exercises/exam-january/backend/src/api/comment/augmentGraphqlSchema.ts)

1. line 29 :
   - getting all comments without checking ownership can be a security issue (using a dedicated function (for example):  
   ```js 
   assertOwnership(comment: {authorId: number}, userId: number)
   ``` 
2. line 40 :
   - variable `user` not used.
3. line 41 :
   - need to check expense ownership (only fetch expense where authorId is the userId).
4. line 47 (optional):
   - use the `expense` variable we fetched instead of the args to get the id -> `expense.id`
5. line 64 :
   - need to check if the expense we create a comment for is own by the user OR is participant (securtity issue) not needed if anyone can add comment (but strange behaviour)
   - need to check if the `expense` exist or not before trying to create comment.
6. line 79 :
   - variable `user` not used.
7. line 81 :
   - need to check (in DB fetch) if the `authorId` is the `current user`, not everyone can update the comment (security issue).
8. line 89 (optional) :
   - use `comment` variable we fetch to get the id -> `comment.id`.
9. line 102 :
   - need to check if the `comment` we delete is authored by the current user.
10. line 104 :
    - never return a "static" value like `return true`, return the function itself ->
    ```js
    return commentRepository.deleteComment(...)
    ```

### [commentController.ts](exercises/exam-january/backend/src/api/comment/commentController.ts)

1. line 6-13 :
   - never manage exception in the controller, that's the repository role (remove `try {} catch() {}`).
2. line 10 :
   - never return manually an `INTERNAL_SERVER_ERROR - 500` in an http request. An internal should not be "hardcoded" -- use `NOT_FOUND` for example.
3. line 19-20 :
   - should manage the case of expense not found.
4. line 27 :
   - Bad return of error code `StatusCodes.OK` instead of `StatusCodes.NOT_FOUND`.
5. line 35 :
   - missing `await` in `async` function. can cause issue with never defined returned value.
   - should manage the case of expense not found.
6. line 41 :
   - should handle the case if creation failed, we only return a successfull code.
7. line 53 (same as 41) :
   - should handle the case if update failed, we only return a successfull code.
8. line 67 :
   - should handle if the comment is not found, we only return a successfull code.

### [commentRepository.ts](exercises/exam-january/backend/src/api/comment/commentRepository.ts)

1. line 16-17 :
   - should add an `include : { author: true, expense: true }`, as the graphql schema defined a relation to table (not a simple id). (Frontend type `Comment` expect a type `User` for `author`).

### [commentRouter.ts](exercises/exam-january/backend/src/api/comment/commentRouter.ts)

1. all file (optional):
   - can use a dedicated Middleware (for auth or special access) in this Router file (or a "main" middleware in `server.ts`)

### [server.ts](exercises/exam-january/backend/src/server.ts)

1. all file (optional):
   - can use a dedicated Middleware (for auth or special access) as a "main" security middleware.


## Frontend

For all frontend ->
No errors management, from the code logic, to the UI.

### [CommentForm.tsx](exercises/exam-january/frontend/src/components/CommentForm.tsx)

1. line 8 (optional) :
   - can add a type to `useState` -> `... = userState<string>('')`
2. line 10-14 :
   - check if `content` is empty or not, can use `.trim()` for example. (Don't send empty comment)

### [CommentList.tsx](exercises/exam-january/frontend/src/components/CommentList.tsx)

1. line 6 & 7:
   - change `commentId` to `number` instead of `string`.
2. line 12 (optional) :
   - can add a type to `useState` -> `... = userState<string>('')`
3. line 28 :
   - use a `useEffect() {} [comments, editingId]` (for example) to handle the editing changes more efficiently (respect React State usage), can limit UI/UX bugs. When deleting a comment for example (`comments` will change so we need to "fix" the current state).
4. line 33
   - should be an `async/await` function (edit is an API call)
   - change `commentId` type as explained.
5. line 55-67 :
   - should only display both the `delete`and `edit` button (UI) if the component has the function passed (here is a partial example of fix):
     ```tsx
     {
       onEdit && editingId !== comment.id && (
         <>
           ...
         </>
       );
     }
     ```
6. line 83 :
   - prefer using 
   ```js
   onClick={() => handleCancel()}
   ```
   so React handle better the function each render.
7. line 51 :
   - for now `comment.author.name` will return an error `trying to read undefined values 'name' for 'author'` because backend send `authorId` not the relation (see line 46 of this Markdown).

### [Component.tsx](exercises/exam-january/frontend/src/pages/ExpenseDetails/Component.tsx)

1. line 7 :
   - when using "custom" type, specify it when using useState for better type handling -> 
   ```js
   const [comments, setComments] = useState<Comments[]>(initialComments);
   ```
2. line 9 (optional - not sure) :
   - using a useEffect for the `sharePerParticipants` calcul, with expense and/or expense.participants as dependencies (if expense is updated).
3. line 11 :
   - function need to be `async` (we are calling ApiClient).
4. line 12 :
   - need to add `await` before `ApiClient` (return a Promise).
5. line 14 :
   - using an hardcoded/static value is prohibited when doing backend request, use of "Magic numbers". We could fetch something that the user is not a part of (security/privacy issue), or it could result in a backend error (system error). We should use the current user id (or maybe participants).
6. line 21 & 23 :
   - `commentId` should be typed/asked as number (remove parseInt() in request).
7. line 26 :
   - To avoid errors in variable management, memory and across states, use 
   ```js
   setComments((prev) =>  prev.map((c) => (c.id === id ? updated : c)));
   ```
   instead of updating directly the variable `comments`.
8. line 29 & 30 :
   - function need to be `async`, and add `await` before the `ApiClient` call (returns a Promise).
   - `commentId` should be passed as number.
9. line 31 (same as line 26) :
   - To avoid errors in variable management, memory and across states, use 
   ```js
   setComments((prev) => prev.filter((c) => c.id !== id));
   ```
   instead of updating directly the variable `comments`.
10. line 79 :
    - never use `any` as a type, this can orient to type mistake across components. (need to add the type before the useState as mentionned before).

### [loader.ts](exercises/exam-january/frontend/src/pages/ExpenseDetails/loader.ts)

1. line 42-43 :
   - need to add `await`, this is an async function that use `ApiClient` (return a promise).

### [Comment.ts](exercises/exam-january/frontend/src/types/Comment.ts)

1. line 4 :
   - use `number` instead of `string` for the `id`
2. line 8 :
   - as explained earlier, we are waiting a type `User` for `author` but we receive an `authorId` instead, either change frontend, or change backend.
