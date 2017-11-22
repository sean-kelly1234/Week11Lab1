/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import dataaccess.NotesDBException;
import dataaccess.UserDB;
import domainmodel.*;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author awarsyle
 */
public class AccountService {
    
    public User checkLogin(String username, String password, String path) {
        User user;
        
        UserDB userDB = new UserDB();
        try {
            user = userDB.getUser(username);
            
            if (user.getPassword().equals(password)) {
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, 
                        "A user logged in: {0}",
                        username);
                try {
                    //WebMailService.sendMail(user.getEmail(), "NotesKeepr Logged in", "<h2>Congrats!  You just loggedin successfully.</h2>" , true);
                    
                    HashMap<String, String> contents = new HashMap<>();
                    
                    contents.put("firstname", user.getFirstname());
                    contents.put("date", (new java.util.Date()).toString());
                    
                    String template = path + "/emailtemplates/login.html";
                    WebMailService.sendMail(user.getEmail(), "NotesKeepr Login", template, contents);
                    
                } catch (Exception ex) {
                    Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
                }
                return user;
            }
            
        } catch (NotesDBException ex) {
        }
        
        return null;
    }
    
    public void resetPassword(String email, String url, String path){
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        
        try {
            UserDB db = new UserDB();
            User user = db.getWithEmail(email);
            user.setResetPasswordUUID(uuid);
            db.update(user);
            HashMap<String, String> contents = new HashMap<>();

            contents.put("firstname", user.getFirstname());
            contents.put("lastname", user.getLastname());
            contents.put("username", user.getUsername());
            contents.put("link", link);

            String template = path + "/emailtemplates/resetpassword.html";
            WebMailService.sendMail(user.getEmail(), "NotesKeepr Reset Password", template, contents);

        } catch (Exception ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean changePassword(String uuid, String password){
        UserService us = new UserService();
        try {
            User user = us.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUUID(null);
            UserDB ur = new UserDB();
            ur.update(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
