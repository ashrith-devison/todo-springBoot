package edu.projects.ashrith.todo.exceptions;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ActivityLogs {
    private String FileName;
    public ActivityLogs(String FileName) {
        if(FileName.equals("ApiResponse.txt")){
            this.FileName = "logRecords/ApiResponse.txt";
        }
        else if (FileName.equals("ApiRequests.txt")) {
            this.FileName = "logRecords/ApiRequests.txt";
        }
        else{
            this.FileName = "logRecords/ApiErrors.txt";
        }

    }
    public void logActivity(String User, String Operation) {
        try(FileWriter fileWriter = new FileWriter(FileName, true)) {
            String timeStamp = LocalDateTime.now(ZoneId.of("Asia/Kolkata")).toString();
            String LogMessage = String.format("[%s] : User %s -> %s the task\n", timeStamp, User, Operation);
            fileWriter.write(LogMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void logActivity(String User, String Operation, String Message){
        try(FileWriter fileWriter = new FileWriter(FileName, true)) {
            String timeStamp = LocalDateTime.now(ZoneId.of("Asia/Kolkata")).toString();
            String LogMessage = String.format("[%s] : User %s -> %s : %s\n", timeStamp, User, Operation, Message);
            fileWriter.write(LogMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
