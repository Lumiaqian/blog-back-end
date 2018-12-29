package com.caoyuqian.blog.pojo;

/**
 * @author qian
 * @version V1.0
 * @Title: Device
 * @Package: com.caoyuqian.blog.pojo
 * @Description: TOTO
 * @date 2018-12-29 10:47
 **/
public class Device {
    private String ip;
    private String deviceManufacturer;
    private String deviceType;
    private String os;
    private String osName;
    private String osVersion;
    private String borderGroup;
    private String borderName;
    private String borderType;
    private String browserManufacturer;
    private String browserVersion;
    private String browserEngine;

    @Override
    public String toString() {
        return "Device{" +
                "ip='" + ip + '\'' +
                ", deviceManufacturer='" + deviceManufacturer + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", os='" + os + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", borderGroup='" + borderGroup + '\'' +
                ", borderName='" + borderName + '\'' +
                ", borderType='" + borderType + '\'' +
                ", browserManufacturer='" + browserManufacturer + '\'' +
                ", browserVersion='" + browserVersion + '\'' +
                ", browserEngine='" + browserEngine + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceManufacturer() {
        return deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getBorderGroup() {
        return borderGroup;
    }

    public void setBorderGroup(String borderGroup) {
        this.borderGroup = borderGroup;
    }

    public String getBorderName() {
        return borderName;
    }

    public void setBorderName(String borderName) {
        this.borderName = borderName;
    }

    public String getBorderType() {
        return borderType;
    }

    public void setBorderType(String borderType) {
        this.borderType = borderType;
    }

    public String getBrowserManufacturer() {
        return browserManufacturer;
    }

    public void setBrowserManufacturer(String browserManufacturer) {
        this.browserManufacturer = browserManufacturer;
    }

    public String getBrowserVersion() {
        return browserVersion;
    }

    public void setBrowserVersion(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    public String getBrowserEngine() {
        return browserEngine;
    }

    public void setBrowserEngine(String browserEngine) {
        this.browserEngine = browserEngine;
    }
}
