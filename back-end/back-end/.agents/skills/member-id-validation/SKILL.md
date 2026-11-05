---
name: member-id-validation
description: 'Implement or update member ID validation in Java and Spring Boot projects. Use when adding, reviewing, or changing username/member ID rules with Jakarta Bean Validation.'
---

# Member ID Validation

Apply the following member ID rules consistently:

- Length is 5 to 20 characters.
- The first character must be an ASCII English letter (`A-Z` or `a-z`).
- Include at least one uppercase English letter and one lowercase English letter.
- Include at least one `!` or `_`.
- Allow only ASCII English letters, digits, `!`, and `_`. Reject whitespace, Korean, and every other character.

## Implementation

1. Inspect the existing request DTO, controller, and project dependencies. Preserve the project's naming and validation-message conventions.
2. Apply `@NotBlank`, `@Size(min = 5, max = 20)`, and this pattern to the member ID field:

   ```java
   @Pattern(
       regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[!_])[A-Za-z][A-Za-z0-9!_]*",
       message = "회원 ID 규칙에 맞지 않습니다."
   )
   ```

3. Ensure the project has `spring-boot-starter-validation` rather than only the Jakarta Validation API, so a validation provider is available at runtime.
4. For request-body validation, apply `@Valid` to the controller's `@RequestBody` parameter. Do not add or alter endpoint behavior unless the task requires it.
5. Run the narrowest relevant test or Gradle compile task and report the result.

The pattern's lookaheads require uppercase, lowercase, and at least one allowed special character. Its character class rejects all characters outside the allowed set, and its first character position requires an English letter.
