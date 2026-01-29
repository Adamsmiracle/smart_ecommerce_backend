### Viewing and Using Swagger (OpenAPI) from the frontend

Last updated: 2026-01-28 09:36

This backend already includes Springdoc OpenAPI UI.

URLs (default when app runs locally):
- Swagger UI (interactive docs in the browser): http://localhost:8080/swagger-ui.html
- OpenAPI JSON schema (for frontend consumption): http://localhost:8080/api-docs

Notes
- Paths are configured in application.properties:
  - springdoc.api-docs.path=/api-docs
  - springdoc.swagger-ui.path=/swagger-ui.html
- If you deploy behind a reverse proxy or change the server port, adjust the host/port accordingly.

Cross-origin (CORS)
- The project enables CORS for API endpoints and the OpenAPI resources so a separate frontend app (e.g., on http://localhost:3000) can fetch /api-docs without CORS errors.

Consuming the OpenAPI spec in a React app (example)
```
// Fetch the OpenAPI JSON
fetch('http://localhost:8080/api-docs')
  .then(res => res.json())
  .then(spec => console.log(spec))
  .catch(console.error);
```

Embedding Swagger UI in React using swagger-ui-react
```
// 1) npm install swagger-ui-react swagger-ui --save
// 2) In a React component:
import SwaggerUI from 'swagger-ui-react';
import 'swagger-ui-react/swagger-ui.css';

export default function ApiDocs() {
  return <SwaggerUI url="http://localhost:8080/api-docs" />;
}
```

Troubleshooting
- If Swagger UI 404s, confirm the app is running and the springdoc dependency is present.
- If the OpenAPI JSON shows a CORS error in the browser console, ensure the backend is up-to-date with the provided CORS configuration and that you are using the correct URL/port.
