package example.swing.service;



import example.swing.model.UserInfo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class FileUserInfoRepository implements UserInfoRepository {
    private static final String INFO_SEPARATOR = ";";
    private final Path path;


    public FileUserInfoRepository(Path path) {
        this.path = path;
        System.out.println();
    }

    public void write(UserInfo info) throws IOException {
        Files.write(path, toWritebleInfo(info),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public List<UserInfo> findAll() throws IOException {
        if (!Files.exists(path)){
            return  Collections.emptyList();
        } else {
            return Arrays.stream(
                    Files.readString(path).split("\n"))
                    .map(this::toInfo)
                    .collect(Collectors.toList()
                    );
        }
    }

    private UserInfo toInfo(String line) {
        var array = line.split(INFO_SEPARATOR);
        return new UserInfo(array[1], array[0]);
    }

    private byte[] toWritebleInfo(UserInfo info) {
        return (info.getEmail() + INFO_SEPARATOR + info.getName() + "\n").getBytes(StandardCharsets.UTF_8);
    }
}
