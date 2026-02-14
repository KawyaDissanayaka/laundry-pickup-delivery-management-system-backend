# Commit History Image Generation API - Usage Examples

## Overview
This feature provides REST API endpoints to fetch Git commit history and generate visual representations of all commits with their features.

## API Endpoints

### 1. Get Commit History as JSON

**Endpoint:** `GET /api/commits/history`

**Description:** Returns all Git commits as JSON array with commit details.

**Example Request:**
```bash
curl http://localhost:8080/api/commits/history
```

**Example Response:**
```json
[
  {
    "hash": "ddf93c18",
    "author": "copilot-swe-agent[bot]",
    "email": "198982749+Copilot@users.noreply.github.com",
    "date": "2026-02-14 06:53:07 +0000",
    "message": "Add commit history image generation feature with API endpoints"
  },
  {
    "hash": "fcfb8f78",
    "author": "Kawya Dissanayaka",
    "email": "kawyadissanayaka95@gmail.com",
    "date": "2026-02-14 11:56:53 +0530",
    "message": "Update README.md"
  },
  {
    "hash": "bcc0774b",
    "author": "Kawya Dissanayaka",
    "email": "kawyadissanayaka95@gmail.com",
    "date": "2026-02-14 10:17:25 +0530",
    "message": "Add screenshots to README for authentication and orders"
  }
]
```

---

### 2. Download Commit History Image

**Endpoint:** `GET /api/commits/history/image`

**Description:** Generates and downloads a PNG image showing the complete commit history.

**Example Request:**
```bash
# Download the image
curl -o commit-history.png http://localhost:8080/api/commits/history/image

# Or using wget
wget http://localhost:8080/api/commits/history/image -O commit-history.png
```

**Example with JavaScript (Frontend):**
```javascript
// Download image
fetch('http://localhost:8080/api/commits/history/image')
  .then(response => response.blob())
  .then(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'commit-history.png';
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
  });
```

---

### 3. View Commit History Image (Inline)

**Endpoint:** `GET /api/commits/history/image/view`

**Description:** Displays the commit history image directly in the browser.

**Example Usage:**
```html
<!-- Display in HTML img tag -->
<img src="http://localhost:8080/api/commits/history/image/view" 
     alt="Commit History" 
     style="width: 100%; max-width: 1200px;">
```

**Browser Access:**
Simply open in browser: `http://localhost:8080/api/commits/history/image/view`

---

## Image Output Features

The generated image includes:
- **Blue header** with "Commit History - Features Overview"
- **Commit hash** (short, 8 characters)
- **Author name** for each commit
- **Date** in ISO format
- **Commit message** (feature description)
- **Alternating row colors** for better readability
- **Professional styling** with borders and spacing

### Image Specifications:
- **Format:** PNG
- **Width:** 1200px
- **Height:** Dynamic (based on number of commits)
- **Row Height:** 80px per commit
- **Header Height:** 100px

---

## Integration Examples

### React Component Example:
```jsx
import React, { useState, useEffect } from 'react';

function CommitHistory() {
  const [commits, setCommits] = useState([]);
  const imageUrl = 'http://localhost:8080/api/commits/history/image/view';

  useEffect(() => {
    fetch('http://localhost:8080/api/commits/history')
      .then(response => response.json())
      .then(data => setCommits(data))
      .catch(error => console.error('Error:', error));
  }, []);

  return (
    <div>
      <h1>Commit History</h1>
      <img src={imageUrl} alt="Commit History Visualization" />
      
      <h2>Commit Details</h2>
      <ul>
        {commits.map(commit => (
          <li key={commit.hash}>
            <strong>{commit.hash}</strong> - {commit.message}
            <br />
            <small>by {commit.author} on {commit.date}</small>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default CommitHistory;
```

### Python Example:
```python
import requests
import json

# Get commit history as JSON
response = requests.get('http://localhost:8080/api/commits/history')
commits = response.json()

print("Commit History:")
for commit in commits:
    print(f"- {commit['hash']}: {commit['message']}")
    print(f"  Author: {commit['author']}")
    print(f"  Date: {commit['date']}\n")

# Download image
img_response = requests.get('http://localhost:8080/api/commits/history/image')
with open('commit-history.png', 'wb') as f:
    f.write(img_response.content)
print("Image saved as commit-history.png")
```

---

## Notes

- ✅ **No authentication required** - These are public endpoints
- ✅ **CORS enabled** - Can be accessed from frontend applications
- ✅ **Real-time data** - Always fetches latest commit history from Git
- ✅ **Automatic generation** - Image is generated on-demand for each request
- ⚠️ **Git required** - The application must be running in a Git repository

---

## Sample Image Output

See the generated image example:
https://github.com/user-attachments/assets/704e921a-1746-4f5f-9657-199dcd23d93e

---

## Troubleshooting

### Issue: Empty response or error
**Solution:** Ensure the application is running in a Git repository with commit history.

### Issue: Image not displaying
**Solution:** Check that Java's AWT/Graphics2D libraries are available in your environment.

### Issue: CORS errors
**Solution:** The endpoints are configured with CORS support for localhost:3000, 5173, and 5174. Update `CommitHistoryController.java` if you need additional origins.

---

## Future Enhancements

Potential improvements for this feature:
- Filter commits by date range
- Filter by author
- Customize image colors/styling
- Export in different formats (SVG, PDF)
- Add commit statistics
- Include branch visualization
- Add pagination for large histories
