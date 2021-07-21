package example.swing;



import example.swing.form.UserForm;
import example.swing.service.FileUserInfoRepository;
import example.swing.service.UserInfoRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ApplicationForm {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:\\Users\\recie\\Desktop\\FormSwing-1.0\\src\\main\\resources\\UserInfo\\Info.dat");
        UserInfoRepository writer = new FileUserInfoRepository(path);
        new UserForm(writer);
    }
}
