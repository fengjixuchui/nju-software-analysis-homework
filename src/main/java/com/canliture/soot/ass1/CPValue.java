package com.canliture.soot.ass1;

/**
 * Created by liture on 2021/9/19 1:19 下午
 *
 * Lattice Value for constant propagation
 */
public class CPValue {

    private final static CPValue NAC = new CPValue() {
        @Override
        public int hashCode() {
            return Integer.MIN_VALUE;
        }
    };

    private final static CPValue UNDEF = new CPValue() {
        @Override
        public int hashCode() {
            return Integer.MAX_VALUE;
        }
    };

    /**
     * concrete value
     */
    private int val;

    private CPValue() { }

    private CPValue(int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }

    public static CPValue getNAC() {
        return NAC;
    }

    public static CPValue getUndef() {
        return UNDEF;
    }

    public static CPValue makeConstant(int val) {
        return new CPValue(val);
    }

    /**
     * 格值的meet;
     * 你应该从 {@link FlowMap#meet(FlowMap, FlowMap)} 里面去调用
     * @param value1
     * @param value2
     * @return meet后的结果
     */
    public static CPValue meetValue(CPValue value1, CPValue value2) {
        if (value1 == getUndef()) {
            return value2;
        }

        if (value2 == getUndef()) {
            return value1;
        }

        if (value1 == getNAC() || value2 == getNAC()) {
            return getNAC();
        }

        // 两个具体的值meet
        if (value1.val() == value2.val()) {
            return makeConstant(value1.val());
        }

        return getNAC();
    }

    @Override
    public String toString() {
        if (this == NAC) {
            return "NAC";
        }
        if (this == UNDEF) {
            return "UNDEF";
        }
        return String.valueOf(val);
    }
}
