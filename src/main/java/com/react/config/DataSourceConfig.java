package com.react.config;


import com.react.utils.CommonApiUtils;
import com.react.utils.EncryptDecryptUtil;
import com.react.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
@Configuration
public class DataSourceConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private EncryptDecryptUtil encryptDecryptUtil;
    @Autowired
    private FileUtil fileUtil;
    @Autowired
    CommonApiUtils commonApiUtls;

    @Bean
    public DataSource dataSource() throws Exception {
        String password = getDBPassword();
        final DataSource dataSource = DataSourceBuilder
                .create()
                .driverClassName(driver)
                .url(jdbcUrl)
                .username(userName)
                .password(password)
                .build();
        return dataSource;
    }

    private String getDBPassword() throws Exception {
        //if we are use the  Hashicorp Vault or any secret management tool then we can fetch the password from there
        Map<String, Pair<Boolean, String>> vaultData = commonApiUtls.getHashiCorpValutSecrets(false, false);
        String passwordFromFile = fileUtil.getFileDataIfFile(password, true);
        passwordFromFile = passwordFromFile != null ? passwordFromFile : password;
        passwordFromFile = encryptDecryptUtil.decrypt(passwordFromFile);
        passwordFromFile = userName != null && vaultData.get("username") != null &&
                vaultData.get("username").getLeft() ?
                vaultData.get("username").getRight() : passwordFromFile;

        log.info("Decrypted DB Password: {}", passwordFromFile);
        return passwordFromFile;
    }

}
