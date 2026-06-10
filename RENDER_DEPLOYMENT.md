# RENDER DEPLOYMENT GUIDE

## Pre-Deployment Checklist ✅

- [x] Application builds successfully with `mvn clean install`
- [x] Spring Boot configured for dynamic PORT (reads from `PORT` environment variable)
- [x] Java 17 configured in pom.xml
- [x] JAR packaging enabled
- [x] `.gitignore` created
- [x] Test coverage verified (15 tests passing)
- [x] Application starts without errors
- [x] POST /bfhl endpoint ready
- [x] GET /health endpoint ready

---

## GitHub Repository Setup

### Files to Commit:
```
pom.xml
.gitignore
src/
VALIDATION_REPORT.md
```

### DO NOT Commit:
```
README.md (explicitly excluded per requirements)
target/ (excluded by .gitignore)
*.jar files (excluded by .gitignore)
.idea/ (excluded by .gitignore)
```

### Git Commands:
```bash
git add pom.xml
git add .gitignore
git add src/
git add VALIDATION_REPORT.md
git commit -m "BFHL API ready for Render deployment"
git push origin main
```

---

## Render Deployment Steps

### Step 1: Create New Service on Render
1. Go to https://dashboard.render.com/
2. Click **"New"** → **"Web Service"**
3. Select **"Build and deploy from a Git repository"**
4. Connect your GitHub account if not already connected
5. Select repository: **varun2005cmd/bfhl**
6. Choose main/master branch

### Step 2: Configure Build & Deployment

Fill in the following settings on Render:

| Setting | Value |
|---------|-------|
| **Service Name** | bfhl |
| **Runtime** | Java |
| **Build Command** | `mvn clean install -DskipTests` |
| **Start Command** | `java -jar target/bfhl-0.0.1-SNAPSHOT.jar` |
| **Environment** | Production |

### Step 3: Environment Variables

**DO NOT add any environment variables** - the application auto-detects Render's PORT.

Spring Boot will automatically read the `PORT` environment variable that Render sets.

### Step 4: Deploy

1. Click **"Create Web Service"**
2. Render will automatically:
   - Clone your repository
   - Run `mvn clean install -DskipTests`
   - Build the JAR file
   - Start the application
   - Assign a PORT automatically
   - Provide a public URL

3. Wait for deployment to complete (typically 3-5 minutes)

---

## Post-Deployment Verification

Once deployed, test your endpoints:

### Health Check
```bash
curl https://your-service-name.onrender.com/health
```

Expected Response:
```json
{
  "status": "UP"
}
```

### API Test (Example A)
```bash
curl -X POST https://your-service-name.onrender.com/bfhl \
  -H "Content-Type: application/json" \
  -d '{"data": ["a", "1", "334", "4", "R", "$"]}'
```

Expected Response:
```json
{
  "is_success": true,
  "user_id": "varun_21092005",
  "email": "varun.btech2023@sitpune.edu.in",
  "roll_number": "23070123149",
  "odd_numbers": ["1"],
  "even_numbers": ["334", "4"],
  "alphabets": ["A", "R"],
  "special_characters": ["$"],
  "sum": "339",
  "concat_string": "Ra"
}
```

---

## Render Deployment Configuration Summary

```
Build Command:    mvn clean install -DskipTests
Start Command:    java -jar target/bfhl-0.0.1-SNAPSHOT.jar
Port:             AUTO (Render sets PORT environment variable)
Java Version:     17
Package Type:     JAR
Spring Boot Ver:  3.2.5
```

---

## API Endpoints Available

| Method | Endpoint | Purpose |
|--------|----------|---------|
| POST | `/bfhl` | Main data processing endpoint |
| GET | `/health` | Health check endpoint |

---

## Troubleshooting

### If deployment fails:

1. **Check Render Logs**: Go to Render Dashboard → Select Service → Logs
2. **Verify Maven build**: Ensure `mvn clean install` runs successfully locally
3. **Check Java version**: Confirm Java 17 is available
4. **Verify PORT configuration**: Check `application.properties` has `server.port=${PORT:8080}`

### Common Issues & Solutions:

| Issue | Solution |
|-------|----------|
| Build fails with "port already in use" | Normal - Render will restart with correct PORT |
| 502 Bad Gateway | Wait 2-3 minutes for deployment, then refresh |
| Port configuration error | Verify `server.port=${PORT:8080}` in application.properties |

---

## Application Details

- **Framework**: Spring Boot 3.2.5
- **Language**: Java 17
- **Build Tool**: Maven
- **Package Type**: Executable JAR
- **Test Framework**: JUnit 5
- **Test Coverage**: 15 comprehensive tests
- **Response Format**: JSON with snake_case properties

---

## Next Steps

1. Push to GitHub:
   ```bash
   git add .
   git commit -m "BFHL API Render deployment ready"
   git push origin main
   ```

2. Deploy on Render using steps above

3. Share deployment URL in format:
   ```
   https://your-service-name.onrender.com/bfhl
   ```

---

**Status**: ✅ READY FOR PRODUCTION
