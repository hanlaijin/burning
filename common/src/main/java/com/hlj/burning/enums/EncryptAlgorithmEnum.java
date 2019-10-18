package com.hlj.burning.enums;

/**
 * Created by hanlaijin@xiaomi.com on 18-5-31.
 */
public enum  EncryptAlgorithmEnum {
    UNKNOW("", 0),
    DES("DES", 56),
    AES("AES", 128),
    RSA("RSA", 512);

    private String algorithm;
    private Integer bit;

    EncryptAlgorithmEnum(String algorithm, Integer bit) {
        this.algorithm = algorithm;
        this.bit = bit;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public Integer getBit() {
        return bit;
    }

    public static EncryptAlgorithmEnum getByAlgorithm(String algorithm) {
        for (EncryptAlgorithmEnum temp : EncryptAlgorithmEnum.values()) {
            if (temp.getAlgorithm().equals(algorithm)) {
                return temp;
            }
        }
        return UNKNOW;
    }

}
