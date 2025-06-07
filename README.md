# ViniBank-BE

ViniBank-BE is a Kotlin-based backend service that supports a secure signup and authentication flow using Spring Boot. It includes a dynamic SDUI (Server-Driven UI) engine to guide users through onboarding screens, and uses Diffie-Hellman key exchange for session-based encryption.

---

## 🚀 Features

- 🔐 Encrypted communication via Diffie-Hellman key exchange
- 👤 Secure user authentication
- 📄 Server-driven UI flow with stages:
  - Email
  - Personal Info
  - Password
  - Success
- ❌ Session-based logout
- 💬 Error handling with status codes

---

## 📦 Endpoints

### `POST /initialize`
Initializes a secure session via DH key exchange.

**Headers:**
- `publicKey`: Base64 encoded client public key

**Returns:**
- `sessionId`: unique session ID
- `publicKey`: backend public key

---

### `POST /authenticate`
Authenticates a user.

**Headers:**
- `sessionId`
- `iv`

**Body:**
Base64-encoded, AES-encrypted JSON:
```json
{
  "email": "bob@example.com",
  "password": "secure456"
}
