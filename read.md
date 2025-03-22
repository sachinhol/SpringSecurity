# Flow of loadUserByUsername(String email) in Spring Security

## Step-by-Step Execution Flow

1. **User Sends Request:**
    - The client (Postman, browser, or frontend) sends a request with **Basic Authentication** headers.
    - Example:
      ```http
      Authorization: Basic base64(username:password)
      ```

2. **Spring Security Intercepts the Request:**
    - The request goes through the `BasicAuthenticationFilter`, which extracts the **username (email)** and **password** from the header.

3. **UserDetailsService is Triggered:**
    - `CustomUserDetailsService.loadUserByUsername(email)` is called by Spring Security.
    - This method fetches user details from the database.

4. **CustomUserDetailsService Implementation:**
    - Inside `loadUserByUsername(email)`, the user is fetched from the repository:
      ```java
      @Service
      public class CustomUserDetailsService implements UserDetailsService {
          @Autowired
          private UserRepository userRepository;
 
          @Override
          public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
              User user = userRepository.findByEmail(email)
                  .orElseThrow(() -> new UsernameNotFoundException("User not found"));
              return new org.springframework.security.core.userdetails.User(
                  user.getEmail(),
                  user.getPassword(),
                  Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
              );
          }
      }
      ```

5. **AuthenticationManager Validates Password:**
    - Spring Security automatically compares the provided password (from the request) with the **hashed password** from the database using `BCryptPasswordEncoder`.
    - If passwords match, authentication is successful.

6. **SecurityContextHolder Stores Authentication:**
    - After authentication, Spring Security stores the authenticated user in the `SecurityContextHolder`.

7. **Access to Secured Endpoint:**
    - If the user is authenticated and has required **roles/permissions**, they can access the protected API (`/order/createorder`).
    - If authorization fails, a **403 Forbidden** error is returned.

## Debugging Tips
- If authentication fails, check logs to see if `loadUserByUsername` is being called.
- Ensure the password stored in the database is hashed using `BCryptPasswordEncoder`.
- Check if `UserDetailsService` is properly registered as a `@Bean` in `SecurityConfig`.

