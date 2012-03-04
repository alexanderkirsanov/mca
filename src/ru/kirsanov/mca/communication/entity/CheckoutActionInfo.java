package ru.kirsanov.mca.communication.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * User: akirsanov
 * Date: 02.03.12 1:35
 */
@XmlRootElement
public class CheckoutActionInfo {
    public String result;
    public String project;

    public CheckoutActionInfo(String result, String project) {
        this.result = result;
        this.project = project;
    }

    public CheckoutActionInfo() {
    }
}
