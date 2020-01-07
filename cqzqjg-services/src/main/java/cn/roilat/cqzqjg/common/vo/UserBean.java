package cn.roilat.cqzqjg.common.vo;

/**
 * @program: zqjg
 * @description: 用户注册
 * @author: liujing
 * @create: 2020-01-07 09:38
 **/
public class UserBean {
    private String companyName;
    private String account;
    private String password;
    private String phoneNumber;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "companyName='" + companyName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
