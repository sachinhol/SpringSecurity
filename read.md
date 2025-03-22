# JWT Authentication Flow in Spring Security

## 📌 Step-by-Step Execution

### 1️⃣ User Login Request
- The client (Postman, browser, frontend) sends a **login request** with credentials.
- Example:
  ```http
  POST /login
  Content-Type: application/json
  {
      "email": "user@example.com",
      "password": "password123"
  }
  ```

### 2️⃣ Authentication Process
- `AuthenticationManager` verifies the credentials.
- Calls `UserDetailsService.loadUserByUsername(email)` to fetch user details from the DB.
- Compares the provided password with the stored **hashed password** using `BCryptPasswordEncoder`.

### 3️⃣ JWT Token Generation
- If authentication is successful:
   - A **JWT token** is created using `JwtService`.
   - The token is signed with a **secret key**.
   - Example:
     ```java
     String token = Jwts.builder()
         .setSubject(user.getEmail())
         .setIssuedAt(new Date())
         .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
         .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
         .compact();
     ```
- The server responds with the token:
  ```json
  {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

### 4️⃣ Client Uses JWT for Authentication
- The client includes the JWT in the **Authorization header** when accessing protected APIs.
- Example:
  ```http
  GET /order
  Authorization: Bearer <JWT_TOKEN>
  ```

### 5️⃣ JWT Validation in Filter
- `JwtAuthenticationFilter` extracts the token from the request.
- The token is **validated**:
   - Signature verification ✅
   - Expiration check ✅
   - User existence check ✅
- If valid, `SecurityContextHolder` is updated:
  ```java
  UsernamePasswordAuthenticationToken authToken =
      new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
  SecurityContextHolder.getContext().setAuthentication(authToken);
  ```

### 6️⃣ Access to Protected Endpoints
- If authentication is successful, the request is forwarded to the controller.
- Otherwise, **401 Unauthorized** is returned.

## 🔥 Debugging & Fixing Issues
✅ Ensure `JwtAuthenticationFilter` is registered in `SecurityConfig`.
✅ Check if `loadUserByUsername` is correctly fetching user data.
✅ Verify the secret key is **at least 256 bits**.
✅ Use `jwt.io` to debug token issues.

---
💡 **Now you're all set with JWT authentication in Spring Boot!** 🚀