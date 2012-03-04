package ru.kirsanov.mca.communication.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: akirsanov
 * Date: 29.02.12 23:52
 */
@XmlRootElement
public class RepositoryInfo {
    public String url;
    public String projectName;
    public String userName;
    public String password;

    public RepositoryInfo(String url, String projectName, String userName, String password) {
        this.url = url;
        this.projectName = projectName;
        this.userName = userName;
        this.password = password;
    }

    public RepositoryInfo() {

    }

    @Override
    public String toString() {
        return "RepositoryInfo{" +
                "url='" + url + '\'' +
                ", projectName='" + projectName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
