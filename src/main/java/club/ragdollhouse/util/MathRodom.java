package club.ragdollhouse.util;

/**
 * 整数随机数工具类
 */
public class MathRodom {
    public static int toRodom(int Max, int min) {
        int i = (int) (min + Math.random() * (Max - min + 1));
        return i;
    }
}
