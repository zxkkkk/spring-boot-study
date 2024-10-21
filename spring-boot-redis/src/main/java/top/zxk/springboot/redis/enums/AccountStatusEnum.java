package top.zxk.springboot.redis.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum AccountStatusEnum {
    /**
     * 停用状态
     */
    DISABLE(0, "停用"),

    /**
     * 正常状态
     */
    ENABLED(1, "正常");

    private final int value;
    private final String name;

    /**
     * 根据数值获取名称
     * @param value 状态的数值
     * @return 状态的名称
     */
    public static String getNameByValue(int value) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (s.getValue() == value) {
                return s.getName();
            }
        }
        return "";
    }

    /**
     * 根据名称获取数值
     * @param name 状态的名称
     * @return 状态的数值
     */
    public static Integer getValueByName(String name) {
        for (AccountStatusEnum s : AccountStatusEnum.values()) {
            if (Objects.equals(s.getName(), name)) {
                return s.getValue();
            }
        }
        return null;
    }
}
