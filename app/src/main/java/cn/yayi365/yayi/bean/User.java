package cn.yayi365.yayi.bean;

/**
 * Created by one_v on 2016/3/4 15:21.
 */
public class User {

    private int id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String fullname;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 地区行政编码
     */
    private int areacode;

    /**
     * 家人人数
     */
    private int famliy_count;

    /**
     * 默认就诊人
     */
    private int default_patient;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 头像文件
     */
    private String avatar;

    /**
     * 创建时间
     */
    private String createtime;

    /**
     * 修改时间
     */
    private int updatetime;

    /**
     * 登录时间
     */
    private int logintime;

    /**
     * 登录IP
     */
    private int loginip;

    /**
     * 用户角色
     */
    private byte role;

    public User() {
    }

    public User(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public User(String mobile, String password, byte role) {
        this.mobile = mobile;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(int updatetime) {
        this.updatetime = updatetime;
    }

    public int getLogintime() {
        return logintime;
    }

    public void setLogintime(int logintime) {
        this.logintime = logintime;
    }

    public int getLoginip() {
        return loginip;
    }

    public void setLoginip(int loginip) {
        this.loginip = loginip;
    }

    public byte getRole() {
        return role;
    }

    public void setRole(byte role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", areacode=" + areacode +
                ", famliy_count=" + famliy_count +
                ", default_patient=" + default_patient +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime=" + updatetime +
                ", logintime=" + logintime +
                ", loginip=" + loginip +
                ", role=" + role +
                '}';
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getAreacode() {
        return areacode;
    }

    public void setAreacode(int areacode) {
        this.areacode = areacode;
    }

    public int getFamliy_count() {
        return famliy_count;
    }

    public void setFamliy_count(int famliy_count) {
        this.famliy_count = famliy_count;
    }

    public int getDefault_patient() {
        return default_patient;
    }

    public void setDefault_patient(int default_patient) {
        this.default_patient = default_patient;
    }

}
