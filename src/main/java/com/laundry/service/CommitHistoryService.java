package com.laundry.service;

import com.laundry.dto.CommitHistoryDTO;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitHistoryService {

    // Image dimension constants
    private static final int IMAGE_WIDTH = 1200;
    private static final int ROW_HEIGHT = 80;
    private static final int HEADER_HEIGHT = 100;
    private static final int PADDING = 20;
    private static final int MESSAGE_MAX_LENGTH = 80;
    private static final int MESSAGE_TRUNCATE_LENGTH = 77;

    /**
     * Fetch git commit history from the repository
     * @return List of CommitHistoryDTO objects
     */
    public List<CommitHistoryDTO> getCommitHistory() {
        List<CommitHistoryDTO> commits = new ArrayList<>();
        
        try {
            // Execute git log command
            ProcessBuilder processBuilder = new ProcessBuilder(
                "git", "log", "--all", 
                "--pretty=format:%H|%an|%ae|%ad|%s", 
                "--date=iso"
            );
            
            processBuilder.directory(new File(System.getProperty("user.dir")));
            Process process = processBuilder.start();
            
            // Use try-with-resources to ensure proper resource cleanup
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|", 5);
                    if (parts.length == 5) {
                        CommitHistoryDTO commit = new CommitHistoryDTO(
                            parts[0].substring(0, Math.min(8, parts[0].length())), // Short hash
                            parts[1],
                            parts[2],
                            parts[3],
                            parts[4]
                        );
                        commits.add(commit);
                    }
                }
            }
            
            process.waitFor();
            
        } catch (IOException e) {
            System.err.println("Error executing git command or reading output: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("Git process was interrupted: " + e.getMessage());
            e.printStackTrace();
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        
        return commits;
    }

    /**
     * Generate an image visualization of the commit history
     * @return ByteArrayOutputStream containing the generated image
     */
    public ByteArrayOutputStream generateCommitHistoryImage() {
        List<CommitHistoryDTO> commits = getCommitHistory();
        
        // Calculate image height based on number of commits
        int height = HEADER_HEIGHT + (commits.size() * ROW_HEIGHT) + PADDING;
        
        // Create buffered image
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        
        // Enable anti-aliasing for better text quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        // Background
        g2d.setColor(new Color(240, 248, 255));
        g2d.fillRect(0, 0, IMAGE_WIDTH, height);
        
        // Draw header
        g2d.setColor(new Color(41, 128, 185));
        g2d.fillRect(0, 0, IMAGE_WIDTH, HEADER_HEIGHT);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 32));
        g2d.drawString("Commit History - Features Overview", 50, 60);
        
        // Draw commits
        int y = HEADER_HEIGHT + PADDING;
        int index = 1;
        
        for (CommitHistoryDTO commit : commits) {
            // Alternate row colors
            if (index % 2 == 0) {
                g2d.setColor(new Color(255, 255, 255));
            } else {
                g2d.setColor(new Color(245, 245, 245));
            }
            g2d.fillRect(0, y, IMAGE_WIDTH, ROW_HEIGHT);
            
            // Draw border
            g2d.setColor(new Color(200, 200, 200));
            g2d.drawRect(0, y, IMAGE_WIDTH, ROW_HEIGHT);
            
            // Draw commit info
            g2d.setColor(new Color(52, 73, 94));
            
            // Commit hash
            g2d.setFont(new Font("Courier New", Font.BOLD, 14));
            g2d.drawString(commit.getHash(), 20, y + 25);
            
            // Author
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));
            g2d.drawString("Author: " + commit.getAuthor(), 20, y + 45);
            
            // Date (show only date part)
            String dateStr = commit.getDate();
            if (dateStr.length() > 10) {
                dateStr = dateStr.substring(0, 10);
            }
            g2d.drawString("Date: " + dateStr, 20, y + 65);
            
            // Message
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.setColor(new Color(41, 128, 185));
            String message = commit.getMessage();
            if (message.length() > MESSAGE_MAX_LENGTH) {
                message = message.substring(0, MESSAGE_TRUNCATE_LENGTH) + "...";
            }
            g2d.drawString(message, 350, y + 40);
            
            y += ROW_HEIGHT;
            index++;
        }
        
        g2d.dispose();
        
        // Convert to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "PNG", baos);
        } catch (IOException e) {
            System.err.println("Error writing PNG image to output stream: " + e.getMessage());
            e.printStackTrace();
        }
        
        return baos;
    }
}
