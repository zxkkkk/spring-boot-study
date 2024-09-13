package top.zxk.springboot.thymeleaf.model;

public class User {
    private Long id;
    private String name;
    private String email;  // 新增字段

    public User(Long id, String name, String email) {  // 更新构造函数
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {  // 新增 getter
        return email;
    }

    public void setEmail(String email) {  // 新增 setter
        this.email = email;
    }
}
